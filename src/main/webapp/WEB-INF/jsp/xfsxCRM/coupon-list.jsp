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
			debugger;
			//(0-是 1-否)'
			if (row.inspectionScene == '0') {
				return "<span class=\"label label-success radius\">是</span>";
			} else if (row.inspectionScene == '1') {
				return "<span class=\"label label-defaunt radius\">否</span>";
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
			if (row.auditStatus == '0') {
				return "<span class=\"label label-success radius\">已审核</span>";
			} else if (row.auditStatus == '1') {
				return "<span class=\"label label-defaunt radius\">未审核</span>";
			} 
			else {
				return "";
			}
		}
	},
    ];
    var url = "${context_root}/crm/couponList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

</script> 
</body>
</html>