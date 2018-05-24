<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="业绩排名"/>
<style>
    div{
        font-size: 15px;
    }
    #tab-menu{
        padding-bottom: 35px;
    }
    .left1{
        width: 150px;
        text-align: center;
    }
    .ll{
        float: right;
        color: #5D5D5D;
    }
    .cc{
        border-bottom: 1px #CECECE solid;
    }
    .cc1{
        color: #5D5D5D;
    }
    #order,#visit,#coupon {
        display: none;
    }
    .rm{
        border-bottom: 3px #41A26F solid;
    }
    .rm1{
        font-weight: bolder;
        color: #41A26F;
    }
    #store{
        padding-left: 70px;
    }
    #store table{
        width: 600px;
    }
</style>
<body>
<div class="page-container">
    <div class="tab-wrapper">
        <div class="tab-content">

            <div class="mt-20"  id="search">

            </div>

            <div class="mt-20"  id="tab-menu">
                <table class="table" style="float: left;margin-left: 50px;width: 650px">
                    <tr>
                        <td id="store_details" class="rm" style="text-align: center"><div class="rm1">门店信息</div></td>
                        <td id="order_details" class="cc" style="text-align: center"><div class="cc1">订单配送</div></td>
                        <td id="visit_details" class="cc" style="text-align: center"><div class="cc1">拜访信息</div></td>
                        <td id="coupon_details" class="cc" style="text-align: center"><div class="cc1">补券信息</div></td>
                    </tr>
                </table>
            </div>
            <input id="userId" value="${store.userId}" style="display: none">
            <input id="storeId" value="${store.storeId}" style="display: none">

            <%--门店信息--%>
            <div class="mt-20" id="store">
                <div class="tab-info">
                    <table class="table table-border table-bordered tab-info1" >
                        <tr><td class="left1" rowspan="2"><div>门店名称</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.storeName}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>联系人</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.userName}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>手机号</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.phone}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>门店地址</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.address}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>注册时间</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.addTime}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>跟进人</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.name}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>最近一次下单时间</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.recentOrderTime}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>下单次数</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.totalNum}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>消费频次</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.consumptionFrequency}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>累计流水</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.totalPrice}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>退货率</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.returnRate}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>门店面积</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.storeArea}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>是否连锁</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.chain}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>门店设备</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.equipment}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>门店类型</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.storeType}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>周边建筑</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.building}</div> </td></tr>
                        <tr><td class="left1" rowspan="2"><div>合作方</div></td></tr>
                        <tr><td class="right1"><div class="ll">${store.partners}</div> </td></tr>
                    </table>
                </div>
            </div>

            <%--订单信息--%>
            <div class="mt-20" id="order">
                <div class="tab-info">
                    <table class="table table-border table-bordered table-hover table-bg table-sort">
                        <thead>
                        <tr class="text-c">
                            <th width="5%">订单编号</th>
                            <th width="5%">下单时间</th>
                            <th width="5%">商品数量</th>
                            <th width="5%">订单金额</th>
                            <th width="5%">订单状态</th>
                            <%--<th width="5%">操作</th>--%>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>

            <%--拜访记录信息--%>
            <div class="mt-20" id="visit">
                <div class="tab-info">
                    <table class="table table-border table-bordered table-hover table-bg table-sort-One">
                        <thead>
                        <tr class="text-c">
                            <th width="5%">拜访时间</th>
                            <th width="5%">拜访目的</th>
                            <th width="5%">拜访方式</th>
                            <th width="5%">陪访对象</th>
                            <th width="5%">拜访结果</th>
                            <th width="5%">拜访内容</th>
                            <%--<th width="5%">操作</th>--%>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>

            <%--补券信息--%>
            <div class="mt-20" id="coupon">
                <div class="tab-info">
                    <table class="table table-border table-bordered table-hover table-bg table-sort-Two">
                        <thead>
                        <tr class="text-c">
                            <th width="5%">申请时间</th>
                            <th width="5%">订单编号</th>
                            <th width="5%">物流单号</th>
                            <th width="5%">损坏果品(kg)</th>
                            <th width="5%">现场验货</th>
                            <th width="5%">补券结果</th>
                            <%--<th width="5%">操作</th>--%>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript">
    var userId = $("#userId").attr("value");
    var storeId = $("#storeId").attr("value");
    var pageTable,pageTableOne,pageTableTwo;
    $(function() {
        /*订单信息*/
        var aoColumns = [
            {
                "sDefaultContent" : "-",
                "mData": "orderNumber",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "orderTime",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "number",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "orderPrice",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "bSortable" : false,
                "sClass" : "text-c",
                "bSearchable" : false,
                "mRender" : function(data, type, row) {
                    //1:待支付；2:待发货；3:待收货；4:已完成,5:已收款
                    if (row.orderStatus == '1') {
                        return "待支付";
                    }else if (row.orderStatus == '2') {
                        return "待发货";
                    }else if (row.orderStatus == '3') {
                        return "待收货";
                    }else if (row.orderStatus == '4') {
                        return "已完成";
                    }else if (row.orderStatus == '5') {
                        return "已收款";
                    }else {
                        return "";
                    }
                }
            },
            <%--{--%>
                <%--"sDefaultContent": "操作",--%>
                <%--"bSortable" : false,--%>
                <%--"sClass": "td-manage text-c",--%>
                <%--"bSearchable": false,--%>
                <%--"mRender": function(data, type, row) {--%>
                    <%--//查看详情--%>
                    <%--var details = "<a title=\"查看\" href=\"javascript:;\" onclick=\"performance_query('查看详情','${context_root}/crm/toPerformanceRankQuery.action?supervisorId=" + row.supervisorNum + "&name="+row.name+"&area="+row.area+"&totalPrice="+row.totalPrice+"&deptRank="+row.deptRank+"&deptName="+row.deptName+"','','570')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>查看</span></a>";--%>
                    <%--return  details;--%>
                <%--}--%>
            <%--},--%>
        ];
        var url = "${context_root}/crmStore/crmStoreDetailsOrder.action?userId="+userId;
        pageTable = _Datatable_Init(pageTable, aoColumns, url);

        /*拜访信息*/
        var aoColumns1 = [
            {
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    return formatDate(row.visitTime,"yyyy-MM-dd hh:mm:ss");
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
                "sDefaultContent" : "-",
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
                        return "未拜访";
                    }
                }
            },
            {
                "sDefaultContent" : "-",
                "mData": "visitResult",
                "bSortable" : false,
                "sClass": "text-c"
            },
            <%--{--%>
                <%--"sDefaultContent": "操作",--%>
                <%--"bSortable" : false,--%>
                <%--"sClass": "td-manage text-c",--%>
                <%--"bSearchable": false,--%>
                <%--"mRender": function(data, type, row) {--%>
                    <%--//查看详情--%>
                    <%--var details = "<a title=\"查看\" href=\"javascript:;\" onclick=\"performance_query('查看详情','${context_root}/crm/toPerformanceRankQuery.action?supervisorId=" + row.supervisorNum + "&name="+row.name+"&area="+row.area+"&totalPrice="+row.totalPrice+"&deptRank="+row.deptRank+"&deptName="+row.deptName+"','','570')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>查看</span></a>";--%>
                    <%--return  details;--%>
                <%--}--%>
            <%--},--%>
        ];
        var urlOne = "${context_root}/crmStore/crmStoreDetailsVisit.action?storeId="+storeId;
        pageTableOne = _Datatable_InitOne(pageTableOne, aoColumns1, urlOne);

        /*补券信息*/
        var aoColumns2 = [
           {
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    return formatDate(row.createTime,"yyyy-MM-dd hh:mm:ss");
                }
            },
            {
                "sDefaultContent" : "-",
                "mData": "orderNum",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "logisticsSingleNum",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "damagedFruit",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "bSortable" : false,
                "sClass" : "text-c",
                "bSearchable" : false,
                "mRender" : function(data, type, row) {
                    //0-是 1-否
                    if (row.inspectionScene == '0') {
                        return "是 ";
                    } else if (row.inspectionScene == '1') {
                        return "否";
                    } else {
                        return "";
                    }
                }
            },
            {
                "bSortable" : false,
                "sClass" : "text-c",
                "bSearchable" : false,
                "mRender" : function(data, type, row) {
                    //0-未审核 1-已通过  2-未通过
                    if (row.auditStatus == '0') {
                        return "未审核";
                    } else if (row.auditStatus == '1') {
                        return "已通过";
                    }else if (row.auditStatus == '2') {
                        return "未通过";
                    }else {
                        return "";
                    }
                }
            },
            <%--{--%>
                <%--"sDefaultContent": "操作",--%>
                <%--"bSortable" : false,--%>
                <%--"sClass": "td-manage text-c",--%>
                <%--"bSearchable": false,--%>
                <%--"mRender": function(data, type, row) {--%>
                    <%--//查看详情--%>
                    <%--var details = "<a title=\"查看\" href=\"javascript:;\" onclick=\"performance_query('查看详情','${context_root}/crm/toPerformanceRankQuery.action?supervisorId=" + row.supervisorNum + "&name="+row.name+"&area="+row.area+"&totalPrice="+row.totalPrice+"&deptRank="+row.deptRank+"&deptName="+row.deptName+"','','570')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>查看</span></a>";--%>
                    <%--return  details;--%>
                <%--}--%>
            <%--},--%>
        ];
        var urlTwo = "${context_root}/crmStore/crmStoreDetailsCoupon.action?storeId="+storeId;
        pageTableTwo = _Datatable_InitTwo(pageTableTwo, aoColumns2, urlTwo);


        // store_details order_details visit_details coupon_details
        /*门店信息*/
        $("#store_details div").click(function () {
            $("#store_details").removeClass("cc");
            $("#store_details div").removeClass("cc1");
            $("#store_details").addClass("rm");
            $("#store_details div").addClass("rm1");

            $("#order_details").removeClass("rm");
            $("#order_details div").removeClass("rm1");
            $("#order_details").addClass("cc");
            $("#order_details div").addClass("cc1");
            $("#visit_details").removeClass("rm");
            $("#visit_details div").removeClass("rm1");
            $("#visit_details").addClass("cc");
            $("#visit_details div").addClass("cc1");
            $("#coupon_details").removeClass("rm");
            $("#coupon_details div").removeClass("rm1");
            $("#coupon_details").addClass("cc");
            $("#coupon_details div").addClass("cc1");

            $("#store").css('display','block');
            $("#order").css('display','none');
            $("#visit").css('display','none');
            $("#coupon").css('display','none');
        })

        /*订单信息*/
        $("#order_details div").click(function () {
            $("#order_details").removeClass("cc");
            $("#order_details div").removeClass("cc1")
            $("#order_details").addClass("rm");
            $("#order_details div").addClass("rm1");

            $("#store_details").removeClass("rm");
            $("#store_details div").removeClass("rm1");
            $("#store_details").addClass("cc");
            $("#store_details div").addClass("cc1");
            $("#visit_details").removeClass("rm");
            $("#visit_details div").removeClass("rm1");
            $("#visit_details").addClass("cc");
            $("#visit_details div").addClass("cc1");
            $("#coupon_details").removeClass("rm");
            $("#coupon_details div").removeClass("rm1");
            $("#coupon_details").addClass("cc");
            $("#coupon_details div").addClass("cc1");

            $("#order").css('display','block');
            $("#store").css('display','none');
            $("#visit").css('display','none');
            $("#coupon").css('display','none');
        })

        /*拜访信息*/
        $("#visit_details div").click(function () {
            $("#visit_details").removeClass("cc");
            $("#visit_details div").removeClass("cc1")
            $("#visit_details").addClass("rm");
            $("#visit_details div").addClass("rm1");

            $("#store_details").removeClass("rm");
            $("#store_details div").removeClass("rm1");
            $("#store_details").addClass("cc");
            $("#store_details div").addClass("cc1");
            $("#order_details").removeClass("rm");
            $("#order_details div").removeClass("rm1");
            $("#order_details").addClass("cc");
            $("#order_details div").addClass("cc1");
            $("#coupon_details").removeClass("rm");
            $("#coupon_details div").removeClass("rm1");
            $("#coupon_details").addClass("cc");
            $("#coupon_details div").addClass("cc1");

            $("#visit").css('display','block');
            $("#store").css('display','none');
            $("#order").css('display','none');
            $("#coupon").css('display','none');
        })

        /*补券信息*/
        $("#coupon_details div").click(function () {
            $("#coupon_details").removeClass("cc");
            $("#coupon_details div").removeClass("cc1");
            $("#coupon_details").addClass("rm");
            $("#coupon_details div").addClass("rm1");

            $("#order_details").removeClass("rm");
            $("#order_details div").removeClass("rm1");
            $("#order_details").addClass("cc");
            $("#order_details div").addClass("cc1");
            $("#visit_details").removeClass("rm");
            $("#visit_details div").removeClass("rm1");
            $("#visit_details").addClass("cc");
            $("#visit_details div").addClass("cc1");
            $("#store_details").removeClass("rm");
            $("#store_details div").removeClass("rm1");
            $("#store_details").addClass("cc");
            $("#store_details div").addClass("cc1");

            $("#coupon").css('display','block');
            $("#store").css('display','none');
            $("#order").css('display','none');
            $("#visit").css('display','none');
        })

    });

</script>
</body>
</html>
