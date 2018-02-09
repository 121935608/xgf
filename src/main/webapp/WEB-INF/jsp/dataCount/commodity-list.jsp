<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品销售统计表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据统计 <span class="c-gray en">&gt;</span> 商品销售统计 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
			   class="input-text Wdate" style="width:120px;" placeholder="开始时间">
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
			   class="input-text Wdate" style="width:120px;" placeholder="结束时间">
		<input type="text" class="input-text" style="width:250px" placeholder="商品名称|商品编号" id="commodityName" name="commodityName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">商品名称</th>
				<th width="10%">商品编号</th>
				<th width="10%">单位</th>
				<th width="10%">销售数量</th>
				<th width="10%">销售金额(元) </th>
				<th width="10%">进价(元)</th>
				<th width="10%">纳税(元)</th>
				<th width="10%">利润(元)</th>
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
            "sDefaultContent": "商品名称",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.commodityName != null) {
                    return row.commodityName;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "商品条码",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.commodityNo != null) {
                    return row.commodityNo;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "单位",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.unit != null) {
                    return row.unit;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "销售数量",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.saleNum != null) {
                    return row.saleNum;
                } else {
                    return 0;
                }
            }
        },
        {
            "sDefaultContent": "销售金额",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.totalPrice != null) {
                    return row.totalPrice;
                } else {
                    return 0;
                }
            }
        },
    {
        "sDefaultContent": "进价",
        "bSortable" : false,
        "sClass": "td-status text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.totalInPrice !=null) {
                return row.totalInPrice;
            }else {
                return 0;
            }
        }
    },
	{
		"sDefaultContent": "纳税",
		"bSortable" : false,
		"sClass": "td-status text-c",
		"bSearchable": false,
		"mRender": function(data, type, row) {
			if (row.totalPrice !=null) {
				return (row.totalPrice*0.17).toFixed(2);
			}else {
				return 0;
			}
		}
	},
	{
		"sDefaultContent": "利润",
		"bSortable" : false,
		"sClass": "td-status text-c",
		"bSearchable": false,
		"mRender": function(data, type, row) {
			if (row.totalPrice !=null) {
				return (row.totalPrice-row.totalInPrice-row.totalPrice*0.17).toFixed(2);
			}else {
				return 0;
			}
		}
	},
    ];
    var url = "${context_root}/dataCount/findCommodityInfo.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function statusTools(row) {
    if (row.status == '0') {
        return "<a style=\"text-decoration:none\" onClick=\"user_stop(this,\'" + row.documentId + "\')\" href=\"javascript:;\" title=\"停用\">停用</a>";
    } else {
        return "<a style=\"text-decoration:none\" onClick=\"user_start(this,\'" + row.documentId + "\')\" href=\"javascript:;\" title=\"启用\">启用</a>";
    }
}

function query() {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
	var commodityName =$("#commodityName").val();
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/dataCount/findCommodityInfo.action?beginTime="+beginTime+"&endTime="+endTime+"&commodityName="+commodityName);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*角色-添加*/
function role_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*角色-编辑*/
function role_edit(title,url,w,h){
	layer_show(title,url,w,h);
}

/*角色-授权*/
function role_Authorize(title,url,w,h){
	layer_show(title,url,w,h);
}

/*停用*/
function user_stop(obj,id){
    parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
        $.ajax({
            url:"${context_root}/system/changeDocumentStatus.action?documentId=" + id +"&status=1",
            type:'post',
            async:true ,
            cache:false ,
            dataType:"json",
            success:function(data){
                if(data.s == true){
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="user_start(this,'+id+')" href="javascript:;" title="启用">启用</a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
                    $(obj).remove();
                    parent.layer.msg('已停用!',{icon: 5,time:1000});
                }else{
                    parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
                }
            },
        }) ;

    });
}

/*启用*/
function user_start(obj,id){
    parent.layer.confirm('确认要启用吗？',{icon: 3, title:'提示'},function(index){
        $.ajax({
            url:"${context_root}/system/changeDocumentStatus.action?documentId=" + id +"&status=0",
            type:'post',
            async:true ,
            cache:false ,
            dataType:"json",
            success:function(data){
                if(data.s == true){
                    $(obj).parents("tr").find(".td-manage").prepend('<a onClick="user_stop(this,'+id+')" href="javascript:;" title="停用" style="text-decoration:none">停用</a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
                    $(obj).remove();
                    parent.layer.msg('已启用!', {icon: 6,time:1000});
                }else{
                    parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
                }
            },
        }) ;

    });
}
</script> 
</body>
</html>