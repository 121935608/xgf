<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="优惠券列表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 谢鲜CRM管理 <span class="c-gray en">&gt;</span> 优惠券列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">商铺名称</th>
				<th width="5%">联系人</th>
				<th width="5%">手机号</th>
				<th width="5%">订单编号</th>
				<th width="5%">物流单号</th>
				<th width="5%">损坏果物(Kg)</th>
				<th width="5%">验货现场结果</th>
				<th width="5%">补券金额</th>
				<th width="5%">审核状态</th>
				<th width="5%">操作</th>
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
        "mData": "storeName",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "contacts",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "phoneNum",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "orderNum",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "logisticsSingleNum",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "damagedFruit",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
		"bSortable" : false,
		"sClass" : "text-c",
		"bSearchable" : false,
		"mRender" : function(data, type, row) {
			//(0-是 1-否)'
			if (row.inspectionScene == '0') {
				return "是";
			} else if (row.inspectionScene == '1') {
				return "否";
			} 
			else {
				return "";
			}
		}
	},
    {
        "mData": "amount",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
		"bSortable" : false,
		"sClass" : "text-c",
		"bSearchable" : false,
		"mRender" : function(data, type, row) {
			//(0-已审核 1-未审核)
			if (row.auditStatus == '1') {
				return "<span class=\"label label-success radius\">已通过</span>";
			} else if (row.auditStatus == '0') {
				return "<span class=\"label label-defaunt radius\">未审核</span>";
			} else if(row.auditStatus == '2') {
				return "<span class=\"label label-danger radius\">不通过</span>";
			}
			else {
				return "";
			}
		}
	},
	{
        "sDefaultContent": "查看",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	//查看详情
            var details = "<a title=\"查看\" href=\"javascript:;\" onclick=\"coupon_query('查看详情','${context_root}/crm/toCouponQuery.action?couponId=" + row.couponId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>查看</span></a>";
        	//审核
        	if(row.auditStatus == '0'){
        		var toAudit = "<a title=\"审核\" href=\"javascript:;\" onclick=\"coupon_audit('审核','${context_root}/crm/couponAudit.action?couponId=" + row.couponId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>审核</span></a>";
        		return  details + "&nbsp;&nbsp;" + toAudit;
        	}
        	else{
        		return  details;
        	}
        }
    },
    ];
    var url = "${context_root}/crm/couponList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

/*优惠券-查看*/
function coupon_query(title, url, w, h) {
    layer_show(title, url, w, h);
}

/*优惠券-编辑*/
function coupon_audit(title,url,w,h){
	layer_show(title,url,w,h);
}

</script> 
</body>
</html>