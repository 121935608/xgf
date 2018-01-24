<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-role-modify">
		<input type="hidden" class="input-text" id="roleId" name="roleId" value="${role.roleId }">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" placeholder="角色名字" id="roleName" name="roleName" value="${role.roleName }">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色标识：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" placeholder="角色标识" id="roleKey" name="roleKey" value="${role.roleKey }">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否启用：</label>
			<div class="formControls col-xs-7 col-sm-4 skin-minimal">
				<y:radio codeGroup="sys-role_status" name="status" selectedValue="${role.status }"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">描述：</label>
			<div class="formControls col-xs-8 col-sm-6">
				<textarea name="description" cols="" rows="" class="textarea" placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,200)">${role.description }</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">${fn:length(role.description)}</em>/200</p>
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
    jQuery.validator.addMethod("checkRoleName", function (value, element) {
        var chrnum =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
        return this.optional(element) || (!chrnum.test(value));
    }, "角色名不能为数字、特殊符号");
    jQuery.validator.addMethod("checkNumber", function (value, element) {
        var reg =/^[0-9]*$/ ;
        return this.optional(element) || (!reg.test(value));
    }, "角色名不能为数字、特殊符号");
    jQuery.validator.addMethod("checkRoleKey", function (value, element) {
        var chrnum =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
        return this.optional(element) || (!chrnum.test(value));
    }, "角色标识不能为数字、特殊符号");
    jQuery.validator.addMethod("checkRoleNumber", function (value, element) {
        var reg =/^[0-9]*$/ ;
        return this.optional(element) || (!reg.test(value));
    }, "角色标识不能为数字、特殊符号");
$("#form-role-modify").validate({
	rules:{
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
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		$.ajax({
			url:"${context_url}/system/saveRole.action", 
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