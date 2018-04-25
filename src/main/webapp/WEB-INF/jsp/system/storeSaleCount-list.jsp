<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商铺采购统计"/>

<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据统计 <span class="c-gray en">&gt;</span> 商铺采购统计 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">

	<div style="min-height: 30px;">
		<form role="form" class="text-c">
		   <div class="row" >
		   		<div class="col-xs-6 col-sm-4 .col-md-4" > 日期范围：
					<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
					-
					<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
				</div>

				<div class="col-xs-3 col-sm-2 .col-md-2" > 
					<input type="text" class="input-text" style="width:250px" placeholder="商户名称或督导员" id="storeName" name="storeName">
				</div> 
				<div class="col-xs-3 col-sm-2 .col-md-2" > 
					<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
					<button type="button" class="btn btn-success radius" onclick="excel_out()"><i class="Hui-iconfont">&#xe665;</i> 下载</button>
				</div> 
		  	</div>
		</form>
	</div>
	
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort" style="table-layout:fixed;">
		<thead>
			<tr class="text-c">
				<th width="10%">商户名称</th>
				<th width="10%">督导员</th>
				<th width="8%">销售总金额</th>
				<th width="5%">交易单数量</th>
			</tr>
		</thead>
	</table>
	</div>
</div>
<script type="text/javascript"  charset="UTF-8">
    var pageTable;
    $(document).ready(function () {
        var aoColumns = [
            {
                "sDefaultContent": "商户名称",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.storeName == null) {
                        return "";
                    } else {
                        return row.storeName;
                    }
                }
            },
            {
                "sDefaultContent": "督导员",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.name == null) {
                        return "";
                    } else {
                        return row.name;
                    }
                }
            },
            {
                "sDefaultContent": "销售总金额",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.orderPrice == null) {
                        return "0";
                    } else {
                        return row.orderPrice;
                    }
                }
            },
            {
                "sDefaultContent": "交易单数量",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.orderNum == null) {
                        return "0";
                    } else {
                        return row.orderNum;
                    }
                }
            },


        ];
        var url = "${context_root}/dataCount/storeSaleList.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });





    function query() {
        var storeName = $("#storeName").val();
        var beginTime = $("#beginTime").val();
        var endTime = $("#endTime").val();
        pageTable.fnSettings().sAjaxSource = "${context_root}/dataCount/storeSaleList.action?storeName=" + encodeURIComponent(encodeURIComponent(storeName)) +
            "&beginTime="+ beginTime +
            "&endTime="+ endTime;
        pageTable.fnClearTable(0);
        pageTable.fnDraw();
    }

    function excel_out() {
        var beginTime = $("#beginTime").val();
        var endTime = $("#endTime").val();
        var storeName =$("#storeName").val();
        var elemIF = document.createElement("iframe");
        elemIF.src = "${context_root}/dataCount/downloadPurchaseData.action?beginTime="+beginTime+"&endTime="+endTime+"&storeName="+storeName;
        elemIF.style.display = "none";
        document.body.appendChild(elemIF);
    }
</script> 
</body>
</html>