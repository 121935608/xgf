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

<aside class="Hui-aside">
	<a href="index.action" style="color: white;font-size: 20px;font-weight: 10px;margin: 5px; text-decoration:none; ">星支付商户管理平台</a>
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
		<c:if test="${permissions!=null }">
			<dl>
				<c:forEach items="${permissions }" var="permission">
					<dt style="text-decoration:none;"><i class="${permission.permsIcon }"></i> ${permission.permsName }</dt>
					<dd style="background-color: #3E3E6A;padding-left: 30px;">
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
<section class="Hui-article-box" style="border-right: 1px white solid;">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li style="height:30px;"><span title="首页" style="line-height: 30px;">首页</span><em></em></li>
			</ul>
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
			<%--<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">--%>
				<%--<ul class="cl">--%>
					<%--<li>${currentUser.accountName }</li>--%>
					<%--<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">${currentUser.userName } <i class="Hui-iconfont">&#xe6d5;</i></a>--%>
						<%--<ul class="dropDown-menu menu radius box-shadow">--%>
							<%--<li><a _href="${context_root}/system/userInfoDeail.action" data-title="个人信息" onClick="Hui_admin_tab(this)">个人信息</a></li>--%>
							<%--<li><a id="loginOut" href=javascript:logout()>退出</a></li>--%>
						<%--</ul>--%>
					<%--</li>--%>
				<%--</ul>--%>
			<%--</nav>--%>
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
