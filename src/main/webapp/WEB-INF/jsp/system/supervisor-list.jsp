<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="督导员管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<span class="select-box" style="width: 120px;">
		   <select name="statusSelect" id="statusSelect" class="select" autocomplete="off">
			   <option value="">状态</option>
			   <option value="0">启用</option>
			   <option value="1">禁用</option>
		   </select>
		</span>
		<input type="text" class="input-text" style="width:250px" placeholder="姓名|督导员编号|电话" id="userName" name="userName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="role_add('添加督导员','${context_root}/system/toSupervisorAdd.action','','410')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加督导员</a></span></div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">姓名</th>
				<th width="15%">所在区域</th>
				<th width="15%">督导员编号</th>
				<th width="15%">电话 </th>
				<th width="10%">状态</th>
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
            "sDefaultContent": "姓名",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.name != null) {
                    return row.name;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "所在区域",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.area != null) {
                    return row.area;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "督导编号",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.supervisorNum != null) {
                    return row.supervisorNum;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "电话",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.phone != null) {
                    return row.phone;
                } else {
                    return "";
                }
            }
        },
    {
        "sDefaultContent": "状态",
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
        "sDefaultContent": "操作",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	//编辑
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"role_edit('编辑角色','${context_root}/system/toSupervisorModify.action?supervisorId=" + row.supervisorId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">编辑</a>";
        	//删除
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"role_del(this,\'" + row.roleId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
        	//授权
        	var toAuthorize = "<a title=\"授权\" href=\"javascript:;\" onclick=\"role_Authorize('授权菜单','${context_root}/system/toRoleAuthorize.action?roleId=" + row.roleId + "','230','406')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e1;</i></a>";
        	return toEdit;
        }
    },
    ];
    var url = "${context_root}/system/supervisorList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function query() {
    var status = $("#statusSelect option:selected").val();
	var userName =$("#userName").val();
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/system/supervisorList.action?status="+status+"&userName="+userName);
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