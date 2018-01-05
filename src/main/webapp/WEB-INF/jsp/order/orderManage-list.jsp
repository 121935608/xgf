<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="督导员管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
			   class="input-text Wdate" style="width:120px;" placeholder="开始时间">
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
			   class="input-text Wdate" style="width:120px;" placeholder="结束时间">
		<span class="select-box" style="width: 120px;">
           <select name="payType" id="payType" class="select" autocomplete="off">
               <option value="">支付状态</option>
               <option value="4">已支付</option>
               <option value="1" >未支付</option>
           </select>
       </span>
		<span class="select-box" style="width: 120px;">
           <select name="platform" id="platform" class="select" autocomplete="off">
               <option value="">订单来源</option>
               <option value="Android">Android</option>
               <option value="iOS" >IOS</option>
           </select>
       </span>
		<span class="select-box" style="width: 120px;">
           <select name="orderStatus" id="orderStatus" class="select" autocomplete="off">
               <option value="">订单状态</option>
               <option value="1">待支付</option>
               <option value="2" >待发货</option>
               <option value="3" >待收货</option>
               <option value="4" >待还款</option>
               <option value="5" >已还款</option>
           </select>
       </span>
		<input type="text" class="input-text" style="width:250px" placeholder="订单号|会员" id="orderNumber" name="orderNumber">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">订单号</th>
				<th width="10%">下单时间</th>
				<th width="10%">付款时间</th>
				<th width="8%">支付状态</th>
				<th width="6%">会员</th>
				<th width="8%">订单金额</th>
				<th width="8%">订单来源</th>
				<th width="8%">订单状态</th>
				<th width="15%">操作</th>
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
            "sDefaultContent": "订单号",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.orderNumber != null) {
                    return row.orderNumber;
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "下单时间",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.orderTime != null) {
                    return formatDate(row.orderTime, "yyyy-MM-dd hh:mm:ss");
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "付款时间",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.payTime != null) {
                    return formatDate(row.payTime,"yyyy-MM-dd hh:mm:ss");
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "支付状态",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.orderStatus !=null) {
                       if(row.orderStatus==1){
                           return "待支付";
					   }else if (row.orderStatus==2){
                           return "待发货";
					   }
					   else if(row.orderStatus==3){
                           return "待收货";
					   }else if (row.orderStatus==4){
                           return "待还款";
					   }else if(row.orderStatus==5){
                           return "已还款";
					   }
                } else {
                    return "";
                }
            }
        },
		{
			"sDefaultContent": "会员",
			"bSortable" : false,
			"sClass": "td-status text-c",
			"bSearchable": false,
			"mRender": function(data, type, row) {
				if (row.userName !=null) {
					return row.userName;
				}else {
					return "";
				}
			}
		},
        {
            "sDefaultContent": "订单金额",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.orderPrice !=null) {
                    return row.orderPrice/100;
                }else {
                    return 0;
                }
            }
        },
        {
            "sDefaultContent": "订单来源",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.platform!=null){
                    if (row.platform=='Android') {
                        return "Android";
                    } else if(row.platform == 'ios') {
                        return "IOS";}
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "订单状态",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.orderStatus == 1) {
                    return "待支付";
                } else if(row.orderStatus == 2) {
                    return "待发货";
                }else if(row.orderStatus == 3) {
                    return "待收货";
                }else if(row.orderStatus == 4) {
                    return "待还款";
                }else if(row.orderStatus == 5) {
                    return "已还款";
                }else {
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
                var tolook = "<a title=\"查看\" href=\"javascript:;\" onclick=\"order_look('查看','${context_root}/order/toLookOrderInfo.action?orderNumber=" + row.orderNumber + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>查看</span></a>";
                //确认收货
                var toConfirm = "<a title=\"确认收货\" href=\"javascript:;\" onclick=\"role_edit('确认收货','${context_root}/system/toRoleModify.action?roleId=" + row.roleId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">确认收货</a>";
                //打印配送单
                var toPrint = "<a title=\"打印配送单\" href=\"javascript:;\" onclick=\"role_Authorize('打印配送单','${context_root}/system/toRoleAuthorize.action?roleId=" + row.roleId + "','230','406')\" class=\"ml-5\" style=\"text-decoration:none\">打印配送单</a>";
                return tolook  + "&nbsp;&nbsp;" + statusTools(row) + "&nbsp;&nbsp;" + toConfirm+ "&nbsp;&nbsp;" + toPrint;
            }
        },
    ];
    var url = "${context_root}/order/findOrderManageList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function statusTools(row) {
    if (row.orderStatus == '2') {
        return "<a style=\"text-decoration:none\" onclick=\"order_send('发送','${context_root}/order/toSendOrder.action?orderNumber=" + row.orderNumber + "','','510')\" href=\"javascript:;\" title=\"发送\"><span style='color: #0e90d2 '>发送</span></a>";
    } else {
        return "<a style=\"cursor: default;\" onClick=\"return false;\" href=\"javascript:return false;;\" title=\"发送\">发送</a>";
    }
}

function query() {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var payType = $("#payType option:selected").val();
    var platform = $("#platform option:selected").val();
    var orderStatus = $("#orderStatus option:selected").val();
	var orderNumber =$("#orderNumber").val();
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/order/findOrderManageList.action?beginTime="+beginTime+"&endTime="+endTime+"&payType="+payType+"&platform="+platform+"&orderStatus="+orderStatus+"&orderNumber="+orderNumber);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*订单-查看*/
function order_look(title,url,w,h){
	layer_show(title,url,w,h);
}

/*角色-编辑*/
function order_send(title,url,w,h){
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