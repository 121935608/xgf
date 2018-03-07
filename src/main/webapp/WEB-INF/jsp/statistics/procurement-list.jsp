<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="采购登记表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据统计 <span class="c-gray en">&gt;</span> 采购登记表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container"> 
	<div style="min-height: 30px;">
		<form role="form" class="text-c">
		   <div class="row" >
		   		<div class="row col-xs-6 col-sm-4 .col-md-4" > 
					
					<div class="col-xs-8 col-sm-8 .col-md-8" >
						<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
						-
						<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
					</div>
				</div>
				
				
		        
				<div class="col-xs-3 col-sm-2 .col-md-2" > 
					<input type="text" class="input-text" style="width:250px" placeholder="商品名称|商品编号" id="fuzzyCondition" name="fuzzyCondition">
				</div> 
				<div class="col-xs-3 col-sm-2 .col-md-2" > 
					<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div> 
		  	</div>
		</form>
	</div>
	
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">商品名称</th>
				<th width="10%">商品条码</th>
				<th width="10%">订单号</th>
				<th width="10%">创建时间</th>
				<th width="10%">单位</th>
				<th width="15%">采购数量</th>
				<th width="10%">采购金额</th>
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
        "sDefaultContent": "订单号",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.orderNumber != null) {
                return row.orderNumber;
            } else {
                return "";
            }
        }
    },
    
    
    {
		"sDefaultContent" : "创建时间",
		"bSortable" : false,
		"sClass" : "text-c",
		"bSearchable" : false,
		"mRender" : function(data, type, row) {
			if (row.payTime != null) {
				return formatDate(row.payTime, "yyyy-MM-dd hh:mm:ss");
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
        "sDefaultContent": "采购数量",
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
        "sDefaultContent": "采购金额",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.totalPrice != null) {
                return row.totalPrice;
            } else {
                return "";
            }
        }
    },
    
    
    
    ];
    var url = "${context_root}/statistics/procurementList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url); 
    
});

function query() {
	var fuzzyCondition = $("#fuzzyCondition").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
   
    pageTable.fnSettings().sAjaxSource = "${context_root}/statistics/procurementList.action?fuzzyCondition=" + encodeURIComponent(encodeURIComponent(fuzzyCondition)) +
					    	"&beginTime="+ beginTime +
					    	"&endTime="+ endTime;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}

</script> 
</body>
</html>