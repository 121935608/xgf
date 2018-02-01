<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 商品登记 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	<span class="select-box" style="width: 120px;border:0px;">
	       <y:select id="categoryId" name="categoryId" codeGroup="${fenlei}" selectedValue=""
					 cssClass="select" headerKey="" headerValue="分类">
		   </y:select>
   </span>
       <select id="discount" name="discount" style="width:150px;height:33px;">
       		<option disabled selected style='display:none;' value="">折扣</option>
       		<option value=1>是</option>
       		<option value=-1>否</option>
       </select>
       <select id="status" name="status" style="width:150px;height:33px;">
       		<option disabled selected style='display:none;' value="">状态</option>
       		<option value="1">启用</option>
       		<option value="-1">禁用</option>
       </select>
		<input type="text" class="input-text" style="width:250px" placeholder="编码|商品名称|商品条码" id="commodityNo" name="commodityNo">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<div class="text-c" style="margin-bottom:20px;">
			 <span class="l" style="margin-left:20px;"><a href="javascript:;" onclick="register_add('添加','${context_root}/commodity/toRegisterAdd.action','','610')" 
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a></span>
			<span class="l" style="margin-left:20px;"><a href="javascript:;" onclick="excel_in()" 
				class="btn btn-primary radius">导入Excel</a></span>
			 <span class="l" style="margin-left:20px;"><a href="javascript:;" onclick="excel_out()" 
				class="btn btn-primary radius"> 导出Excel</a></span>
		</div>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="8%">编码</th>
				<th width="8%">商品名称</th>
				<th width="8%">商品条码</th>
				<th width="8%">供货商</th>
				<th width="8%">分类</th>
				<th width="7%">单位</th>
				<th width="7%">进价（元）</th>
				<th width="7%">售价（元）</th>
				<th width="7%">会员价（元）</th>
				<th width="5%">折扣</th>
				<th width="7%">库存</th>
				<th width="8%">状态</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
	</table>
	</div>
<script type="text/javascript">
var pageTable;
$(document).ready(function(){ 
    var aoColumns = [
    {
        "mData": "commodityCode",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "commodityName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "commodityNo",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "supplierName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "categoryName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "unitName",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "inPrice",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "salePrice",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "mData": "vipPrice",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
    	"sDefaultContent": "折扣",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": "",
        "mRender": function(data, type, row) {
            if (row.discount == 1) {
                return "是";
            } else if(row.discount == -1){
                return "否";
            }else{
            	return "";
            }
        }
    },
    {
        "mData": "stockNum",
        "bSortable" : false,
        "sClass": "text-c",
        "defaultContent": ""
    },
    {
        "sDefaultContent": "状态",
        "bSortable" : false,
        "sClass": "td-status text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.commodityStatus == '0') {
                return "<span class=\"label label-success radius\">启用</span>";
            } else if(row.commodityStatus == '-1'){
                return "<span class=\"label label-defaunt radius\">停用</span>";
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
            //编辑
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"register_edit('编辑','${context_root}/commodity/toRegisterModify.action?commodityId=" + row.commodityId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";
            //删除
            var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"register_del(this,\'" + row.commodityId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>删除</span></a>";
        	return toEdit  + "&nbsp;&nbsp;" + toDelete ;
        }
    },
    ];
    var url = "${context_root}/commodity/registerList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function query() {
    var status = $("#status option:selected").val();
    var categoryId = $("#categoryId option:selected").val();
    var discount = $("#discount option:selected").val();
    var commodityNo=$("#commodityNo").val();
    var reg =/[`~!@#$%^&*()_\-=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-={}|《》？：“”【】、；‘’，。、]/;
    if (reg.test(commodityNo)){
        alert("含有非法字符");
        return;
	}
    pageTable.fnSettings().sAjaxSource =encodeURI("${context_root}/commodity/registerList.action?status=" + status+"&categoryId="+categoryId+"&discount="+discount+"&commodityNo="+commodityNo);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}
function excel_out() {
	var status = $("#status option:selected").val();
    var categoryId = $("#categoryId option:selected").val();
    var discount = $("#discount option:selected").val();
    var commodityNo=$("#commodityNo").val();
    var jsonObject = '{\"status\":\"' + status + '\",\"categoryId\":\"' + categoryId + '\",\"discount\":\"' + discount + '\",\"commodityNo\":\"' + commodityNo + '\"}';
    var elemIF = document.createElement("iframe");   
    elemIF.src = "${context_root}/commodity/expRegisterList.action?jsonObject="+jsonObject;   
    elemIF.style.display = "none";   
    document.body.appendChild(elemIF);
}
function excel_in() {
	var url='${context_root}/commodity/toImpPage.action?impUrl='+encodeURIComponent('/commodity/impRegisterList.action')+'&molUrl='+encodeURIComponent('/commodity/getImpExcelModel.action')
	layer.open({
		  type: 2,
		  title: '导入',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['380px', '50%'],
		  content: url
	});
}

/*用户-编辑*/
function register_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
/*添加*/
function register_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-删除*/
function register_del(obj,id){
	parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			    url:"${context_root}/commodity/deleteRegister.action?commodityId=" + id,
				type:'post',
				async:true ,
				cache:false ,
				dataType:"json",
				success:function(data){
					if(data.s == true){
						$(obj).parents("tr").remove();
						parent.layer.msg('已删除!',{icon:1,time:1000});
						loadData() ;
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