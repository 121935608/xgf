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
                        <y:select id="process" name="process" codeGroup="${ispass}" selectedValue=""
                                  cssClass="select" headerKey="" headerValue="状态">
                        </y:select>
                    </div>
                </div>
                <div class="col-xs-3 col-sm-2 .col-md-2" >
                    <button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
                </div>
                <div class="btn excel"  style="margin-left: 10px" onclick="Excel()">导出Excel</div>
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
                    if (row.process == 'NOPASS') {
                        return "<span class=\"label label-defaunt radius\">审核不通过</span>";
                    }else if (row.process == 'WRZ') {
                        return "<span class=\"label label-defaunt radius\">未认证</span>";
                    }else if (row.process == 'APRING') {
                        return "<span class=\"label label-defaunt radius\">认证审核中</span>";
                    }else if (row.process == 'APRNO') {
                        return "<span class=\"label label-defaunt radius\">认证不通过</span>";
                    }else if (row.process == 'APRYES') {
                        return "<span class=\"label label-success radius\">认证通过</span>";
                    }else if(row.process == 'PASS'){
                        return "<span class=\"label label-defaunt radius\">审核通过未认证</span>";
                    }else{
                        return "<span class=\"label label-defaunt radius\">待审核</span>";
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
                    var toEditThree = "<a style=\"text-decoration:none;margin-left:5px;\" onClick=\"addPublic(this,\'" + row.storeId + "\')\" href=\"javascript:;\" title=\"转如公海\"><span style='color: #0e90d2 '>转入公海</span></a>";
                    var toDetails = "<a title=\"门店详情\" href=\"javascript:;\" onclick=\"store_details('门店详情','${context_root}/crmStore/crmStoreDetails.action?storeId=" + row.storeId + "','','800')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>门店详情</span></a>";
                    if(row.name != null){
                        return toEdit + toEditTwo + toEditThree+toDetails;
                    }else{
                        return toEdit + toEditTwo+toDetails;
                    }
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

    /*业绩排名-查看*/
    function store_details(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    function query() {
        var supervisor = $("#supervisor").val();
        var process = $("#process").val();
        var beginTime = $("#beginTime").val();
        var endTime = $("#endTime").val();
        pageTable.fnSettings().sAjaxSource = "${context_root}/crmStore/crmStoreSelect.action?supervisor="+ supervisor +
            "&process="+ process +
            "&beginTime="+ beginTime +
            "&endTime="+ endTime;
        pageTable.fnClearTable(0);
        pageTable.fnDraw();
    }

    function addPublic(obj,id){
        parent.layer.confirm('确认要转入公海吗？',{icon: 3, title:'提示'},function(index){
            $.ajax({
                url:"${context_root}/crmStore/addPublicSupervistor.action?storeId=" + id,
                type:'post',
                async:true ,
                cache:false ,
                dataType:"json",
                success:function(data){
                    if(data.s == true){
                        parent.layer.msg('已转入公海!', {icon: 6, time: 1500});
                        window.location.reload();
                    }else{
                        parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
                    }
                },

            }) ;
        });
    }
    function Excel() {
        var supervisorId = $("#supervisor").val();
        var process = $("#process").val();
        var beginTime = $("#beginTime").val();
        var endTime = $("#endTime").val();
        var url="${context_root}/crmStore/crmStoreTableSelect.action?supervisorId="+ supervisorId +
            "&process="+ process +
            "&beginTime="+ beginTime +
            "&endTime="+ endTime;
        downloadFile(url);
    }

    function downloadFile(url) {
        /* try{
             var elemIF = document.createElement("iframe");
             elemIF.src = url;
             elemIF.style.display = "none";
             document.body.appendChild(elemIF);
         }catch(e){

         }*/
        window.open(url ,'_self');//进行访问
    }
</script>
</body>