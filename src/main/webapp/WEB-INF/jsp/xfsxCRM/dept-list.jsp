<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="部门管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 谢鲜CRM管理 <span class="c-gray en">&gt;</span> 部门列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
		<input type="text" class="input-text" style="width:250px" placeholder="输入部门名称" id="deptName" name="deptName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜部门</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="dept_add('添加部门','${context_root}/crm/toDeptAdd.action','','410')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加部门</a></span></div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">部门名称</th>
				<th width="15%">部门描述</th>
				<th width="15%">创建时间</th>
				<th width="15%">更新时间</th>
				<th width="12%">操作</th>
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
        "mData": "deptName",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "describe",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "sDefaultContent": "创建时间",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            return formatDate(row.createTime,"yyyy-MM-dd hh:mm:ss");
        }
    },
    {
		"sDefaultContent" : "更新时间",
		"bSortable" : false,
		"sClass" : "text-c",
		"bSearchable" : false,
		"mRender" : function(data, type, row) {
			if (row.updateTime != null) {
				return formatDate(row.updateTime,"yyyy-MM-dd hh:mm:ss");
			} else {
				return "-";
			}
		}
	},
    {
        "sDefaultContent": "编辑",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	//编辑
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"dept_edit('编辑部门','${context_root}/crm/toDeptUpdate.action?deptId=" + row.deptId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
        	//删除
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"dept_del(this,\'" + row.deptId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>删除</span></a>";
        	return  toEdit + "&nbsp;&nbsp;" + toDelete;
        }
    },
    ];
    var url = "${context_root}/crm/deptList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});


function query() {
    var deptName = $("#deptName").val();
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/crm/deptList.action?deptName=" + deptName);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}

/*部门-添加*/
function dept_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*部门-编辑*/
function dept_edit(title,url,w,h){
	layer_show(title,url,w,h);
}

/*部门-删除*/
function dept_del(obj,id){
	parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			    url:"${context_root}/crm/deleteDeptById.action?deptId=" + id, 
				type:'post',
				async:true ,
				cache:false ,
				dataType:"json",
				success:function(data){
					if(data.s == true){
						$(obj).parents("tr").remove();
						parent.layer.msg('已删除!',{icon:1,time:1000});
						loadData() ;
					}else{
						parent.layer.msg("删除失败！该部门信息已被引用，请先确认并将相关数据更改！" , {icon: 2,title:"系统提示"});
					}
				},
				
			}) ;
	});
}
</script> 
</body>
</html>