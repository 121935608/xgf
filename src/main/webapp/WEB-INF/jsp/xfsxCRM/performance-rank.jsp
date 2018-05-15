<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="业绩排名"/>
<style>
    div{
        font-size: 15px;
    }
    .cc{
        border-bottom: 1px #CECECE solid;
    }
    .cc1{
        color: #5D5D5D;
    }
    #tab_city {
        display: none;
    }
    .rm{
        border-bottom: 3px #41A26F solid;
    }
    .rm1{
        font-weight: bolder;
        color: #41A26F;
    }
    #dept,#city div{
        cursor: pointer;
    }
    .select-box{
        width: 200px;
    }
    .dept {
        border: hidden;
        padding: 0px;
    }
    #province,#city,#district{
        font-size: 14px;
        height: 33.5px;
    }
</style>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 谢鲜CRM管理 <span class="c-gray en">&gt;</span> 业绩排名 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">

    <div class="text-c">
        <%--<input type="text" class="input-text" style="width:250px" placeholder="门店名称" id="storeName" name="storeName">--%>
        <%--<input type="text" class="input-text" style="width:250px" placeholder="业务员姓名" id="supervisorName" name="supervisorName">--%>
            <div class="row cl">
                <div data-toggle="distpicker">
                    <span class="select-box dept" >
                        <select class="select-box"  data-province="---- 选择省 ----" id="province" name="province"></select>
                   </span>
                    <span class="select-box dept" >
                        <select class="select-box"  data-city="---- 选择市 ----" id="city" name="city"></select>
                   </span>
                    <span id="dept12" class="select-box dept" >
                         <select class="select-box"  data-district="---- 选择区 ----" id="district" name="county"></select>
                   </span>
                    <span id="dept11" class="select-box dept">
                        <y:select id="dept1" name="dept1" codeGroup="${dept}" selectedValue=""
                                  cssClass="select" headerKey="" headerValue="组类">
                        </y:select>
                   </span>
                    <button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i>查询</button>
                </div>
            </div>

    </div>

    <div class="mt-20"  id="tab-menu">
        <table class="table" >
            <tr>
                <td id="dept" class="rm" style="text-align: center" width="15%"><div class="rm1">组内排名</div></td>
                <td id="city1" class="cc" style="text-align: center"  width="15%"><div class="cc1">城市排名</div></td>
                <td class="cc" style="text-align: center"  width="35%"><div class="cc1"></div></td>
                <td class="cc" style="text-align: center"  width="35%"><div class="cc1"></div></td>
            </tr>
        </table>
    </div>

    <div class="mt-20"  id="tab_dept">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="5%">NO.</th>
                <th width="5%">销售员</th>
                <th width="5%">省份</th>
                <th width="5%">城市</th>
                <th width="5%">区域</th>
                <th width="5%">组类</th>
                <th width="5%">客户下单总额(元)</th>
                <th width="5%">组类排序(升序/降序)</th>
                <th width="5%">操作</th>
            </tr>
            </thead>
        </table>
    </div>

    <div class="mt-20" id="tab_city">
        <table class="table table-border table-bordered table-hover table-bg table-sort-One">
            <thead>
            <tr class="text-c">
                <th width="5%">NO.</th>
                <th width="5%">销售员</th>
                <th width="5%">省份</th>
                <th width="5%">城市</th>
                <th width="5%">区域</th>
                <th width="5%">组类</th>
                <th width="5%">客户下单总额(元)</th>
                <th width="5%">组类排序(升序/降序)</th>
                <th width="5%">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<input id="userCrmId" value="${currentUser.userId}" style="display: none;">
<script type="text/javascript">
    var pageTable,pageTableOne;
    var num=0;
    var userCrmId = $("#userCrmId").attr("value");

    var queryType ="monthPerformance";
    $(document).ready(function(){

        var aoColumns = [
            {
                "sDefaultContent" : "-",
                "bSortable" : false,
                "sClass": "text-c",
                "mRender": function(data, type, row) {
                    num+=1;
                    return  num;
                }
            },
            {
                "sDefaultContent" : "-",
                "mData": "name",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "province",
                "bSortable" : false,
                "sClass": "text-c"
            },{
                "sDefaultContent" : "-",
                "mData": "city",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "county",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "mData": "deptName",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent" : "-",
                "bSortable" : false,
                "sClass": "text-c",
                "mRender": function(data, type, row) {
                    return FormatMoney(row.totalPrice.toString());
                }
            },
            {
                "sDefaultContent" : "-",
                "mData": "deptRank",
                "bSortable" : false,
                "sClass": "text-c"
            },
            {
                "sDefaultContent": "操作",
                "bSortable" : false,
                "sClass": "td-manage text-c",
                "bSearchable": false,
                "mRender": function(data, type, row) {
                    //查看详情
                    var details = "<a title=\"查看\" href=\"javascript:;\" onclick=\"performance_query('查看详情','${context_root}/crm/toPerformanceRankQuery.action?supervisorId=" + row.supervisorNum + "&name="+row.name+"&area="+row.area+"&totalPrice="+row.totalPrice+"&deptRank="+row.deptRank+"&deptName="+row.deptName+"','','570')\" class=\"ml-5\" style=\"text-decoration:none\"><span style='color: #0e90d2 '>查看</span></a>";
                    return  details;
                }
            },
        ];

        var url = "${context_root}/crm/performanceRankList.action?userId="+userCrmId;
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
        var urlOne = "${context_root}/crm/performanceRankList.action?city=上海市";
        pageTableOne = _Datatable_InitOne(pageTableOne, aoColumns, urlOne);

        $("#dept div").click(function () {
            $("#dept").removeClass("cc");
            $("#dept div").removeClass("cc1");
            $("#dept").addClass("rm");
            $("#dept div").addClass("rm1");

            $("#city1").removeClass("rm");
            $("#city1 div").removeClass("rm1");
            $("#city1").addClass("cc");
            $("#city1 div").addClass("cc1");

            $("#tab_dept").css('display', 'block');
            $("#dept11").css('display', 'inline-block');
            $("#dept12").css('display', 'inline-block');
            $("#tab_city").css('display', 'none');
        })

        $("#city1 div").click(function () {
            $("#city1").removeClass("cc");
            $("#city1 div").removeClass("cc1")
            $("#city1").addClass("rm");
            $("#city1 div").addClass("rm1");

            $("#dept").removeClass("rm");
            $("#dept div").removeClass("rm1");
            $("#dept").addClass("cc");
            $("#dept div").addClass("cc1");

            $("#tab_dept").css('display', 'none');
            $("#dept11").css('display', 'none');
            $("#dept12").css('display', 'none');
            $("#tab_city").css('display', 'block');
        })
    });

    function query() {
        var dept1= $("#dept1").val();
        var province= $("#province").val();
        var city= $("#city").val();
        var county= $("#district").val();
        pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/crm/performanceRankList.action?deptId="+dept1+"&province="+province+"&city="+city+"&county="+county);
        pageTable.fnClearTable(0);
        pageTable.fnDraw();
        pageTableOne.fnSettings().sAjaxSource = encodeURI("${context_root}/crm/performanceRankList.action?province="+province+"&city="+city);
        pageTableOne.fnClearTable(0);
        pageTableOne.fnDraw();
    }

    /*业绩排名-查看*/
    function performance_query(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    //格式化金钱方法
    function FormatMoney(money) {
        if (/[^0-9\.]/.test(money)) return '0.00';
        money = money.replace(/^(\d*)$/, "$1.");
        money = (money + "00").replace(/(\d*\.\d\d)\d*/, "$1");
        money = money.replace(".", ",");
        var re = /(\d)(\d{3},)/;
        while (re.test(money)) {
            money = money.replace(re, "$1,$2");
        }
        money = money.replace(/,(\d\d)$/, ".$1");
        return '' + money.replace(/^\./, "0.");
    }
</script>
<script type="text/javascript" src="${context_url}/uiloader/static/dist/distpicker.data.js"></script>
<script type="text/javascript" src="${context_url}/uiloader/static/dist/distpicker.js"></script>
</body>
</html>