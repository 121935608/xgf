<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="会员资料"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 会员/用户管理 <span class="c-gray en">&gt;</span> 会员资料 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l"><a href="javascript:;" onclick="level_add('添加等级','${context_root}/member/toLevelAdd.action','','410')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加会员等级</a></span>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort" style="table-layout:fixed;">
            <thead>
            <tr class="text-c">
                <th width="10%">会员等级</th>
                <th width="8%">升级积分</th>
                <th width="8%">会员折扣</th>
                <th width="10%">升级方式</th>
                <th width="10%">操作</th>
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
                "mData": "会员等级",
                "bSortable" : false,
                "sClass": "text-c",
                "mRender": function(data, type, row) {
                    if (row.levelNo != null) {
                        return row.levelNo;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "升级积分",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.score != null) {
                        return row.score;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "会员折扣",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.discount != null) {
                        return row.discount;
                    } else {
                        return "";
                    }
                }
            },
            {
                "mData": "升级方式",
                "bSortable" : false,
                "sClass": "text-c",
                "mRender": function(data, type, row) {
                    if (row.addWay != null) {
                        return row.addWay;
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
                    var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"level_edit('编辑','${context_root}/member/toEditLevel.action?levelId=" + row.levelId + "','','410')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
                    return toEdit;
                }
            },
        ];
        var url = "${context_root}/member/levelList.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);

    });

    /*添加等级*/
    function level_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*编辑等级*/
    function level_edit(title,url,w,h){
        layer_show(title,url,w,h);
    }

</script>
</body>
</html>