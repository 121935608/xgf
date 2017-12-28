<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="商户管理列表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span
        class="c-gray en">&gt;</span> 商户管理列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
   <div class="text-c">
       <span class="select-box" style="width: 120px;">
           <select name="statusSelect" id="statusSelect" class="select" autocomplete="off">
               <option value="">状态</option>
               <option value="0">启用</option>
               <option value="1" >禁用</option>
           </select>
       </span>
       <input type="text" style="width: 250px;" class="input-text" placeholder="账号|店铺名称" id="search"
                    name="search">
        <button type="button" onclick="query()" class="btn btn-success radius"><i class="Hui-iconfont">&#xe665;</i> 搜索
        </button>
       <%--<a class="btn btn-primary radius" href="javascript:void(0)" title="Excle导出" onclick="excle()">导出Excle</a>--%>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="6%">账号</th>
                <th width="10%">店铺名称</th>
                <th width="8%">联系人</th>
                <th width="8%">联系方式</th>
                <th width="8%">状态</th>
                <th width="10%">操作</th>
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
           /* {
                "mData": "status",
                "bSortable" : false,
                "sClass": "text-c",
                "defaultContent": ""
            },*/
            {
                "sDefaultContent": "状态",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.locked == 0){
                        return "启用";
                    }else if (row.locked == 1){
                        return "禁用";
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
                    var toQuery = "<a title=\"查看\" href=\"javascript:;\" onclick=\"user_query('查看','${context_root}/system/toMerchantQuery.action?userId=" + row.userId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">查看</a>";
                    //账户余额
                    var toBalance = "<a title=\"账户余额\" href=\"javascript:;\" onclick=\"user_balance('账户余额','${context_root}/system/accountBalance.action?userId=" + row.userId + "','850','850')\" class=\"ml-5\" style=\"text-decoration:none\">账户余额</a>";
                    return statusTools(row) + "&nbsp;&nbsp;" + toQuery + "&nbsp;&nbsp;" + toBalance;
                }
            },
        ];
        var url = "${context_root}/system/merchantList.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });




    function statusTools(row) {
        if (row.locked == '0') {
            return "<a style=\"text-decoration:none\" onClick=\"user_stop(this,\'" + row.userId + "\')\" href=\"javascript:;\" title=\"禁用\">禁用</a>";
        } else {
            return "<a style=\"text-decoration:none\" onClick=\"user_start(this,\'" + row.userId + "\')\" href=\"javascript:;\" title=\"启用\">启用</a>";
        }
    }

    function query() {
        var status = $("#statusSelect option:selected").val();

        var name=$("#search").val();
        //var jsonObject = '{\"beginTime\":\"' + beginTime + '\",\"endTime\":\"' + endTime + '\",\"source\":\"' + source + '\",\"type\":\"' + type + '\",\"status\":\"' + status + '\",\"phone\":\"' + phone + '\"}';
        pageTable.fnSettings().sAjaxSource =encodeURI("${context_root}/system/merchantList.action?status=" + status+"&name="+name);
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

    /*用户-查看*/
    function user_query(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*用户-余额*/
    function user_balance(title, url, w, h) {
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
                url: "${context_root}/system/changeMerchantStatus.action?userId=" + id + "&status=1",
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
                url: "${context_root}/system/changeMerchantStatus.action?userId=" + id + "&status=0",
                type: 'post',
                async: true,
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.s == true) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="user_stop(this,' + id + ')" href="javascript:;" title="停用" style="text-decoration:none">禁用</a>');
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
