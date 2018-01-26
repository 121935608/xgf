<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="会员资料"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 会员/用户管理 <span class="c-gray en">&gt;</span> 会员资料 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <span class="select-box" style="width: 120px;">
           <select name="memberLevel" id="memberLevel" class="select" autocomplete="off">
               <option value="">会员等级</option>
               <option value="A">A</option>
               <option value="B">B</option>
               <option value="C">C</option>
               <option value="D">D</option>
               <option value="E">E</option>
           </select>
        </span>
        <span class="select-box" style="width: 120px;">
           <select name="timeLimit" id="timeLimit" class="select" autocomplete="off">
               <option value="">有效情况</option>
               <option value="0">未过期</option>
               <option value="1">过期</option>
           </select>
        </span>
       <%-- <div class="col-xs-6 col-sm-4 .col-md-4" > 日期范围：
            <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
            -
            <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
        </div>--%>
        <input type="text" style="width: 250px;" class="input-text" placeholder="会员号|姓名" id="memberNo"
               name="memberNo">
        <button type="button" onclick="query()" class="btn btn-success radius"><i class="Hui-iconfont">&#xe665;</i> 搜索
        </button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l"><a href="javascript:;" onclick="member_add('添加会员','${context_root}/member/toMmeberAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加会员</a></span>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort" style="table-layout:fixed;">
            <thead>
            <tr class="text-c">
                <th width="8%">会员号</th>
                <th width="8%">姓名</th>
                <th width="10%">会员等级</th>
                <th width="10%">积分 </th>
                <th width="10%">开卡日期</th>
                <th width="15%">有效情况</th>
                <th width="8%">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript"  charset="UTF-8">
    var pageTable;
    $(document).ready(function(){
        var aoColumns = [
            {
                "mData": "会员号",
                "bSortable" : false,
                "sClass": "text-c",
                "mRender": function(data, type, row) {
                    if (row.memberNo != null) {
                        return row.memberNo;
                    } else {
                        return "";
                    }
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
                "mData": "会员等级",
                "bSortable" : false,
                "sClass": "text-c",
                "mRender": function(data, type, row) {
                    if (row.level != null) {
                        return row.level;
                    } else {
                        return "";
                    }
                }
            },
            {
                "mData": "积分",
                "bSortable" : false,
                "sClass": "text-c",
                "mRender": function(data, type, row) {
                    if (row.score != null) {
                        return row.score;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "开卡日期",
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
                "mData": "有效情况",
                "bSortable" : false,
                "sClass": "text-c ",
                "mRender": function(data, type, row) {
                    if (row.isLimit != null) {
                        return row.isLimit;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "操作",
                "bSortable" : false,
                "sClass": "td-manage text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    //查看
                    var toLook = "<a title=\"查看\" href=\"javascript:;\" onclick=\"member_look('查看','${context_root}/merchant/checkCertification.action?storeId=" + row.storeId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>查看</span></a>";
                    return toLook;
                }
            },
        ];
        var url = "${context_root}/member/membershipList.action?storeId=${storeId}";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);

    });

    function query() {
        var memberLevel=$("#memberLevel option:selected").val();
        var beginTime="";
        var endTime="";
        var timeLimit=$("#timeLimit option:selected").val();
        var memberNo=$("#memberNo").val();
        if (timeLimit=="0"){
            beginTime="未过期";
        }else if (timeLimit =="1"){
            endTime="过期";
        }
        pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/member/membershipList.action?memberLevel=" + memberLevel+"&beginTime="+beginTime+"&endTime="+endTime+"&memberNo="+memberNo+"&storeId=${storeId}");
        pageTable.fnClearTable(0);
        pageTable.fnDraw();
    }


    /*添加会员*/
    function member_add(title,url,w,h){
        layer_show(title,url,w,h);
    }


    function sign(){
        {
            var data={
                "account": "test@bestsign.cn",
                "mail": "test@bestsign.cn",
                "mobile": "13800001234",
                "name": "杭州上上签",
                "userType": "2"
            }
            $.ajax({
                url:"https://openapi.bestsign.cn/openapi/v3/user/reg/",
                type:'post',
                async:true ,
                cache:false ,
                data:data,
                dataType:"json",
                success:function(data){
                    alert(22222);
                },
                error:function(data){debugger
                    alert(data);
                }
            });
        }
    }


</script>
</body>
</html>