<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="导流会员注册贷款表"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据统计 <span
        class="c-gray en">&gt;</span> 导流会员注册贷款表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
   <div class="text-c">
        <input type="text" placeholder="开始时间" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
                    class="input-text Wdate" style="width:120px;">
        <input type="text" placeholder="结束时间" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
               class="input-text Wdate" style="width:120px;">

       <span class="select-box" style="width: 100px;border: hidden;">
       <y:select id="sourceId" name="sourceId" codeGroup="${sources}" selectedValue=""
                     cssClass="select" headerKey="" headerValue="渠道">
       </y:select>
       </span>
       <span class="select-box" style="width: 120px;">
           <select name="productTypeSelect" id="productTypeSelect" class="select" autocomplete="off">
               <option value="">APP/H5前端</option>
               <option value="0" >星点贷H5</option>
               <option value="1" >星点贷APP</option>
               <option value="2" >邀借APP</option>
               <option value="3" >邀借H5</option>
           </select>
       </span>
       <input type="text" style="width: 250px;" class="input-text" placeholder="会员号" id="search"
                    name="search">
        <button type="button" onclick="query()" class="btn btn-success radius"><i class="Hui-iconfont">&#xe665;</i> 搜索
        </button>
       <a class="btn btn-primary radius" href="javascript:void(0)" title="Excle导出" onclick="excle()">导出Excle</a>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="4%">序号</th>
                <th width="8%">会员号</th>
                <th width="8%">访问渠道</th>
                <th width="6%">APP/H5前端</th>
                <th width="12%">访问时间</th>
                <th width="12%">网点IP</th>
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
                "mData": "orderNumber",
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
                "mData": "sourceCodeName",
                "bSortable" : false,
                "sClass": "text-c",
                "defaultContent": ""
            },
            {
                "mData": "frontOrApp",
                "bSortable" : false,
                "sClass": "text-c",
                "defaultContent": ""
            },
            {
                "sDefaultContent": "访问时间",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                   if (row.accessTime==null){
                       return "";
                   }
                   return formatDate(row.accessTime*1000, "yyyy-MM-dd hh:mm:ss");
                }
            },
            {
                "mData": "accessIP",
                "bSortable" : false,
                "sClass": "text-c",
                "defaultContent": ""
            },

        ];
        var url = "${context_root}/system/userAccessList.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });




    function statusTools(row) {
        if (row.locked == '0') {
            return "<a style=\"text-decoration:none\" onClick=\"user_stop(this,\'" + row.userId + "\')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a>";
        } else {
            return "<a style=\"text-decoration:none\" onClick=\"user_start(this,\'" + row.userId + "\')\" href=\"javascript:;\" title=\"启用\"><i class=\"Hui-iconfont\">&#xe615;</i></a>";
        }
    }

    function query() {

        var beginTime = $("#beginTime").val();

        var endTime = $("#endTime").val();

        var sourceCodeId = $("#sourceId option:selected" ).val();

        var frontOrApp= $("#productTypeSelect option:selected").val();

        var phone=$("#search").val();

        pageTable.fnSettings().sAjaxSource =encodeURI("${context_root}/system/userAccessList.action?beginTime=" + beginTime+"&endTime="+endTime+"&sourceCodeId="+sourceCodeId+"&phone="+phone+"&frontOrApp="+frontOrApp);
        pageTable.fnClearTable(0);
        pageTable.fnDraw();

    }
    function excle() {
        var beginTime = $("#beginTime").val();

        var endTime = $("#endTime").val();

        var sourceCodeId = $("#sourceId").val();

        var frontOrApp= $("#productTypeSelect").val();

        var phone = $("#search").val();

        window.location.href = encodeURI("${context_root}/system/userAccessExcelPoiList.action?phone=" + phone + "&sourceCodeId=" + sourceCodeId + "&beginTime=" + beginTime + "&endTime=" + endTime+"&frontOrApp="+frontOrApp);

    }

    /*用户-添加*/
    function user_add(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*用户-编辑*/
    function user_edit(title, url, w, h) {
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
