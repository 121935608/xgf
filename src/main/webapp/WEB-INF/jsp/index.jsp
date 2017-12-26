<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="星支付商户管理平台"/>
<script type="text/javascript">
//退出系统方法
function logout() {
	var result = confirm('您确定要退出本系统吗?');
	if(result){
		location.href = '${context_root}/logout.action';
	}
}
</script>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="index.action">星支付商户管理平台</a><span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
				    <c:forEach items="${permissions }" var="permission">
						<li><a href="javascript:;" class="dropDown_A"><i class="${permission.permsIcon }"></i> ${permission.permsName } <i class="Hui-iconfont"></i></a></li>
					</c:forEach>
				</ul>
			</nav>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li>${currentUser.accountName }</li>
					<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">${currentUser.userName } <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a _href="${context_root}/system/userInfoDeail.action" data-title="个人信息" onClick="Hui_admin_tab(this)">个人信息</a></li>
							<li><a id="loginOut" href=javascript:logout()>退出</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</header>
<aside class="Hui-aside">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
		<c:if test="${permissions!=null }">
			<dl id="menu-system">
				<c:forEach items="${permissions }" var="permission">
					<dt><i class="${permission.permsIcon }"></i> ${permission.permsName }<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
					<dd>
						<ul>
							<c:forEach items="${permission.children }" var="children">
								<li><a _href="${context_root}${children.permsUrl }" data-title="${children.permsName }" href="javascript:void(0)">${children.permsName }</a></li>
							</c:forEach>
						</ul>
						 
					</dd>
				</c:forEach>
			</dl>
		</c:if>
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="首页">首页</span><em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="${context_root}/welcome.action"></iframe>
		</div>
	</div>
</section>
</body>
</html>
