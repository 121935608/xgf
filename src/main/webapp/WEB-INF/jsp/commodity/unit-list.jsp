<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 单位管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<div class="text-c" style="margin-bottom:20px;">
			 <span class="l" style="margin-left:20px;"><a href="javascript:;" onclick="unit_add('添加','${context_root}/commodity/toUnitAdd.action','','410')" 
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a></span>
		</div>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">编号</th>
				<th width="10%">单位</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
	</table>
	</div>
<script type="text/javascript">
var pageTable;
$(document).ready(function(){ 
    var aoColumns = [
    {
        "mData": "unitCode",
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
        "sDefaultContent": "操作",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            //编辑
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"unit_edit('编辑','${context_root}/commodity/toUnitModify.action?unitCode=" + row.unitCode + "','','410')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
        	return toEdit;
        }
    },
    ];
    var url = "${context_root}/commodity/unitList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

/*用户-编辑*/
function unit_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
/*添加*/
function unit_add(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 
</body>
</html>