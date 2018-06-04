<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-supervisor-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="姓名" id="name" name="name">
            </div>
        </div>
        <div class="row cl" style="display:none;">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="市的编号" id="cityCode" name="cityCode">
                <input type="text" class="input-text" placeholder="省的编号" id="provinceCode" name="provinceCode">
                <input type="text" class="input-text" placeholder="地址" id="area" name="area">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登录账号：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="登录账号" id="crmLogin" name="crmLogin">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登录密码：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="登录密码" id="crmPwd" name="crmPwd">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图片：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="file" onchange="pic(event)" id="picture" name="picture" accept="image/*">
                <img alt="" id="myImg" src="" height="100px",width="100px">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">所属部门：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <y:select id="deptId" name="deptId" codeGroup="${deptList}" selectedValue="请选择"
                          cssClass="select" headerKey="" headerValue="请选择">
                </y:select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>联系电话：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text " placeholder="联系电话" id="phone" name="phone" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所在区域:</label>
            <div data-toggle="distpicker">
                <select data-province="---- 选择省 ----" id="province" name="province"></select>
                <select data-city="---- 选择市 ----" id="city" name="city"></select>
                <select data-district="---- 选择区 ----" id="district" name="county"></select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">住所具体位置：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text " placeholder="具体位置" id="address" name="address" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <y:select id="roleId" name="roleId" codeGroup="${roles }" selectedValue="1"
                          cssClass="select" headerKey="" headerValue="--请选择--">
                </y:select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
            <div class="formControls col-xs-7 col-sm-4 skin-minimal">
                <y:radio codeGroup="sys-locked" name="status" selectedValue="0"/>
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<script type="text/javascript">
    function pic(e) {
        for (var i = 0; i < e.target.files.length; i++) {
            var file = e.target.files.item(i);
            if (!(/^image\/.*$/i.test(file.type))) {
                continue; //不是图片 就跳出这一次循环
            }
            var imagSize =  document.getElementById("picture").files[0].size;
            if(imagSize>1024*1024*3){
                alert("图片最大为3M！");
                document.getElementById("picture").value="";
                return;
            }
            //实例化FileReader API
            var freader = new FileReader();
            freader.readAsDataURL(file);
            freader.onload = function(e) {
                $("#myImg").attr("src",e.target.result);
            }
        }
    }
    $(function(){
        $("#city").on("input",function(){
            var city = $("#city option:selected");
            $("#cityCode").val(city.attr("data-code"));
        })
        $("#city").on("input",function(){
            var province = $("#province option:selected");
            $("#provinceCode").val(province.attr("data-code"));
        })
        $("#province,#city,#district").on("input",function(){
            var province=$("#province option:selected").val();
            var city=$("#city option:selected").val()
            var district=$("#district option:selected").val();
            $("#area").val(province+city+district);
        })
    });
    jQuery.validator.addMethod("chineseName", function (value, element) {
        var chrnum =/^[\u4E00-\u9FA5]{2,4}$/;
        return this.optional(element) || (chrnum.test(value));
    }, "请输入中文");
    jQuery.validator.addMethod("checkPhone", function (value, element) {
        var chrnum =/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
        return this.optional(element) || (chrnum.test(value));
    }, "请输入正确的手机号");
    $("#form-supervisor-add").validate({
        rules:{
            name:{
                required:true,
                isSpace:true
            },
            crmLogin: {
                required: true,
                isSpace: true,
                remote: {
                    url: "${context_root}/crmUser/checkCrmLoginView.action",
                    type: "post",
                    dataType: "text",
                    data: {
                        name: function () {
                            return $.trim($("#crmLogin").val());
                        }
                    },
                    dataFilter: function (data, type) {
                        if (data == "0") return true;
                        else return "用户名已存在";
                    }
                }
            },
            crmPwd: {
                required: true,
                isSpace: true
            },
            phone:{
                required:true,
                isSpace:true,
                checkPhone:true,
                remote: {
                    url: "${context_root}/system/checkPhoneUnique.action",
                    type: "post",
                    dataType: "text",
                    data: {
                        name: function () {
                            return $.trim($("#phone").val());
                        }
                    },
                    dataFilter: function (data, type) {
                        if (data == "0") return true;
                        else return "已存在该电话号的督导员";
                    }
                }
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            /*var reg=/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
            var phone=$("#phone").val();
            if (!reg.test(phone)){
                alert("请输入正确的手机号");
                return;
            }*/
            if ($("#area").val() ===null){
                alert("请选择区域");
                return;
            }
            alert("改变了");
            var index = parent.layer.load();
            var formData = new FormData($('#form-supervisor-add')[0]);
            $.ajax({
                url:"${context_url}/crmUser/toAddSpervistorInfoView.action",
                type:'post',
                data:formData,
                mimeType: "multipart/form-data",
                contentType: false,
                cache:false,
                processData: false,
                success:function(data){
                    parent.layer.close(index);
                    var data = JSON.parse(data);
                    if(data.s == true){
                        index = parent.layer.getFrameIndex(window.name);
                        parent.layer.msg("保存成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
                            window.parent.location.reload();
                        }) ;
                    }else{
                        parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
                    }
                },
            });
        }
    });
</script>
<script type="text/javascript" src="${context_url}/uiloader/static/dist/distpicker.data.js"></script>
<script type="text/javascript" src="${context_url}/uiloader/static/dist/distpicker.js"></script>
</body>
</html>