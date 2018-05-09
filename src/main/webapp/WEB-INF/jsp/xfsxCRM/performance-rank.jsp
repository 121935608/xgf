<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="业绩排名"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 谢鲜CRM管理 <span class="c-gray en">&gt;</span> 业绩排名 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">

        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="5%">NO.</th>
                <th width="5%">销售员</th>
                <th width="5%">省份</th>
                <th width="5%">城市</th>
                <th width="5%">区域</th>
                <th width="5%">组类</th>
                <th width="5%">客户下单总额(元)</th>
                <th width="5%">组类排序(升序/降序)</th>
                <th width="5%">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<input id="userCrmId" value="${currentUser.userId}" style="display: none;">
<script type="text/javascript">
    var pageTable;
    var num=0;
    $(document).ready(function(){

        var aoColumns = [
            {
                "sDefaultContent" : "-",
                "bSortable" : false,
                "sClass": "text-c",
                "mRender": function(data, type, row) {
                    num+=1;
                    return  num;
                }
            },
            {
                "sDefaultContent" : "-",
                "mData": "name",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "province",
                "bSortable" : false,
                "sClass": "text-c"
            },{
                "sDefaultContent" : "-",
                "mData": "city",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "county",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "deptName",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "totalPrice",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "deptRank",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent": "操作",
                "bSortable" : false,
                "sClass": "td-manage text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    //编辑
                    <%--var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"dept_edit('编辑部门','${context_root}/crm/toDeptUpdate.action?deptId=" + row.deptId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";--%>
                    var toEdit ="查看";
                    return  toEdit;
                }
            },

        ];
        var userCrmId = $("#userCrmId").attr("value");
        var url = "${context_root}/crm/performanceRankList.action?userId="+userCrmId;
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });

</script>
</body>
</html>