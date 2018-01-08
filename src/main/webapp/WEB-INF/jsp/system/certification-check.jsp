<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<style>
	.info-div{
		margin: 0px 20px;
	}
	.info-div>div{
		margin-top: 5px  !important;
	}
	h4{
		margin: 0px ;
		text-align: right;
	}
</style>
<body>
<article class="page-container"> 
	<form action="" method="post" class="form form-horizontal" id="form-certification-check"> 
		<div class="info-div">
			<div class="row cl"> 
				<div class="col-xs-3 col-sm-2">
					<h4>店铺信息</h4>
				</div>
			</div> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">店铺名称</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${store["storeName"]}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">店长</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${store["userName"]}</span> 
				</div>
			</div> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">身份证号码</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${store["idCard"]}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">联系方式</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${store["phone"]}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">所在区域</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${store["area"]}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">详细地址</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${store["address"]}</span>
				</div>
			</div>
		</div>
		<div class="info-div"  style="display:none">
			<div class="row cl"> 
				<div class="col-xs-3 col-sm-2">
					<h4>银行资料</h4>
				</div>
			</div> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">银行类型</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${bankaccount["bankType"]}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">开户行类型</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${bankaccount["accountType"]}</span>
				</div>
			</div> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">开户人</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${bankaccount["userName"]}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">经营类型</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${bankaccount["oprateType"]}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">银行卡号</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${bankaccount["cardNumber"]}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">证件类型</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${bankaccount["idType"]}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">证件号码</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${bankaccount["idCard"]}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">备注</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${bankaccount["remark"]}</span>
				</div>
			</div>
		</div>
		<div class="info-div">
			<div class="row cl"> 
				<div class="col-xs-3 col-sm-2">
					<h4>证件信息</h4>
				</div>
			</div> 
			<div class="row cl">
				<div class="col-xs-2 col-sm-2"> 
				</div>
				<div class="col-xs-2 col-sm-2">
					<img alt="ssss" src="">
					<p>身份证正面</p>
				</div>
				<div class="col-xs-2 col-sm-2">
					<img alt="ssss" src="">
					<p>身份证正面</p>
				</div>
				<div class="col-xs-2 col-sm-2">
					<img alt="ssss" src="">
					<p>身份证正面</p>
				</div>
				<div class="col-xs-2 col-sm-2">
					<img alt="ssss" src="">
					<p>身份证正面</p>
				</div>
			</div> 
			<div class="row cl">
				<div class="col-xs-2 col-sm-2"> 
				</div>
				<div class="col-xs-2 col-sm-2">
					<img alt="ssss" src="">
					<p>身份证正面</p>
				</div>
				<div class="col-xs-2 col-sm-2">
					<img alt="ssss" src="">
					<p>身份证正面</p>
				</div>
				<div class="col-xs-2 col-sm-2">
					<img alt="ssss" src="">
					<p>身份证正面</p>
				</div>
				<div class="col-xs-2 col-sm-2">
					<img alt="ssss" src="">
					<p>身份证正面</p>
				</div>
			</div>
		</div>
		  
		<div class="info-div">
			<input type="hidden" class="input-text" id="storeId" name="storeId" value="${store['storeId']}">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">审核结果</label>
				<div class="formControls col-xs-8 col-sm-4">
					<y:select id="process" name="process" codeGroup="${ispass}" selectedValue=""
								cssClass="select" headerKey="" headerValue="请选择"> 
					</y:select>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>督导员</label>
				
				<div class="formControls col-xs-8 col-sm-4">
						<y:select id="supervisorId" name="supervisorId" codeGroup="${supervisorList}" selectedValue=""
								cssClass="select" headerKey="" headerValue="请选择督导员">
						</y:select>
				</div> 
			</div> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">审核说明</label>
				<div class="formControls col-xs-8 col-sm-6">
					<textarea name="remark" cols="" rows="" class="textarea" placeholder="一般来说是写拒绝原因" datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,200)"></textarea>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</div>
		
		
	</form>
</article>

<script type="text/javascript">
$("#form-certification-check").validate({
	rules:{
		supervisorId:{
			required:true,
			isSpace:true,
		},
		process:{
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
			url:"${context_url}/merchant/checkCertificationSubmit.action", 
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