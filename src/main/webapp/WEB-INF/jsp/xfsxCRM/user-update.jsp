<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-supervisor-modify">
        <input type="hidden" class="input-text" id="supervisorId" name="supervisorId" value="${Supervisor.supervisorId }">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="姓名" id="name" name="name" value="${Supervisor.name }">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登录账号：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="姓名" id="crmLogin" name="crmLogin" value="${Supervisor.crmLogin }">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登录密码：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="姓名" id="crmPwd" name="crmPwd" value="${Supervisor.crmPwd }">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图片：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <img src="${imgPath}${Supervisor.headPortrait}" width="80px" height="80px" class="image" id="image">
                <input type="file" class="picture" id="picture" accept="image/*" name="picture" onchange="changImg(event)">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">所属部门：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <y:select id="deptId" name="deptId" codeGroup="${deptList}" selectedValue="${Supervisor.deptId }"
                          cssClass="select" headerKey="" headerValue="请选择">
                </y:select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>联系电话：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="联系电话" id="phone" name="phone" value="${Supervisor.phone }"
                >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">*<span class="c-red"></span>所在区域:</label>
            <div data-toggle="distpicker">
                <select id="province"></select>
                <select id="city"></select>
                <select id="district"></select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <y:select id="roleId" name="roleId" codeGroup="${roles }" selectedValue="${Supervisor.roleId }"
                          cssClass="select" headerKey="" headerValue="--请选择--">
                </y:select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
            <div class="formControls col-xs-7 col-sm-4 skin-minimal">
                <y:radio codeGroup="sys-role_status" name="status" selectedValue="${Supervisor.status }"/>
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
    //校验上传文件是否为图片格式
    function changImg(e){
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
                $("#image").attr("src",e.target.result);
            }
        }
    }
    jQuery.validator.addMethod("chineseName", function (value, element) {
        var chrnum =/^[\u4E00-\u9FA5]{2,4}$/;
        return this.optional(element) || (chrnum.test(value));
    }, "请输入中文");

    jQuery.validator.addMethod("checkPhone", function (value, element) {
        var chrnum =/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        return this.optional(element) || (chrnum.test(value));
    }, "请输入正确的手机号");
    $("#form-supervisor-modify").validate({
        rules:{
            name: {
                required: true,
                isSpace: true,
                chineseName:true,
            },
            crmLogin: {
                required: true,
                isSpace: true,
            },
            crmPwd: {
                required: true,
                isSpace: true,
            },
            phone:{
                required:true,
                isSpace:true,
                checkPhone:true,
                remote: {
                    url: "${context_root}/system/checkPhoneUnique.action?supervisorId=${Supervisor.supervisorId}",
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
            },
            roleKey:{
                required:true,
                isSpace:true,
            },
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            var province=$("#province option:selected").val();
            var city=$("#city option:selected").val()
            var district=$("#district option:selected").val();
            var area=province+city+district;
            if (area ===null ||area===''|| area===undefined ){
                alert("请选择区域");
                return;
            }
            var index = parent.layer.load();
            var formData = new FormData($('#form-supervisor-modify')[0]);
            $.ajax({
                url:"${context_url}/crmUser/updateSpervistorIDView.action?area="+area,
                type:'post',
                data:formData,
                mimeType: "multipart/form-data",
                contentType: false,
                cache:false,
                processData: false,
                success:function(data){
                    parent.layer.close(index);
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