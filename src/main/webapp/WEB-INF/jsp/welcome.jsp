<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="首页"/>
<body>
<style>
    table {
        border-collapse: collapse;
    / / 表格单元格间距样式 border: 1 px solid black;
    }

    tr {
        border: 1px solid black;
    }

    td {
        border: 0px;
    }

</style>
<script>
    function aaa() {
        var pay = ${pays};
        alert(pay);
    }
</script>
<div class="page-container">
    <div style="height:250px;">

        <a href="${context_url}/system/merchantView.action">
            <div id="left" style="float: left;width: 20%;height:200px;margin: 0 40px;text-align: center;margin-left: 15%; ">
                <img src="${context_url}/uiloader/static/h-ui/images/tongji.png"
                     style="text-align: center;width:140px;height: 140px;margin-top: 10px;margin-bottom: 15px"><br>
                <span>注册数量: ${count}</span>
            </div>
        </a>

        <a href="${context_url}/merchant/certificationView.action">
            <div style="float: left;width: 20%;height:200px;margin: 0 40px;text-align: center; ">
                <img src="${context_url}/uiloader/static/h-ui/images/renzheng.png"
                     style="text-align: center;width:140px;height: 140px;margin-top: 10px;margin-bottom: 15px"><br>
                <span>认证申请: ${certificationCount}</span>
            </div>
        </a>

        <a href="${context_url}/order/orderManageView.action">
            <div style="float: left;width: 20%;height:200px;margin: 0 40px;text-align: center; overflow: hidden">
                <img src="${context_url}/uiloader/static/h-ui/images/daikuan.png"
                     style="text-align: center;width:140px;height: 140px;margin-top: 10px;margin-bottom: 15px"><br>
                <span>贷款申请: ${orderCount}</span>
            </div>
        </a>
    </div>
    <div style="clear:both;">
        <div border="0">
            <div style="width: 50%;float: left;text-align:center;">
                <table border="1" style="text-align:center;margin:0 auto;width:80%;float:left;margin-left: 10%;margin-right: 10%">
                    <tr style="width:100%;height:40px;border:0px;background-color: #D1D1D1">
                        <td style="width:15%;text-align: center;">交易记录</td>
                        <td style="width:35%"></td>
                        <td style="width:35%"></td>
                        <td style="text-align:center;width: 15%"><a href="${context_url}/dataCount/paysView.action"
                                                                    style="color:blue;">更多>></a></td>
                    </tr>
                    <c:forEach items="${pays}" var="m" varStatus="n">
                        <tr style="width:100%;height: 40px;">
                            <td style="width:5%;text-align:center;">${n.count}</td>
                            <td style="width:40%;text-align:center;">${m.storeName}</td>
                            <td style="width:35%;">${m.payTypes}</td>
                            <td style="width:20%">${m.money}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div style="width: 50%;float: left;text-align:center;">
                <table border="1" style="text-align:center;margin:0 auto;width:80%;float:left;margin-left: 10%;margin-right: 10%">
                    <tr style="width:100%;height:40px;border:0px;background-color: #D1D1D1">
                        <td style="width:15%;text-align: center;">交易记录</td>
                        <td style="width:35%"></td>
                        <td style="width:35%"></td>
                        <td style="text-align:center;width: 15%"><a href="${context_url}/dataCount/paysView.action"
                                                                    style="color:blue;">更多</a></td>
                    </tr>
                    <c:forEach items="${pays}" var="m" varStatus="n">
                        <tr style="width:100%;height: 40px;">
                            <td style="width:5%;text-align:center;">${n.count}</td>
                            <td style="width:40%;text-align:center;">${m.tradeCode}</td>
                            <td style="width:35%;">${m.addTimes}</td>
                            <td style="width:20%">${m.money}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>


        </div>
    </div>
</div>
</body>
</html>