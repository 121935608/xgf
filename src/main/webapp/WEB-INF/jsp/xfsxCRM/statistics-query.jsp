<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="统计数据"/>
<style>
	div{
		border-radius:25px;
	}
	.explain{
	   padding:20px 0px 0px 0px;
	   color:#F8F8FF;
	   font-size:18px;
	}
	.explain1{
	   padding:20px 0px 0px 0px;
	   color:#333333;
	   font-size:18px;
	}
	.data{
	   font-size:32px;
	   color:#F8F8FF;
	}
	.data1{
	   font-size:32px;
	   color:#333333;
	}
</style>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 谢鲜CRM管理 <span class="c-gray en">&gt;</span> 数据统计 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div style="height:200px;margin-left:40px;">
        <h4 style="padding-left:30px;font-weight:bold;color:#0085B0;">累计下单额</h4>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center;background-color:#EABB4F;">
			<p class="explain">今日累计下单额</p>
			<p class="data">${statistics.todayAccumulativeAmount}元</p>
		</div>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center;background-color:#4F93F8; ">
			<p class="explain">昨日累计下单额</p>
			<p class="data">${statistics.yesterdayAccumulativeAmount}元</p>
		</div>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center; background-color:#FEA853;">
			<p class="explain">周累计下单额</p>
			<p class="data">${statistics.weeklyAccumulativeAmount}元</p>
		</div>
		
		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center; overflow: hidden;background-color: #35CE5C;">
			<p class="explain">月累计下单额</p>
			<p class="data">${statistics.monthlyAccumulativeAmount}元</p>
		</div>
	</div>
	
	<div style="height:200px;margin-left:40px;">
        <h4 Style="padding-left:30px;font-weight:bold;color:#0085B0;">累计成交单量</h4>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center;background-color:#CCCCCC;">
			<p class="explain1">今日累计成交单量</p>
			<p class="data1">${statistics.todayNumberOfTransactions}次</p>
		</div>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center;background-color:#CCCCCC; ">
			<p class="explain1">昨日累计成交单量</p>
			<p class="data1">${statistics.yesterdayNumberOfTransactions}次</p>
		</div>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center; background-color:#CCCCCC;">
			<p class="explain1">周累计成交单量</p>
			<p class="data1">${statistics.weeklyNumberOfTransactions}次</p>
		</div>
		
		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center; overflow: hidden;background-color: #CCCCCC;">
			<p class="explain1">月累计成交单量</p>
			<p class="data1">${statistics.monthlyNumberOfTransactions}次</p>
		</div>
	</div>
	
	<div style="height:200px;margin-left:40px;">
        <h4 Style="padding-left:30px;font-weight:bold;color:#0085B0;">下单客户转化率</h4>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center;background-color:#CCCCCC;">
			<p class="explain1">今日下单客户转化率</p>
			<p class="data1">${statistics.todayCustomerConversionRate}%</p>
		</div>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center;background-color:#CCCCCC; ">
			<p class="explain1">昨日下单客户转化率</p>
			<p class="data1">${statistics.yesterdayCustomerConversionRate}%</p>
		</div>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center; background-color:#CCCCCC;">
			<p class="explain1">周下单客户转化率</p>
			<p class="data1">${statistics.weeklyCustomerConversionRate}%</p>
		</div>
		
		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center; overflow: hidden;background-color: #CCCCCC;">
			<p class="explain1">月下单客户转化率</p>
			<p class="data1">${statistics.monthlyCustomerConversionRate}%</p>
		</div>
	</div>
	
	<div style="height:200px;margin-left:40px;">
        <h4 Style="padding-left:30px;font-weight:bold;color:#0085B0;">门店补券率</h4>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center;background-color:#CCCCCC;">
			<p class="explain1">今日门店补券率</p>
			<p class="data1">${statistics.todayStoreCouponRate}%</p>
		</div>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center;background-color:#CCCCCC; ">
			<p class="explain1">昨日门店补券</p>
			<p class="data1">${statistics.yesterdayStoreCouponRate}%</p>
		</div>

		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center; background-color:#CCCCCC;">
			<p class="explain1">周门店补券率</p>
			<p class="data1">${statistics.weeklyStoreCouponRate}%</p>
		</div>
		
		<div style="float: left;width: 20%;height:140px;margin: 0 10px;text-align: center; overflow: hidden;background-color: #CCCCCC;">
			<p class="explain1">月门店补券率</p>
			<p class="data1">${statistics.monthlyStoreCouponRate}%</p>
		</div>
	</div>
</div>
<script type="text/javascript">
	
</script>
</body>
</html>
