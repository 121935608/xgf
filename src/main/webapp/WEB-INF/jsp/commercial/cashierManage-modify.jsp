<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">

	<form action="" method="post"  class="form form-horizontal" id="form-cashierManage-modify">
	<input type="hidden" value="${cash.cashierId }" id="cashierId" name="cashierId" >
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账号：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${cash.cashierName }" placeholder="" id="cashierName" name="cashierName">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${cash.name }" placeholder="" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="password" class="input-text" value="......" placeholder="" id="password" name="password">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="password" class="input-text" value="......" placeholder="" id="confirmPassword" name="confirmPassword">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<select id="status" name="status" style="width:220px;height:33px;">
		       		<option value=1 <c:if test="${cash.status eq 1}">selected</c:if>>启用</option>
		       		<option value=-1 <c:if test="${cash.status eq -1}">selected</c:if>>停用</option>
		       </select>
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
$("#form-cashierManage-modify").validate({
	rules:{		
		cashierName: {
	            required: true,
	            isSpace: true,
	           remote: {
	                url: "${context_root}/commercial/checkCashierManageNameUnique.action",
	                type: "post",
	                dataType: "text",
	                data: {
	                	cashierName: function () {
	                        return $.trim($("#cashierName").val());
	                    },
	                    cashierId: function () {
	                        return $.trim($("#cashierId").val());
	                    }
	                },
	                dataFilter: function (data, type) {
	                    if (data == 0) return true;
	                    else return "该名称已存在";
	                }
	            }
	        }, 
	        password:{
				required:true,
				isSpace:true
			},
			name:{
				required:true,
				isSpace:true,
				maxlength:20
			},
			confirmPassword: {
	            required: true,
	            isSpace: true,
	            equalTo: password
	        },
		status:{
			required:true,
			isSpace:true,
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		var formData = new FormData($('#form-cashierManage-modify')[0]);
		$.ajax({
			url:"${context_root}/commercial/saveCashierManage.action", 
			type:'post',
			data:formData,
			mimeType: "multipart/form-data",
			contentType: false,
			cache:false,
			processData: false,			
			success:function(data){
				var data = JSON.parse(data);
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