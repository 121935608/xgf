<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link href="uiloader/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="uiloader/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css"/>
    <link href="uiloader/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="uiloader/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="uiloader/lib/jQuery-Validation-Engine/css/validationEngine.jquery.css?1" rel="stylesheet">
    <link href="uiloader/lib/jQuery-Validation-Engine/css/template.css?1" rel="stylesheet">
    <title>服务商平台登录页</title>
    <meta name="keywords" content="服务商平台登录页">
    <meta name="description" content="服务商平台登录页。">
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value=""/>
<div class="loginWraper">
    <div class="loginBox">
        <form id="loginForm" class="form form-horizontal" action="${pageContext.request.contextPath}/login.action"
              method="post">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="username" name="username" type="text" placeholder="请输入登录账号"
                           class="input-text size-L validate[required]">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="password" name="password" type="password" placeholder="请输入登录密码"
                           class="input-text size-L validate[required]">
                </div>
            </div>
            <%-- jcaptchaEbabled 在JCaptchaValidateFilter设置 --%>
            <c:if test="${jcaptchaEbabled}">
                <div class="row cl">
                    <div class="formControls col-xs-8 col-xs-offset-3">
                        <input class="input-text size-L validate[required,ajax[ajaxJcaptchaCall]]" type="text"
                               placeholder="验证码" id="jcaptchaCode" name="jcaptchaCode" style="width:150px;">
                        <img id="jcaptchaImage" src="jcaptcha.jpg"> <a class="jcaptcha-btn">看不清，换一张</a></div>
                </div>
            </c:if>
            <div class="row cl">
                <%--<div class="formControls col-xs-8 col-xs-offset-3">
                  <label for="online">
                    <input type="checkbox" name="rememberMe">
                        使我保持登录状态</label>
                </div>--%>

            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input id="loginsubmit" type="submit" class="btn btn-success radius size-L"
                           value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input type="reset" class="btn btn-default radius size-L"
                           value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
                </div>
            </div>
            <font color="red">
                <c:if test="${not empty param.kickout}">您的账号在其他地方登陆。被强制退出。</c:if>
                <c:if test="${not empty param.forceLogout}">您已经被管理员强制退出。请重新登录</c:if>
                ${errorMsg }</font>
        </form>
            <a href="${context_root}/toModifyPassword.action" class="btn btn-success radius size-L">忘记密码</a>
    </div>
</div>
<div class="footer">Copyright 星融金服</div>
<script type="text/javascript" src="uiloader/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="uiloader/static/h-ui/js/H-ui.js"></script>
<script src="uiloader/lib/jQuery-Validation-Engine/js/jquery.validationEngine.js?1" charset="utf-8"
        type="text/javascript"></script>
<script src="uiloader/lib/jQuery-Validation-Engine/js/languages/jquery.validationEngine-zh_CN.js?1" charset="utf-8"
        type="text/javascript"></script>
<script type="text/javascript">

    if (window != top) {
        top.location.href = location.href;
    }

    $(function () {
        $("#username").focus();
        $(".jcaptcha-btn").click(function () {
            var img = $("#jcaptchaImage")
            var imageSrc = img.attr("src");
            if (imageSrc.indexOf("?") > 0) {
                imageSrc = imageSrc.substr(0, imageSrc.indexOf("?"));
            }
            imageSrc = imageSrc + "?" + new Date().getTime();
            img.attr("src", imageSrc);
        });
        $.validationEngineLanguage.allRules.ajaxJcaptchaCall = {
            "url": "${pageContext.request.contextPath}/jcaptcha-validate.action",
            "alertTextLoad": "* 正在验证，请稍等。。。"
        };
        $("#loginForm").validationEngine({scroll: false});

    });
</script>
</body>
</html>
