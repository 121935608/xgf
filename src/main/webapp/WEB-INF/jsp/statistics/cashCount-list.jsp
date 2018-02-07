<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据统计 <span class="c-gray en">&gt;</span> 收银统计表<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	<div class="row col-xs-6 col-sm-4 .col-md-4" > 
					
					<div class="col-xs-8 col-sm-8 .col-md-8" style="margin-left:90%;">
						<input type="text" placeholder="开始时间" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
						<input type="text" placeholder="结束时间" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
					</div>
				</div>
		<div style="margin-right:10%;">
			<input type="text" class="input-text" style="width:250px" placeholder="收银员" id="cashName" name="cashName">
			<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		</div>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">收银员</th>
				<th width="10%">支付宝（元）</th>
				<th width="8%">微信（元）</th>
				<th width="6%">银联（元）</th>
				<th width="6%">现金（元）</th>
			</tr>
		</thead>
	</table>
	</div>
<script type="text/javascript">
var pageTable;
$(document).ready(function(){ 
    var aoColumns = [
    {
        "mData": "cashName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "zhifubao",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "weixin",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "yinlian",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "xianjin",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    ];
    var url = "${context_root}/statistics/cashCountList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function query() {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var cashName=$("#cashName").val();
    var reg =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
    if (reg.test(cashName)){
        alert("含有非法字符");
        return;
	}
    pageTable.fnSettings().sAjaxSource =encodeURI("${context_root}/statistics/cashCountList.action?beginTime=" + beginTime+"&endTime="+endTime+"&cashName="+cashName);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}

</script> 
</body>
</html>