<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-sourceCode-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>渠道编号：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" required="required" class="input-text " value="" placeholder="" name="sourcecodeid">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>渠道名字：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" value="" placeholder="" name="sourceCodeName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>推广地址：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off"  placeholder=""  name="sourceCodeAddress">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>软件版本号：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" name="phoneSysVersion">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>下载地址：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" name="install">
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
$("#form-sourceCode-add").validate({
	/*rules:{
        userName:{
            required:true,
            minlength: 5,
            isSpace:true,
            remote: {
                url: "/system/checkNameUnique.action",
                type: "post",
                dataType: "text",
                data: {
                	name : function() {
                        return $.trim($("#userName").val());
                    }
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
		password:{
			required:true,
			isSpace:true,
			minlength: 6
		},
		repeatPassword:{
			required:true,
			isSpace:true,
			minlength: 6,
			equalTo: password
		},
		accountName:{
			required:true,
			isSpace:true,
		},
		email:{
			required:true,
			email:true
		},
		mobilePhone:{
			required:true,
			isPhone:true
		},
		roleId:{
			required:true,
		},
	},
	messages: {
        "userName": {
            remote: "该用户已经存在"
        }
    },*/
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		$.ajax({
			url:"${context_root}/system/saveSourceCode.action",
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