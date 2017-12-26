<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<article class="page-container">
	<form action="/${context_root}/system/changeUserPwd.action" method="post" class="form form-horizontal" id="form-change-password">
	    <input type="hidden" name="userId" id="userId" value="${user.userId}">
	    <input type="hidden" name="userName" id="userName" value="${user.userName}">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账户：</label>
			<div class="formControls col-xs-8 col-sm-4"> ${user.userName } </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="password" name="password" id="password" class="input-text"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-xs-8 col-sm-4">
			    <input type="password" name="repeatPassword" id="repeatPassword" class="input-text"/>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>
<script type="text/javascript">
$("#form-change-password").validate({
	rules:{
		password:{
			required:true,
			isSpace:true,
			minlength: 6
		},
		repeatPassword:{
			required:true,
			isSpace:true,
			minlength: 6,
			equalTo: password
		},
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		$.ajax({
			url:"${context_root}/system/changeUserPwd.action", 
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
						parent.$(".show_iframe:visible > iframe").contents().find("#search").click() ;
						parent.layer.close(index);
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