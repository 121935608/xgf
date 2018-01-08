<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="账户余额"/>
<body>

<div class="page-container">
   <div class="text-c">
       <div class="info-div">
           <div class="row cl">
               <label class="form-label col-xs-4 col-sm-3">店铺名称</label>
               <div class="formControls col-xs-8 col-sm-4">
                   <span>${merchant.storeName}</span>
               </div>
           </div>
           <div class="row cl">
               <label class="form-label col-xs-4 col-sm-3">账户</label>
               <div class="formControls col-xs-8 col-sm-4">
                   <span>${merchant.mobilePhone}</span>
               </div>
           </div>
       </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="10%">时间</th>
                <th width="8%">金额(元)</th>
                <th width="8%">入账后金额(元)</th>
                <th width="10%">原因</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript">
    var pageTable;
    $(document).ready(function () {
        var aoColumns = [
            {
                "sDefaultContent": "结算单号",
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
                "sDefaultContent": "结算单号",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.totalPrice != null) {
                        return row.totalPrice/100;
                    } else {
                        return "";
                    }
                }
            },

            {
                "sDefaultContent": "结算单号",
                "bSortable" : false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    if (row.remain != null) {
                        return row.remain/100;
                    } else {
                        return "";
                    }
                }
            },
            {
                "sDefaultContent": "原因",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                   return "购物消费";

                }
            },
        ];
        var url = "${context_root}/system/accountInfoList.action?userId="+${merchant.userId};
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });




    function statusTools(row) {
        if (row.status == '1') {
            return "<a style=\"text-decoration:none\" onClick=\"user_stop(this,\'" + row.userId + "\')\" href=\"javascript:;\" title=\"禁用\">禁用</a>";
        } else {
            return "<a style=\"text-decoration:none\" onClick=\"user_start(this,\'" + row.userId + "\')\" href=\"javascript:;\" title=\"启用\">启用</a>";
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
                url: "${context_root}/system/changeMerchantStatus.action?userId=" + id + "&status=-1",
                type: 'post',
                async: true,
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.s == true) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="user_start(this,' + id + ')" href="javascript:;" title="启用">启用</a>');
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
                url: "${context_root}/system/changeMerchantStatus.action?userId=" + id + "&status=1",
                type: 'post',
                async: true,
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.s == true) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="user_stop(this,' + id + ')" href="javascript:;" title="禁用" style="text-decoration:none">禁用</a>');
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
