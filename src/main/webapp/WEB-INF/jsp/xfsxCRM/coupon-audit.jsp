<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-coupon-audit">
		<input type="hidden" class="input-text" id="couponId" name="couponId" value="${coupon.couponId }">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">审核备注：</label>
			<div class="formControls col-xs-8 col-sm-6">
				<textarea name="auditRemarks" cols="" rows="" class="textarea" placeholder="" datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,200)">${coupon.auditRemarks}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">${fn:length(coupon.auditRemarks)}</em>/200</p>
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
$("#form-coupon-audit").validate({
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		$.ajax({
			url:"${context_url}/crm/updateCouponAudit.action", 
			type:'post',
			async:true ,
			cache:false ,
			data:$(form).serialize(),
			dataType:"json",
			success:function(data){
				parent.layer.close(index);
				if(data.s == true){
					index = parent.layer.getFrameIndex(window.name);
					parent.layer.msg("审核成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
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