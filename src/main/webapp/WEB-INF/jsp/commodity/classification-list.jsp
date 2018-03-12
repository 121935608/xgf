<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<link rel="stylesheet" href="${context_root}/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
<script type="text/javascript" src="${context_root}/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<body class="pos-r">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 分类管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pos-a" style="width:150px;left:0;height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5">
	<ul id="classificationTree" class="ztree">
	</ul>
</div>

<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-classification-save" style="margin-left:130px;">
	    <input type="hidden" value="" id="categoryId" name="categoryId">
	     <div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>目录：</label>
			<div class="formControls col-xs-8 col-sm-4">
				 <y:select id="parentId" name="parentId" codeGroup="${parents}" selectedValue=""
					cssClass="select" headerKey="0" headerValue="一级目录">
				</y:select>
			</div>
		</div>  
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>分类：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="分类名称" id="categoryName" name="categoryName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">图片：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="file"  id="picture" accept="image/*" name="picture" onchange="changImg(event)">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>排序：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" value="" id="sort" name="sort">
			</div>
		</div>
		 <%-- <div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="status" name="status"
					codeGroup="${statusList}" selectedValue=""
					cssClass="select" headerKey="0" headerValue="启用">
			</y:select>
			</div>
		</div>   --%>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>
<SCRIPT type="text/javascript">
	  var setting = {
		view: {
			selectedMulti: false
		},
		edit: {
			enable: true,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeRemove: beforeRemove,
			onClick: onClick
		}
		
	}; 
	  
		//删除菜单
		function showRemoveBtn(treeId, treeNode) {
			return true;
		}
		//重命名菜单
		function showRenameBtn(treeId, treeNode) {
			return false;
		}
	
		//删除菜单
		function beforeRemove(treeId, treeNode) {
			parent.layer.confirm("确认删除菜单【 " + treeNode.name + "】吗？",function(index){
				$.ajax({
						url:"${context_root}/commodity/delMenu.action" ,
						data : {'categoryId':treeNode.id,'parentId':treeNode.pId} ,
						type:'post',
						async:true ,
						cache:false ,
						dataType:"json",
						success:function(data){
							if(data.s == true){
								parent.layer.msg("删除成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
								    location.replace(location.href);
								});
							}else{
								parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
							}
						},
				});
			});
			return false;
		}
	
	//单击菜单触发
	 function onClick(e,treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("classificationTree");
		zTree.expandNode(treeNode);
		$.post("${context_root}/commodity/getMenu.action?categoryId=" + treeNode.id , function(data){
			$("#categoryId").val(data.parentId);
			$("#categoryId").val(data.categoryId);
			$("#categoryName").val(data.categoryName);
			$("#img").val(data.img);
			$("#status").val(data.status);
			$("#sort").val(data.sort);
			
			$("input[name=available][value='"+data.available+"']").iCheck('check');
			$("#parentId option").removeAttr("selected");
			checkOption(data.parentId);
			$("select option[value='"+data.parentId+"']").attr("selected", "selected"); 
		})
	}
	
	 $(document).ready(function(){
			var t = $("#classificationTree");
			$.post("${context_root}/commodity/classificationList.action", function(data){
				t = $.fn.zTree.init(t, setting, data);
				t.expandAll(true);
			});
		});

	//校验上传文件是否为图片格式
	 function changImg(e){
	     for (var i = 0; i < e.target.files.length; i++) {
	         var file = e.target.files.item(i);
	         if (!(/^image\/.*$/i.test(file.type))) {
	             continue; //不是图片 就跳出这一次循环
	         }
	         var imagSize =  document.getElementById("picture").files[0].size;
	     	if(imagSize>1024*1024*3){
	             alert("图片最大为3M！");
	             document.getElementById("picture").value="";
	             return;
	         }
	         //实例化FileReader API
	         var freader = new FileReader();
	         freader.readAsDataURL(file);
	     }
	 }
	 $("#form-classification-save").validate({
		rules:{
			parentId:{
				required:true,
			},
			categoryName:{
				isSpace:true,
				required:true,
				maxlength:20
			},
			status:{
				isSpace:true,
				required:true
			}, 
			sort:{
				isSpace:true,
				required:true
			}, 
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			var index = parent.layer.load();
			var formData = new FormData($('#form-classification-save')[0]);
			$.ajax({
				url:"${context_root}/commodity/saveClassification.action", 
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
							window.location.reload();
						}) ;
					}else{
						parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
					}
				},
			});
		}
	}); 

	var selectOption = $("#parentId").html();
	function checkOption(parentId)
	{
		var selectHeader = "<option value='0'>一级菜单</option>";
		if(parentId == '0')
		{
			$("#parentId").find("option").remove();
			$("#parentId").append(selectHeader);
		}
		else
		{
			$("#parentId").find("option").remove();
			$("#parentId").append(selectOption);
			$("select option[value='0']").remove();
		}
	} 
	
	var selectOption2 = $("#status").html();
	function checkOption2(status)
	{
		var selectHeader = "<option value='0'>启用</option>";
		if(status == '1')
		{
			$("#status").find("option").remove();
			$("#status").append(selectHeader);
		}
		else
		{
			$("#status").find("option").remove();
			$("#status").append(selectOption);
			$("select option[value='0']").remove();
		}
	} 
	 
</SCRIPT> 
</body>
</html>