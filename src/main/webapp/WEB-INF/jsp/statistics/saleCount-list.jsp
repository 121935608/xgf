<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据统计 <span class="c-gray en">&gt;</span> 销售统计表<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
			   class="input-text Wdate" style="width:120px;" placeholder="开始时间">
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
			   class="input-text Wdate" style="width:120px;" placeholder="结束时间">
		<input type="text" class="input-text" style="width:250px" placeholder="商品编码|商品名称|商品条码" id="commodityNo" name="commodityNo">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		<button type="button" class="btn btn-success radius" onclick="excel_out()"><i class="Hui-iconfont">&#xe665;</i> 导出</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">商品编码</th>
				<th width="6%">商品名称</th>
				<th width="8%">商品条码</th>
				<th width="8%">销量</th>
				<th width="6%">单位</th>
				<th width="6%">销售金额（元）</th>
				<th width="6%">成本（元）</th>
				<th width="6%">利润</th>
				<th width="6%">销量占比</th>
			</tr>
		</thead>
	</table>
	</div>
	</div>
<script type="text/javascript">
var pageTable;
$(document).ready(function(){ 
    var aoColumns = [
    {
        "mData": "commodityCode",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "commodityName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "commodityNo",
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
        "mData": "unitName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "totalVipPrice",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "inPrice",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "lirun",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "sDefaultContent": "销量占比",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.totalPrice != null) {
                return row.totalPrice+"%";
            } else {
                return "";
            }
        }
    }
    ];
    var url = "${context_root}/statistics/saleCountList.action";
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
    pageTable.fnSettings().sAjaxSource =encodeURI("${context_root}/statistics/saleCountList.action?beginTime=" + beginTime+"&endTime="+endTime+"&commodityNo="+commodityNo);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}
function excel_out() {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var commodityNo = $("#commodityNo").val();
    var reg =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
    if (reg.test(commodityNo)){
        alert("含有非法字符");
        return;
    }
    var elemIF = document.createElement("iframe");
    elemIF.src = "${context_root}/statistics/downloadSaleCountData.action?beginTime="+beginTime+"&endTime="+endTime+"&commodityNo="+commodityNo
    elemIF.style.display = "none";
    document.body.appendChild(elemIF);
}

</script> 
</body>
</html>