<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="拜访记录列表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 谢鲜CRM管理 <span class="c-gray en">&gt;</span> 拜访记录列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">门店名称</th>
				<th width="5%">业务员姓名</th>
				<th width="5%">计划拜访日期</th>
				<th width="5%">拜访方式</th>
				<th width="5%">拜访目的</th>
				<th width="5%">陪访对象</th>
				<th width="5%">拜访结果状态</th>
				<th width="5%">拜访结果</th>
				<th width="5%">创建时间</th>
			</tr>
		</thead>
	</table>
	</div>
</div>
<script type="text/javascript">
var pageTable;
$(document).ready(function(){ 
    var aoColumns = [
    {
    	"sDefaultContent" : "-",
        "mData": "storeName",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
    	"sDefaultContent" : "-",
        "mData": "supervisorName",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            return formatDate(row.visitTime,"yyyy-MM-dd");
        }
    },
    {
		"bSortable" : false,
		"sClass" : "text-c",
		"bSearchable" : false,
		"mRender" : function(data, type, row) {
			//0-上门拜访  1-电话拜访  2-座谈拜访  3-其它方式
			if (row.visitManner == '0') {
				return "上门拜访";
			} else if (row.visitManner == '1') {
				return "电话拜访";
			} 
			else if (row.visitManner == '2') {
				return "座谈拜访";
			} 
			else if (row.visitManner == '3') {
				return "其它方式";
			} 
			else {
				return "";
			}
		}
	},
	{
		"bSortable" : false,
		"sClass" : "text-c",
		"bSearchable" : false,
		"mRender" : function(data, type, row) {
			//0-首次访  1-提高销量  2-追回欠款  3-处理投诉  4-收集需求  5-技术支持  6-其它方式
			if (row.visitGoal == '0') {
				return "首次访 ";
			} else if (row.visitGoal == '1') {
				return "提高销量";
			} 
			else if (row.visitGoal == '2') {
				return "追回欠款";
			} 
			else if (row.visitGoal == '3') {
				return "处理投诉";
			} 
			else if (row.visitGoal == '4') {
				return "收集需求";
			}
			else if (row.visitGoal == '5') {
				return "技术支持";
			}
			else if (row.visitGoal == '6') {
				return "其它方式";
			}
			else {
				return "";
			}
		}
	},
    {
        "mData": "visitWith",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
		"bSortable" : false,
		"sClass" : "text-c",
		"bSearchable" : false,
		"mRender" : function(data, type, row) {
			//1-有效/0-无效
			if (row.visitResultStatus == '1') {
				return "<span class=\"label label-success radius\">有效</span>";
			} else if (row.visitResultStatus == '0') {
				return "<span class=\"label label-defaunt radius\">无效</span>";
			} else {
				return "";
			}
		}
	},
    {
        "mData": "visitResult",
        "bSortable" : false,
        "sClass": "text-c"
    },
    {
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            return formatDate(row.createTime,"yyyy-MM-dd hh:mm:ss");
        }
    },
    ];
    var url = "${context_root}/crm/visitList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

</script> 
</body>
</html>