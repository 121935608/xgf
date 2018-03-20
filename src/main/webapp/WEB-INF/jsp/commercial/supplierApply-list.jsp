<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="收银员管理" />
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		商户相关 <span class="c-gray en">&gt;</span>招商入驻申请 <a
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
       <span class="select-box" style="width: 120px;">
           <select name="status" id="status" class="select" autocomplete="off">
               <option value="">状态</option>
               <option value=0>未处理</option>
               <option value=1 >已处理</option>
           </select>
       </span>
		<input type="text" class="input-text" style="width:250px" placeholder="申请人|联系方式|公司名称|邮箱" id="fuzzyCondition" name="fuzzyCondition">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="8%">序号</th>
						<th width="10%">申请人</th>
						<th width="10%">联系电话</th>
						<th width="10%">公司名称</th>
						<th width="12%">公司地址</th>
						<th width="12%">邮箱</th>
						<th width="12%">申请时间</th>
						<th width="10%">状态</th>
						<th width="8%">处理结果</th>
						<th width="8%">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" charset="UTF-8">
		var pageTable;
		$(document).ready(function() {
			var aoColumns = [ 
			{
				"sDefaultContent" : "序号",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.num != null) {
						return row.num;
					} else {
						return "";
					}
				}
			},
			{
				"sDefaultContent" : "申请人",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.applyName != null) {
						return row.applyName;
					} else {
						return "";
					}
				}
			},
			{
				"sDefaultContent" : "联系电话",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.applyPhone != null) {
						return row.applyPhone;
					} else {
						return "";
					}
				}
			},
			{
				"sDefaultContent" : "公司名称",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.compName != null) {
						return row.compName;
					} else {
						return "";
					}
				}
			},
			{
				"sDefaultContent" : "公司地址",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if ((row.compAddr != null)&&(row.detailAddr != null)) {
						return row.compAddr+row.detailAddr;
					} else if((row.compAddr != null)&&(row.detailAddr == null)){
						return row.compAddr
					}else {
						return "";
					}
				}
			},
			{
				"sDefaultContent" : "邮箱",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.email != null) {
						return row.email;
					} else {
						return "";
					}
				}
			},
			{
				"sDefaultContent" : "申请时间",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.addTime != null) {
						return formatDate(row.addTime, "yyyy-MM-dd hh:mm:ss");
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
					if (row.status == 0) {
						return "未处理";
					}else if(row.status == 1){
						return "已处理";
					}else {
						return "";
					}
				}
			},
			{
				"sDefaultContent" : "处理结果",
				"bSortable" : false,
				"sClass" : "text-c",
				"bSearchable" : false,
				"mRender" : function(data, type, row) {
					if (row.remark != null) {
						return row.remark;
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
		        	if(row.status == 1){
		        		return "";
		        	}
		        	//处理
		            var toEdit = "<a title=\"处理\" href=\"javascript:;\" onclick=\"supplier_edit('处理','${context_root}/merchant/toSupplierApplyModify.action?applyId=" + row.applyId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>处理</span></a>";
		        	return toEdit;
		        }
		    },
			
			];
			var url = "${context_root}/merchant/supplierApplyList.action";
			pageTable = _Datatable_Init(pageTable, aoColumns, url);
		});
		
		function query() {
			var fuzzyCondition = $("#fuzzyCondition").val();
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			var status = $("#status option:selected").val();
			pageTable.fnSettings().sAjaxSource = "${context_root}/merchant/supplierApplyList.action?fuzzyCondition="
					+ encodeURIComponent(encodeURIComponent(fuzzyCondition))+"&beginTime="+beginTime+"&endTime="+endTime+"&status="+status;
			pageTable.fnClearTable(0);
			pageTable.fnDraw();
		}
		
		/*处理*/
		function supplier_edit(title,url,w,h){
			layer_show(title,url,w,h);
		}
	</script>
</body>
</html>