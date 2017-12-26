<%@ page contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/uiloader/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/uiloader/lib/zTree/v3/css/demo.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/uiloader/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>
<body>

<ul id="menuTree" class="ztree" style="height: 320px;width: 218px;margin-top: 0px"></ul>
<input class="btn btn-secondary-outline radius size-M" id="perms" type="button" value="提交" style="width: 230px">
<script type="text/javascript">
var setting = {
	view: {
		selectedMulti: false
	},
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	},
};
var zTree;
$(document).ready(function(){
	var zTree = $("#menuTree");
	var tree = '${zTreeNodes}';
	var zTreeNodes = eval(tree);
	zTree = $.fn.zTree.init(zTree, setting, zTreeNodes);
	
	$("#perms").bind("click" , function(){
		var nodes = zTree.getCheckedNodes();
		var tmpNode;
		var perms = "";
		var roleId = '${roleId}';
		
		for(var i=0; i<nodes.length; i++){
			tmpNode = nodes[i];
			if(i!=nodes.length-1){
				perms += tmpNode.id+",";
			}else{
				perms += tmpNode.id;
			}
		}
		
		$.ajax({
			url:" ${pageContext.request.contextPath}/system/savePermissions.action" ,
			data : {'roleId':roleId,'perms':perms} ,
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				if(data.s == true){
					parent.layer.msg("删除成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
						window.parent.location.reload();
					});
				}else{
					parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
				}
			},
		});
		
	});
});
</script>
</body>
</html>