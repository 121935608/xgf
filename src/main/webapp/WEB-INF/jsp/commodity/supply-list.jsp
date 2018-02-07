<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 供应商管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<div class="text-c" style="margin-bottom:20px;">
			 <span class="l" style="margin-left:20px;"><a href="javascript:;" onclick="supply_add('添加','${context_root}/commodity/toSupplyAdd.action','','410')" 
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a></span>
		</div>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">供应商编号</th>
				<th width="10%">供应商名称</th>
				<th width="10%">联系人</th>
				<th width="10%">联系电话</th>
				<th width="10%">状态</th>
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
        "mData": "supplierCode",
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
        "mData": "contactName",
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
        "sDefaultContent": "状态",
        "bSortable" : false,
        "sClass": "td-status text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.status == 1) {
                return "<span class=\"label label-success radius\">启用</span>";
            } else if(row.status == -1){
                return "<span class=\"label label-defaunt radius\">停用</span>";
            }else{
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
            //编辑
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"supply_edit('编辑','${context_root}/commodity/toSupplyModify.action?supplierCode=" + row.supplierCode + "','','410')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
        	return statusTools(row)  + "&nbsp;&nbsp;" + toEdit;
        }
    },
    ];
    var url = "${context_root}/commodity/supplyList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function statusTools(row) {
    if (row.status == 1) {
        return "<a style=\"text-decoration:none\" onClick=\"supply_stop(this,\'" + row.supplierCode + "\')\" href=\"javascript:;\" title=\"停用\"><span style='color: #0e90d2 '>停用</span></a>";
    } else {
        return "<a style=\"text-decoration:none\" onClick=\"supply_start(this,\'" + row.supplierCode + "\')\" href=\"javascript:;\" title=\"启用\"><span style='color: #0e90d2 '>启用</span></a>";
    }
}
/*用户-编辑*/
function supply_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
/*添加*/
function supply_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*停用*/
function supply_stop(obj,id){
	parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			url:"${context_root}/commodity/changeSupplyStatus.action?supplierCode=" + id +"&status=-1", 
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				if(data.s == true){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="supply_start(this,'+"'"+id+"'"+')" href="javascript:;" title="启用"><span style=\'color: #0e90d2 \'>启用</span></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
					$(obj).remove();
					parent.layer.msg('已停用!',{icon: 5,time:1000});
				}else{
					parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
				}
			},
		}) ;
		
	});
}

/*启用*/
function supply_start(obj,id){
	parent.layer.confirm('确认要启用吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			url:"${context_root}/commodity/changeSupplyStatus.action?supplierCode=" + id +"&status=1", 
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				if(data.s == true){
					$(obj).parents("tr").find(".td-manage").prepend('<a onClick="supply_stop(this,'+"'"+id+"'"+')" href="javascript:;" title="停用" style="text-decoration:none"><span style=\'color: #0e90d2 \'>停用</span></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
					$(obj).remove();
					parent.layer.msg('已启用!', {icon: 6,time:1000});
				}else{
					parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
				}
			},
		}) ;
		
	});
}
</script> 
</body>
</html>