<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品广告" />
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		内容管理 <span class="c-gray en">&gt;</span>商品广告 <a
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
						<y:select id="type" name="type"
							codeGroup="${typeList}" selectedValue=""
							cssClass="select" headerKey="" headerValue="类型">
						</y:select>
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
		
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="commodityAd_add('添加','${context_root}/content/toCommodityAdAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a></span></div>
	

		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="10%">楼层</th>
						<th width="10%">类型</th>
						<th width="10%">标题</th>
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
				"sDefaultContent" : "楼层",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.floor != null) {
						return row.floor;
					} else {
						return "";
					}
				}
			},
			
			{
				"sDefaultContent" : "类型",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					//1：横栏广告；2：fit广告
					if (row.type == '1') {
						return "横栏广告";
					}else if (row.type == '2') {
						return "fit广告";
					}  else{
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
					if (row.commodityAdName != null) {
						return row.commodityAdName;
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
		            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"commodityAd_edit('编辑','${context_root}/content/toCommodityAdModify.action?commodityAdId=" + row.commodityAdId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>";
		            return statusTools(row)  + "&nbsp;&nbsp;" + toEdit;
		        }
		    },
			
			];
			var url = "${context_root}/content/commodityAdList.action";
			pageTable = _Datatable_Init(pageTable, aoColumns, url);

		});
		
		function statusTools(row) {
		    if (row.status == '1') {
		        return "<a style=\"text-decoration:none\" onClick=\"commodityAd_stop(this,\'" + row.commodityAdId + "\')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a>";
		    } else {
		        return "<a style=\"text-decoration:none\" onClick=\"commodityAd_start(this,\'" + row.commodityAdId + "\')\" href=\"javascript:;\" title=\"启用\"><i class=\"Hui-iconfont\">&#xe615;</i></a>";
		    }
		}
		
		function query() {
			var fuzzyCondition = $("#fuzzyCondition").val();
			var status = $("#status").val();
			var type = $("#type").val();
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();

			pageTable.fnSettings().sAjaxSource = "${context_root}/content/commodityAdList.action?fuzzyCondition="
					+ encodeURIComponent(encodeURIComponent(fuzzyCondition))					
					+ "&status="
					+ status
					+ "&type="
					+ type
					+ "&beginTime="
					+ beginTime
					+ "&endTime="
					+ endTime;
			pageTable.fnClearTable(0);
			pageTable.fnDraw();
		}
		
		
		/*添加*/
		function commodityAd_add(title,url,w,h){
			layer_show(title,url,w,h);
		}
		
		/*编辑*/
		function commodityAd_edit(title,url,w,h){
			layer_show(title,url,w,h);
		}
		
		/*停用*/
		function commodityAd_stop(obj,id){
			parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
				$.ajax({
					url:"${context_root}/content/changeCommodityAdStatus.action?commodityAdId=" + id +"&status=-1", 
					type:'post',
					async:true ,
					cache:false ,
					dataType:"json",
					success:function(data){
						if(data.s == true){
							$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="commodityAd_start(this,'+"'"+id+"'"+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
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
		function commodityAd_start(obj,id){
			parent.layer.confirm('确认要启用吗？',{icon: 3, title:'提示'},function(index){
				$.ajax({
					url:"${context_root}/content/changeCommodityAdStatus.action?commodityAdId=" + id +"&status=1", 
					type:'post',
					async:true ,
					cache:false ,
					dataType:"json",
					success:function(data){
						if(data.s == true){
							$(obj).parents("tr").find(".td-manage").prepend('<a onClick="commodityAd_stop(this,'+"'"+id+"'"+')" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
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
	</script>
</body>
</html>