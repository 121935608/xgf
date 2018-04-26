<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="成员列表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 谢鲜CRM管理 <span class="c-gray en">&gt;</span> 门店列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <input type="text" class="input-text" style="width:250px" placeholder="姓名|联系方式|店铺名称" id="applyName" name="applyName">
        <button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="10%">门店名称</th>
                <th width="5%">联系人</th>
                <th width="15%">手机号</th>
                <th width="15%">地址</th>
                <th width="15%">组类</th>
                <th width="15%">销售员 </th>
                <th width="15%">状态 </th>
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
            },
            {
                "sDefaultContent": "销售员",
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
                    var toEdit = "<a title=\"查看详情\" href=\"javascript:;\" onclick=\"certification_check('认证申请审核','${context_root}/merchant/checkCertification.action?storeId=" + row.storeId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>审核</span></a>";
                    return toEdit;
                }
            },
        ];
        var url = "${context_root}/crmStore/crmStoreSelect.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });

    /*审核申请*/
    function certification_check(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*角色-添加*/
    function role_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*角色-编辑*/
    function role_edit(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*督导员-停用*/
    function supervisor_stop(obj,id){
        parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
            $.ajax({
                url:"${context_root}/crmUser/updateSupervistorStatusView.action?supervisorId=" + id+"&status=1",
                type:'post',
                async:true ,
                cache:false ,
                dataType:"json",
                success:function(data){
                    if(data.s == true){
                        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="supervisor_start(this,' + id + ')" href="javascript:;" title="启用"><span style=\'color: #0e90d2 \'>启用</span></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
                        $(obj).remove();
                        parent.layer.msg('已停用!', {icon: 5, time: 1000});
                    }else{
                        parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
                    }
                },

            }) ;
        });
    }

    /*督导员-启用*/
    function supervisor_start(obj, id) {
        parent.layer.confirm('确认要停用吗？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                url: "${context_root}/crmUser/updateSupervistorStatusView.action?supervisorId=" + id + "&status=0",
                type: 'post',
                async: true,
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.s == true) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="supervisor_stop(this,' + id + ')" href="javascript:;" title="停用" style="text-decoration:none"><span style=\'color: #0e90d2 \'>停用</span></a>');
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