<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="用户管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<%--<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入用户名称、电话、邮箱" id="userName" name="userName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>--%>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="user_add('添加用户','${context_root}/system/toUserAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a></span></div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">用户ID</th>
				<th width="10%">用户名</th>
				<th width="15%">账号 </th>
				<th width="10%">所属角色</th>
				<th width="15%">邮箱</th>
				<th width="10%">手机号码</th>
				<th width="10%">状态</th>
				<th width="15%">创建时间</th>
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
        "mData": "userId",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "accountName",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "userName",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "roleName",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "email",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "mobilePhone",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "sDefaultContent": "账号状态",
        "bSortable" : false,
        "sClass": "td-status text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.locked == '0') {
                return "<span class=\"label label-success radius\">已启用</span>";
            } else {
                return "<span class=\"label label-defaunt radius\">已停用</span>";
            }
        }
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
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"user_edit('编辑','${context_root}/system/toUserModify.action?userId=" + row.userId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>";
        	//修改密码
        	var toPassword = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"change_password('修改密码','${context_root}/system/toChangePwd.action?userId=" + row.userId + "','600','270')\" href=\"javascript:;\" title=\"修改密码\"><i class=\"Hui-iconfont\">&#xe63f;</i></a>";
        	//删除
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"user_del(this,\'" + row.userId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
        	return statusTools(row)  + "&nbsp;&nbsp;" + toEdit  + "&nbsp;&nbsp;" + toPassword + "&nbsp;&nbsp;" + toDelete;
        }
    },
    ];
    var url = "${context_root}/system/userList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function statusTools(row) {
    if (row.locked == '0') {
        return "<a style=\"text-decoration:none\" onClick=\"user_stop(this,\'" + row.userId + "\')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a>";
    } else {
        return "<a style=\"text-decoration:none\" onClick=\"user_start(this,\'" + row.userId + "\')\" href=\"javascript:;\" title=\"启用\"><i class=\"Hui-iconfont\">&#xe615;</i></a>";
    }
}

function query() {
    var userName = $("#userName").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var jsonObject = '{\"userName\":\"' + userName + '\",\"beginTime\":\"' + beginTime + '\",\"endTime\":\"' + endTime + '\"}';
    
    pageTable.fnSettings().sAjaxSource = "${context_root}/system/userList.action?jsonObject=" + jsonObject;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*用户-添加*/
function user_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*用户-编辑*/
function user_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,w,h){
	layer_show(title,url,w,h);	
}

/*管理员-停用*/
function user_stop(obj,id){
	parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			url:"${context_root}/system/changeUserStatus.action?userId=" + id +"&locked=1", 
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				if(data.s == true){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="user_start(this,'+id+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
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

/*管理员-启用*/
function user_start(obj,id){
	parent.layer.confirm('确认要启用吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			url:"${context_root}/system/changeUserStatus.action?userId=" + id +"&locked=0", 
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				if(data.s == true){
					$(obj).parents("tr").find(".td-manage").prepend('<a onClick="user_stop(this,'+id+')" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
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

/*用户-删除*/
function user_del(obj,id){
	parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			    url:"${context_root}/system/deleteUserById.action?userId=" + id, 
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