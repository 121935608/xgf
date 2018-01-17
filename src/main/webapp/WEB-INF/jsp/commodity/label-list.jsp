<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理" />
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		商品管理 <span class="c-gray en">&gt;</span> 标签管理 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">


		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;"
				onclick="label_add('添加标签','${context_root}/commodity/toLabelAdd.action','','610')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加标签</a></span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="5%">序号</th>
						<th width="10%">标签名称</th>
						<th width="10%">图片</th>
						<th width="10%">状态</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		var pageTable;
		$(document)
				.ready(
						function() {
							var aoColumns = [
									{
										"sDefaultContent" : "序号",
										"bSortable" : false,
										"sClass" : "text-c",
										"bSearchable" : false,
										"mRender" : function(data, type, row) {
											if (row.laberNum != null) {
												return row.laberNum;
											} else {
												return "";
											}
										}
									},
									{
										"sDefaultContent" : "标签名称",
										"bSortable" : false,
										"sClass" : "text-c",
										"bSearchable" : false,
										"mRender" : function(data, type, row) {
											if (row.categoryName != null) {
												return row.categoryName;
											} else {
												return "";
											}
										}
									},

									{
										"sDefaultContent" : "图片",
										"bSortable" : false,
										"sClass" : "text-c",
										"bSearchable" : false,
										"mRender" : function(data, type, row) {
											if (row.img != null) {
												return row.img;
											} else {
												return "";
											}
										}
									},
									{
										"sDefaultContent" : "状态",
										"bSortable" : false,
										"sClass" : "text-c",
										"bSearchable" : false,
										"mRender" : function(data, type, row) {
											//1:启用；-1:禁用）
											if (row.status == '1') {
												return "启用";
											} else if (row.status == '-1') {
												return "禁用";
											} else {
												return "";
											}
										}
									},

									{
								        "sDefaultContent": "操作",
								        "bSortable" : false,
								        "sClass": "td-manage text-c",
								        "bSearchable": false,
								        "mRender": function(data, type, row) {
								        	
								        	//编辑
								            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"label_edit('编辑','${context_root}/commodity/toLabelModify.action?categoryId=" + row.categoryId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
								        	//删除
								        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"label_del(this,\'" + row.categoryId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>删除</span></a>";
								        	
								        	return  toEdit + "&nbsp;&nbsp;" + toDelete;
								        }
								    }, ];
							var url = "${context_root}/commodity/labelList.action";
							pageTable = _Datatable_Init(pageTable, aoColumns,
									url);
						});

		function statusTools(row) {
			if (row.status == '1') {
				return "<a style=\"text-decoration:none\" onClick=\"label_stop(this,\'"
						+ row.categoryId
						+ "\')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a>";
			} else {
				return "<a style=\"text-decoration:none\" onClick=\"label_start(this,\'"
						+ row.categoryId
						+ "\')\" href=\"javascript:;\" title=\"启用\"><i class=\"Hui-iconfont\">&#xe615;</i></a>";
			}
		}

		/*添加*/
		function label_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}

		/*编辑*/
		function label_edit(title, url, w, h) {
			layer_show(title, url, w, h);
		}
		
		/*删除*/
		function label_del(obj,id){
			parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
				$.ajax({
					    url:"${context_root}/commodity/deleteLabelById.action?categoryId=" + id, 
						type:'post',
						async:true ,
						cache:false ,
						dataType:"json",
						success:function(data){
							if(data.s == true){
								$(obj).parents("tr").remove();
								parent.layer.msg('已删除!',{icon:1,time:1000});
								loadData() ;
							}else{
								parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
							}
						},
						
					}) ;
			});
		}

		/*停用*/
		function label_stop(obj, id) {
			parent.layer
					.confirm(
							'确认要停用吗？',
							{
								icon : 3,
								title : '提示'
							},
							function(index) {
								$
										.ajax({
											url : "${context_root}/commodity/changeLabelStatus.action?categoryId="
													+ id + "&status=-1",
											type : 'post',
											async : true,
											cache : false,
											dataType : "json",
											success : function(data) {
												if (data.s == true) {
													$(obj)
															.parents("tr")
															.find(".td-manage")
															.prepend(
																	'<a style="text-decoration:none" onClick="label_start(this,'
																			+ "'"
																			+ id
																			+ "'"
																			+ ')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
													$(obj)
															.parents("tr")
															.find(".td-status")
															.html(
																	'<span class="label label-defaunt radius">已停用</span>');
													$(obj).remove();
													parent.layer.msg('已停用!', {
														icon : 5,
														time : 1000
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

							});
		}

		/*启用*/
		function label_start(obj, id) {
			parent.layer
					.confirm(
							'确认要启用吗？',
							{
								icon : 3,
								title : '提示'
							},
							function(index) {
								$
										.ajax({
											url : "${context_root}/commodity/changeLabelStatus.action?categoryId="
													+ id + "&status=1",
											type : 'post',
											async : true,
											cache : false,
											dataType : "json",
											success : function(data) {
												if (data.s == true) {
													$(obj)
															.parents("tr")
															.find(".td-manage")
															.prepend(
																	'<a onClick="label_stop(this,'
																			+ "'"
																			+ id
																			+ "'"
																			+ ')" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
													$(obj)
															.parents("tr")
															.find(".td-status")
															.html(
																	'<span class="label label-success radius">已启用</span>');
													$(obj).remove();
													parent.layer.msg('已启用!', {
														icon : 6,
														time : 1000
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

							});
		}
	</script>
</body>
</html>