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
	<form action="" method="post" class="form form-horizontal" id="form-financial-modify">
	<!-- 收银余额 -->
		<input type="hidden" class="input-text" id="closeMoney" name="closeMoney" value="${financial.closeMoney }">
	<!-- 费率 -->
		<input type="hidden" class="input-text" id="xzfRate" name="xzfRate" value="${financial.xzfRate }">
	<!-- 待还款金额 -->
		<input type="hidden" class="input-text" id="totalMoney" name="totalMoney" value="${financial.totalMoney }">
	<!-- 商铺ID -->
		<input type="hidden" class="input-text" id="storeId" name="storeId" value="${financial.storeId }">
	<!-- 用户ID -->
		<input type="hidden" class="input-text" id="userId" name="userId" value="${financial.userId }">
		<div id="table">
			<table>
				<tr>
					<td>可结算金额</td>
					<td>${financial.openMoney }&nbsp;元</td>
				</tr>
				<tr>
					<td>结算金额</td>
					<td><input type="number" name="fee" id="fee"/>&nbsp;元</td>
				</tr>
				<tr>
					<td>备注</td>
					<td><textarea style="width:50%;" name="remark" id="remark" cols="" rows="" class="textarea" datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,250)">${unHelpInfo.reply}</textarea>
                    </td>
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
$("#form-financial-modify").validate({
	rules:{
		remark:{
			maxlength:250
		},
		fee:{
			required:true,
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var fee = $("#fee").val();
		var type = 1;
		if(parseFloat(fee) > parseFloat('${financial.openMoney }')){
			alert("结算金额过大！");
			return;
		}else if(fee == '${financial.openMoney }'){
			type = 0;
		}
		var index = parent.layer.load();
		$.ajax({
			url:"${context_root}/dataCount/modifyFinancial.action?type="+type, 
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