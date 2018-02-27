<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="数据统计"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据统计 <span class="c-gray en">&gt;</span> 库存查询 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	<span class="select-box" style="width: 120px;border:0px;">
	       <y:select id="categoryId" name="categoryId" codeGroup="${fenlei}" selectedValue=""
					 cssClass="select" headerKey="" headerValue="分类">
		   </y:select>
   </span>
       <select id="condition" name="condition" style="width:150px;height:33px;">
       		<option disabled selected style='display:none;' value="">库存情况</option>
       		<option value=0>库存正常</option>
       		<option value=1>库存过剩</option>
       		<option value=-1>库存不足</option>
       </select>
		<input type="text" class="input-text" style="width:250px" placeholder="编码|商品名称|商品条码" id="commodityNo" name="commodityNo">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">编码</th>
				<th width="8%">商品名称</th>
				<th width="8%">商品条码</th>
				<th width="8%">供货商</th>
				<th width="8%">分类</th>
				<th width="7%">单位</th>
				<th width="7%">库存上限</th>
				<th width="7%">库存下限</th>
				<th width="6%">库存</th>
			</tr>
		</thead>
	</table>
	</div>
<script type="text/javascript">
var pageTable;
$(document).ready(function(){ 
    var aoColumns = [
    {
        "mData": "commodityCode",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "commodityName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "commodityNo",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "supplierName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "categoryName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "unitName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "upperLimit",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "lowerLimit",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "stockNum",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    }
    ];
    var url = "${context_root}/commodity/registerList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function query() {
    var categoryId = $("#categoryId option:selected").val();
    var condition = $("#condition option:selected").val();
    var commodityNo=$("#commodityNo").val();
    var reg =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
    if (reg.test(commodityNo)){
        alert("含有非法字符");
        return;
	}
    pageTable.fnSettings().sAjaxSource =encodeURI("${context_root}/commodity/registerList.action?condition=" + condition+"&categoryId="+categoryId+"&commodityNo="+commodityNo);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}

</script> 
</body>
</html>