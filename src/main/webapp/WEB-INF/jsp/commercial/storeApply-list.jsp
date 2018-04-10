<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>商户相关 <span class="c-gray en">&gt;</span>用户登记<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<input type="text" class="input-text" style="width:250px" placeholder="姓名|联系方式|店铺名称" id="applyName" name="applyName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="4%">序号</th>
				<th width="4%">姓名</th>
				<th width="8%">联系方式</th>
				<th width="10%">店铺名称</th>
				<th width="10%">店铺地址</th>
				<th width="10%">申请时间</th>
				<th width="6%">处理人</th>
				<th width="10%">处理结果</th>
				<th width="4%">操作</th>
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
        "mData": "num",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "applyName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "phone",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "storeName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
		"sDefaultContent" : "店铺地址",
		"bSortable" : false,
		"sClass" : "text-c",
		"bSearchable" : false,
		"mRender" : function(data, type, row) {
			if ((row.storeAdrr != null)&&(row.detailAdrr != null)) {
				return row.storeAdrr+row.detailAdrr;
			} else if((row.storeAdrr != null)&&(row.detailAdrr == null)){
				return row.storeAdrr
			}else {
				return "";
			}
		}
	}, 
    {
		"sDefaultContent" : "申请时间",
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
        "mData": "dealUser",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
		"sDefaultContent" : "处理结果",
		"bSortable" : false,
		"sClass" : "text-c",
		"bSearchable" : false,
		"mRender" : function(data, type, row) {
			if (row.dealResult != null) {
				return "<div id=\"dealResult\" title="+row.dealResult+" style=\"width:120px;overflow:hidden; white-space:nowrap; text-overflow:ellipsis;text-align:center;\">"+row.dealResult+"</div>";
			} else {
				return "";
			}
		}
	},
    {
        "sDefaultContent": "操作",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	if(row.dealStatus == 1){
        		return "";
        	}else{
	        	//编辑
	            var toEdit = "<a title=\"处理\" href=\"javascript:;\" onclick=\"storeApply_edit('处理','${context_root}/merchant/toStoreApplyModify.action?applyId=" + row.applyId + "','','410')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>处理</span></a>";
	        	return toEdit;
        	}
        }
    }
    ];
    var url = "${context_root}/merchant/storeApplyList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function query() {
    var applyName=$("#applyName").val();
    var reg =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
    if (reg.test(applyName)){
        alert("含有非法字符");
        return;
	}
    pageTable.fnSettings().sAjaxSource =encodeURI("${context_root}/merchant/storeApplyList.action?applyName=" + applyName);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}
/*处理*/
function storeApply_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 
</body>
</html>