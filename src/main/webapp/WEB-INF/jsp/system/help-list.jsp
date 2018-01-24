<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="帮助与反馈"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span
        class="c-gray en">&gt;</span> 帮助与反馈 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime"
                    class="input-text Wdate" style="width:120px;" placeholder="开始时间">
        <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime"
               class="input-text Wdate" style="width:120px;" placeholder="结束时间">
        <input type="text" class="input-text" style="width:250px" placeholder="回复者" id="answer" name="answer">
        <input type="text" class="input-text" style="width:250px" placeholder="会员" id="name" name="name">
        <button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
        </button>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="5%">序号</th>
                <th width="10%">会员</th>
                <th width="10%">提问</th>
                <th width="10%">提出时间</th>
                <th width="10%">回复内容</th>
                <th width="10%">回复者</th>
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
                "sDefaultContent": "序号",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.number == null) {
                        return "";
                    } else {
                        return row.number;
                    }
                }
            },
            {
                "sDefaultContent": "会员",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.userName == null) {
                        return "";
                    } else {
                        return row.userName;
                    }
                }
            },
            {
                "sDefaultContent": "提问",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.question == null) {
                        return "";
                    } else {
                        return row.question;
                    }
                }
            },
            {
                "sDefaultContent": "提出时间",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.addTime == null) {
                        return "";
                    } else {
                        return formatDate(row.addTime, "yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {
                "sDefaultContent": "回复内容",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.reply == null) {
                        return "";
                    } else {
                        return row.reply;
                    }
                }
            },
            {
                "sDefaultContent": "回复者",
                "bSortable": false,
                "sClass": "text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    if (row.answer == null) {
                        return "";
                    } else {
                        return row.answer;
                    }
                }
            },
            {
                "sDefaultContent": "编辑",
                "bSortable": false,
                "sClass": "td-manage text-c",
                "bSearchable": false,
                "mRender": function (data, type, row) {
                    //回复
                    var toReply = "<a title=\"回复\" href=\"javascript:;\" onclick=\"user_reply('回复','${context_root}/system/toReply.action?feedBackId=" + row.feedBackId +"&userId="+row.userId+"','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>回复</span></a>";
                    return toReply;
                }
            },
        ];
        var url = "${context_root}/system/helpList.action";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
    });

    function statusTools(row) {
        if (row.toReply == '0') {
            return "<a title=\"回复\" href=\"javascript:;\" onclick=\"user_reply('回复','${context_root}/system/toReply.action?feedBackId=" + row.feedBackId +"&userId="+row.userId+"','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>回复</span></a>";
        } else if(row.toReply =='1') {
            return "<a style=\"cursor: default;\" title=\"回复\">回复</a>";
        }
    }

    function query() {
        var answer = $("#answer").val();
        var name = $("#name").val();
        var beginTime = $("#beginTime").val();
        var endTime = $("#endTime").val();

        pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/system/helpList.action?answer=" + answer + "&name=" + name + "&beginTime=" + beginTime + "&endTime=" + endTime);
        pageTable.fnClearTable(0);
        pageTable.fnDraw();
    }

    function excle() {
        var phone = $("#phone").val();
        var sourceCodeName = $("#sourceCodeName").val();
        var beginTime = $("#beginTime").val();
        var endTime = $("#endTime").val();

        window.location.href = encodeURI("${context_root}/system/memberExcelPoiList.action?phone=" + phone + "&sourceCodeName=" + sourceCodeName + "&beginTime=" + beginTime + "&endTime=" + endTime);

    }


    /*用户-添加*/
    function user_reply(title, url, w, h) {
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