<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="财务结算"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商户相关 <span class="c-gray en">&gt;</span>财务结算 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
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
						<y:select id="status" name="status" codeGroup="${statusList}" selectedValue=""
								cssClass="select" headerKey="" headerValue="转账状态">
						</y:select> 
				</div> 
		        
				<div class="col-xs-3 col-sm-2 .col-md-2" > 
					<input type="text" class="input-text" style="width:250px" placeholder="结算单号" id="fuzzyCondition" name="fuzzyCondition">
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
				<th width="10%">结算单号</th>
				<th width="10%">创建时间</th>
				<th width="10%">合计金额（元）</th>
				<th width="10%">费率</th>
				<th width="10%">结算金额（元）</th>
				<th width="10%">转账状态</th>
				
				
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
        "sDefaultContent": "结算单号",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.amountNum != null) {
                return row.amountNum;
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
        "sDefaultContent": "合计金额（元）",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.totalMoney != null) {
                return row.totalMoney;
            } else {
                return "";
            }
        }
    },
    
    {
        "sDefaultContent": "费率",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.xzfRate != null) {
                return row.xzfRate;
            } else {
                return "";
            }
        }
    },
   
    {
        "sDefaultContent": "结算金额（元）",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.amountMoney != null) {
                return row.amountMoney;
            } else {
                return "";
            }
        }
    },
    
   
    {
        "sDefaultContent": "转账状态",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) { 
        	 //0:转账成功；1:转账失败
        	if(row.status=='0'){
        		return "转账成功";
        	}else if(row.status=='1'){
        		return "转账失败";
        	}else{
        		return "";
        	} 
        } 	
    }
    
    ];
    var url = "${context_root}/statistics/financialList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url); 
    
});

function query() {
	var status = $("#status").val();
	var fuzzyCondition = $("#fuzzyCondition").val();
	var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
   
    pageTable.fnSettings().sAjaxSource = "${context_root}/statistics/financialList.action?fuzzyCondition=" + encodeURIComponent(encodeURIComponent(fuzzyCondition)) +
					    	"&status="+ status +
					    	"&beginTime="+ beginTime +
					    	"&endTime="+ endTime;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}

</script> 
</body>
</html>