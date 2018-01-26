<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="督导员管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> <span id="cons">商品列表 </span> <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
        <span class="select-box" style="width: 100px;border: hidden;">
             <y:select id="categoryId" name="categoryId" codeGroup="${classes}" selectedValue=""
                     cssClass="select" headerKey="" headerValue="分类">
             </y:select>
       </span>
        <span class="select-box" style="width: 100px;border: hidden;">
             <y:select id="origin" name="origin" codeGroup="${origin}" selectedValue=""
                     cssClass="select" headerKey="" headerValue="产地">
             </y:select>
       </span>

        <span class="select-box" style="width: 100px;border: hidden;">
             <y:select id="tag" name="tag" codeGroup="${category}" selectedValue=""
                       cssClass="select" headerKey="" headerValue="标签">
             </y:select>
       </span>
        <span class="select-box" style="width: 100px;border: hidden;">
             <y:select id="supply" name="supply" codeGroup="${supply}" selectedValue=""
                       cssClass="select" headerKey="" headerValue="供货地">
             </y:select>
       </span>
		<span class="select-box" style="width: 120px;">
           <select name="commodityStatus" id="commodityStatus" class="select" autocomplete="off">
               <option value="">上下架</option>
               <option value="0">上架</option>
               <option value="1" >下架</option>
           </select>
       </span>
		<input type="text" class="input-text" style="width:250px" placeholder="编号|商品名称|商品条码" id="commodityName" name="commodityName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="8%">编号</th>
				<th width="8%">商品名称</th>
				<th width="8%">商品条码</th>
				<th width="5%">分类 </th>
				<th width="8%">产地</th>
				<th width="5%">重量</th>
				<th width="3%">单位</th>
				<th width="5%">国内/国外</th>
				<th width="5%">等级</th>
				<th width="5%">规格</th>
				<th width="5%">进价(元)</th>
				<th width="5%">费率</th>
				<th width="5%">售价(元)</th>
				<th width="5%">库存</th>
				<th width="5%">存储方式</th>
				<th width="5%">上下架</th>
				<th width="5%">状态</th>
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
            "sDefaultContent": "编号",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.commodityId != null) {
                    return row.commodityId;
                } else {
                    return "";
                }
            }
        },
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
            "sDefaultContent": "分类",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.categoryName != null) {
                    return row.categoryName;
                } else {
                    return "";
                }
            }
        },
    {
        "sDefaultContent": "产地",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.origin !=null) {
                return row.origin;
            }else {
                return "";
            }
        }
    },
        {
            "sDefaultContent": "重量",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.weight !=null) {
                    return row.weight;
                }else {
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
                if (row.unit !=null) {
                    return  row.unit;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "国内/国外",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.country == "1") {
                    return "国内";
                } else if(row.country == "2") {
                    return "进口";
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "等级",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.grade !=null) {
                    return row.grade;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "规格",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.specification !=null) {
                    return row.specification;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "进价(元)",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.inPrice !=null) {
                    return row.inPrice;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "费率",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.taxRate !=null) {
                    return row.taxRate;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "售价(元)",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.salePrice !=null) {
                    return row.salePrice;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "库存",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.stockNum !=null) {
                    return row.stockNum;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "存储方式",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.storage !=null) {
                    return row.storage;
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "上下架",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.commodityStatus == 0) {
                    return "上架";
                } else if(row.commodityStatus == 1) {
                    return "下架";
                }else {
                    return "";
                }
            }
        },
        {
            "sDefaultContent": "状态",
            "bSortable" : false,
            "sClass": "td-status text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                if (row.status == 1) {
                    return "<span class=\"label label-success radius\">已启用</span>";
                } else {
                    return "<span class=\"label label-defaunt radius\">已停用</span>";
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
                var toEdit = "<a title=\"添加商品\" href=\"javascript:;\" onclick=\"product_edit('编辑商品','${context_root}/commodity/toEditProduct.action?commodityId=" + row.commodityId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>编辑</span></a>";

                return statusTools(row)+"&nbsp;&nbsp;"+toEdit;
            }
        },
    ];
    var url = "${context_root}/commodity/findProductList.action?type=${product.type}";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);

    var type='${product.type}';
    if (type==='s'){
		$("#cons").text("新增商品列表");
	}else if (type==='c'){
        $("#cons").text("商品列表");
	}
});

function statusTools(row) {
    if (row.status == 1) {
        return "<a style=\"text-decoration:none\" onClick=\"user_stop(this,\'" + row.commodityId + "\')\" href=\"javascript:;\" title=\"停用\"><span style='color: #0e90d2 '>停用</span></a>";
    } else {
        return "<a style=\"text-decoration:none\" onClick=\"user_start(this,\'" + row.commodityId + "\')\" href=\"javascript:;\" title=\"启用\"><span style='color: #0e90d2 '>启用</span></a>";
    }
}

function query() {
    var tag = $("#tag option:selected").val();
    var origin = $("#origin option:selected").val();
    var categoryId = $("#categoryId option:selected").val();
    var supply = $("#supply option:selected").val();
    var commodityStatus = $("#commodityStatus option:selected").val();
	var commodityName =$("#commodityName").val();
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/commodity/findProductList.action?tag="+tag+"&origin="+origin+"&categoryId="+categoryId+"&supply="+supply+"&commodityStatus="+commodityStatus+"&commodityName="+commodityName+"&type=${product.type}");
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*角色-添加*/
function role_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*角色-编辑*/
function product_edit(title,url,w,h){
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
            url:"${context_root}/commodity/changeProductStatus.action?commodityId=" + id +"&status=-1&type=${product.type}",
            type:'post',
            async:true ,
            cache:false ,
            dataType:"json",
            success:function(data){
                if(data.s == true){
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="user_start(this,'+id+')" href="javascript:;" title="启用"><span style=\'color: #0e90d2 \'>启用</span></a>');
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
            url:"${context_root}/commodity/changeProductStatus.action?commodityId=" + id +"&status=1&type=${product.type}",
            type:'post',
            async:true ,
            cache:false ,
            dataType:"json",
            success:function(data){
                if(data.s == true){
                    $(obj).parents("tr").find(".td-manage").prepend('<a onClick="user_stop(this,'+id+')" href="javascript:;" title="停用" style="text-decoration:none"><span style=\'color: #0e90d2 \'>停用</span></a>');
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