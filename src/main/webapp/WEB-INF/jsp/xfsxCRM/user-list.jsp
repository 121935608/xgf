<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="成员列表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 谢鲜CRM管理 <span class="c-gray en">&gt;</span> 成员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
		<span class="select-box" style="width: 120px;">
		   <select name="statusSelect" id="statusSelect" class="select" autocomplete="off">
			   <option value="">状态</option>
			   <option value="0">启用</option>
			   <option value="1">禁用</option>
		   </select>
		</span>
        <input type="text" class="input-text" style="width:250px" placeholder="姓名|督导员编号|电话" id="userName" name="userName">
        <button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="role_add('添加督导员','${context_root}/crmUser/addSpervistorInfoView.action','','410')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加督导员</a></span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="5%">姓名</th>
                <th width="10%">图片</th>
                <th width="5%">督导员编号</th>
                <th width="5%">登录名</th>
                <th width="7%">省</th>
                <th width="7%">市</th>
                <th width="7%">区</th>
                <th width="10%">督导员部门</th>
                <th width="8%">督导员角色</th>
                <th width="12%">电话 </th>
                <th width="10%">状态</th>
                <th width="10%">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript">
    var pageTable;
    var imgpath="${imgPath}";
    $(document).ready(function(){
        var aoColumns = [
            {
                "sDefaultContent": "姓名",
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
                "sDefaultContent": "图片",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    return "<img src=\""+imgpath+row.headPortrait +"\"  style=\"width:50px;height:50px\"/>";
                }
            },
            {
                "sDefaultContent": "督导编号",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.supervisorNum != null) {
                        return row.supervisorNum;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "登录名",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.name != null) {
                        return row.crmLogin;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "省",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.province != null) {
                        return row.province;
                    } else {
                        return "";
                    }
                }
            },{
                "sDefaultContent": "市",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.city != null) {
                        return row.city;
                    } else {
                        return "";
                    }
                }
            },{
                "sDefaultContent": "区",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.county != null) {
                        return row.county;
                    } else {
                        return "";
                    }
                }
            },{
                "sDefaultContent": "督导部门",
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
                "sDefaultContent": "督导员角色",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.roleName != null) {
                        return row.roleName;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "电话",
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
                    if (row.status == '0') {
                        return "<span class=\"label label-success radius\">已启用</span>";
                    } else {
                        return "<span class=\"label label-defaunt radius\">已停用</span>";
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
                    var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"role_edit('编辑督导员','${context_root}/crmUser/updateSpervistorInfoView.action?supervisorId=" + row.supervisorId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
                    var toEditTwo = "<a style=\"text-decoration:none\" onClick=\"supervisorUpdatePwd(this,\'" + row.supervisorId + "\')\" href=\"javascript:;\" title=\"重置密码\"><span style='color: #0e90d2 '>重置密码</span></a>";
                    var toEditThree= "<a title=\"业务转交\" href=\"javascript:;\" onclick=\"store_supervistor('业务转交','${context_root}/crmUser/crmUserStoreUrl.action?supervisorIdOne=" + row.supervisorNum + "','','700')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>业务转交</span></a>";
                    return statusTools(row) +"&nbsp;&nbsp;" + toEdit +"&nbsp;&nbsp;"+toEditTwo+"&nbsp;&nbsp;"+toEditThree;
                }
            },
        ];
        var url = "${context_root}/crmUser/userCRMView.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });
    $(function(){
        query();
    })
    function statusTools(row) {
        if (row.status == '0') {
            return "<a style=\"text-decoration:none\" onClick=\"supervisor_stop(this,\'" + row.supervisorId + "\')\" href=\"javascript:;\" title=\"停用\"><span style='color: #0e90d2 '>停用</span></a>";
        } else {
            return "<a style=\"text-decoration:none\" onClick=\"supervisor_start(this,\'" + row.supervisorId + "\')\" href=\"javascript:;\" title=\"启用\"><span style='color: #0e90d2 '>启用</span></a>";
        }
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
        parent.layer.confirm('确认要启用吗？', {icon: 3, title: '提示'}, function (index) {
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
                }
            });

        });
    }

    function query() {
        var status = $("#statusSelect option:selected").val();
        var userName =$("#userName").val();
        pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/crmUser/userCRMView.action?status="+status+"&userName="+userName);
        pageTable.fnClearTable(0);
        pageTable.fnDraw();
    }

    /*重置密码123456*/
    function supervisorUpdatePwd(obj,id){
        parent.layer.confirm('确认要将密码重置为“123456”吗？',{icon: 3, title:'提示'},function(index){
            $.ajax({
                url:"${context_root}/crmUser/updateSupervistorStatusView.action?supervisorId=" + id+"&crmPwd=123456",
                type:'post',
                async:true ,
                cache:false ,
                dataType:"json",
                success:function(data){
                    if(data.s == true){
                        parent.layer.msg('密码已重置为“123456!”', {icon: 6, time: 1500});
                    }else{
                        parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
                    }
                },

            }) ;
        });
    }
    
    /*门店-业务员*/
    function store_supervistor(title,url,w,h){
        layer_show(title,url,w,h);
    }
</script>
</body>