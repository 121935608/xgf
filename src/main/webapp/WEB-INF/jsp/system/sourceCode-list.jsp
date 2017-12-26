<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="渠道配置"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 渠道配置 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<%--<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入用户名称、电话、邮箱" id="userName" name="userName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>--%>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="sourceCode_add('添加渠道','${context_root}/system/toSourceCodeAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加渠道</a></span></div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">渠道编号</th>
				<th width="10%">渠道名字</th>
				<th width="15%">推广地址</th>
				<th width="10%">软件版本号</th>
				<%--<th width="15%">下载地址</th>--%>
				<%--<th width="10%">手机号码</th>
				<th width="10%">状态</th>
				<th width="15%">创建时间</th>--%>
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
        "mData": "sourcecodeid",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "sourceCodeName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
        {
            "mData": "sourceCodeAddress",
            "bSortable" : false,
            "sClass": "text-c",
            "defaultContent": ""
        },
    {
        "mData": "phoneSysVersion",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
        /*{
            "sDefaultContent": "下载地址",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                return "<a href=\""+row.install+"\" target=\"_blank\"  class=\"ml-5\" style=\"text-decoration:none\">"+row.install+"</a>";
            }
        },*/
    {
        "sDefaultContent": "编辑",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            //编辑
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"sourceCode_edit('编辑','${context_root}/system/toSourceCodeModify.action?sourcecodeid=" + row.sourcecodeid + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>";
        	//删除
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"sourceCode_del(this,\'" + row.sourcecodeid + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
        	return toEdit  +  "&nbsp;&nbsp;" + toDelete;
        }
    },
    ];
    var url = "${context_root}/system/sourceCodeList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});


function query() {
    var userName = $("#userName").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var jsonObject = '{\"userName\":\"' + userName + '\",\"beginTime\":\"' + beginTime + '\",\"endTime\":\"' + endTime + '\"}';
    
    pageTable.fnSettings().sAjaxSource = "${context_root}/system/sourceCodeList.action?jsonObject=" + jsonObject;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*渠道-添加*/
function sourceCode_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*渠道-编辑*/
function sourceCode_edit(title,url,w,h){
	layer_show(title,url,w,h);
}


/*渠道-删除*/
function sourceCode_del(obj,sourcecodeid){
	parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			    url:"${context_root}/system/deleteSourceCodeById.action?sourcecodeid=" + sourcecodeid,
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