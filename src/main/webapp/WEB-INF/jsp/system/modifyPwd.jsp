<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<link rel="stylesheet" type="text/css" href="${context_url}/uiloader/static/jqueryStep/css/jquery.step.css" />
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
<div class="main">
    <div id="step"></div>
    <form action="" method="post" class="form form-horizontal" id="form-userName">
        <div class="row cl">
        <div id="con1" class="formControls col-xs-8 col-xs-offset-3">
            <input type="text" name="username" id="username" placeholder="注册时手机号"
                   class="input-text size-L ">
            <input class="input-text size-L " type="text"
                   placeholder="验证码" id="jcaptchaCode" name="jcaptchaCode" style="width:150px;">
            <img id="jcaptchaImage" src="jcaptcha.jpg"> <a class="jcaptcha-btn">看不清，换一张</a>
        </div>
        </div>
    </form>
    <form id="form-phone">
        <div class="row cl">
            <div id="con2" style="display:none;" class="formControls col-xs-8 col-xs-offset-3">
                <input type="text" name="phone" id="phone">
                <input type="text" name="verify" id="verify" placeholder="填写手机验证码"
                       class="input-text size-L validate[required]">
            </div>
        </div>
    </form>
    <form id="form-password">
        <div class="row cl">
            <div style="display:none;" class="formControls col-xs-8 col-xs-offset-3" id="con3">
                <input type="password" name="password" placeholder="请输入新密码"
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
    <div class="info">index：<span id="index"></span></div>
    <!--  style="display:none;"-->
</div>

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

    $("#nextBtn").on("click", function() {
        if($step.getIndex()=="0"){
            alert(111);
            $("#form-userName").validate({
                rules:{
                    userName:{
                        required:true,
                        isSpace:true,
                    },
                },
                onkeyup:false,
                focusCleanup:true,
                success:"valid",
                submitHandler:function(form){
                    var userName=$("#username").val();
                    alert(userName);
                    $.ajax({
                        url:"${context_root}/system/confirmUser.action?userName=" + userName,
                        type:'post',
                        async:true ,
                        cache:false ,
                        dataType:"json",
                        success:function(data){
                            if(data.s == true){
                                var phone=document.getElementById("phone");
                                phone.value=username;
                                $step.nextStep();
                                $index.text($step.getIndex());
                            }else{
                                parent.layer.alert("你还不是我们的商户", {icon: 2,title:"系统提示"});
                            }
                        },
                    });
                }
            });
        alert(222);
        }
        if($step.getIndex()=="1"){
            $("#con1").css("display","none");
            $("#con2").css("display","block");
            $("#con3").css("display","none");
            $step.nextStep();
            $index.text($step.getIndex());
        }
        if($step.getIndex()=="2"){
            $("#con1").css("display","none");
            $("#con2").css("display","none");
            $("#con3").css("display","block");
            $step.nextStep();
            $index.text($step.getIndex());
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

    });
</script>
</body>
</html>