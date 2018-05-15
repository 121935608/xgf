<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="业绩排名"/>
<style>
    div{
        font-size: 15px;
    }
    #info table tr td{
        text-align: center;
        border: 1px solid black;
        line-height: 35px;
    }
    .left{
        font-weight: bolder;
        background-color: #E0E1E1;
        width: 260px;
    }
    .right{
        width: 460px;
    }
    .tab-info1{
        width: 300px;
        margin-left: 50px;
        margin-top: 15px;
    }
    .left1{
        width: 110px;
        text-align: center;
    }
    .rr{
        float: right;
    }
    .ll{
        float: left;
        color: #5D5D5D;
    }
   .cc{
        border-bottom: 1px #CECECE solid;
    }
    .cc1{
        color: #5D5D5D;
    }
    #WEEK,#MON {
        display: none;
    }
    .rm{
        border-bottom: 3px #41A26F solid;
    }
    .rm1{
        font-weight: bolder;
        color: #41A26F;
    }
</style>
<body>
<div class="page-container">
    <div class="mt-20">
        <div class="info-div">
            <div class="row cl">
                <div class="col-xs-3 col-sm-2" style="width: 720px;padding-left: 70px">
                    <div id="info">
                        <table >
                            <tr>
                                <td class="left"><div>姓名</div></td>
                                <td class="right"><div>${result.map.name}</div></td>
                            </tr>
                            <tr>
                                <td class="left"><div>所属区域</div></td>
                                <td class="right"><div>${result.map.area}</div></td>
                            </tr>
                            <tr>
                                <td class="left"><div>所属组织</div></td>
                                <td class="right"><div>${result.map.deptName}</div></td>
                            </tr>
                            <tr>
                                <td class="left"><div>下单总额</div></td>
                                <td class="right" id="totalPrice"></td>
                            </tr>
                            <tr>
                                <td class="left"><div>组内排名</div></td>
                                <td class="right" id="rank"></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-wrapper">
            <div class="tab-content">
                <div class="mt-20"  id="tab-menu">
                    <table class="table" style="float: left;margin-left: 50px;width: 650px">
                        <tr>
                            <td id="day1" class="rm" style="text-align: center"><div class="rm1">日业绩</div></td>
                            <td id="week1" class="cc" style="text-align: center"><div class="cc1">周业绩</div></td>
                            <td id="mon1" class="cc" style="text-align: center"><div class="cc1">月业绩</div></td>
                        </tr>
                    </table>
                </div>
                <div class="mt-20" id="DAY">
                    <div class="tab-info">
                        <table class="table table-border table-bordered tab-info1" style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>下单总额</div></td>
                                <td class="right1" id="w1a"><div class="ll">今日</div> </td>
                            </tr>
                            <tr>
                                <td class="right1" id="w1a1"><div class="ll">昨日</div> </td>
                            </tr>
                        </table>
                        <table class="table table-border table-bordered tab-info1"  style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>下单门店数</div></td>
                                <td class="right1"><div class="ll">今日</div><div class="rr">${result.map1.storeIdNum}个</div></td>
                            </tr>
                            <tr>
                                <td class="right1"><div class="ll">昨日</div><div class="rr"> ${result.map1.storeIdNum1}个</div></td>
                            </tr>
                        </table>
                    </div>

                    <div class="tab-info">
                        <table class="table table-border table-bordered tab-info1" style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>拜访门店数</div></td>
                                <td class="right1"><div class="ll">今日</div><div class="rr"> ${result.map1.visitStoreNum}个</div></td>
                            </tr>
                            <tr>
                                <td class="right1"><div class="ll">昨日</div><div class="rr"> ${result.map1.visitStoreNum1}个</div></td>
                            </tr>
                        </table>
                        <table class="table table-border table-bordered tab-info1" style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>首次下单门店数</div></td>
                                <td class="right1"><div class="ll">今日</div><div class="rr"> ${result.map1.firstStore}个</div></td>
                            </tr>
                            <tr>
                                <td class="right1"><div class="ll">昨日</div><div class="rr"> ${result.map1.firstStore1}个</div></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="mt-20" id="WEEK">
                    <div class="tab-info">
                        <table class="table table-border table-bordered tab-info1" style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>下单总额</div></td>
                                <td class="right1" id="w2a"><div class="ll">本周</div></td>
                            </tr>
                            <tr>
                                <td class="right1" id="w2a1"><div class="ll">上周</div></td>
                            </tr>
                        </table>
                        <table class="table table-border table-bordered tab-info1"  style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>下单门店数</div></td>
                                <td class="right1"><div class="ll">本周</div><div class="rr">${result.map2.storeIdNum}个</div></td>
                            </tr>
                            <tr>
                                <td class="right1"><div class="ll">上周</div><div class="rr">${result.map2.storeIdNum1}个</div></td>
                            </tr>
                        </table>
                    </div>

                    <div class="tab-info">
                        <table class="table table-border table-bordered tab-info1" style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>拜访门店数</div></td>
                                <td class="right1"><div class="ll">本周</div><div class="rr">${result.map2.visitStoreNum}个</div></td>
                            </tr>
                            <tr>
                                <td class="right1"><div class="ll">上周</div><div class="rr">${result.map2.visitStoreNum1}个</div></td>
                            </tr>
                        </table>
                        <table class="table table-border table-bordered tab-info1" style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>首次下单门店数</div></td>
                                <td class="right1"><div class="ll">本周</div><div class="rr">${result.map2.firstStore}个</div></td>
                            </tr>
                            <tr>
                                <td class="right1"><div class="ll">上周</div><div class="rr">${result.map2.firstStore1}个</div></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="mt-20" id="MON">
                    <div class="tab-info">
                        <table class="table table-border table-bordered tab-info1" style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>下单总额</div></td>
                                <td class="right1" id="w3a"><div class="ll">本月</div></td>
                            </tr>
                            <tr>
                                <td class="right1" id="w3a1"><div class="ll">上月</div></td>
                            </tr>
                        </table>
                        <table class="table table-border table-bordered tab-info1"  style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>下单门店数</div></td>
                                <td class="right1"><div class="ll">本月</div><div class="rr"> ${result.map3.storeIdNum}个</div></td>
                            </tr>
                            <tr>
                                <td class="right1"><div class="ll">上月</div><div class="rr"> ${result.map3.storeIdNum1}个</div></td>
                            </tr>
                        </table>
                    </div>

                    <div class="tab-info" >
                        <table class="table table-border table-bordered tab-info1" style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>拜访门店数</div></td>
                                <td class="right1"><div class="ll">本月</div><div class="rr">${result.map3.visitStoreNum}个</div></td>
                            </tr>
                            <tr>
                                <td class="right1"><div class="ll">上月</div><div class="rr">${result.map3.visitStoreNum1}个</div></td>
                            </tr>
                        </table>
                        <table class="table table-border table-bordered tab-info1" style="float: left">
                            <tr>
                                <td class="left1" rowspan="2"><div>首次下单门店数</div></td>
                                <td class="right1"><div class="ll">本月</div><div class="rr">${result.map3.firstStore}个</div></td>
                            </tr>
                            <tr>
                                <td class="right1"><div class="ll">上月</div><div class="rr">${result.map3.firstStore1}个</div></td>
                            </tr>
                        </table>
                    </div>
                </div>
        </div>
    </div>
</div>

    <script type="text/javascript">
        $(function() {
            var mon=${result.map.totalPrice};
            var rank=${result.map.deptRank};
            var totalPrice=FormatMoney(mon.toString());
            $("#totalPrice").html("<div>"+totalPrice+"元</div>");
            $("#rank").text(parseInt(rank));

            //月
            var w3am=${result.map3.amount};
            var w3a=FormatMoney(w3am.toString());
            var $span=$("<div class='rr'>"+w3a+"元</div>");
            $("#w3a").append($span);
            var w3am1=${result.map3.amount1};
            var w3a1=FormatMoney(w3am1.toString());
            var $span=$("<div class='rr'>"+w3a1+"元</div>");
            $("#w3a1").append($span);

            //周
            var w2am=${result.map2.amount};
            var w2a=FormatMoney(w2am.toString());
            var $span=$("<div class='rr'>"+w2a+"元</div>");
            $("#w2a").append($span);
            var w2am1=${result.map2.amount1};
            var w2a1=FormatMoney(w2am1.toString());
            var $span=$("<div class='rr'>"+w2a1+"元</div>");
            $("#w2a1").append($span);

            //日
            var w1ad=${result.map1.amount};
            var w1a=FormatMoney(w1ad.toString());
            var $span=$("<div class='rr'>"+w1a+"元</div>");
            $("#w1a").append($span);
            var w1ad1=${result.map1.amount1};
            var w1a1=FormatMoney(w1ad1.toString());
            var $span=$("<div class='rr'>"+w1a1+"元</div>");
            $("#w1a1").append($span);


            $("#day1 div").click(function () {
                $("#day1").removeClass("cc");
                $("#day1 div").removeClass("cc1");
                $("#day1").addClass("rm");
                $("#day1 div").addClass("rm1");

                $("#week1").removeClass("rm");
                $("#week1 div").removeClass("rm1");
                $("#week1").addClass("cc");
                $("#week1 div").addClass("cc1");
                $("#mon1").removeClass("rm");
                $("#mon1 div").removeClass("rm1");
                $("#mon1").addClass("cc");
                $("#mon1 div").addClass("cc1");

                $("#DAY").css('display','block');
                $("#WEEK").css('display','none');
                $("#MON").css('display','none');
            })

            $("#week1 div").click(function () {
                $("#week1").removeClass("cc");
                $("#week1 div").removeClass("cc1")
                $("#week1").addClass("rm");
                $("#week1 div").addClass("rm1");

                $("#day1").removeClass("rm");
                $("#day1 div").removeClass("rm1");
                $("#day1").addClass("cc");
                $("#day1 div").addClass("cc1");
                $("#mon1").removeClass("rm");
                $("#mon1 div").removeClass("rm1");
                $("#mon1").addClass("cc");
                $("#mon1 div").addClass("cc1");

                $("#DAY").css('display','none');
                $("#WEEK").css('display','block');
                $("#MON").css('display','none');
            })

            $("#mon1 div").click(function () {
                $("#mon1").removeClass("cc");
                $("#mon1 div").removeClass("cc1")
                $("#mon1").addClass("rm");
                $("#mon1 div").addClass("rm1");

                $("#day1").removeClass("rm");
                $("#day1 div").removeClass("rm1");
                $("#day1").addClass("cc");
                $("#day1 div").addClass("cc1");
                $("#week1").removeClass("rm");
                $("#week1 div").removeClass("rm1");
                $("#week1").addClass("cc");
                $("#week1 div").addClass("cc1");

                $("#DAY").css('display','none');
                $("#WEEK").css('display','none');
                $("#MON").css('display','block');
            })
        });

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
</body>
</html>
