<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="收银支付流水"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商户相关 <span class="c-gray en">&gt;</span>收银支付流水 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container"> 
	<div style="min-height: 30px;">
		<form role="form" class="text-c">
		   <div class="row" >
		   		<div class="row col-xs-6 col-sm-4 .col-md-4" > 
					<div class="col-xs-8 col-sm-8 .col-md-8" >
						<input type="text" placeholder="开始时间" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
						-
						<input type="text" placeholder="结束时间" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
					</div>
					
					
				</div>
				
				 <div class="row  col-xs-3 col-sm-2 .col-md-2" >  
						<y:select id="payType" name="payType" codeGroup="${payTypeList}" selectedValue=""
								cssClass="select" headerKey="" headerValue="支付方式">
						</y:select> 
				</div> 
				
				<div class="col-xs-3 col-sm-2 .col-md-2" > 
					<input type="text" class="input-text" style="width:250px" placeholder="交易号" id="fuzzyCondition" name="fuzzyCondition">
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
				<th width="10%">交易号</th>
				<th width="10%">创建时间</th>
				<th width="10%">收款金额（元）</th>
				<th width="10%">支付方式</th>
				
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
        "sDefaultContent": "交易号",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.tradeCode != null) {
                return row.tradeCode;
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
			if (row.addTime != null) {
				return formatDate(row.addTime, "yyyy-MM-dd hh:mm:ss");
			} else {
				return "";
			}

		}
	},
	
	 {
        "sDefaultContent": "收款金额（元）",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.money != null) {
                return row.money;
            } else {
                return "";
            }
        }
    },
    
    {
        "sDefaultContent": "支付方式",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) { 
        	 //支付类型1支付宝2微信支付3银联4京东白条
        	if(row.payType=='1'){
        		return "支付宝支付";
        	}else if(row.payType=='2'){
        		return "微信支付";
        	}else if(row.payType=='3'){
        		return "银联支付";
        	}else if(row.payType=='4'){
        		return "京东白条支付";
        	}else{
        		return "";
        	} 
        } 	
    },
  ];
    var url = "${context_root}/statistics/paysList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url); 
    
});

function query() {
	var payType = $("#payType").val();
	var fuzzyCondition = $("#fuzzyCondition").val();
	var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    
    pageTable.fnSettings().sAjaxSource = "${context_root}/statistics/paysList.action?fuzzyCondition=" + encodeURIComponent(encodeURIComponent(fuzzyCondition)) +
    						"&payType="+ payType +
    						"&beginTime="+ beginTime +
					    	"&endTime="+ endTime;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}

</script> 
</body>
</html>