<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="uiloader/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="uiloader/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="uiloader/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="uiloader/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
<link href="uiloader/static/jqueryStep/css/jquery.step.css" type="text/css" rel="stylesheet">
<link href="uiloader/lib/jQuery-Validation-Engine/css/validationEngine.jquery.css?1" rel="stylesheet">
<link href="uiloader/lib/jQuery-Validation-Engine/css/template.css?1" rel="stylesheet">

<title>服务商平台修改密码页</title>
<meta name="keywords" content="服务商平台修改密码页">
<meta name="description" content="服务商平台修改密码页。">
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="loginWraper">
  <div class="loginBox">
      <ul>

      </ul>
    <form id="modifyPwdForm" class="form form-horizontal"  method="post">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
         <input id="username" name="username" type="text" placeholder="注册时手机号"  class="input-text size-L validate[required]">
        </div>
      </div>
     <%-- <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password" name="password" type="password" placeholder="请输入验证码"  class="input-text size-L validate[required]">
        </div>
      </div>--%>
      <%-- jcaptchaEbabled 在JCaptchaValidateFilter设置 --%>
      <c:if test="${jcaptchaEbabled}">
	      <div class="row cl">
	        <div class="formControls col-xs-8 col-xs-offset-3">
              <input class="input-text size-L validate[required,ajax[ajaxJcaptchaCall]]" type="text" placeholder="验证码" id="jcaptchaCode" name="jcaptchaCode" style="width:150px;">
	          <img id="jcaptchaImage" src="jcaptcha.jpg"> <a class="jcaptcha-btn">看不清，换一张</a> </div>
	      </div>
      </c:if>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input id="loginsubmit" type="submit" class="btn btn-success radius size-L" value="&nbsp;下&nbsp;&nbsp;一&nbsp;&nbsp;步&nbsp;">
        </div>
      </div>
      <font color="red">
          <c:if test="${not empty param.kickout}">您的账号在其他地方登陆。被强制退出。</c:if>
          <c:if test="${not empty param.forceLogout}">您已经被管理员强制退出。请重新登录</c:if>
          ${errorMsg }</font>
    </form>
  </div>
</div>
<div class="footer">Copyright 星融金服</div>
<script type="text/javascript" src="uiloader/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="uiloader/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="uiloader/static/jqueryStep/js/jquery.step.min.js"></script>
<script src="uiloader/lib/jQuery-Validation-Engine/js/jquery.validationEngine.js?1" charset="utf-8" type="text/javascript"></script>
<script src="uiloader/lib/jQuery-Validation-Engine/js/languages/jquery.validationEngine-zh_CN.js?1" charset="utf-8" type="text/javascript"></script>
<script type="text/javascript">
    var $step = $("#step");
    var $index = $("#index");
    $step.step({ index: 0, time: 500, title: ["填写申请表", "上传资料", "待确认", "已确认", "预约完成"] });
    $index.text($step.getIndex());
    $("#prevBtn").on("click", function() { $step.prevStep(); $index.text($step.getIndex()); });
    $("#nextBtn").on("click", function() { $step.nextStep(); $index.text($step.getIndex()); });
    $("#btn1").on("click", function() { $step.toStep(1); $index.text($step.getIndex()); });
    $("#btn2").on("click", function() { $step.toStep(2); $index.text($step.getIndex()); });
    if(window != top)
{
    top.location.href=location.href;
}

$(function() {
    $("#username").focus();
    $(".jcaptcha-btn").click(function() {
        var img = $("#jcaptchaImage")
        var imageSrc = img.attr("src");
        if(imageSrc.indexOf("?") > 0) {
            imageSrc = imageSrc.substr(0, imageSrc.indexOf("?"));
        }
        imageSrc = imageSrc + "?" + new Date().getTime();
        img.attr("src", imageSrc);
    });
    $.validationEngineLanguage.allRules.ajaxJcaptchaCall={
        "url": "${pageContext.request.contextPath}/jcaptcha-validate.action",
        "alertTextLoad": "* 正在验证，请稍等。。。"
    };
    $("#modifyPwdForm").validationEngine({scroll:false});
});
</script>
</body>
</html>
