<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="菜单管理"/>

<link rel="stylesheet" href="${context_root}/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
<script type="text/javascript" src="${context_root}/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<body class="pos-r">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 菜单管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pos-a" style="width:150px;left:0;height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5">
	<ul id="menuTree" class="ztree">
	</ul>
</div>

<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-menu-save" style="margin-left:130px;">
	    <input type="hidden" value="" id="permsId" name="permsId">
	    <input type="hidden" value="1" id="permsType" name="permsType">
	    <div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上级菜单：</label>
			<div class="formControls col-xs-8 col-sm-4">
				 <y:select id="parentId" name="parentId" codeGroup="${parents}" selectedValue=""
					cssClass="select" headerKey="0" headerValue="一级菜单">
				</y:select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单名称：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="名称" id="permsName" name="permsName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单连接：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" value="" placeholder="URL" id="permsUrl" name="permsUrl">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单顺序：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" value="" placeholder="顺序" id="permsLevel" name="permsLevel">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">菜单图标：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off"  placeholder="图标" id="permsIcon" name="permsIcon">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限编码：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="permsKey" name="permsKey">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否启用：</label>
			<div class="formControls col-xs-7 col-sm-4 skin-minimal">
				<y:radio codeGroup="sys-available" name="available" selectedValue="0"/>
			</div>
		</div>
		
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">描述：</label>
			<div class="formControls col-xs-8 col-sm-6">
				<textarea name="description" id="description" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,200)"></textarea>
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

	function showRemoveBtn(treeId, treeNode) {
		return true;
	}
	
	function showRenameBtn(treeId, treeNode) {
		return false;
	}

	//删除菜单
	function beforeRemove(treeId, treeNode) {
		parent.layer.confirm("确认删除菜单【 " + treeNode.name + "】吗？",function(index){
			$.ajax({
					url:"${context_root}/system/delMenu.action" ,
					data : {'permsId':treeNode.id,'parentId':treeNode.pId} ,
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
		var zTree = $.fn.zTree.getZTreeObj("menuTree");
		zTree.expandNode(treeNode);
		$.post("${context_root}/system/getMenu.action?permsId=" + treeNode.id , function(data){
			$("#permsId").val(data.permsType);
			$("#permsId").val(data.permsId);
			$("#permsName").val(data.permsName);
			$("#permsUrl").val(data.permsUrl);
			$("#permsLevel").val(data.permsLevel);
			$("#permsIcon").val(data.permsIcon);
			$("#permsKey").val(data.permsKey);
			$("#description").val(data.description);
			$("input[name=available][value='"+data.available+"']").iCheck('check');
			$("#parentId option").removeAttr("selected");
			checkOption(data.permsType);
			$("select option[value='"+data.parentId+"']").attr("selected", "selected");
		})
	}
	
	$("#form-menu-save").validate({
		rules:{
			parentId:{
				required:true,
			},
			permsName:{
				isSpace:true,
				required:true
			},
			permsUrl:{
				isSpace:true,
				required:true
			},
			permsLevel:{
				isSpace:true,
				required:true
			},
			permsKey:{
				isSpace:true,
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			var index = parent.layer.load();
			$.ajax({
				url:"${context_root}/system/saveMenu.action", 
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
							location.replace(location.href);
						}) ;
					}else{
						parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
					}
				},
			});
		}
	});
	
	$(document).ready(function(){
		var t = $("#menuTree");
		$.post("${context_root}/system/menuList.action", function(data){
			t = $.fn.zTree.init(t, setting, data);
			t.expandAll(true);
		});
	});
	
	$("#permsIcon").bind("click" , function(){
		layer_show("图标选择","${context_root}/system/menuIcon.action",800,500);
	});
	
	var selectOption = $("#parentId").html();
	function checkOption(permsType)
	{
		var selectHeader = "<option value='0'>一级菜单</option>";
		if(permsType == '0')
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
	
</SCRIPT>
</body>
</html>