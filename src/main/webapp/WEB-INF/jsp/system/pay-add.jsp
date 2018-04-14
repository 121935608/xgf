<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-payMethod-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>支付方式编号：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="payCode" name="payCode">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>支付方式：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="payName" name="payName">
            </div>
        </div>
        <%--<div class="row cl">--%>
            <%--<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>操作者：</label>--%>
            <%--<div class="formControls col-xs-8 col-sm-4">--%>
                <%--<input type="text" class="input-text" value="" placeholder="" id="creator" name="creator">--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否启用：</label>
            <div class="formControls col-xs-7 col-sm-4 skin-minimal">
                <y:radio codeGroup="sys-role_status" name="status" selectedValue="1"/>
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

//    jQuery.validator.addMethod("checkRoleName", function (value, element) {
//        var chrnum =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
//        return this.optional(element) || (!chrnum.test(value));
//    }, "角色名不能为数字、特殊符号");
//    jQuery.validator.addMethod("checkNumber", function (value, element) {
//        var reg =/^[0-9]*$/ ;
//        return this.optional(element) || (!reg.test(value));
//    }, "角色名不能为数字、特殊符号");
//    jQuery.validator.addMethod("checkRoleKey", function (value, element) {
//        var chrnum =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
//        return this.optional(element) || (!chrnum.test(value));
//    }, "角色标识不能为数字、特殊符号");
//    jQuery.validator.addMethod("checkRoleNumber", function (value, element) {
//        var reg =/^[0-9]*$/ ;
//        return this.optional(element) || (!reg.test(value));
//    }, "角色标识不能为数字、特殊符号");
    $("#form-payMethod-add").validate({
        rules:{
            payCode:{
                required:true,
                isSpace:true,
            },
            payName:{
                required:true,
                isSpace:true
            }
//            creator:{
//                required:true,
//                isSpace:true,
//                checkRoleKey:true,
//                checkRoleNumber:true,
//            },
//            status:{
//                required:true,
//                isSpace:true,
//                checkRoleKey:true,
//                checkRoleNumber:true,
//            },
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            var index = parent.layer.load();
            $.ajax({
                url:"${context_root}/system/savePayMethod.action",
                type:'post',
                async:true ,
                cache:false ,
                data:$(form).serialize(),
                dataType:"json",
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
</body>
</html>