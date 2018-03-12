<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">

	<form action="" method="post"  class="form form-horizontal" id="form-unit-modify">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单位编号：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${unit.unitCode }" placeholder="" id="unitCode" name="unitCode" readonly="true">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单位：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${unit.unitName }" placeholder="" id="unitName" name="unitName">
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
$("#form-unit-modify").validate({
	rules:{		
		unitName: {
	            required: true,
	            isSpace: true,
	            maxlength:20,
	           remote: {
	                url: "${context_root}/commodity/checkUnitName.action",
	                type: "post",
	                dataType: "text",
	                data: {
	                	unitName: function () {
	                        return $.trim($("#unitName").val());
	                    },
	                    unitCode: function () {
	                        return $.trim($("#unitCode").val());
	                    }
	                },
	                dataFilter: function (data, type) {
	                    if (data == 0) return true;
	                    else return "该名称已存在";
	                }
	            }
	        }, 
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		var formData = new FormData($('#form-unit-modify')[0]);
		$.ajax({
			url:"${context_root}/commodity/modifyUnit.action", 
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