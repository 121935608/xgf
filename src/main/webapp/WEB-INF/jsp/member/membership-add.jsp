<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<div class="formControls col-xs-8 col-sm-4">
				<input type="hidden" class="input-text" value="${storeId}" placeholder="" id="storeId" name="storeId">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>会员号：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" name="memberNo" id="memberNo">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" value="" placeholder="" name="name" id="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>有效期至：</label>
			<div class="formControls col-xs-8 col-sm-4">
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'timeLimi\')||\'%y-%M-%d\'}'})" id="timeLimi"
			   class="input-text Wdate">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>会员积分：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" value="" placeholder="" id="score" name="score">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>会员等级：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<span class="select-box" style="width: 120px;">
				   <select name="level" id="level" class="select" autocomplete="off">
					   <option value="">请选择会员等级</option>
					   <option value="A">A</option>
					   <option value="B">B</option>
					   <option value="C">C</option>
					   <option value="D">D</option>
					   <option value="E">E</option>
				   </select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>qq：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="qq" name="qq">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="email" class="input-text" value="" placeholder="@" id="email" name="email">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>住址：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="address" name="address">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-6">
				<textarea name="remark" cols="" rows="" class="textarea"  placeholder="" datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,200)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
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

$("#form-member-add").validate({
	rules:{
		memberNo: {
            required: true,
            isSpace: true,
            isPhone:true,
           remote: {
                url: "${context_root}/member/checkNameUnique.action",
                type: "post",
                dataType: "text",
                data: {
                	memberNo: function () {
                        return $.trim($("#memberNo").val());
                    }
                },
                dataFilter: function (data, type) {
                    if (data == 0) return true;
                    else return "该名称已存在";
                }
            }
        }, 
        name:{
			required:true,
			isSpace:true,
		},
        timeLimi:{
			required:true,
		},
        score:{
			required:true,
			min:0
		},
        address:{
			required:true,
		},
        level:{
            required:true,
        },
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		var timeLimi = $("#timeLimi").val();
		$.ajax({
			url:"${context_root}/member/saveMember.action?timeLimi="+timeLimi,
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