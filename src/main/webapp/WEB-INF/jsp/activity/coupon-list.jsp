<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="收银员管理"/>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
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
               onclick="coupon_add('添加优惠券','${context_root}/coupon/addUI.action','','410')"
               class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
		</span>
        <form role="form" class="text-c">
            <div class="row">
                <input type="text" onfocus="WdatePicker()" id="startTime" class="input-text Wdate" style="width:120px;" placeholder="开始时间">
                <input type="text" onfocus="WdatePicker()" id="endTime" class="input-text Wdate" style="width:120px;" placeholder="结束时间">
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
            <tr class="text-c">
                <th width="10%">优惠券名称</th>
                <th width="10%">优惠券金额（元）</th>
                <th width="10%">有效期</th>
                <th width="10%">发布数量</th>
                <th width="10%">领取方式</th>
                <th width="10%">创建时间</th>
                <th width="10%">使用说明</th>
                <th width="10%">操作</th>
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
                "sDefaultContent": "优惠券名称",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.name != null) {
                        return row.name;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "优惠券金额",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.money != null) {
                        return row.money;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "有效期",
                "bSortable": false,
                "sClass": "td-status text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.timeType ==0) {
                        return row.useDays+"天";
                    }
                    if(row.timeType==1){
                        if(row.startTime!=null&&row.endTime!=null)
                            return row.startTime.split(" ")[0]+"一"+row.endTime.split(" ")[0];
                    }
                }
            },
            {
                "sDefaultContent": "发布数量",
                "bSortable": false,
                "sClass": "td-status text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.num != null) {
                        return row.num;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent" : "领取方式",
                "bSortable" : false,
                "sClass" : "td-status text-c",
                "bSearchable" : false,
                "mRender" : function(data, type, row) {
                    if (row.receiveType != null) {
                        if(row.receiveType==0)
                            return "自动领取";
                        else return "手动领取";
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent" : "创建时间",
                "bSortable" : false,
                "sClass" : "td-status text-c",
                "bSearchable" : false,
                "mRender" : function(data, type, row) {
                    if (row.createTime != null) {
                        return row.createTime;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent" : "使用说明",
                "bSortable" : false,
                "sClass" : "td-status text-c",
                "bSearchable" : false,
                "mRender" : function(data, type, row) {
                    if (row.useDescription != null) {
                        return row.useDescription;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "操作",
                "bSortable": false,
                "sClass": "td-manage text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    //编辑
                    return "<a title=\"编辑\" href=\"javascript:;\" onclick=\"cashier_edit('编辑','${context_root}/coupon/editUI.action?id=" + row.id + "','','410')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";

                }
            },

        ];
        var url = "${context_root}/coupon/list.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);

    });

    function query() {
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var name = $("#name").val();

        pageTable.fnSettings().sAjaxSource = "${context_root}/coupon/list.action?startTime="+startTime+"&endTime="+endTime+"&name="+name;

        pageTable.fnClearTable(0);
        pageTable.fnDraw();
    }



    /*添加*/
    function coupon_add(title, url, w, h) {
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