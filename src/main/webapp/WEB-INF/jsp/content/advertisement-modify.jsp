﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<%@page isELIgnored="false" %>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post"  class="form form-horizontal" id="form-advertisement-modify">
	<input type="hidden" class="input-text" id="advertisementId" name="advertisementId" value="${advertisement.advertisementId }">
	
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text"  placeholder="${advertisement.advertisementName }" id="advertisementName" name="advertisementName" value="${advertisement.advertisementName }">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>URL：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="url" class="input-text"  placeholder="${advertisement.advertisementUrl }" id="advertisementUrl" name="advertisementUrl" value="${advertisement.advertisementUrl }">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="status" name="status"
					codeGroup="${statusList}" selectedValue="${advertisement.status }"
					cssClass="select" headerKey="" headerValue="状态" >
			</y:select>
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;确定&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>


<script type="text/javascript">
$("#form-advertisement-modify").validate({
	rules:{
		/* advertisementName: {
            required: true,
            isSpace: true,
           remote: {
                url: "${context_root}/content/checkAdvertisementNameUnique.action",
                type: "post",
                dataType: "text",
                data: {
                    name: function () {
                        return $.trim($("#advertisementName").val());
                    }
                },
                dataFilter: function (data, type) {
                    if (data == "0") return true;
                    else return "该名称已存在";
                }
            }
        }, */
		
		advertisementUrl: {
            required: true,
            isSpace: true,
            maxlength:250
        },
        advertisementName: {
            required: true,
            isSpace: true,
            maxlength:20
        },
		status:{
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
			url:"${context_root}/content/saveAdvertisement.action", 
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