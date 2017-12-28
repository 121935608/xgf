<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-user-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账号：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="userName" name="userName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>初始密码：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="password" class="input-text" autocomplete="off" value="" placeholder="密码" id="password"
                       name="password">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="password" class="input-text" autocomplete="off" placeholder="确认新密码" id="repeatPassword"
                       name="repeatPassword">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="accountName" name="accountName">
            </div>
        </div>
        <%--<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="@" id="email" name="email">
            </div>
        </div>--%>
        <%--<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机号码：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="mobilePhone" name="mobilePhone">
            </div>
        </div>--%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <y:select id="roleId" name="roleId" codeGroup="${roles }" selectedValue="1"
                          cssClass="select" headerKey="" headerValue="--请选择--">
                </y:select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否启用：</label>
            <div class="formControls col-xs-7 col-sm-4 skin-minimal">
                <y:radio codeGroup="sys-locked" name="locked" selectedValue="0"/>
            </div>
        </div>


        <%--<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">描述：</label>
            <div class="formControls col-xs-8 col-sm-6">
                <textarea name="description" cols="" rows="" class="textarea" placeholder="说点什么...最少输入10个字符"
                          datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,200)"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
            </div>
        </div>--%>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>


<script type="text/javascript">
   // 只能输入英文
    jQuery.validator.addMethod("english", function (value, element) {
        var chrnum = /^([a-zA-Z]+)$/;
        return this.optional(element) || (chrnum.test(value));
    }, "只能输入字母");
    $("#form-user-add").validate({
        rules: {
            userName: {
                required: true,
                minlength: 5,
                isSpace: true,
                english: true,
                remote: {
                    url: "${context_root}/system/checkNameUnique.action",
                    type: "post",
                    dataType: "text",
                    data: {
                        name: function () {
                            return $.trim($("#userName").val());
                        }
                    },
                    dataFilter: function (data, type) {
                        if (data == "0") return true;
                        else return false;
                    }
                }
            },
            password: {
                required: true,
                isSpace: true,
                minlength: 6
            },
            repeatPassword: {
                required: true,
                isSpace: true,
                minlength: 6,
                equalTo: password
            },
            accountName: {
                required: true,
                isSpace: true,
            },
           /* email: {
                required: true,
                email: true
            },*/
           /* mobilePhone: {
                required: true,
                isPhone: true
            },*/
            roleId: {
                required: true,
            },
        },
        messages: {
            "userName": {
                remote: "该用户已经存在"
            }
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var index = parent.layer.load();
            $.ajax({
                url: "${context_root}/system/saveUser.action",
                type: 'post',
                async: true,
                cache: false,
                data: $(form).serialize(),
                dataType: "json",
                success: function (data) {
                    parent.layer.close(index);
                    if (data.s == true) {
                        index = parent.layer.getFrameIndex(window.name);
                        parent.layer.msg("保存成功,正在刷新数据请稍后……", {icon: 1, time: 1000, shade: [0.1, '#fff']}, function () {
                            window.parent.location.reload();
                        });
                    } else {
                        parent.layer.alert(data.m, {icon: 2, title: "系统提示"});
                    }
                },
            });
        }
    });
</script>
</body>
</html>