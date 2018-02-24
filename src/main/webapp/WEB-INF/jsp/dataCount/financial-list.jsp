<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="财务结算"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>数据统计 <span class="c-gray en">&gt;</span> <span id="cons"> </span> <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<style>
	#show:hover{cursor:pointer;color:blue;}
	#hide:hover{cursor:pointer;color:blue;}
</style>
	<div class="text-c">
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
			   class="input-text Wdate" style="width:120px;" placeholder="开始时间">
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
			   class="input-text Wdate" style="width:120px;" placeholder="结束时间">
		<%--<span class="select-box" style="width: 120px;">
           <select name="payType" id="payType" class="select" autocomplete="off">
               <option value="">支付方式</option>
               <option value="1">支付宝支付</option>
               <option value="2" >微信支付</option>
               <option value="3" >银联支付</option>
               <option value="4" >京东白条</option>
           </select>
       </span>--%>
		<input type="text" class="input-text" style="width:250px" placeholder="结算单号|商铺名称" id="storeName" name="storeName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="12%">结算单号</th>
				<th width="10%">创建日期</th>
				<th width="15%">商铺名称</th>
				<th width="8%">合计金额(元) </th>
				<th width="8%">手续费</th>
				<th width="8%">应结金额(元)</th>
				<th width="8%">已结金额(元)</th>
				<th width="8%">未结金额(元)</th>
				<th width="10%">操作</th>

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
            "sDefaultContent": "结算单号",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.amountNum != null) {
                    return "<span style=\"color:blue;\" id=\"show\" onClick=\"getDetail(this,\'" + row.amountId + "\')\">+</span><span id=\"hide\" style=\"display:none;color:blue;\" onClick=\"hideRows(this,\'" + row.amountId + "\')\">-</span>&nbsp;&nbsp;<span>"+row.amountNum+"</span>";
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "创建日期",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.addTime != null) {
                    return formatDate(row.addTime, "yyyy-MM-dd hh:mm:ss");
                } else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "商铺名称",
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
            "sDefaultContent": "合计金额",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.totalMoney != null) {
                    return row.totalMoney;
                } else {
                    return "";
                }
            }
        },
    {
        "sDefaultContent": "手续费",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.xzfRate !=null) {
                return row.xzfRate+"%";
            }else {
                return "";
            }
        }
    },
        {
            "sDefaultContent": "应结金额",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.amountMoney !=null) {
                    return row.amountMoney;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "已结金额",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
            	if (row.closeMoney !=null) {
                    return row.closeMoney;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "未结金额",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
            	if (row.openMoney !=null) {
                    return row.openMoney;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "",
            "bSortable" : false,
            "sClass": "td-manage text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
            	var type = '${type}';
            	if(type == 'B'){
            		$("thead").children("tr").children("th:last").css("display","none");
            		$('tr').find('td:last').css("display","none");
            		return;
            		/* $(".td-manage").css("display","none"); */
            	}
            	if(row.openMoney == "0"){
            		return "";
            	}
                //对账
                var toEdit = "<a title=\"对账\" href=\"javascript:;\" onclick=\"financial_edit('对账','${context_root}/dataCount/toFinancialModify.action?amountId=" + row.amountId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>对账</span></a>";
            	return  toEdit ;
            }
        }
    ];
    var url = "${context_root}/dataCount/findFinancialList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
    var type='${type}';
    if (type=='S'){
		$("#cons").text("商家收银结算");
	}else if (type=='B'){
        $("#cons").text("收银日结");
	}
});


function query() {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
	var storeName =$("#storeName").val();
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/dataCount/findFinancialList.action?beginTime="+beginTime+"&endTime="+endTime+"&status="+status+"&storeName="+storeName);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}

function getDetail(obj,amountId){
	$.ajax({
		url:"${context_root}/dataCount/getDetail.action?amountId="+amountId,
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
				if(i==data.length-1)
					var tr = "<tr><td colspan=\"2\" style=\"text-align:center;border-top:0px;border-left:0px;\">"+formatDate(data[i].addTime,"yyyy-MM-dd hh:mm:ss")+"</td>"
					+"<td style=\"border-left:0px;border-top:0px;text-align:center;\">"+data[i].money/100+"</td>"
					+"<td colspan=\"2\" style=\"border-left:0px;border-top:0px;text-align:center;\">"+data[i].userName+"</td>"
					+"<td colspan=\"2\" style=\"border-top:0px;border-left:0px;text-align:center;\">"+(data[i].remark==null?"":data[i].remark)+"</td>"
					+"<td style=\"border-left:0px;border-top:0px;\"></td>"
					+"<td style=\"border-top:0px;border-left:0px;\"></td></tr>";
					else var tr = "<tr><td colspan=\"2\" style=\"text-align:center;border-top:0px;border-left:0px;border-bottom:0px;\">"+formatDate(data[i].addTime,"yyyy-MM-dd hh:mm:ss")+"</td>"
					+"<td style=\"border-left:0px;border-top:0px;text-align:center;border-bottom:0px;\">"+data[i].money/100+"</td>"
					+"<td colspan=\"2\" style=\"border-left:0px;border-top:0px;text-align:center;border-bottom:0px;\">"+data[i].userName+"</td>"
					+"<td colspan=\"2\" style=\"border-top:0px;border-left:0px;text-align:center;border-bottom:0px;\">"+(data[i].remark==null?"":data[i].remark)+"</td>"
					+"<td style=\"border-bottom:0px;border-left:0px;border-top:0px;\"></td>"
					+"<td style=\"border-top:0px;border-left:0px;border-bottom:0px;\"></td></tr>";
					$(obj).parent("td").parent("tr").after(tr);
			}
			if(data.length == 0){
				var tr = "<tr><td colspan=\"2\" style=\"text-align:center;border-left:0px;\">时间</td>"
				+"<td style=\"border-left:0px;text-align:center;\">结算金额</td>"
				+"<td colspan=\"2\" style=\"border-left:0px;text-align:center;\">操作者</td>"
				+"<td colspan=\"2\" style=\"border-left:0px;text-align:center;\">备注</td>"
				+"<td style=\"border-left:0px;\"></td>"
				+"<td style=\"border-left:0px;\"></td></tr>";
			}else{
				var tr = "<tr><td colspan=\"2\" style=\"text-align:center;border-bottom:0px;border-left:0px;\">时间</td>"
				+"<td style=\"border-left:0px;border-bottom:0px;text-align:center;\">结算金额</td>"
				+"<td colspan=\"2\" style=\"border-left:0px;border-bottom:0px;text-align:center;\">操作者</td>"
				+"<td colspan=\"2\" style=\"border-left:0px;text-align:center;border-bottom:0px;\">备注</td>"
				+"<td style=\"border-left:0px;border-bottom:0px;\"></td>"
				+"<td style=\"border-bottom:0px;border-left:0px;\"></td></tr>";
			}
			$(obj).parent("td").parent("tr").after(tr);
			$(obj).parent("td").children("span").first().css("display","none");
			$(obj).parent("td").children("span").eq(1).css("display","inline");
			
		},
	});
}
/*对账*/
function financial_edit(title,url,w,h){
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