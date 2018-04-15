<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="督导员管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span> 订单管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
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
               <option value="iOS" >iOS</option>
           </select>
       </span>
		<span class="select-box" style="width: 120px;">
           <select name="orderStatus" id="orderStatus" class="select" autocomplete="off">
               <option value="">订单状态</option>
               <option value="1">待支付</option>
               <option value="2" >待发货</option>
               <option value="3" >待收货</option>
               <option value="4" >已完成</option>
               <option value="5" >已收款</option>
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
				<th width="8%">支付方式</th>
				<th width="8%">操作</th>
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
                           return "未支付";
					   }else if (row.orderStatus==2){
                           return "已支付";
					   }
					   else if(row.orderStatus==3){
                           return "已支付";
					   }else if (row.orderStatus==4){
                           return "已支付";
					   }else if(row.orderStatus==5){
                           return "已支付";
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
                    return row.orderPrice;
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
                    if (row.platform=="Android") {
                        return "Android";
                    } else if(row.platform =="iOS") {
                        return "iOS";}
                }else {
                    return "";
                }

//                row.platform;
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
                    return "已完成";
                }else if(row.orderStatus == 5) {
                    return "已收款";
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "支付方式",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                console.log(row.payCode);
                if(row.payCode =="WX") {
                    return "微信";
                }else if (row.payCode =="CL"){
                    return "额度支付";
                }else if (row.payCode =="ZFB"){
                    return "支付宝";
                }else if (row.payCode =="HDFK"){
                    return "货到付款";
                }else{
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
                //打印配送单
                var toPrint = "<a title=\"打印配送单\" href=\"javascript:;\" onclick=\"order_print('打印配送单','${context_root}/order/toPrintOrder.action?orderNumber=" + row.orderNumber + "','230','406')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>打印配送单</span></a>";
                var toEdit=""
                if(row.payCode=="HDFK"&&row.orderStatus==4){
					toEdit = "<a title=\"确认收款\" href=\"javascript:;\" onclick=\"pay_edit(this,\'" + row.orderNumber + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>确认收款</span></a>";
                }
                //对账
                <%--var toEdit = "<a title=\"对账\" href=\"javascript:;\" onclick=\"repay_edit('对账','${context_root}/merchant/toRepayModify.action?repayId=" + row.repayId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>对账</span></a>";--%>
                return tolook+toEdit;
            }
        },
    ];
    var url = "${context_root}/order/findOrderManageList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function pay_edit(obj,id){
    parent.layer.confirm('确认已收款吗？',{icon: 3, title:'提示'},function(index){
        $.ajax({
            url:"${context_root}/order/payOrder.action?orderNumber=" + id,
            type:'post',
            async:true ,
            cache:false ,
            dataType:"json",
            success:function(data){
                if(data.s == true){
                    $(obj).parents("tr").remove();
                    parent.layer.msg('已收款!',{icon:1,time:1000});
                    loadData() ;
                }else{
                    parent.layer.msg("操作失败" , {icon: 2,title:"系统提示"});
                }
            },

        }) ;
    });
}

//发货
function sendTools(row) {
    if (row.orderStatus == '2') {
        return "<a style=\"text-decoration:none\" onclick=\"order_send('发送','${context_root}/order/toSendOrder.action?orderNumber=" + row.orderNumber + "','','510')\" href=\"javascript:;\" title=\"发送\"><span style='color: #0e90d2 '>发送</span></a>";
    } else {
        return "<a style=\"cursor: default;\" title=\"发送\">发送</a>";
    }
}
//确认收货
function statusTools(row) {
    if (row.orderStatus == '3') {
        return "<a style=\"text-decoration:none\" onClick=\"order_confirm(this,\'" + row.orderNumber + "\')\" href=\"javascript:;\" title=\"确认收货\"><span style='color: #0e90d2 '>确认收货</span></a>";
    } else {
        return "<a style=\"cursor: default;\" title=\"确认收货\">确认收货</a>";
    }
}

function repay_edit(title,url,w,h){
    layer_show(title,url,w,h);
}

//打印配送单
function printTools(row) {
	if(row.orderStatus !=1){
	    return "<a style=\"text-decoration:none\" onclick=\"order_print('打印配送单','${context_root}/order/toPrintOrder.action?orderNumber=" + row.orderNumber + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>打印配送单</span></a>";
	}else {
	    return "<a style=\"cursor: default;\" title=\"打印配送单\">打印配送单</a>";
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

/*订单-发货*/
function order_send(title,url,w,h){
	layer_show(title,url,w,h);
}

/*订单-打印*/
function order_print(title,url,w,h){
	layer_show(title,url,w,h);
}

/*停用*/
function order_confirm(obj,orderNumber){
    parent.layer.confirm('确认要停用吗？',{icon: 3, title:'确认收货后开始计算贷款日期'},function(index){
        $.ajax({
            url:"${context_root}/order/toOrderConfirm.action?orderNumber=" +orderNumber+"&orderStatus=4",
            type:'post',
            async:true ,
            cache:false ,
            dataType:"json",
            success:function(data){
                if(data.s == true){
                    $(obj).html('<a style="cursor: default;" title="确认收货">确认收货</a>');
                    parent.layer.msg('已收货!', {icon: 6,time:1000});
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