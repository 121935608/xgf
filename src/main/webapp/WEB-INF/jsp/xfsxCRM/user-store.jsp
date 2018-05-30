<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="成员列表"/>
<body>
<div class="page-container">
    <div class="text-c">
        <input id="supervisorIdOne" name="" value="${supervisor['supervisorNum']}" style="display: none;">
        <input type="text" class="input-text" style="width:250px" placeholder="姓名|督导员编号|电话" id="userName" name="userName">
        <button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th style="width: 7%;height: 20px;"><input type="radio" name="selAll" value="" disabled="true"/></th>
                <th width="8%">姓名</th>
                <th width="7%">省</th>
                <th width="7%">市</th>
                <th width="7%">区</th>
                <th width="10%">督导员编号</th>
                <th width="10%">督导员部门</th>
                <th width="15%">电话 </th>
                <th width="10%">状态</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div style="margin-top: 20px;margin-left:300px;">
    <div class="btn btn-primary" id="save" state="${supervisor['supervisorNum']}">提交</div>
</div>
<script type="text/javascript">
    var pageTable;
    $(document).ready(function(){
        var aoColumns = [
            {
                "sDefaultContent": "复选",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    return "<input class= \"userName\" type=\"radio\" name=\"checkName\" value="+row.supervisorNum+"></input>";
                }
            },
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
            },
            {
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
            },
            {
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
            }
        ];
        var url = "${context_root}/crmUser/userCRMView.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });
    function updateSupervisor(supervisorIdOne){
        alert(supervisorIdOne);
    }
    
    function query() {
        var userName = $("#userName").val();
        pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/crmUser/userCRMView.action?userName=" + userName);
        pageTable.fnClearTable(0);
        pageTable.fnDraw();
    }
    
    $(function(){
        $("#save").click(function(){
            var supervisorIdOne = $(this).attr("state");;
            var supervisorId = $('input[name="checkName"]:checked ').val();
            if(supervisorId != undefined && supervisorId != null){
                $.ajax({
                    url:"${context_url}/crmUser/crmUpdateStoreSupervistor.action?supervisorIdOne=" + supervisorIdOne +"&supervisorId=" + supervisorId,
                    type:'post',
                    async:true ,
                    cache:false ,
                    dataType:"json",
                    success:function(data){
                        if(data.s == true){
                            parent.layer.msg("保存成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
                                window.parent.location.reload();
                            }) ;
                        }else{
                            parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
                        }
                    }
                })
            }else{
            	parent.layer.alert("您还未勾选督导员！" , {icon: 2,title:"系统提示"});
            }
        })
    })
</script>
</body>