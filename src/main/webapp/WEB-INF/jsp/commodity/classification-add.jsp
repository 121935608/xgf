﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader />
<body>
	<article class="page-container">
		<form action="" method="post" class="form form-horizontal"
			id="form-classification-add">

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>目录：</label>
				<div class="formControls col-xs-8 col-sm-4">

					<select>
						<option value="rootDirectory">根目录</option>
						<option value="fruit">水果</option>
						<option value="vegetables">蔬菜</option>
						<option value="aquatic">水产</option>
					</select>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>分类名称：</label>
				<div class="formControls col-xs-8 col-sm-4">
					<input type="text" class="input-text" value="" placeholder=""
						id="categoryName" name="categoryName">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>图片：</label>
				<div class="formControls col-xs-8 col-sm-4">
					<input type="file" id="img" accept="image/*" name="picture" onchange="changImg(event)">
				</div>
			</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" id="save"
						value="&nbsp;&nbsp;确定&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</article>


	<script type="text/javascript">
	//校验上传文件是否为图片格式
	 function changImg(e){
	     for (var i = 0; i < e.target.files.length; i++) {
	         var file = e.target.files.item(i);
	         if (!(/^image\/.*$/i.test(file.type))) {
	             continue; //不是图片 就跳出这一次循环
	         }
	         var imagSize =  document.getElementById("img").files[0].size;
	     	if(imagSize>1024*1024*3){
	             alert("图片最大为3M！");
	             document.getElementById("img").value="";
	             return;
	         }
	         //实例化FileReader API
	         var freader = new FileReader();
	         freader.readAsDataURL(file);
	     }
	 }
		$("#form-classification-add")
				.validate(
						{
							rules : {
								categoryName : {
									required : true,
									isSpace : true,
									maxlength:20
								},
							},
							onkeyup : false,
							focusCleanup : true,
							success : "valid",
							submitHandler : function(form) {
								var index = parent.layer.load();
								$
										.ajax({
											url : "${context_root}/commodity/saveClassification.action",
											type : 'post',
											async : true,
											cache : false,
											data : $(form).serialize(),
											dataType : "json",
											success : function(data) {
												parent.layer.close(index);
												if (data.s == true) {
													index = parent.layer
															.getFrameIndex(window.name);
													parent.layer
															.msg(
																	"保存成功,正在刷新数据请稍后……",
																	{
																		icon : 1,
																		time : 1000,
																		shade : [
																				0.1,
																				'#fff' ]
																	},
																	function() {
																		window.parent.location
																				.reload();
																	});
												} else {
													parent.layer.alert(data.m,
															{
																icon : 2,
																title : "系统提示"
															});
												}
											},
										});
							}
						});
	</script>
</body>
</html>