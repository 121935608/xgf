<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
<style>
	.picture{width: 150px;height:150px;position:absolute;left: 2;cursor:pointer;border-color: orange;
             filter:alpha(opacity:0);opacity: 0  }
        .image{position:absolute; border-color: red;left: 2;cursor:pointer;} 
</style>
	<form action="" method="post" class="form form-horizontal" id="form-label-modify">
		<input type="hidden" class="input-text" id="categoryId" name="categoryId"   value="${category.categoryId }">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>标签名称：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" placeholder="标签名称" id="categoryName" name="categoryName" value="${category.categoryName }">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图片：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<img src="${imgPath}${category.img}" width="80px" height="80px" class="image" id="image">
	        	<input type="file" class="picture" id="picture" accept="image/*" name="picture" onchange="changImg(event)">
			</div>
		</div>
		
		<div class="row cl"style="margin-top:80px">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="status" name="status"
					codeGroup="${statusList}" selectedValue="${category.status }"
					cssClass="select" headerKey="0" headerValue="状态" >
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
function changImg(e){
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
            $("#image").attr("src",e.target.result);
        }
    }
}
$("#form-label-modify").validate({
	rules:{
		/* categoryName: {
            required: true,
            isSpace: true,
            maxlength:8,
           remote: {
                url: "${context_root}/commodity/checkNamesUnique.action",
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
        categoryName:{
			required:true,
			isSpace:true,
		},
		status:{
			required:true,
			isSpace:true,
		},
		
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		var formData = new FormData($('#form-label-modify')[0]);
		$.ajax({
			url:"${context_root}/commodity/saveLabel.action", 
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