<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<style>
	.picture{width: 150px;height:150px;position:absolute;left: 2;cursor:pointer;border-color: orange;
             filter:alpha(opacity:0);opacity: 0  }
        .image{position:absolute; border-color: red;left: 2;cursor:pointer;} 
</style>
<article class="page-container">

	<form action="" method="post"  class="form form-horizontal" id="form-register-modify">
	<input type="hidden" value="${register.commodityId }" id="commodityId" name="commodityId" >
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品名称：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${register.commodityName }" placeholder="" id="commodityName" name="commodityName">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图片：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<img src="${imgPath}${register.imgMain}" width="80px" height="80px" class="image" id="image">
	        	<input type="file" class="picture" id="picture" accept="image/*" name="picture" onchange="changImg(event)">
			</div>
		</div>
		<div class="row cl" style="margin-top:80px">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品条码：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${register.commodityNo}" placeholder="" id="commodityNo" name="commodityNo">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="supplierId" name="supplierId"
					codeGroup="${supply}" selectedValue="${register.supplierId}"
					cssClass="select" headerKey="" headerValue="--请选择--">
			</y:select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>分类：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="parentCategoryId" name="parentCategoryId"
					codeGroup="${fenlei}" selectedValue="${register.categoryId}"
					cssClass="select" headerKey="" headerValue="--请选择--">
			</y:select>
			</div>
			<div class="formControls col-xs-8 col-sm-4" id="goods">
				<select id="categoryId" style="width:150px;height:28px" name='categoryId'>
					<c:forEach items="${categorys}" var="c" >
						<option value="${c.commodityId}"<c:if test="${c.categoryName eq register.categoryName}">selected</c:if>>${c.categoryName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单位：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="unitId" name="unitId"
					codeGroup="${unit}" selectedValue="${register.unitId}"
					cssClass="select" headerKey="" headerValue="--请选择--">
			</y:select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>进价（元）：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${register.inPrice}" placeholder="" id="inPrice" name="inPrice">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>售价（元）：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${register.salePrice}" placeholder="" id="salePrice" name="salePrice">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>会员价（元）：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${register.vipPrice}" placeholder="" id="vipPrice" name="vipPrice">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>折扣：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<select id="discount" name="discount" style="width:220px;height:33px;">
		       		<option value=1 <c:if test="${register.discount eq 1}">selected</c:if>>是</option>
		       		<option value=-1 <c:if test="${register.discount eq -1}">selected</c:if>>否</option>
		       </select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>库存：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" value="${register.stockNum}" placeholder="" id="stockNum" name="stockNum" style="width:150px">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>库存上限：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" value="${register.upperLimit}" placeholder="" id="upperLimit" name="upperLimit" style="width:150px">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>库存下限：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" value="${register.lowerLimit}" placeholder="" id="lowerLimit" name="lowerLimit" style="width:150px">
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
function changImg(e) {
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
$("#form-register-modify").validate({
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
                    },
                    commodityId: function () {
                        return $.trim($("#commodityId").val());
                    }
                },
                dataFilter: function (data, type) {
                    if (data == "0") return true;
                    else return "该名称已存在";
                }
            }
        }, 
		commodityNo:{
			required:true,
			isSpace:true,
		},
		supplierId:{
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
		var formData = new FormData($('#form-register-modify')[0]);
		$.ajax({
			url:"${context_root}/commodity/modifyRegister.action", 
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