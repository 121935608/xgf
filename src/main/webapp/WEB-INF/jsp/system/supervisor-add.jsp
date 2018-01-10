<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-role-modify">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" placeholder="姓名" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>联系电话：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text " placeholder="联系电话" id="phone" name="phone" >
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所在区域:</label>
			<div data-toggle="distpicker">
				<select data-province="---- 选择省 ----" id="province"></select>
				<select data-city="---- 选择市 ----" id="city"></select>
				<select data-district="---- 选择区 ----" id="district"></select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-7 col-sm-4 skin-minimal">
				<y:radio codeGroup="sys-locked" name="status" selectedValue="0"/>
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
$("#form-role-modify").validate({
	rules:{
        name:{
			required:true,
			isSpace:true,
		},
        phone:{
			required:true,
			isSpace:true,
		},
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var reg=" \"^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$\";"
		var phone=$("#phone").val();
		if (!reg.test(phone)){
		    alert("不是正确的手机号");
		    return;
		}
        var province=$("#province option:selected").val();
        var city=$("#city option:selected").val()
        var district=$("#district option:selected").val();
        var area=province+city+district;
		var index = parent.layer.load();
		$.ajax({
			url:"${context_url}/system/supervisorAdd.action?area="+area,
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
<script type="text/javascript" src="${context_url}/uiloader/static/dist/distpicker.data.js"></script>
<script type="text/javascript" src="${context_url}/uiloader/static/dist/distpicker.js"></script>
</body>
</html>