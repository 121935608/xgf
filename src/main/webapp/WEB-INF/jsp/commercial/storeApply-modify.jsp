<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<style>
	#table{margin-left：200px;}
	tr td{line-height:40px;}
	textarea{width:62.5%;height:200px;}
</style>
<center>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-storeApply-modify">
		<input type="hidden" class="input-text" id="applyId" name="applyId" value="${applyId }">
		<div id="table">
			<table>
				<tr>
					<td><span style="font-size:14px;font-weight:bold;">处理结果:</span></td>
					<td><textarea style="width:50%;" name="dealResult" id="dealResult" autofocus="autofocus" placeholder="填写您的处理结果" cols="" rows="" class="textarea" datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,250)"></textarea>
                    </td>
				</tr>
			</table>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" style="margin-right:5%;">
				<input class="btn btn-primary radius" type="button" onclick="cancelForm()" id="cancel" value="&nbsp;&nbsp;取消&nbsp;&nbsp;" style="margin-right:40%;">
			</div>
		</div>
	</form>
</article>
</center>
<script type="text/javascript">
function cancelForm(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
$("#form-storeApply-modify").validate({
	rules:{
		dealResult:{
			required: true,
			maxlength:250
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		$.ajax({
			url:"${context_root}/merchant/modifyStoreApply.action", 
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