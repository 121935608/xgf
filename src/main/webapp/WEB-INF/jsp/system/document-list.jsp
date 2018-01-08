<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="督导员管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<span class="select-box" style="width: 120px;">
		   <select name="statusSelect" id="statusSelect" class="select" autocomplete="off">
			   <option value="">启用状态</option>
			   <option value="0">启用</option>
			   <option value="1">禁用</option>
		   </select>
		</span>
		<input type="text" class="input-text" style="width:250px" placeholder="请输入公告标题" id="title" name="title">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="role_add('添加公文','${context_root}/system/toDocumentAdd.action','','410')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加公文</a></span></div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%"></th>
				<th width="15%">公文标题</th>
				<th width="15%">创建者</th>
				<th width="15%">创建时间 </th>
				<th width="10%">启用状态</th>
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
            "sDefaultContent": "",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.documentNum != null) {
                    return row.documentNum;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "公文标题",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.title != null) {
                    return row.title;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "创建者",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.creator != null) {
                    return row.creator;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "创建时间",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.creatTime != null) {
                    return formatDate(row.creatTime, "yyyy-MM-dd hh:mm:ss");
                } else {
                    return "";
                }
            }
        },
    {
        "sDefaultContent": "启用状态",
        "bSortable" : false,
        "sClass": "td-status text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.status == '0') {
                return "<span class=\"label label-success radius\">已启用</span>";
            } else {
                return "<span class=\"label label-defaunt radius\">已停用</span>";
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
            var toEdit = "<a title=\"修改\" href=\"javascript:;\" onclick=\"role_edit('修改公文','${context_root}/system/toDocumentModify.action?documentId=" + row.documentId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>修改</span></a>";
        	return statusTools(row) + "&nbsp;&nbsp;"+toEdit;
        }
    },
    ];
    var url = "${context_root}/system/documentList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function statusTools(row) {
    if (row.status == '0') {
        return "<a style=\"text-decoration:none\" onClick=\"user_stop(this,\'" + row.documentId + "\')\" href=\"javascript:;\" title=\"停用\"><span style='color: #0e90d2 '>停用</span></a>";
    } else {
        return "<a style=\"text-decoration:none\" onClick=\"user_start(this,\'" + row.documentId + "\')\" href=\"javascript:;\" title=\"启用\"><span style='color: #0e90d2 '>启用</span></a>";
    }
}

function query() {
    var status = $("#statusSelect option:selected").val();
	var title =$("#title").val();
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/system/documentList.action?status="+status+"&title="+title);
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

/*停用*/
function user_stop(obj,id){
    parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
        $.ajax({
            url:"${context_root}/system/changeDocumentStatus.action?documentId=" + id +"&status=1",
            type:'post',
            async:true ,
            cache:false ,
            dataType:"json",
            success:function(data){
                if(data.s == true){
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="user_start(this,'+id+')" href="javascript:;" title="启用"><span style=\'color: #0e90d2 \'>启用</span></a>');
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
function user_start(obj,id){
    parent.layer.confirm('确认要启用吗？',{icon: 3, title:'提示'},function(index){
        $.ajax({
            url:"${context_root}/system/changeDocumentStatus.action?documentId=" + id +"&status=0",
            type:'post',
            async:true ,
            cache:false ,
            dataType:"json",
            success:function(data){
                if(data.s == true){
                    $(obj).parents("tr").find(".td-manage").prepend('<a onClick="user_stop(this,'+id+')" href="javascript:;" title="停用" style="text-decoration:none"><span style=\'color: #0e90d2 \'>停用</span></a>');
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