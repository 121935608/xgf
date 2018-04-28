<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="企业认证申请表"/>
<body>
<style>
	#show:hover{cursor:pointer;color:blue;}
	#hide:hover{cursor:pointer;color:blue;}
</style>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商户相关 <span class="c-gray en">&gt;</span> 还款计划表11 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container"> 
	<div class="text-c">
		<span class="select-box" style="width: 120px;">
           <select name="dateType" id="dateType" class="select" autocomplete="off">
               <option value="">日期类型</option>
               <option value="addTime">创建时间</option>
               <option value="repayDate">还款日期</option>
           </select>
       </span>
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
			   class="input-text Wdate" style="width:120px;" placeholder="开始时间">
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
			   class="input-text Wdate" style="width:120px;" placeholder="结束时间">
       <span class="select-box" style="width: 120px;">
           <select name="status" id="status" class="select">
               <option value="">状态</option>
               <option value="0">待还款</option>
               <option value="1">已还款</option>
               <option value="4" >待处理</option>
               <option value="2" >处理中</option>
           </select>
       </span>
		<input type="text" class="input-text" style="width:250px" placeholder="还款单号|订单号|店铺名称" id="fuzzyCondition" name="fuzzyCondition">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		<button type="button" class="btn btn-success radius" onclick="excel_out()"><i class="Hui-iconfont">&#xe665;</i> 导出</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c" id="thead">
				<th width="14%">还款单号</th>
				<th width="10%">创建时间</th>
				<th width="12%">订单号</th>
				<th width="12%">店铺名称</th>
				<th width="10%">还款日期</th>
				<th width="6%">应还金额（元）</th>
				<th width="6%">已还金额（元）</th>
				<th width="6%">结余金额（元）</th>
				<th width="6%">状态</th>
				<%--<th width="6%" id="do">操作</th>--%>
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
        "mData": "repayNo",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.repayNo != null) {
                return "<span style=\"color:blue;\" id=\"show\" onClick=\"getRepayDetail(this,\'" + row.repayId + "\')\">+</span><span id=\"hide\" style=\"display:none;color:blue;\" onClick=\"hideRows(this,\'" + row.repayId + "\')\">-</span>&nbsp;&nbsp;<span>"+row.repayNo+"</span>";
            } else {
                return "";
            }
        }
    },
    {
        "sDefaultContent": "创建时间",
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
        "mData": "orderNumber",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.orderNumber != null) {
                return row.orderNumber;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "storeName",   
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.storeName != null) {
                return row.storeName;
            } else {
                return "";
            }
        }
    },
    {
        "sDefaultContent": "还款日期",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.planRepayDate != null) {
            	return formatDate(row.planRepayDate,"yyyy-MM-dd hh:mm:ss");
            } else {
                return "";
            }
        } 
    }, 
    {
        "mData": "planTotal",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.planTotal != null) {
                return row.planTotal;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "repayMoney",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.repayMoney != null) {
                return row.repayMoney;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "withholdMoney",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.withholdMoney != null) {
                return row.withholdMoney;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "status",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if ((row.status == 0) || (row.status == 3)) {
                return "待还款";
            } else if(row.status == 1){
            	return "已还款";
            }else if(row.status == 2){
            	return "处理中";
            }else if(row.status == 4){
            	return "待处理";
            }else {
                return "";
            }
        }
    },
    <%--{--%>
        <%--"sDefaultContent": "",--%>
        <%--"bSortable" : false,--%>
        <%--"sClass": "td-manage text-c",--%>
        <%--"bSearchable": false,--%>
        <%--"mRender": function(data, type, row) {--%>
        	<%--var type = '${type}';--%>
        	<%--if(type == 'B'){--%>
        		<%--$("thead").children("tr").children("th:last").css("display","none");--%>
        		<%--$('tr').find('td:last').css("display","none");--%>
        		<%--return;--%>
        	<%--}--%>
        	<%--if((row.status == 1) || (row.status == 2) || (row.status == 4)){--%>
        		<%--return "";--%>
        	<%--}--%>
            <%--//对账--%>
            <%--var toEdit = "<a title=\"对账\" href=\"javascript:;\" onclick=\"repay_edit('对账','${context_root}/merchant/toRepayModify.action?repayId=" + row.repayId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>对账</span></a>";--%>
        	<%--return  toEdit ;--%>
        <%--}--%>
    <%--}--%>
    ];
    var url = "${context_root}/merchant/repaymentList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url); 
    
});

function query() {
	var fuzzyCondition = $("#fuzzyCondition").val();
	var dateType = $("#dateType").val();
    var status = $("#status").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    if(((beginTime != "") || (endTime != "")) && (dateType == "")){
    	alert("请选择日期类型！");
    	return;
    }
    pageTable.fnSettings().sAjaxSource = "${context_root}/merchant/repaymentList.action?fuzzyCondition=" + encodeURIComponent(encodeURIComponent(fuzzyCondition)) +
					    	"&dateType="+ dateType +
					    	"&status="+ status +
					    	"&beginTime="+ beginTime +
					    	"&endTime="+ endTime;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


function excel_out() {
    var fuzzyCondition = $("#fuzzyCondition").val();
    var dateType = $("#dateType").val();
    var status = $("#status").val();
    var p = "&fuzzyCondition="+fuzzyCondition+"&dateType="+dateType+"&status="+status
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var elemIF = document.createElement("iframe");
    elemIF.src = "${context_root}/merchant/downloadFinancialData.action?beginTime="+beginTime+"&endTime="+endTime+p;
    elemIF.style.display = "none";
    document.body.appendChild(elemIF);
}






  function getRepayDetail(obj,repayId){
		$.ajax({
			url:"${context_root}/merchant/getRepayDetail.action?repayId="+repayId,
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				$(obj).parent("td").parent("tr").parent("tbody").children("tr").each(function(i){                   // 遍历 tr
					   $(this).children("td").eq(0).children("span").first().css("display","inline");
					   $(this).children("td").eq(0).children("span").eq(1).css("display","none");
					   var oth = $("tbody").children("tr[role=row]");
						$("tbody").children("tr").not(oth).css("display","none");
				});
				for(var i=data.length-1;i>=0;i--){
					var type = "";
					if(data[i].repayType == 1){
						type = "主动还款";
					}else if(data[i].repayType == 2){
						type = "财务结算";
					}
					if(i==data.length-1)
						var tr = "<tr><td colspan=\"2\" style=\"text-align:center;border-top:0px;border-left:0px;\">"+formatDate(data[i].repayTime,"yyyy-MM-dd hh:mm:ss")+"</td>"
						+"<td style=\"border-left:0px;border-top:0px;text-align:center;\">"+type+"</td>"
						+"<td colspan=\"2\" style=\"border-left:0px;border-top:0px;text-align:center;\">"+data[i].repayMoney+"</td>"
						+"<td colspan=\"3\" style=\"border-top:0px;border-left:0px;text-align:center;\">"+(data[i].remark==null?"":data[i].remark)+"</td>"
						+"<td style=\"border-left:0px;border-top:0px;\"></td>"
						+"<td style=\"border-top:0px;border-left:0px;\"></td></tr>";
						else var tr = "<tr><td colspan=\"2\"  style=\"text-align:center;border-top:0px;border-left:0px;border-bottom:0px;\">"+formatDate(data[i].repayTime,"yyyy-MM-dd hh:mm:ss")+"</td>"
						+"<td style=\"border-left:0px;border-top:0px;text-align:center;border-bottom:0px;\">"+type+"</td>"
						+"<td colspan=\"2\" style=\"border-left:0px;border-top:0px;text-align:center;border-bottom:0px;\">"+data[i].repayMoney+"</td>"
						+"<td colspan=\"3\" style=\"border-top:0px;border-left:0px;text-align:center;border-bottom:0px;\">"+(data[i].remark==null?"":data[i].remark)+"</td>"
						+"<td style=\"border-bottom:0px;border-left:0px;border-top:0px;\"></td>"
						+"<td style=\"border-top:0px;border-left:0px;border-bottom:0px;\"></td></tr>";
					$(obj).parent("td").parent("tr").after(tr);
				}
				if(data.length == 0){
					var tr = "<tr><td  colspan=\"2\" style=\"text-align:center;border-left:0px;\">时间</td>"
						+"<td style=\"border-left:0px;text-align:center;\">结算方式</td>"
						+"<td colspan=\"2\" style=\"border-left:0px;text-align:center;\">结算金额</td>"
						+"<td colspan=\"3\" style=\"border-left:0px;text-align:center;\">备注</td>"
						+"<td style=\"border-left:0px;text-align:center;\"></td>"
						+"<td style=\"border-left:0px;\"></td></tr>";
				}else{
					var tr = "<tr></td><td colspan=\"2\" style=\"text-align:center;border-bottom:0px;border-left:0px;\">时间</td>"
					+"<td style=\"border-left:0px;border-bottom:0px;text-align:center;\">结算方式</td>"
					+"<td colspan=\"2\" style=\"border-left:0px;border-bottom:0px;text-align:center;\">结算金额</td>"
					+"<td colspan=\"3\" style=\"border-left:0px;text-align:center;border-bottom:0px;\">备注</td>"
					+"<td style=\"border-left:0px;border-bottom:0px;text-align:center;\"></td>"
					+"<td style=\"border-bottom:0px;border-left:0px;\"></td></tr>";
				}
				/* 追加行 */
				$(obj).parent("td").parent("tr").after(tr);
				/* 隐藏所有行的+ */
				$(obj).parent("td").children("span").first().css("display","none");
				/* 该行的+变- */
				$(obj).parent("td").children("span").eq(1).css("display","inline");
				
			},
		});
  }
/*对账*/
function repay_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
function hideRows(obj){
	var oth = $("tbody").children("tr[role=row]");
	$("tbody").children("tr").not(oth).css("display","none");
	$(obj).parent("td").children("span").first().css("display","inline");
	$(obj).parent("td").children("span").eq(1).css("display","none");
}
</script> 
</body>
</html>