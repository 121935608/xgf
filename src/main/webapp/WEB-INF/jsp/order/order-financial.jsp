<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="督导员管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
			   class="input-text Wdate" style="width:120px;" placeholder="开始时间">
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
			   class="input-text Wdate" style="width:120px;" placeholder="结束时间">
		<span class="select-box" style="width: 120px;">
           <select name="payType" id="payType" class="select" autocomplete="off">
               <option value="">支付方式</option>
               <option value="1">支付宝支付</option>
               <option value="2" >微信支付</option>
               <option value="3" >银联支付</option>
               <option value="4" >京东白条</option>
           </select>
       </span>
		<span class="select-box" style="width: 120px;">
           <select name="statusSelect" id="statusSelect" class="select" autocomplete="off">
               <option value="">转账状态</option>
               <option value="0">已结清</option>
               <option value="1" >未结清</option>
           </select>
       </span>
		<input type="text" class="input-text" style="width:250px" placeholder="结算单号|商铺名称" id="storeName" name="storeName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">结算单号</th>
				<th width="10%">创建日期</th>
				<th width="15%">商铺名称</th>
				<th width="10%">合计金额(元) </th>
				<th width="5%">费率</th>
				<th width="10%">结算金额(元)</th>
				<th width="10%">状态</th>
				<th width="10%">操作</th>
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
            "sDefaultContent": "创建日期",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.addTime != null) {
                    return formatDate(row.addTime, "yyyy-MM-dd hh:mm:ss");
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "商铺名称",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.storeName != null) {
                    return row.storeName;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "合计金额",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.totalMoney != null) {
                    return row.totalMoney/100;
                } else {
                    return "";
                }
            }
        },
    {
        "sDefaultContent": "费率",
        "bSortable" : false,
        "sClass": "td-status text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.xzfRate !=null) {
                return row.xzfRate;
            }else {
                return "";
            }
        }
    },
        {
            "sDefaultContent": "结算金额",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.amountMoney !=null) {
                    return row.amountMoney;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "转账状态",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.amountStatus == 0) {
                    return "已结清";
                } else if(row.amountStatus == 1) {
                    return "未结清";
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "操作",
            "bSortable": false,
            "sClass": "td-manage text-c",
            "bSearchable": false,
            "mRender": function (data, type, row) {
                //结账
                return statusTools(row);
            }
        },
    ];
    var url = "${context_root}/dataCount/findFinancialList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});
//结账
function statusTools(row) {
    if (row.amountStatus == 1) {
        return "<a style=\"text-decoration:none\" onclick=\"order_check('结账','${context_root}/order/toCheck.action?amountId=" + row.amountId +"','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>结账</span></a>";
    } else {
        return "<a style=\"cursor: default;\" title=\"结账\">结账</a>";
    }
}

function query() {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var status = $("#statusSelect option:selected").val();
	var storeName =$("#storeName").val();
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/dataCount/findFinancialList.action?beginTime="+beginTime+"&endTime="+endTime+"&status="+status+"&storeName="+storeName);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}

/*结账*/
function order_check(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 
</body>
</html>