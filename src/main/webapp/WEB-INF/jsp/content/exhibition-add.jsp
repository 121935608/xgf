<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post"  class="form form-horizontal" id="form-exhibition-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="categoryName" name="categoryName">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图片：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="file" onchange="pic(event)" id="picture" name="picture" accept="image/*">
				<img alt="" id="myImg" src="" height="100px",width="100px">  
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="status" name="status"
					codeGroup="${statusList}" selectedValue=""
					cssClass="select" headerKey="" headerValue="状态">
			</y:select>
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;确定&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>


<script type="text/javascript">
//校验上传文件是否为图片格式
function pic(e) {
	for (var i = 0; i < e.target.files.length; i++) {  
        var file = e.target.files.item(i);  
        if (!(/^image\/.*$/i.test(file.type))) {  
            continue; //不是图片 就跳出这一次循环  
        }  
          
        	var imagSize =  document.getElementById("picture").files[0].size;
        	if(imagSize>1024*1024*3){
                alert("图片最大为3M！");
                document.getElementById("picture").value="";
                return;
            }
       	//实例化FileReader API
        var freader = new FileReader();  
        freader.readAsDataURL(file);  
        freader.onload = function(e) { 
            $("#myImg").attr("src",e.target.result);  
        }  
    }  
}
$("#form-exhibition-add").validate({
	rules:{
		/* categoryName: {
            required: true,
            isSpace: true,
           remote: {
                url: "${context_root}/content/checkExhibitionNameUnique.action",
                type: "post",
                dataType: "text",
                data: {
                    name: function () {
                        return $.trim($("#categoryName").val());
                    }
                },
                dataFilter: function (data, type) {
                    if (data == "0") return true;
                    else return "该名称已存在";
                }
            }
        }, */
		picture:{
			required:true,
			isSpace:true,
		},
		categoryName:{
			required:true,
			isSpace:true,
		},
		
        status:{
			required:true,
			isSpace:true,
		},
		
		/* messages: {
            "categoryName": {
                remote: "该名称已经存在"
            }
        }, */
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		var formData = new FormData($('#form-exhibition-add')[0]);
		$.ajax({
			url:"${context_root}/content/saveExhibition.action", 
			type:'post',
			data:formData,
			mimeType: "multipart/form-data",
			contentType: false,
			cache:false,
			processData: false,			
			success:function(data){
				var data = JSON.parse(data);
				parent.layer.close(index);
				if(data.s == true){
					index = parent.layer.getFrameIndex(window.name);
					parent.layer.msg("保存成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
						window.parent.location.reload();
					}) ;
				}else{
					parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
				}
			},
		});
	}
});
</script> 
</body>
</html>