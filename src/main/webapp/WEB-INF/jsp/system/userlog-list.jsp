<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="登录日志"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 登录日志 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<input type="text" class="input-text" style="width:250px" placeholder="输入用户名称" id="userName" name="userName">
		<button type="button" class="btn btn-success radius" id="userBtn" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
	<div class="mt-20">
		<y:table url="${context_root}/system/userlogList.action" id="userlogform" width="100%" checkbox="true" rowId="signId">
			<y:column width ="10%"   name ="signId"       title = '访问日志ID'/>
			<y:column width ="10%"   name ="userName"     title = '用户名称'/>
			<y:column width ="10%"   name ="status"       title = '登录状态'   colType="code" codekey="sys-login_status"/>
			<y:column width ="10%"   name ="loginIP"      title = '登录地址'/>
			<y:column width ="10%"   name ="browser"      title = '浏览器'/>
			<y:column width ="10%"   name ="os"           title = '操作系统'/>
			<y:column width ="20%"   name ="msg"          title = '提示信息'/>
			<y:column width ="20%"   name ="loginTime"    title = '登录时间'    colType="date"   format="yyyy-MM-dd hh:mm:ss"/>
		</y:table>
	</div>
</div>
<script type="text/javascript">
		  
function query() {
	//var rowId = $.y.getRowId();
	//alert(rowId);
    
    var userName = $("#userName").val();
    var jsonObject = '{\"userName\":\"' + userName + '\"}';
    
    pageTable.fnSettings().sAjaxSource = "${context_root}/system/userlogList.action?userName=" + userName;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
    
}


</script> 
</body>
</html>