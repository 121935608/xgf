<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="采购统计表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>数据统计 <span class="c-gray en">&gt;</span> 采购统计表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container"> 
		<div class="text-c">
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
			   class="input-text Wdate" style="width:120px;" placeholder="开始时间">
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
			   class="input-text Wdate" style="width:120px;" placeholder="结束时间">
		<input type="text" class="input-text" style="width:250px" placeholder="商品名称|商品编号" id="fuzzyCondition" name="fuzzyCondition">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">商品名称</th>
				<th width="10%">商品条码</th>
				<th width="10%">单位</th>
				<th width="15%">数量</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
	</table>
	</div>
</div>
<script type="text/javascript"  charset="UTF-8">
var pageTable;
$(document).ready(function(){ 
	var aoColumns = [
    {
        "sDefaultContent": "商品名称",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	
            if (row.commodityName != null) {
                return row.commodityName;
            } else {
                return "";
            }
        }
    },
    {
        "sDefaultContent": "商品条码",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.commodityNo != null) {
                return row.commodityNo;
            } else {
                return "";
            }
        }
    },
    {
        "sDefaultContent": "单位",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.unit != null) {
                return row.unit;
            } else {
                return "";
            }
        }
    },

    {
        "sDefaultContent": "数量",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.commodityNum != null) {
                return row.commodityNum;
            } else {
                return "";
            }
        }
    },
    {
        "sDefaultContent": "操作",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	if(row.status == 1){
        		return "";
        	}
        	//入库
            var toEdit = "<a title=\"入库\" href=\"javascript:;\" onclick=\"toStock_edit('入库','${context_root}/commodity/toStockModify.action?commodityId=" + row.commodityId + "','910','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>入库</span></a>";
        	return toEdit;
        }
    },
    ];
    var url = "${context_root}/commodity/toStockList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url); 
    
});

function query() {
	var fuzzyCondition = $("#fuzzyCondition").val();
	var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
   
    pageTable.fnSettings().sAjaxSource = "${context_root}/commodity/toStockList.action?fuzzyCondition=" + encodeURIComponent(encodeURIComponent(fuzzyCondition)) +
					    	
					    	"&beginTime="+ beginTime +
					    	"&endTime="+ endTime;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}
/*入库*/
function toStock_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 
</body>
</html>