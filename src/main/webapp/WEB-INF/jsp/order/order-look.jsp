﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="查看订单信息"/>
<body>
<div class="page-container">
	<div class="mt-20">
		<div class="info-div">
			<div class="row cl">
				<div class="col-xs-3 col-sm-2">
					<h4>订单信息</h4>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">订单号:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${orders.orderNumber}</span>
				</div>
			</div>
				<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">支付状态:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>
						<c:if  test="${orders.orderStatus eq 1}">未支付</c:if>
						<c:if  test="${orders.orderStatus ne 1}">已支付</c:if>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">会员:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${orders.userName}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">付款金额:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>¥${orders.orderPrice}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">下单时间:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${orders.orderTimes}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">订单状态:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>
						<c:if  test="${orders.orderStatus eq 1}">待支付</c:if>
						<c:if  test="${orders.orderStatus eq 2}">待发货</c:if>
						<c:if  test="${orders.orderStatus eq 3}">待收货</c:if>
						<c:if  test="${orders.orderStatus eq 4}">已完成</c:if>
						<c:if  test="${orders.orderStatus eq 5}">已收款</c:if>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">支付方式:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>
						<c:if  test="${orders.payCode eq 'ZFB'}">支付宝</c:if>
						<c:if  test="${orders.payCode eq 'WX'}">微信</c:if>
						<c:if  test="${orders.payCode eq 'HDFK'}">货到付款</c:if>
						<c:if  test="${orders.payCode eq 'CL'}">额度支付</c:if>
					</span>
				</div>
			</div>
		</div>
		<div class="info-div">
			<div class="row cl">
				<div class="col-xs-3 col-sm-2">
					<h4>配送信息</h4>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">下单店铺:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${orders.storeName}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">收货人:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${orders.userNames}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">联系方式:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${orders.phone}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">配送:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>${orders.area}${orders.address}</span>
				</div>
			</div>

		</div>


		<div class="info-div">
			<div class="row cl">
				<div class="col-xs-3 col-sm-2">
					<h4>配送信息</h4>
				</div>
			</div>
			<table>
				<tr>
					<td>商品名称</td>
					<td>商品条码</td>
					<td>规格</td>
					<td>数量</td>
					<td>单价</td>
					<td>总金额</td>
				</tr>
				<c:forEach items="${orderDetails}" var="order" varStatus="n">
				<tr>
					<td>${order.commodityName}</td>
					<td>${order.commodityNo}</td>
					<td>${order.unit}</td>
					<td>${order.commodityNum}</td>
					<td>¥${order.inPrice}</td>
					<td>¥${order.totalMoney}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="info-div">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">商品金额:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>¥${orders.totalPrice}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">运费:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>¥${orders.freight}</span>
				</div>
			</div>
			<c:if test="${orders.payCode=='CL'}">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">账期金额:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>¥${orders.payment}</span>
				</div>
			</div>
			</c:if>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">付款金额:</label>
				<div class="formControls col-xs-8 col-sm-4">
					<span>¥${orders.orderPrice}</span>
				</div>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript">
  /*  var pageTable;
    $(document).ready(function () {
        var aoColumns = [
            {
                "mData": "mobilePhone",
                "bSortable" : false,
                "sClass": "text-c",
                "defaultContent": ""
            },
            {
                "mData": "storeName",
                "bSortable" : false,
                "sClass": "text-c",
                "defaultContent": ""
            },

            {
                "mData": "userName",
                "bSortable" : false,
                "sClass": "text-c",
                "defaultContent": ""
            },
            {
                "mData": "phone",
                "bSortable" : false,
                "sClass": "text-c",
                "defaultContent": ""
            },
            {
                "mData": "status",
                "bSortable" : false,
                "sClass": "text-c",
                "defaultContent": ""
            },
            /!*  {
                  "sDefaultContent": "首推时间",
                  "bSortable": false,
                  "sClass": "text-c",
                  "bSearchable": false,
                  "mRender": function (data, type, row) {
                      if (row.createTime == null){
                          return "";
                      }
                      return formatDate(row.createTime * 1000, "yyyy-MM-dd hh:mm:ss")
                  }
              },*!/
            {
                "sDefaultContent": "操作",
                "bSortable" : false,
                "sClass": "td-manage text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    //查看
                    var toQuery = "<a title=\"查看\" href=\"javascript:;\" onclick=\"user_query('查看','${context_root}/system/toMerchantQuery.action?userId=" + row.userId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">查看</a>";
                    //禁用
                    var toForbidden = "<a title=\"禁用\" href=\"javascript:;\" onclick=\"user_forbidden(this,\'" + row.userId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    //账户余额
                    var toBalance = "<a title=\"账户余额\" href=\"javascript:;\" onclick=\"user_balance('账户余额','${context_root}/system/toRoleAuthorize.action?roleId=" + row.roleId + "','230','406')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e1;</i></a>";
                    return toBalance  + "&nbsp;&nbsp;" + toQuery + "&nbsp;&nbsp;" + toForbidden;
                }
            },
        ];
        var url = "${context_root}/system/merchantList.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });*/




    function statusTools(row) {
        if (row.locked == '0') {
            return "<a style=\"text-decoration:none\" onClick=\"user_stop(this,\'" + row.userId + "\')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a>";
        } else {
            return "<a style=\"text-decoration:none\" onClick=\"user_start(this,\'" + row.userId + "\')\" href=\"javascript:;\" title=\"启用\"><i class=\"Hui-iconfont\">&#xe615;</i></a>";
        }
    }

    function query() {
        var timeType=$("#timeTypeSelect option:selected").val();

        var beginTime = $("#beginTime").val();

        var endTime = $("#endTime").val();

        var source = $("#sourceId option:selected" ).val();

        var type = $("#typeSelect").val();

        var status = $("#statusSelect").val();

        var frontOrApp= $("#productTypeSelect option:selected").val();
        var phone=$("#search").val();
        //var jsonObject = '{\"beginTime\":\"' + beginTime + '\",\"endTime\":\"' + endTime + '\",\"source\":\"' + source + '\",\"type\":\"' + type + '\",\"status\":\"' + status + '\",\"phone\":\"' + phone + '\"}';
        pageTable.fnSettings().sAjaxSource =encodeURI("${context_root}/system/dataList.action?beginTime=" + beginTime+"&endTime="+endTime+"&source="+source+"&type="+type+"&status="+status+"&phone="+phone+"&timeType="+timeType+"&frontOrApp="+frontOrApp);
        pageTable.fnClearTable(0);
        pageTable.fnDraw();

    }

    function excle() {
        var timeType=$("#timeTypeSelect option:selected").val();

        var beginTime = $("#beginTime").val();

        var endTime = $("#endTime").val();

        var source = $("#sourceId option:selected" ).val();

        var type = $("#typeSelect").val();

        var status = $("#statusSelect").val();

        var frontOrApp= $("#productTypeSelect option:selected").val();
        var phone=$("#search").val();

        window.location.href = encodeURI("${context_root}/system/datacountExcelPoiList.action?beginTime=" + beginTime+"&endTime="+endTime+"&source="+source+"&type="+type+"&status="+status+"&phone="+phone+"&timeType="+timeType+"&frontOrApp="+frontOrApp);

    }

    /*用户-添加*/
    function user_add(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*用户-查看*/
    function user_query(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*密码-修改*/
    function change_password(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*管理员-停用*/
    function user_stop(obj, id) {
        parent.layer.confirm('确认要停用吗？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                url: "${context_root}/system/changeUserStatus.action?userId=" + id + "&locked=1",
                type: 'post',
                async: true,
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.s == true) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="user_start(this,' + id + ')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
                        $(obj).remove();
                        parent.layer.msg('已停用!', {icon: 5, time: 1000});
                    } else {
                        parent.layer.alert(data.m, {icon: 2, title: "系统提示"});
                    }
                },
            });

        });
    }

    /*管理员-启用*/
    function user_start(obj, id) {
        parent.layer.confirm('确认要启用吗？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                url: "${context_root}/system/changeUserStatus.action?userId=" + id + "&locked=0",
                type: 'post',
                async: true,
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.s == true) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="user_stop(this,' + id + ')" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
                        $(obj).remove();
                        parent.layer.msg('已启用!', {icon: 6, time: 1000});
                    } else {
                        parent.layer.alert(data.m, {icon: 2, title: "系统提示"});
                    }
                },
            });

        });
    }

    /*用户-删除*/
    function user_del(obj, id) {
        parent.layer.confirm('确认要删除吗？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                url: "${context_root}/system/deleteMemberById.action?id=" + id,
                type: 'post',
                async: true,
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.s == true) {
                        $(obj).parents("tr").remove();
                        parent.layer.msg('已删除!', {icon: 1, time: 1000});
                        loadData();
                    } else {
                        parent.layer.alert(data.m, {icon: 2, title: "系统提示"});
                    }
                },

            });
        });
    }
    /** 时间格式化 */
    function formatDate(date, pattern) {
        var oldDate = new Date(date);
        var newDate = oldDate.format(pattern);
        return newDate;
    }


    var idTmr;

    function getExplorer() {
        var explorer = window.navigator.userAgent;
        //ie
        if (explorer.indexOf("MSIE") >= 0) {
            return 'ie';
        }
        //firefox
        else if (explorer.indexOf("Firefox") >= 0) {
            return 'Firefox';
        }
        //Chrome
        else if (explorer.indexOf("Chrome") >= 0) {
            return 'Chrome';
        }
        //Opera
        else if (explorer.indexOf("Opera") >= 0) {
            return 'Opera';
        }
        //Safari
        else if (explorer.indexOf("Safari") >= 0) {
            return 'Safari';
        }
    }

    function method1(tableid) {
        if (getExplorer() == 'ie') {
            var curTbl = document.getElementById(tableid);
            var oXL = new ActiveXObject("Excel.Application");
            var oWB = oXL.Workbooks.Add();
            var xlsheet = oWB.Worksheets(1);
            var sel = document.body.createTextRange();
            sel.moveToElementText(curTbl);
            sel.select();
            sel.execCommand("Copy");
            xlsheet.Paste();
            oXL.Visible = true;
            oXL.ScreenUpdating = false;
            oXL.Selection.CurrentRegion.Select;
            oXL.Selection.Interior.Pattern = 0;
            oXL.Selection.Borders.LineStyle = 1;
            oXL.Selection.ColumnWidth = 5;
            oXL.Selection.RowHeight = 16;

            oXL.Selection.Columns.AutoFit;

            try {
                var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
            } catch (e) {
                print("Nested catch caught " + e);
            } finally {
                oWB.SaveAs(fname);
                oWB.Close(savechanges = false);
                oXL.Quit();
                oXL = null;
                oXL.Selection.Borders.Weight = 2;
                idTmr = window.setInterval("Cleanup();", 1);
            }

        }
        else {
            tableToExcel(tableid)
        }
    }

    function Cleanup() {
        window.clearInterval(idTmr);
        CollectGarbage();
    }

    var tableToExcel = (function () {
        var uri = 'data:application/vnd.ms-excel;base64,',
            template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel"' +
                'xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>'
                + '<x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets>'
                + '</x:ExcelWorkbook></xml><![endif]-->' +
                ' <style type="text/css">' +
                '.excelTable  {' +
                'border-collapse:collapse;' +
                ' border:thin solid #999; ' +
                '}' +
                '   .excelTable  th {' +
                '   border: thin solid #999;' +
                '  padding:20px;' +
                '  text-align: center;' +
                '  border-top: thin solid #999;' +
                ' background-color: #E6E6E6;' +
                ' }' +
                ' .excelTable  td{' +
                ' border:thin solid #999;' +
                '  padding:2px 5px;' +
                '  text-align: center;' +
                ' }</style>' +
                '</head><body ><table class="excelTable">{table}</table></body></html>',
            base64 = function (s) {
                return window.btoa(unescape(encodeURIComponent(s)))
            },
            format = function (s, c) {
                return s.replace(/{(\w+)}/g,
                    function (m, p) {
                        return c[p];
                    })
            }
        return function (table, name) {
            if (!table.nodeType) table = document.getElementById(table);
            var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML};
            window.location.href = uri + base64(format(template, ctx));
        }
    })()

</script>
</body>
</html>
