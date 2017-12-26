<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="操作日志"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 系统日志 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入用户名称" id="userName" name="userName">
		<button type="button" class="btn btn-success radius" id="userBtn" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">ID</th>
				<th width="15%">模块信息</th>
				<th width="10%">模块名 </th>
				<th width="10%">执行地址</th>
				<th width="10%">状态</th>
				<th width="20%">请求URL</th>
				<th width="10%">用户账号</th>
				<th width="15%">操作时间</th>
				<th width="5%">操作</th>
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
        "mData": "logId",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "action",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "title",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "userIp",
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
                return "<span class=\"label label-success radius\">正常</span>";
            } else {
                return "<span class=\"label label-danger radius\">异常</span>";
            }
        }
    },
    {
        "mData": "url",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "mData": "userName",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "sDefaultContent": "操作时间",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            return formatDate(row.createTIme,"yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        "sDefaultContent": "操作",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	var param = row.requestParam.replace(/\"/g,"&#34;");
        	var status = row.status;
        	if(status == 1)
        	{
        		var errorMessage = row.errorMessage.replace(/\"/g,"&#34;");
        		param+= "<br/>异常信息<br/>" + errorMessage;
        	}
        	var toView =  "<a title=\"详细\" href=\"javascript:;\" onclick=\"operLog_show(\'" + param + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe695;</i></a>";
            return toView;
        }
    },
    ];
    var url = "${context_root}/system/operlogList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function query() {
    var userName = $("#userName").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var jsonObject = '{\"userName\":\"' + userName + '\",\"beginTime\":\"' + beginTime + '\",\"endTime\":\"' + endTime + '\"}';
    
    pageTable.fnSettings().sAjaxSource = "${context_root}/system/operlogList.action?jsonObject=" + jsonObject;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*操作日志-查看*/
function operLog_show(param){
	//墨绿深蓝风
	layer.alert("请求参数信息<br/>" + param, {
	  skin: 'layui-layer-molv' //样式类名
	  ,closeBtn: 0
	});
}
</script> 
</body>
</html>