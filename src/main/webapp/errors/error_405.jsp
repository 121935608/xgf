<!DOCTYPE>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
<title>出错了</title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/uiloader/static/h-ui.admin/css/error.css" />
</head>
<body>
	<div class="container">
		<div style="position: relative; width: 600px; margin: 0 auto;">
			<div class="cont">
				<div class="c1 demo">
					<p>
						<span>4</span><span>0</span><span>5</span>
					</p>
				</div>
				<h1>对不起，您访问的地址不存在或出错了！您可以选择以下操作：</h1>
				<div class="c2">
					<a target="_top" class="home" href="<%=request.getContextPath() %>/index.jsp">返回首页</a>
				</div>
				<div class="c3">
					<a class="c3" href="index">本站</a>提醒您 - 您访问的地址不存在或出错了，请重新输入！
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
function index(){
	window.location.href = getRootPath() + "/index.jsp";
}

function getRootPath(){
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPath = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);
    return(localhostPath + projectName);
}
</script>