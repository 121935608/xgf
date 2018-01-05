<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="督导员管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-setting-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>星支付结算方式：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" placeholder="" id="xzfMethod" name="xzfMethod" value="${setting.xzfMethod}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>星支付代付费率：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" style="width: 525px;" class="input-text" placeholder="" autocomplete="off" id="xzfRate" name="xzfRate" value="${setting.xzfRate}">%
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>订单账期费率：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" style="width: 525px;" class="input-text" placeholder="" id="orderRate" name="orderRate" value="${setting.orderRate}">%
			</div>
		</div>

		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
    $("#form-setting-add").validate({
        rules:{
            xzfMethod:{
                required:true,
                isSpace:true,
            },
            xzfRate:{
                required:true,
                isSpace:true,
            },
            orderRate:{
                required:true,
                isSpace:true,
            },
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            var index = parent.layer.load();
            $.ajax({
                url:"${context_url}/system/settingAdd.action",
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