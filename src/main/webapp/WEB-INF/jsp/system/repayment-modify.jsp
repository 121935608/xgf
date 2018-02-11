<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<style>
	#table{margin-left：200px;}
	tr td{line-height:40px;}
	input{width:150px;height:20px;}
	textarea{width:62.5%;height:200px;}
</style>
<center>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-repay-modify">
		<input type="hidden" class="input-text" id="repayId" name="repayId" value="${repay.repayId }">
		<input type="hidden" class="input-text" id="withholdMoney" name="withholdMoney" value="${repay.withholdMoney }">
		<input type="hidden" class="input-text" id="repayMoney" name="repayMoney" value="${repay.repayMoney }">
		<input type="hidden" class="input-text" id="planTotal" name="planTotal" value="${repay.planTotal }">
		<div id="table">
			<table>
				<tr>
					<td>还款单号</td>
					<td>${repay.repayNo }</td>
				</tr>
				<tr>
					<td>结余金额</td>
					<td>${repay.withholdMoney }&nbsp;元</td>
				</tr>
				<tr>
					<td>还款金额</td>
					<td><input type="number" name="dueFee" id="dueFee"/>&nbsp;元</td>
				</tr>
				<tr>
					<td>备注</td>
					<td><textarea id="remark" name="remark"></textarea></td>
				</tr>
			</table>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>
</center>
<script type="text/javascript">
$("#form-repay-modify").validate({
	rules:{
		repayMoney:{
			required:true,
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var dueFee = $("#dueFee").val();
		var type = 0;
		if(parseFloat(dueFee) > parseFloat('${repay.withholdMoney }')){
			alert("还款金额过大！");
			return;
		}else if(dueFee == '${repay.withholdMoney }'){
			type = 1;
		}
		var index = parent.layer.load();
		$.ajax({
			url:"${context_root}/merchant/modifyRepay.action?type="+type, 
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
					parent.layer.alert("操作失败！" , {icon: 2,title:"系统提示"});
				}
			},
		});
	}
});
</script> 
</body>
</html>