<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<style>
	#table{margin-left：200px;}
	tr td{line-height:40px;}
	input{width:150px;height:20px;}
	textarea{width:62.5%;height:200px;}
</style>
<center>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-supplierApply-modify">
		<input type="hidden" class="input-text" id="applyId" name="applyId" value="${supplierApply.applyId }">
		<div id="table">
			<table>
				<tr>
					<td>申请人</td>
					<td>${supplierApply.applyName }</td>
				</tr>
				<tr>
					<td>联系方式</td>
					<td>${supplierApply.applyPhone }</td>
				</tr>
				<tr>
					<td>公司名称</td>
					<td>${supplierApply.compName }</td>
				</tr>
				<tr>
					<td>公司地址</td>
					<td>${supplierApply.compAddr }${supplierApply.detailAddr }</td>
				</tr>
				<tr>
					<td>邮箱</td>
					<td>${supplierApply.email }</td>
				</tr>
				<tr>
					<td>备注</td>
					<td><textarea style="width:50%;" name="remark" id="remark" cols="" rows="" class="textarea" datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,250)">${supplierApply.remark }</textarea>
                    </td>
				</tr>
			</table>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>
</center>
<script type="text/javascript">
$("#form-supplierApply-modify").validate({
	rules:{
		remark:{
			maxlength:250
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		$.ajax({
			url:"${context_root}/merchant/modifySupplierApply.action", 
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
					parent.layer.alert("操作失败！" , {icon: 2,title:"系统提示"});
				}
			},
		});
	}
});
</script> 
</body>
</html>