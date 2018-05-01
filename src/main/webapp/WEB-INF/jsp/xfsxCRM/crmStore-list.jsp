<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="成员列表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 谢鲜CRM管理 <span class="c-gray en">&gt;</span> 门店列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div style="min-height: 30px;">
        <form role="form" class="text-c">
            <div class="row" >
                <div class="col-xs-6 col-sm-4 .col-md-4" > 日期范围：
                    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
                    -
                    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
                </div>
                <div class="row  col-xs-6 col-sm-4 .col-md-4" >
                    <div class="col-xs-6 col-sm-6 .col-md-6" >
                        <y:select id="supervisor" name="supervisor" codeGroup="${supervisorList}" selectedValue=""
                                  cssClass="select" headerKey="" headerValue="督导员">
                        </y:select>
                    </div>
                    <div class="col-xs-6 col-sm-6 .col-md-6" >
                        <y:select id="auditStatus" name="auditStatus" codeGroup="${ispass}" selectedValue=""
                                  cssClass="select" headerKey="" headerValue="状态">
                        </y:select>
                    </div>
                </div>
                <div class="col-xs-3 col-sm-2 .col-md-2" >
                    <button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
                </div>
            </div>
        </form>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <%--<th width="5%">NO.</th>--%>
                <th width="10%">门店名称</th>
                <th width="5%">联系人</th>
                <th width="7%">手机号</th>
                <th width="15%">地址</th>
                <th width="10%">申请时间</th>
                <th width="5%">督导员</th>
                <th width="5%">组类</th>
                <th width="5%">下单总金额</th>
                <th width="5%">近30天下单总金额</th>
                <th width="5%">近30天下单次数(次)</th>
                <th width="5%">本周拜访次数(次) </th>
                <th width="10%">状态 </th>
                <th width="15%">操作 </th>
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
                "sDefaultContent": "门店名称",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.storeName != null) {
                        return row.storeName;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "联系人",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.userName != null) {
                        return row.userName;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "手机号",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.phone != null) {
                        return row.phone;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "地址",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.area != null) {
                        return row.area;
                    } else {
                        return "";
                    }
                }
            },{
                "sDefaultContent": "申请时间",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.addTime != null) {
                        return formatDate(row.addTime,"yyyy-MM-dd hh:mm:ss");
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "督导员",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.name != null) {
                        return row.name;
                    } else {
                        return "";
                    }
                }
            },{
                "sDefaultContent": "组类",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.deptName != null) {
                        return row.deptName;
                    } else {
                        return "";
                    }
                }
            },{
                "sDefaultContent": "下单总金额",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.totalPrice != null) {
                        return row.totalPrice;
                    } else {
                        return "";
                    }
                }
            },{
                "sDefaultContent": "近30天下单总金额",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.monthPrice != null) {
                        return row.monthPrice;
                    } else {
                        return "";
                    }
                }
            },{
                "sDefaultContent": "近30天下单次数",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.monthNum != null) {
                        return row.monthNum;
                    } else {
                        return "";
                    }
                }
            },{
                "sDefaultContent": "本周拜访次数",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.totalVisitNum != null) {
                        return row.totalVisitNum;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "状态",
                "bSortable" : false,
                "sClass": "td-status text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.auditStatus == '0') {
                        return "<span class=\"label label-success radius\">待审核</span>";
                    } else if(row.auditStatus == '1'){
                        return "<span class=\"label label-success radius\">审核通过</span>";
                    } else {
                        return "<span class=\"label label-defaunt radius\">审核不通过</span>";
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
                    var toEdit = "<a title=\"查看详情\" href=\"javascript:;\" onclick=\"certification_check('认证申请审核','${context_root}/crmStore/crmCheckStoreSelect.action?storeId=" + row.storeId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>审核</span></a>";
                    var toEditTwo = "<a title=\"分配业务员\" href=\"javascript:;\" onclick=\"store_supervistor('门店分配业务员','${context_root}/crmStore/crmStoreSupervistorSelect.action?storeId=" + row.storeId + "','','700')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>分配业务员</span></a>";
                    return toEdit + toEditTwo;
                }
            },
        ];
        var url = "${context_root}/crmStore/crmStoreSelect.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });

    /*CRM审核门店*/
    function certification_check(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*门店-业务员*/
    function store_supervistor(title,url,w,h){
        layer_show(title,url,w,h);
    }

    function query() {
        var supervisor = $("#supervisor").val();
        var auditStatus = $("#auditStatus").val();
        var beginTime = $("#beginTime").val();
        var endTime = $("#endTime").val();
        pageTable.fnSettings().sAjaxSource = "${context_root}/crmStore/crmStoreSelect.action?supervisor="+ supervisor +
            "&auditStatus="+ auditStatus +
            "&beginTime="+ beginTime +
            "&endTime="+ endTime;
        pageTable.fnClearTable(0);
        pageTable.fnDraw();
    }
</script>
</body>