<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
<style>
	table{width:70%;margin-top:5%;margin-bottom:5%;}
	td{border:solid#000 1px;line-height:30px;text-align:center;}
</style>
	<center>
		<div class="row cl">
			流水号：
				${cashFlow.tradeCode }
				&nbsp;&nbsp;&nbsp;&nbsp;
			实付金额：${cashFlow.money }
		</div>
		<table style="border-collapse:collapse;border:none;">
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>商品价格</td>
				<td>实付金额</td>
			</tr>
			<c:forEach items="${cashFlow.cashDetail }" var="f">
				<tr>
					<td>${f.commodityName}</td>
					<td>${f.commodityNo}${f.unitName}</td>
					<td>${f.totalPrice}</td>
					<td>${f.totalVipPrice}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="button" onclick="doPrint()" id="print" value="&nbsp;&nbsp;打印&nbsp;&nbsp;">
				<input class="btn btn-primary radius" type="button" onclick="cancelForm()" id="cancel" value="&nbsp;&nbsp;关闭&nbsp;&nbsp;">
			</div>
		</div>
		</center>
</article>
</body>
<script>
function cancelForm(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
function doPrint(){
	document.getElementById('print').style.display="none";//隐藏
	document.getElementById('cancel').style.display="none";//隐藏
	window.print();
	document.getElementById('print').style.display="inline";
	document.getElementById('cancel').style.display="inline";
}
</script>
</html>