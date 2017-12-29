<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="企业认证申请表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商户相关 <span class="c-gray en">&gt;</span> 还款计划表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container"> 
	<div style="min-height: 30px;">
		<form role="form" class="text-c">
		   <div class="row" >
		   		<div class="row col-xs-6 col-sm-4 .col-md-4" > 
					<div class="col-xs-4 col-sm-4 .col-md-4" >
						<y:select id="dateType" name="dateType" codeGroup="${dateTypeList}" selectedValue=""
								cssClass="select" headerKey="" headerValue="日期类型">
						</y:select>
					</div>
					<div class="col-xs-8 col-sm-8 .col-md-8" >
						<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
						-
						<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
					</div>
				</div>
		        <div class="row  col-xs-3 col-sm-2 .col-md-2" >  
						<y:select id="repaymentStatus" name="repaymentStatus" codeGroup="${repaymentStatusList}" selectedValue=""
								cssClass="select" headerKey="" headerValue="状态">
						</y:select> 
				</div> 
				<div class="col-xs-3 col-sm-2 .col-md-2" > 
					<input type="text" class="input-text" style="width:250px" placeholder="还款单号|订单号|店铺名称" id="fuzzyCondition" name="fuzzyCondition">
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
				<th width="10%">还款单号</th>
				<th width="10%">创建时间</th>
				<th width="10%">订单号</th>
				<th width="15%">店铺名称</th>
				<th width="10%">还款日期</th>
				<th width="15%">还款金额（元）</th>
				<th width="10%">还款状态</th>
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
        "mData": "paymentNumber",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "sDefaultContent": "创建时间",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) { 
            return formatDate(row.orderTime,"yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        "mData": "orderNumber",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "storeName",   
        "bSortable" : false,
        "sClass": "text-c", 
    },
    {
        "sDefaultContent": "还款时间",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) { 
            return formatDate(row.payTime,"yyyy-MM-dd hh:mm:ss");
        }
    }, 
    {
        "mData": "payment",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "sDefaultContent": "还款状态",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) { 
        	 //（1:待支付；2:待发货；3:待收货；4:待还款；5:已还款）
        	if(row.orderStatus=='4'){
        		return "待还款";
        	}else if(row.orderStatus=='5'){
        		return "已还款";
        	}else{
        		return "";
        	} 
        } 	
    }
    ];
    var url = "${context_root}/merchant/repaymentList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url); 
    
});

function query() {
	var fuzzyCondition = $("#fuzzyCondition").val();
	var dateType = $("#dateType").val();
    var repaymentStatus = $("#repaymentStatus").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
   /*  var jsonObject = '{\"accountName\":\"' + accountName + 
    	           '\",\"supervisor\":\"' + supervisor + 
    	           '\",\"repaymentStatus\":\"' + repaymentStatus + 
    	           '\",\"beginTime\":\"' + beginTime +
    	           '\",\"endTime\":\"' + endTime + '\"}';  */ 
    pageTable.fnSettings().sAjaxSource = "${context_root}/merchant/repaymentList.action?fuzzyCondition=" + encodeURIComponent(encodeURIComponent(fuzzyCondition)) +
					    	"&dateType="+ dateType +
					    	"&repaymentStatus="+ repaymentStatus +
					    	"&beginTime="+ beginTime +
					    	"&endTime="+ endTime;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


</script> 
</body>
</html>