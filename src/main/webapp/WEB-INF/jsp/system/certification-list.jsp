<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="企业认证申请表"/>
<style>
	.text-z{
		overflow:hidden;
		text-overflow:ellipsis;
		white-space:nowrap;
	}
	.text-z:hover{
		height: auto;
		white-space: normal;
	}
</style>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商户相关 <span class="c-gray en">&gt;</span> 企业认证申请表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">

	<div style="min-height: 30px;">
		<form role="form" class="text-c">
		   <div class="row" >
		   		<div class="col-xs-6 col-sm-4 .col-md-4" > 日期范围：
					<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
					-
					<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
				</div>
		        <div class="row  col-xs-6 col-sm-4 .col-md-4" > 
					<div class="col-xs-6 col-sm-6 .col-md-6" >
						<y:select id="supervisor" name="supervisor" codeGroup="${supervisorList}" selectedValue=""
								cssClass="select" headerKey="" headerValue="督导员">
						</y:select>
					</div>
					<div class="col-xs-6 col-sm-6 .col-md-6" >
						<y:select id="repaymentStatus" name="repaymentStatus" codeGroup="${repaymentStatusList}" selectedValue=""
								cssClass="select" headerKey="" headerValue="状态">
						</y:select>
					</div>
				</div> 
				<div class="col-xs-3 col-sm-2 .col-md-2" > 
					<input type="text" class="input-text" style="width:250px" placeholder="账号" id="accountName" name="accountName">
				</div> 
				<div class="col-xs-3 col-sm-2 .col-md-2" > 
					<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div> 
		  	</div>
		</form>
	</div>
	
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort" style="table-layout:fixed;">
		<thead>
			<tr class="text-c">
				<th width="10%">申请编号</th>
				<th width="10%">申请时间</th>
				<th width="8%">账号</th>
				<th width="5%">督导员</th>
				<th width="8%">银行卡号</th>
				<th width="5%">状态</th>
				<th width="15%" style="word-break: break-all; word-wrap:break-word;">原因</th>
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
        "mData": "storeNo",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.storeNo != null) {
                return row.storeNo;
            } else {
                return "";
            }
        }
    },
    {
        "sDefaultContent": "申请时间",
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
        "mData": "accountName",
        "bSortable" : false,
        "sClass": "text-c",
       	"mRender": function(data, type, row) {
               if (row.accountName != null) {
                   return row.accountName;
               } else {
                   return "";
               }
           }
    },
    {
        "mData": "name",  //督导员
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.name != null) {
                return row.name;
            } else {
                return "";
            }
        }
    },
    {
       "mData": "cardNumber",  //督导员
       "bSortable" : false,
       "sClass": "text-c",
       "mRender": function(data, type, row) {
           if (row.cardNumber != null) {
                return row.cardNumber;
               } else {
               return "";
               }
           }
    },
    {
        "sDefaultContent": "状态",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) { 
        	//审核下拉框      未认证:WRZ  审核中:APRING 审核不通过:APRNO 审核通过:APRYES  
        	if(row.process=='WRZ'){
        		return "未认证";
        	}else if(row.process=='APRING'){
        		return "审核中";
        	}else if(row.process=='APRNO'){
        		return "审核不通过";
        	}else if(row.process=='APRYES'){
        		return "审核通过";
        	}else{
        		return "";
        	}
             
        } 	
    }, 
    {
        "mData": "remark",
        "bSortable" : false,
        "sClass": "text-c text-z",
       	"mRender": function(data, type, row) {
               if (row.remark != null) {
                   return row.remark;
               } else {
                   return "";
               }
           }
    },
    {
        "sDefaultContent": "审核",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {  
        	//编辑
            var toEdit = "<a title=\"审核\" href=\"javascript:;\" onclick=\"certification_check('认证申请审核','${context_root}/merchant/checkCertification.action?storeId=" + row.storeId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
        	return toEdit;
        }
    },
    ];
    var url = "${context_root}/merchant/certificationList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url); 
    
});

function query() {
	var accountName = $("#accountName").val();
	var supervisor = $("#supervisor").val();
    var repaymentStatus = $("#repaymentStatus").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
   /*  var jsonObject = '{\"accountName\":\"' + accountName + 
    	           '\",\"supervisor\":\"' + supervisor + 
    	           '\",\"repaymentStatus\":\"' + repaymentStatus + 
    	           '\",\"beginTime\":\"' + beginTime +
    	           '\",\"endTime\":\"' + endTime + '\"}';  */ 
    pageTable.fnSettings().sAjaxSource = "${context_root}/merchant/certificationList.action?accountName=" + encodeURIComponent(encodeURIComponent(accountName)) +
					    	"&supervisor="+ supervisor +
					    	"&repaymentStatus="+ repaymentStatus +
					    	"&beginTime="+ beginTime +
					    	"&endTime="+ endTime;  
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}

 
/*审核申请*/
function certification_check(title,url,w,h){
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