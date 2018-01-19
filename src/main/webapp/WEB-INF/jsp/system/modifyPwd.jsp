<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <title>密码修改页</title>
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
    <%@include file="/WEB-INF/jsp/common/import-css.jspf"%>
    <%@include file="/WEB-INF/jsp/common/import-js.jspf"%>
</head>
<body style="background:#3283AC url(${context_url}/uiloader/static/h-ui.admin/images/xgf-login.png) no-repeat 0px 30%; background-size:100%;height:60%;">
<link href="${context_url}/uiloader/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
<link href="${context_url}/uiloader/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css"/>
<link href="${context_url}/uiloader/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${context_url}/uiloader/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css"/>
<link href="${context_url}/uiloader/lib/jQuery-Validation-Engine/css/validationEngine.jquery.css?1" rel="stylesheet">
<link href="${context_url}/uiloader/lib/jQuery-Validation-Engine/css/template.css?1" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${context_url}/uiloader/static/jqueryStep/css/jquery.step.css" />
<script type="text/javascript" src="${context_url}/uiloader/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${context_url}/uiloader/static/h-ui/js/H-ui.js"></script>
<script src="${context_url}/uiloader/lib/jQuery-Validation-Engine/js/jquery.validationEngine.js?1" charset="utf-8"
        type="text/javascript"></script>
<script src="${context_url}/uiloader/lib/jQuery-Validation-Engine/js/languages/jquery.validationEngine-zh_CN.js?1" charset="utf-8"
        type="text/javascript"></script>
<script type="text/javascript" src="${context_url}/uiloader/static/jqueryStep/js/jquery.step.min.js"></script>
<style>
    button {
        display: inline-block;
        padding: 6px 12px;
        font-size: 14px;
        line-height: 1.42857143;
        text-align: center;
        cursor: pointer;
        border: 1px solid transparent;
        border-radius: 4px;
        color: #fff;
        background-color: #5bc0de;
    }

    .main {
        width: 1000px;
        margin: 100px auto;
    }

    #step {
        margin-bottom: 60px;
    }

    .btns {
        float: left;
    }

    .info {
        float: left;
        height: 34px;
        line-height: 34px;
        margin-left: 40px;
        font-size: 28px;
        font-weight: bold;
        color: #928787;
    }

    .info span {
        color: red;
    }
</style>
<div class="main" style="padding-top: 20%;">
    <div id="step"></div>
    <form id="check-form">
        <div class="row cl">
        <div id="con1" class="formControls col-xs-8 col-xs-offset-3">
            <input type="text" name="username" id="username" placeholder="注册时手机号"
                   class="input-text size-L ">
            <input class="input-text size-L validate[required,ajax[ajaxJcaptchaCall]]" type="text"
                   placeholder="验证码" id="jcaptchaCode" name="jcaptchaCode" style="width:150px;">
            <img id="jcaptchaImage" src="jcaptcha.jpg"> <a class="jcaptcha-btn">看不清，换一张</a>
        </div>
        </div>
    </form>
    <form >
        <div class="row cl">
            <div id="con2" style="display:none;" class="formControls col-xs-8 col-xs-offset-3">
                <input type="text" name="phone" id="phone" class="input-text size-L">
                <input type="button" id="btn" value="免费获取验证码" />
                <input type="text" name="verify" id="verify" placeholder="填写手机验证码"
                       class="input-text size-L validate[required,ajax[ajaxJcaptchaCall]]">
            </div>
        </div>
    </form>
    <form >
        <div class="row cl">
            <div style="display:none;" class="formControls col-xs-8 col-xs-offset-3" id="con3">
                <input type="password" name="password" id="password" placeholder="请输入新密码"
                       class="input-text size-L validate[required]">
                <input type="password" name="rPassword" id="rPassword" placeholder="请确认新密码"
                       class="input-text size-L validate[required]">
            </div>
        </div>
    </form>
    <div class="btns">
        <%--<button id="prevBtn">上一步</button>--%>
            <div class="row cl">

                    <button id="nextBtn" style="margin-left: 500px;">下一步</button>
                    <button id="sureBtn" style="display:none; margin-left: 500px;" >完成</button>

            </div>
    </div>
   <%-- <div class="info">index：<span id="index"></span></div>--%>
    <!--  style="display:none;"-->
</div>
<div class="footer">Copyright 星融金服</div>
<script type="text/javascript">
    var $step = $("#step");
    var $index = $("#index");

    $step.step({
        index: 0,
        time: 500,
        title: ["填写账号", "身份验证", "设置新密码", "完成"]
    });

    $index.text($step.getIndex());

    $("#prevBtn").on("click", function() {
        $step.prevStep();
        $index.text($step.getIndex());
    });



    $("#nextBtn").on("click",function() {
        var userName=$("#username").val();

        if($step.getIndex()=="0"){
            if (userName ==null){
                alert("输入的手机号不能为空!")
            }
            var reg=/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
            if (!reg.test(userName)){
                alert("请输入正确的手机号");
                return;
            }

            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/system/confirmUser.action?userName=" +userName,
                dataType: "json",
                success: function(data){
                    if(data.s == true){
                        $step.nextStep();
                        $index.text($step.getIndex());
                        $("#con1").css("display","none");
                        $("#con2").css("display","block");
                        $("#con3").css("display","none");
                        var phone=document.getElementById("phone");
                        phone.value=userName;

                    }else{
                        parent.layer.alert("你还不是我们的商户", {icon: 2,title:"系统提示"});
                    }

                }
            }) ;
        }

        if($step.getIndex()=="1"){
            var verify=$("#verify").val();
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/system/checkVerify.action?verifyCode=" +verify,
                dataType: "json",
                success: function(data){
                    if(data.s == true){
                        $step.nextStep();
                        $index.text($step.getIndex());
                        $("#con1").css("display","none");
                        $("#con2").css("display","none");
                        $("#con3").css("display","block");
                    }else{
                        parent.layer.alert("您输入的验证码不对", {icon: 2,title:"系统提示"});
                    }

                }
            }) ;



        }
        if($step.getIndex()=="2"){
            var password=$("#password").val();
            var rPassword=$("#rPassword").val();
            if (password!=rPassword){
                alert("两次输入的密码不一致");
                return;
            }
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/system/changePassword.action?userName="+userName+"&password="+password,
                dataType: "json",
                success: function(data){
                    if(data.s == true){
                        window.location.href="${context_root}/login.jsp";
                    }else{
                        parent.layer.alert("修改密码失败", {icon: 2,title:"系统提示"});
                    }

                }
            }) ;

        }
        if($step.getIndex()=="3"){
            $("#nextBtn").css("display","none");
            $("#sureBtn").css("display","inline");
            $step.nextStep();
            $index.text($step.getIndex());
        }
        var i = $step.getIndex();
        /* var div = $("form").eq(i);
        $("form").eq(i).child().css("display","block");
        $("form").not(div).child().css("display","none"); */

    });

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
            $("#check-form").validationEngine({scroll: false});

    });

    /*//点击验证码
    $(".jcaptcha-btn").click(function () {
        var img = $("#jcaptchaImage")
        var imageSrc = img.attr("src");
        if (imageSrc.indexOf("?") > 0) {
            imageSrc = imageSrc.substr(0, imageSrc.indexOf("?"));
        }
        imageSrc = imageSrc + "?" + new Date().getTime();
        img.attr("src", imageSrc);
    });*/

//60s倒计时
    var wait=60;
    function time(o) {
        if (wait == 0) {
            o.removeAttribute("disabled");
            o.value="免费获取验证码";
            wait = 60;
        } else {
            o.setAttribute("disabled", true);
            o.value="重新发送(" + wait + ")";
            wait--;
            setTimeout(function() {
                    time(o)
                },
                1000)
        }
    }

        $("#btn").on("click",function () {
            console.log(this)
            time(this);
            sendVery();
        });
        

    /*document.getElementById("btn").onclick=function(){time(this);}*/
    function sendVery() {
    $.ajax({
        type: "post",
        url: "${pageContext.request.contextPath}/system/sendVerify.action?userName="+$("#username").val(),
        dataType: "json",
        success: function(data){
            if(data.s == true){
                parent.layer.alert("手机验证码发送成功", {icon: 5,title:"系统提示"});
            }else{
                parent.layer.alert("修改密码失败", {icon: 2,title:"系统提示"});
            }
        }
    });
}
</script>
</body>
</html>