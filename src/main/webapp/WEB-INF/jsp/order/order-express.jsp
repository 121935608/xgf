<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="督导员管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span> 订单快递查询 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<span class="select-box" style="width: 120px;">
           <select name="expressFirm" id="expressFirm" class="select" autocomplete="off">
               <option value="">快递公司</option>
               <option value="顺丰快递">顺丰快递</option>
               <option value="圆通快递" >圆通快递</option>
               <option value="申通快递" >申通快递</option>
               <option value="韵达" >韵达</option>
           </select>
       </span>
		<input type="text" class="input-text" style="width:250px" placeholder="订单号|快递单号" id="orderNumber" name="orderNumber">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">订单号</th>
				<th width="10%">快递单号</th>
				<th width="10%">快递公司</th>
				<th width="15%">送货地址</th>
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
            "sDefaultContent": "快递单号",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.expressNo != null) {
                    return row.expressNo;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "快递公司",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.expressFirm != null) {
                    return row.expressFirm;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "送货地址",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.address !=null) {
                       return row.address;
                } else {
                    return "";
                }
            }
        },

    ];
    var url = "${context_root}/order/findOrderExpressList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function query() {
    var expressFirm = $("#expressFirm option:selected").val();
	var orderNumber =$("#orderNumber").val();
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/order/findOrderExpressList.action?expressFirm="+expressFirm+"&orderNumber="+orderNumber);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}
</script>
</body>
</html>