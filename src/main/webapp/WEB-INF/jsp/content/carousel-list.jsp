﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="轮播管理" />
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		内容管理 <span class="c-gray en">&gt;</span>轮播管理 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
	
		<div style="min-height: 30px;">
			<form role="form" class="text-c">
				<div class="row">
					<div class="row col-xs-6 col-sm-4 .col-md-4">
						
						<div class="col-xs-8 col-sm-8 .col-md-8">
							<input type="text" placeholder="开始时间"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})"
								id="beginTime" class="input-text Wdate" style="width: 120px;">
							- <input type="text" placeholder="结束时间"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})"
								id="endTime" class="input-text Wdate" style="width: 120px;">
						</div>
					</div>
					<div class="row  col-xs-3 col-sm-2 .col-md-2">
						<y:select id="status" name="status"
							codeGroup="${statusList}" selectedValue=""
							cssClass="select" headerKey="" headerValue="状态">
						</y:select>
					</div>
					<div class="col-xs-3 col-sm-2 .col-md-2">
						<input type="text" class="input-text" style="width: 250px"
							placeholder="标题" id="fuzzyCondition" name="fuzzyCondition">
					</div>
					<div class="col-xs-3 col-sm-2 .col-md-2">
						<button type="button" class="btn btn-success radius"
							onclick="query()">
							<i class="Hui-iconfont">&#xe665;</i> 搜索
						</button>
					</div>
				</div>
			</form>
		</div>
		
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="carousel_add('添加账号','${context_root}/content/toCarouselAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a></span></div>
	

		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="10%">序号</th>
						<th width="10%">标题</th>
						
						<th width="10%">图片</th>
						<th width="15%">访问量</th>
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
		var imgpath="${imgPath}";
		$(document).ready(function() {
			var aoColumns = [ {
				"sDefaultContent" : "序号",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.sort != null) {
						return row.sort;
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
					if (row.carouselName != null) {
						return row.carouselName;
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
				"mRender": function(data, type, row) {
		        	return "<img src=\""+imgpath+row.carouselImg +"\"  style=\"width:50px;height:50px\"/>";
		        }
			},
			
			{
				"sDefaultContent" : "访问量",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.visitNum != null) {
						return row.visitNum;
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
						return "<span class=\"label label-defaunt radius\">已禁用</span>";
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
		            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"carousel_edit('编辑','${context_root}/content/toCarouselModify.action?carouselId=" + row.carouselId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
		        	//删除
		        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"carousel_del(this,\'" + row.carouselId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>删除</span></a>";
		        	return toEdit + "&nbsp;&nbsp;" + toDelete;
		        }
		    },
			
			];
			var url = "${context_root}/content/carouselList.action";
			pageTable = _Datatable_Init(pageTable, aoColumns, url);

		});
		
		function statusTools(row) {
		    if (row.status == '1') {
		        return "<a style=\"text-decoration:none\" onClick=\"carousel_stop(this,\'" + row.carouselId + "\')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a>";
		    } else {
		        return "<a style=\"text-decoration:none\" onClick=\"carousel_start(this,\'" + row.carouselId + "\')\" href=\"javascript:;\" title=\"启用\"><i class=\"Hui-iconfont\">&#xe615;</i></a>";
		    }
		}
		
		function query() {
			var fuzzyCondition = $("#fuzzyCondition").val();
			var status = $("#status").val();
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();

			pageTable.fnSettings().sAjaxSource = "${context_root}/content/carouselList.action?fuzzyCondition="
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
		function carousel_add(title,url,w,h){
			layer_show(title,url,w,h);
		}
		
		/*编辑*/
		function carousel_edit(title,url,w,h){
			layer_show(title,url,w,h);
		}
		
		/*停用*/
		function carousel_stop(obj,id){
			parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
				$.ajax({
					url:"${context_root}/content/changeCarouselStatus.action?carouselId=" + id +"&status=-1", 
					type:'post',
					async:true ,
					cache:false ,
					dataType:"json",
					success:function(data){
						if(data.s == true){
							$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="carousel_start(this,'+"'"+id+"'"+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
							$(obj).remove();
							parent.layer.msg('已停用!',{icon: 5,time:1000});
						}else{
							parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
						}
					},
				}) ;
				
			});
		}

		/*启用*/
		function carousel_start(obj,id){
			parent.layer.confirm('确认要启用吗？',{icon: 3, title:'提示'},function(index){
				$.ajax({
					url:"${context_root}/content/changeCarouselStatus.action?carouselId=" + id +"&status=1", 
					type:'post',
					async:true ,
					cache:false ,
					dataType:"json",
					success:function(data){
						if(data.s == true){
							$(obj).parents("tr").find(".td-manage").prepend('<a onClick="carousel_stop(this,'+"'"+id+"'"+')" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
							$(obj).remove();
							parent.layer.msg('已启用!', {icon: 6,time:1000});
						}else{
							parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
						}
					},
				}) ;
				
			});
		}
		
		/*删除*/
		function carousel_del(obj,id){
			parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
				$.ajax({
					    url:"${context_root}/content/deleteCarouselById.action?carouselId=" + id, 
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