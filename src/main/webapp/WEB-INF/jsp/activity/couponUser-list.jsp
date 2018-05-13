<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="收银员管理"/>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span id="aa" value="123" class="c-gray en">11&gt;</span>
    活动管理 <span class="c-gray en">&gt;</span>优惠券管理 <a
        class="btn btn-success radius r"
        style="line-height: 1.6em; margin-top: 3px"
        href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;"
               onclick="couponUser_add('发放优惠券','${context_root}/couponUser/addUI.action','','410')"
               class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 放款优惠券</a>
		</span>
        <form role="form" class="text-c">
            <div class="row">
                <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" id="startTime" class="input-text Wdate" style="width:120px;" placeholder="开始时间">
                <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" id="endTime" class="input-text Wdate" style="width:120px;" placeholder="结束时间">
                <input type="text" class="input-text" style="width: 250px" placeholder="优惠券名称" id="name" name="name">
                <button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索
                </button>
            </div>
        </form>
    </div>




    <div class="mt-20">
        <table
                class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr  class="text-c">
                <th width="10%">序号</th>
                <th width="10%">优惠券ID</th>
                <th width="10%">优惠券名称</th>
                <th width="10%">商家</th>
                <th width="10%">是否使用</th>
                <th width="10%">是否过期</th>
                <th width="10%">领取时间</th>
                <th width="10%">使用订单</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript" charset="UTF-8">
    var pageTable;
    $(document).ready(function () {
        var aoColumns = [
            {
                "mData": "no",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data,type,row) {
                    return "";
                }
            },
            {
                "sDefaultContent": "优惠券ID",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.id != null) {
                        return row.id;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "优惠券名称",
                "bSortable": false,
                "sClass": "td-status text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    return row.couponUserName;
                }
            },
            {
                "sDefaultContent": "商家",
                "bSortable": false,
                "sClass": "td-status text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    return row.storeName;
                }
            },
            {
                "sDefaultContent" : "是否使用",
                "bSortable" : false,
                "sClass" : "td-status text-c",
                "bSearchable" : false,
                "mRender" : function(data, type, row) {
                    if (row.useStatus ==1) {
                        return "已使用";
                    } else if(row.useStatus==0){
                        return "未使用";
                    }else{
                        return "";
                    }
                }
            },
            {
                "sDefaultContent" : "是否过期",
                "bSortable" : false,
                "sClass" : "td-status text-c",
                "bSearchable" : false,
                "mRender" : function(data, type, row) {
                    if (row.expireStatus ==1) {
                        return "已过期";
                    } else if(row.expireStatus==0){
                        return "未过期";
                    }else{
                        return "";
                    }
                }
            },
            {
                "sDefaultContent" : "领取时间",
                "bSortable" : false,
                "sClass" : "td-status text-c",
                "bSearchable" : false,
                "mRender" : function(data, type, row) {
                    if (row.reviveTime != null) {
                        return row.reviveTime;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "使用订单",
                "bSortable": false,
                "sClass": "td-manage text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    return row.orderId;
                }
            },

        ];

        var url = "${context_root}/couponUser/list.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);

    });

    function query() {
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var name = $("#name").val();

        pageTable.fnSettings().sAjaxSource = "${context_root}/couponUser/list.action?startTime="+startTime+"&endTime="+endTime+"&name="+name;

        pageTable.fnClearTable(0);
        pageTable.fnDraw();
    }



    /*添加*/
    function couponUser_add(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*编辑*/
    function cashier_edit(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*停用*/
    function cashierManage_stop(obj, id) {
        parent.layer.confirm('确认要停用吗？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                url: "${context_root}/commercial/changeCashierManageStatus.action?cashierId=" + id + "&status=-1",
                type: 'post',
                async: true,
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.s == true) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="cashierManage_start(this,' + "'" + id + "'" + ')" href="javascript:;" title="启用"><i class="Hui-iconfont">启用</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
                        $(obj).remove();
                        parent.layer.msg('已停用!', {icon: 5, time: 1000});
                    } else {
                        parent.layer.alert(data.m, {icon: 2, title: "系统提示"});
                    }
                },
            });

        });
    }

    /*启用*/
    function cashierManage_start(obj, id) {
        parent.layer.confirm('确认要启用吗？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                url: "${context_root}/commercial/changeCashierManageStatus.action?cashierId=" + id + "&status=1",
                type: 'post',
                async: true,
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.s == true) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="cashierManage_stop(this,' + "'" + id + "'" + ')" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">停用</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
                        $(obj).remove();
                        parent.layer.msg('已启用!', {icon: 6, time: 1000});
                    } else {
                        parent.layer.alert(data.m, {icon: 2, title: "系统提示"});
                    }
                },
            });

        });
    }
</script>
</body>
</html>