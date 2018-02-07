<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据统计 <span class="c-gray en">&gt;</span> 收银流水 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<center>
	<div class="text-c">
	<div class="row col-xs-6 col-sm-4 .col-md-4" > 
					
					<div class="col-xs-8 col-sm-8 .col-md-8" style="margin-left:90%;">
						<input type="text" placeholder="开始时间" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
						<input type="text" placeholder="结束时间" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
					</div>
				</div>
		<div style="margin-right:10%;">
			<input type="text" class="input-text" style="width:250px" placeholder="编码|商品名称|商品条码" id="commodityNo" name="commodityNo">
			<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		</div>
	</div>
	</center>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">流水号</th>
				<th width="10%">收银机</th>
				<th width="8%">日期</th>
				<th width="6%">类型</th>
				<th width="6%">收银员</th>
				<th width="6%">会员</th>
				<th width="6%">商品数量</th>
				<th width="6%">商品价格</th>
				<th width="6%">折后价格</th>
				<th width="7%">操作</th>
			</tr>
		</thead>
	</table>
	</div>
<script type="text/javascript">
var pageTable;
$(document).ready(function(){ 
    var aoColumns = [
    {
        "mData": "tradeCode",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "machineNo",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
		"sDefaultContent" : "日期",
		"bSortable" : false,
		"sClass" : "text-c",
		"bSearchable" : false,
		"mRender" : function(data, type, row) {
			if (row.addTime != null) {
				return formatDate(row.addTime, "yyyy-MM-dd hh:mm:ss");
			} else {
				return "";
			}

		}
	},
    {
        "sDefaultContent": "类型",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	if(row.type == 1 ){
        		return "销售";
        	}else if(row.type == 2){
        		return "退货";
        	}else{
        		return "";
        	}
        }
    },
       
    {
        "mData": "cashName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "memberName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "number",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "money",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "totalMoney",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "sDefaultContent": "操作",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	if(row.type == 1 ){
	            //明细
	            var toEdit = "<a title=\"明细\" href=\"javascript:;\" onclick=\"cash_edit('明细','${context_root}/statistics/toCashDetail.action?cashId=" + row.cashId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>明细</span></a>";
	        	return toEdit ;
        	}
        	return "";
        }
    },
    ];
    var url = "${context_root}/statistics/cashList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function query() {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var commodityNo=$("#commodityNo").val();
    var reg =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
    if (reg.test(commodityNo)){
        alert("含有非法字符");
        return;
	}
    pageTable.fnSettings().sAjaxSource =encodeURI("${context_root}/statistics/cashList.action?beginTime=" + beginTime+"&endTime="+endTime+"&commodityNo="+commodityNo);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}

/*明细*/
function cash_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
/* 打印
function cash_print(title,url,w,h){
	window.print();
} */
</script> 
</body>
</html>