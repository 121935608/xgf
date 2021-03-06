﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="广告通知" />
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		内容管理 <span class="c-gray en">&gt;</span>广告通知 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
	<div class="text-c">
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
			   class="input-text Wdate" style="width:120px;" placeholder="开始时间">
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
			   class="input-text Wdate" style="width:120px;" placeholder="结束时间">
			   <span class="select-box" style="width: 120px;border:0px;">
					<y:select id="status" name="status"
						codeGroup="${statusList}" selectedValue=""
						cssClass="select" headerKey="" headerValue="状态">
					</y:select>
				</span>
				
		<input type="text" class="input-text" style="width:250px" placeholder="标题" id="fuzzyCondition" name="fuzzyCondition">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="advertisement_add('添加','${context_root}/content/toAdvertisementAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a></span></div>

		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="10%">序号</th>
						<th width="10%">标题</th>
						<th width="10%">URL</th>
						<th width="10%">创建时间</th>
						<th width="15%">状态</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" charset="UTF-8">
		var pageTable;
		$(document).ready(function() {
			var aoColumns = [ {
				"sDefaultContent" : "序号",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.advertisementNum != null) {
						return row.advertisementNum;
					} else {
						return "";
					}
				}
			},
			
			
			{
				"sDefaultContent" : "标题",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.advertisementName != null) {
						return row.advertisementName;
					} else {
						return "";
					}
				}
			},
			
			{
				"sDefaultContent" : "URL",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.advertisementUrl != null) {
						return row.advertisementUrl;
					} else {
						return "";
					}
				}
			},
			
			{
				"sDefaultContent" : "创建时间",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.createTime != null) {
						return formatDate(row.createTime, "yyyy-MM-dd hh:mm:ss");
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
						return "<span class=\"label label-success radius\">已启用</span>";
					} else if (row.status == '-1') {
						return "<span class=\"label label-defaunt radius\">已停用</span>";
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
		            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"advertisement_edit('编辑','${context_root}/content/toAdvertisementModify.action?advertisementId=" + row.advertisementId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
		        	//删除
		        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"advertisement_del(this,\'" + row.advertisementId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>删除</span></a>";
		        	
		        	return  toEdit + "&nbsp;&nbsp;" + toDelete;
		        }
		    },
			
			];
			var url = "${context_root}/content/advertisementList.action";
			pageTable = _Datatable_Init(pageTable, aoColumns, url);

		});
		
		function query() {
			var fuzzyCondition = $("#fuzzyCondition").val();
			var status = $("#status").val();
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();

			pageTable.fnSettings().sAjaxSource = "${context_root}/content/advertisementList.action?fuzzyCondition="
					+ encodeURIComponent(encodeURIComponent(fuzzyCondition))
					+ "&status="
					+ status
					+ "&beginTime="
					+ beginTime
					+ "&endTime="
					+ endTime;
			pageTable.fnClearTable(0);
			pageTable.fnDraw();
		}
		
		
		/*添加*/
		function advertisement_add(title,url,w,h){
			layer_show(title,url,w,h);
		}
		
		/*编辑*/
		function advertisement_edit(title,url,w,h){
			layer_show(title,url,w,h);
		}
		
		/*删除*/
		function advertisement_del(obj,id){
			parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
				$.ajax({
					    url:"${context_root}/content/deleteAdvertisementById.action?advertisementId=" + id, 
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
		
	</script>
</body>
</html>