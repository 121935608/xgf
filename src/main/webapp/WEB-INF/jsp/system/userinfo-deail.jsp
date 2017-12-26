<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 个人信息 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form action = "save.action" id = "userForm" name = "userForm" method="Post">	 
<table class="table table-border table-bordered table-hover table-bg table-sort">
<input type="hidden"  id="userId" name ="userId" value="${user.userId}">
<input type="hidden"  id="type" name = "type" value="${type}">
	<tr>
		<td>用户名:</td>
		<td>${currentUser.userName }</td>
	</tr>
	<tr>
		<td>账户名:</td>
		<td>${currentUser.accountName }</td>
	</tr>
	<tr>
		<td>电子邮箱:</td>
		<td>${currentUser.email }</td>
	</tr>
	<tr>
		<td>手机号码:</td>
		<td>${currentUser.mobilePhone }</td>
	</tr>
	<tr>
		<td>是否启用</td>
		<td>
    		<c:if test="${currentUser.locked==0}">
    			启用 
    		</c:if>
    		<c:if test="${currentUser.locked==1}">
    			禁用
    		</c:if>
		</td>
	</tr>
	<tr>
		<td>描述:</td>
		<td>${currentUser.description }</td>
	</tr>
	<tr>
		<td>创建时间:</td>
		<td>${currentUser.createTime }</td>
	</tr>
</table>
</form>		
</div>
</body>
</html>