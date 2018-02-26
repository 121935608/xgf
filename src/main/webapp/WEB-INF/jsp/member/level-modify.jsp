<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-level-edit">
		<div class="row cl">
			<div class="formControls col-xs-8 col-sm-4">
				<input type="hidden" class="input-text" value="${Level.levelId}" placeholder="" id="levelId" name="levelId">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>会员等级：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="levelNo" name="levelNo"
					codeGroup="${level}" selectedValue="${Level.levelNo}"
					cssClass="select" headerKey="" headerValue="--请选择--">
			</y:select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>会员折扣：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" autocomplete="off" value="${Level.discount}" placeholder="" name="discount" id="discount">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>升级积分：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" value="${Level.score}" placeholder="" id="score" name="score">
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
$("#form-level-edit").validate({
	rules:{
		levelNo:{
			required:true,
		},
		discount:{
			required:true,
			min:0,
			max:1
		},
        score:{
			required:true,
			min:0
		},
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		$.ajax({
			url:"${context_root}/member/saveLevel.action",
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