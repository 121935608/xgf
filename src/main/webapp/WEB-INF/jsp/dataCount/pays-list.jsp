<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="收银支付流水"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据统计 <span class="c-gray en">&gt;</span> 收银支付流水 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
			   class="input-text Wdate" style="width:120px;" placeholder="开始时间">
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
			   class="input-text Wdate" style="width:120px;" placeholder="结束时间">
		<span class="select-box" style="width: 120px;">
           <select name="statusSelect" id="statusSelect" class="select" autocomplete="off">
               <option value="">支付方式</option>
               <option value="0">现金</option>
               <option value="1">支付宝</option>
               <option value="2" >微信</option>
               <option value="3" >银联</option>
               <option value="4" >京东白条</option>
           </select>
       </span>
		<input type="text" class="input-text" style="width:250px" placeholder="交易号|商铺名称" id="storeName" name="storeName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-icon<%----%>font">&#xe665;</i> 搜索</button>
		<button type="button" class="btn btn-success radius" onclick="excel_out()"><i class="Hui-iconfont">&#xe665;</i>下载</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="10%">交易号</th>
				<th width="15%">创建时间</th>
				<th width="15%">商铺名称</th>
				<th width="15%">收款金额(元) </th>
				<th width="10%">支付方式</th>
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
            "sDefaultContent": "交易号",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.tradeCode != null) {
                    return row.tradeCode;
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
            "sDefaultContent": "收款金额",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.money != null) {
                    return row.money;
                } else {
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
        	if (row.payType == 0) {
                return "现金";
            }else if (row.payType == 1) {
                return "支付宝";
            } else if(row.payType == 2) {
                return "微信";
			}else if(row.payType == 3){
                return "银联";
			}else if(row.payType == 4){
                return "京东白条";
            }else {
                return "";
            }
        }
    },
    ];
    var url = "${context_root}/dataCount/findALLPays.action";
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
    var payType = $("#statusSelect option:selected").val();
	var storeName =$("#storeName").val();
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/dataCount/findALLPays.action?beginTime="+beginTime+"&endTime="+endTime+"&payType="+payType+"&storeName="+storeName);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}



function excel_out() {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var payType = $("#statusSelect option:selected").val();
    var storeName =$("#storeName").val();
    <%--$.ajax({--%>
		<%--url:"${context_root}/dataCount/downloadData.action",--%>
		<%--data:"--%>
	<%--});--%>
    var elemIF = document.createElement("iframe");
    elemIF.src = "${context_root}/dataCount/downloadData.action?beginTime="+beginTime+"&endTime="+endTime+"&payType="+payType+"&storeName="+storeName
    elemIF.style.display = "none";
    document.body.appendChild(elemIF);

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