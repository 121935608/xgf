<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="角色管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入角色名称" id="roleName" name="roleName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜角色</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="role_add('添加角色','${context_root}/system/toRoleAdd.action','','410')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a></span></div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">角色ID</th>
				<th width="15%">角色名</th>
				<th width="15%">角色标识</th>
				<th width="15%">状态 </th>
				<th width="10%">描述</th>
				<th width="10%">创建时间</th>
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
        "mData": "roleId",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "roleName",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "roleKey",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "sDefaultContent": "角色状态",
        "bSortable" : false,
        "sClass": "td-status text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.status == '0') {
                return "<span class=\"label label-success radius\">可用</span>";
            } else {
                return "<span class=\"label label-defaunt radius\">不可用</span>";
            }
        }
    },
    {
        "mData": "description",
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
        "sDefaultContent": "编辑",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	//编辑
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"role_edit('编辑角色','${context_root}/system/toRoleModify.action?roleId=" + row.roleId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
        	//删除
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"role_del(this,\'" + row.roleId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>删除</span></a>";
        	//授权
        	var toAuthorize = "<a title=\"授权\" href=\"javascript:;\" onclick=\"role_Authorize('授权菜单','${context_root}/system/toRoleAuthorize.action?roleId=" + row.roleId + "','230','406')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>授权</span></a>";
        	return toAuthorize  + "&nbsp;&nbsp;" + toEdit + "&nbsp;&nbsp;" + toDelete;
        }
    },
    ];
    var url = "${context_root}/system/roleList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function query() {
    var roleName = $("#roleName").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var jsonObject = '{\"roleName\":\"' + roleName + '\",\"beginTime\":\"' + beginTime + '\",\"endTime\":\"' + endTime + '\"}';
    
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/system/roleList.action?jsonObject=" + jsonObject);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*角色-添加*/
function role_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*角色-编辑*/
function role_edit(title,url,w,h){
	layer_show(title,url,w,h);
}

/*角色-授权*/
function role_Authorize(title,url,w,h){
	layer_show(title,url,w,h);
}

/*角色-删除*/
function role_del(obj,id){
	parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			    url:"${context_root}/system/deleteRoleById.action?roleId=" + id, 
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
						parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
					}
				},
				
			}) ;
	});
}
</script> 
</body>
</html>