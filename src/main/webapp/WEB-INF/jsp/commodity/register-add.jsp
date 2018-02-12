<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post"  class="form form-horizontal" id="form-register-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品名称：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="commodityName" name="commodityName">
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
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品条码：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="commodityNo" name="commodityNo">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="supplierId" name="supplierId"
					codeGroup="${supply}" selectedValue=""
					cssClass="select" headerKey="" headerValue="--请选择--">
			</y:select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>分类：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="parentCategoryId" name="parentCategoryId"
					codeGroup="${fenlei}" selectedValue=""
					cssClass="select" headerKey="" headerValue="--请选择--">
			</y:select>
			</div>
			<div class="formControls col-xs-8 col-sm-4" id="goods">
				<select id="categoryId" style="width:150px;height:28px" name='categoryId'>
					<option value='' disabled selected style='display:none;'>--请选择--</option>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单位：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="unitId" name="unitId"
					codeGroup="${unit}" selectedValue=""
					cssClass="select" headerKey="" headerValue="--请选择--">
			</y:select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>进价（元）：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="inPrice" name="inPrice">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>售价（元）：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="salePrice" name="salePrice">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>会员价（元）：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="vipPrice" name="vipPrice">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>折扣：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<select id="discount" name="discount" style="width:220px;height:33px;">
		       		<option disabled selected style='display:none;' value="">--请选择--</option>
		       		<option value=1>是</option>
		       		<option value=-1>否</option>
		       </select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>库存：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" value="" placeholder="" id="stockNum" name="stockNum" style="width:150px">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>库存上限：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" value="" placeholder="" id="upperLimit" name="upperLimit" style="width:150px">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>库存下限：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" value="" placeholder="" id="lowerLimit" name="lowerLimit" style="width:150px">
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
//商品分类
$("#categoryId").click(function (){
		var parentCategoryId = $("#parentCategoryId option:selected").val();
		if(parentCategoryId == ""){
			alert("请选择商品分类！");
			return;
		}
})
	$("#parentCategoryId").click(function (){
		var parentCategoryId = $("#parentCategoryId option:selected").val();
		$.ajax({
			url:"${context_root}/commodity/getCategorys.action?parentCategoryId="+parentCategoryId,
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				$("#categoryId").empty();
				for(var i=0;i<data.length;i++){
					$("#categoryId").append("<option value='"+data[i].categoryId+"'>"+data[i].categoryName+"</option>");
				}
			},
		});
	})
$("#form-register-add").validate({
	rules:{		
		commodityName: {
            required: true,
            isSpace: true,
           remote: {
                url: "${context_root}/commodity/checkCategoryName.action",
                type: "post",
                dataType: "text",
                data: {
                	commodityName: function () {
                        return $.trim($("#commodityName").val());
                    }
                },
                dataFilter: function (data, type) {
                    if (data == "0") return true;
                    else return "该名称已存在";
                }
            }
        }, 
		picture:{
			required:true,
			isSpace:true,
		},
		commodityNo:{
			required:true,
			isSpace:true,
		},
		supplierId:{
			required:true,
			isSpace:true,
		},
		categoryId:{
			required:true,
			isSpace:true,
		},
		unitId:{
			required:true,
			isSpace:true,
		},
		inPrice:{
			required:true,
			isSpace:true,
		},
		salePrice:{
			required:true,
			isSpace:true,
		},
		vipPrice:{
			required:true,
			isSpace:true,
		},
		discount:{
			required:true,
			isSpace:true,
		},
		stockNum:{
			required:true,
			isSpace:true,
		},
		upperLimit:{
			required:true,
			isSpace:true,
		},
		lowerLimit:{
			required:true,
			isSpace:true,
		},
		status:{
			required:true,
			isSpace:true,
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		var formData = new FormData($('#form-register-add')[0]);
		$.ajax({
			url:"${context_root}/commodity/saveRegister.action", 
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