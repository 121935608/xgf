<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-dept-modify">
		<input type="hidden" class="input-text" id="deptId" name="deptId" value="${dept.deptId }">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>部门名称：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" placeholder="角色名字" id="deptName" name="deptName" value="${dept.deptName }">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">部门描述：</label>
			<div class="formControls col-xs-8 col-sm-6">
				<textarea name="describe" cols="" rows="" class="textarea" placeholder="" datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,200)">${dept.describe }</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">${fn:length(dept.describe)}</em>/200</p>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>

<script type="text/javascript">
    jQuery.validator.addMethod("checkDeptName", function (value, element) {
        var chrnum =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
        return this.optional(element) || (!chrnum.test(value));
    }, "角色名不能为数字、特殊符号");
$("#form-dept-modify").validate({
	/* rules:{
		roleName:{
			required:true,
			isSpace:true,
            checkRoleName:true,
            checkNumber:true,
            remote: {
                url: "${context_root}/system/checkRoleUnique.action?roleId=${role.roleId }",
                type: "post",
                dataType: "text",
                data: {
                    name: function () {
                        return $.trim($("#roleName").val());
                    }
                },
                dataFilter: function (data, type) {
                    if (data == "0") return true;
                    else return "该角色已存在";
                }
            }
		},
		roleKey:{
			required:true,
			isSpace:true,
            checkRoleKey:true,
            checkRoleNumber:true,
		},
	}, */
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		$.ajax({
			url:"${context_url}/crm/saveDept.action", 
			type:'post',
			async:true ,
			cache:false ,
			data:$(form).serialize(),
			dataType:"json",
			success:function(data){
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