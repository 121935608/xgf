<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="审核订单信息"/>
<body>
<article class="page-container">
    <form action="${context_root}/order/orderSave.action" method="post" class="form form-horizontal" id="form-order-modify">
        <input type="hidden" name="id" value="WO-20180521-00309" id="num">
        <%-- 订单信息 --%>
        <div class="info-div">
            <div class="row cl">
                <div class="col-xs-3 col-sm-2">
                    <h4>订单信息</h4>
                </div>
                <div id="modifyDiv" class="col-xs-offset-8 col-sm-offset-8 col-xs-2 col-sm-2" style="display: block">
                    <a title="修改" href="javascript:;" onclick="modifyOrder()"><h5>修改</h5></a>
                </div>
                <div id="saveDiv" class="col-xs-offset-8 col-sm-offset-8 col-xs-2 col-sm-2" style="display: none">
                    <a title="保存" href="javascript:;" onclick="saveOrder('${orders.orderNumber}')"><h5>保存</h5></a>
                </div>
            </div>
            <%-- 第一行 --%>
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>订单号:</label>
                <div class="formControls col-xs-2 col-sm-2">
                    <span>${orders.orderNumber}</span>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>下单时间:</label>
                <div class="formControls col-xs-2 col-sm-2">
                    <span>${orders.orderTimes}</span>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>支付状态:</label>
                <div class="formControls col-xs-2 col-sm-2">
                 <span>
                    <c:if test="${orders.orderStatus eq 1}">未支付</c:if>
                    <c:if test="${orders.orderStatus ne 1}">已支付</c:if>
                 </span>
                </div>
            </div>
            <%-- 第二行 --%>
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>支付方式:</label>
                <div class="formControls col-xs-2 col-sm-2">
                    <span>
						<c:if test="${orders.payCode eq 'ZFB'}">支付宝</c:if>
						<c:if test="${orders.payCode eq 'WX'}">微信</c:if>
						<c:if test="${orders.payCode eq 'HDFK'}">货到付款</c:if>
						<c:if test="${orders.payCode eq 'CL'}">额度支付</c:if>
					</span>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>应付金额:</label>
                <div class="formControls col-xs-2 col-sm-2">
                    <span>¥${orders.orderPrice}</span>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>订单状态:</label>
                <div class="formControls col-xs-2 col-sm-2">
                    <span>
                        <c:if test="${orders.orderStatus eq -1}">已取消</c:if>
                        <c:if test="${orders.orderStatus eq 1}">待支付</c:if>
                        <c:if test="${orders.orderStatus eq 2}">待发货</c:if>
                        <c:if test="${orders.orderStatus eq 3}">待收货</c:if>
                        <c:if test="${orders.orderStatus eq 4}">已完成</c:if>
                        <c:if test="${orders.orderStatus eq 5}">已收款</c:if>
                        <c:if test="${orders.orderStatus eq 6}">客服审核</c:if>
                        <c:if test="${orders.orderStatus eq 7}">库存审核</c:if>
                        <c:if test="${orders.orderStatus eq 8}">退货中</c:if>
                        <c:if test="${orders.orderStatus eq 9}">关闭交易</c:if>
                    </span>
                </div>
            </div>
            <%-- 第三行 --%>
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>会员:</label>
                <div class="formControls col-xs-2 col-sm-2">
                    <span>${orders.userName}</span>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>订货商家:</label>
                <div class="formControls col-xs-2 col-sm-2">
                    <span>${orders.storeName}</span>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>收货人:</label>
                <div class="formControls col-xs-2 col-sm-2">
                    <span id="userNamesSpan" name="order.userNames">${orders.userNames}</span>
                </div>
            </div>
            <%-- 第四行 --%>
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>联系方式:</label>
                <div class="formControls col-xs-2 col-sm-2">
                    <span id="phoneSpan">${orders.phone}</span>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red"></span>收货地址:</label>
                <div class="formControls col-xs-5 col-sm-5">
                    <span id="areaSpan">${orders.area}${orders.address}</span>
                </div>
            </div>
        </div>

        <%-- 客服备注 --%>
        <div id="bei" class="row cl">
            <label class="form-label col-xs-1.5 col-sm-2"><span class="c-red"></span>操作人员备注:</label>
            <div class="formControls col-xs-5 col-sm-5">
                <input type="text" class="input-text" value=" " id="serviceRemark" name="serviceRemark">
            </div>
        </div>

        <%-- 商品信息 --%>
        <div class="info-div">
            <div class="row cl">
                <div class="col-xs-3 col-sm-2">
                    <h4>商品信息</h4>
                </div>
            </div>

            <%-- 商品表格 --%>
            <div class="mt-20">
                <table id="orderTable" class="table table-border table-bordered table-hover table-bg table-sort">
                    <thead>
                    <tr class="text-c">
                        <th width="10%">商品名称</th>
                        <th width="10%">商品条形码</th>
                        <th width="5%">单位</th>
                        <th width="5%">数量</th>
                        <th width="5%">售价</th>
                        <th width="7.5%">主观价</th>
                        <th width="7.5%">计费方式</th>
                        <th width="10%">重量(kg)</th>
                        <th width="10%">金额(元)</th>
                        <th width="10%">操作</th>
                    </tr>
                    </thead>
                    <c:forEach items="${orderCommodityDetails}" var="orderCommodityDetail" varStatus="n">
                        <tr>
                                <%--<td style="display: none">${orderCommodityDetail.orderNumber}</td>--%>
                            <td>${orderCommodityDetail.commodityName}</td>
                            <td>${orderCommodityDetail.commodityNo}</td>
                            <td>${orderCommodityDetail.unit}</td>
                            <td id="${orderCommodityDetail.orderNumber}"
                                ondblclick="changeTd(this)">${orderCommodityDetail.commodityNum}</td>
                            <td>${orderCommodityDetail.salePrice/100}</td>
                            <td>${orderCommodityDetail.subPrice/100}</td>
                            <td>${orderCommodityDetail.subPriceUnit}</td>
                            <td>${orderCommodityDetail.weight}</td>
                            <td onclick="showAllMoney(this)">${orderCommodityDetail.totalMoney}</td>
                            <td><input id="cancelInput" type="checkbox"/>取消
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>

        <%-- 运费 --%>
        <div class="row cl">
            <label class="form-label col-xs-offset-7 col-xs-2 col-sm-2"><span class="c-red"></span>运费:</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" id="freightInput" value="${orders.freight}" placeholder="输入运费"/>
                <input type="hidden" id="freightHiddenInput" value="${orders.freight}" placeholder="输入运费"/>
            </div>
        </div>

        <%-- 总金额 --%>
        <div class="row cl">
            <label class="form-label col-xs-offset-7 col-xs-2 col-sm-2"><span class="c-red"></span>总共费用:</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" id="moneyInput" value="${orders.orderPrice}" placeholder="总费用"/>
                <input type="hidden" id="moneyHiddenInput" value="${orders.totalPrice}" placeholder="总费用"/>
            </div>
        </div>

        <%-- 按钮 --%>
        <div id="add" class="row cl">
            <div class="col-xs-1.5 col-sm-2">
                <input class="btn btn-primary radius" type="button" id="addInput" onClick="addOrder()"
                       value="&nbsp;&nbsp;添加&nbsp;&nbsp;">
            </div>
        </div>

        <%-- 添加商品信息 --%>
        <div class="info-div" id="addOrderDiv">
            <div class="row cl">
                <div class="col-xs-3 col-sm-2">
                    <h4>添加商品</h4>
                </div>
            </div>

            <%-- 商品表格 --%>
            <div class="mt-20">
                <table id="addTable" class="table table-border table-bordered table-hover table-bg table-sort">
                    <thead>
                    <tr class="text-c">
                        <%--<th width="5%">序号</th>--%>
                        <th width="10%">商品名称</th>
                        <th width="10%">商品条形码</th>
                        <th width="5%">单位</th>
                        <th width="5%">数量</th>
                        <th width="5%">售价</th>
                        <th width="7.5%">主观价</th>
                        <th width="7.5%">计费方式</th>
                        <th width="10%">重量(kg)</th>
                        <th width="10%">金额(元)</th>
                        <th width="10%">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>

            <%-- 添加商品总金额 --%>
            <div class="row cl" id="addOrderMoneyDiv" style="display: none;">
                <label class="form-label col-xs-offset-7 col-xs-2 col-sm-2"><span class="c-red"></span>总共费用:</label>
                <div class="formControls col-xs-2 col-sm-2">
                    <input type="text" id="addOrderMoneyInput" value="" placeholder="添加商品总费用"/>
                </div>
            </div>
        </div>

        <%-- 操作按钮 --%>
        <div id="add" class="row cl">
            <div class="col-xs-offset-3 col-xs-2 col-sm-2">
                <input class="btn btn-primary radius" type="button" id="submitBut" onClick="submitModifyOrder()"
                       value="&nbsp;&nbsp;提交审核&nbsp;&nbsp;"/>
            </div>
            <div class="col-xs-2 col-sm-2">
                <input class="btn btn-primary radius" type="button" id="cancelBut" onClick="cancelAllOrderFn()"
                       value="&nbsp;&nbsp;整单取消&nbsp;&nbsp;"/>
            </div>
            <div class="col-xs-2 col-sm-2">
                <input class="btn btn-primary radius" type="button" id="closeBut" onClick="closeWin()"
                       value="&nbsp;&nbsp;关闭&nbsp;&nbsp;"/>
            </div>
        </div>
    </form>
</article>
</body>
<script type="text/javascript">
    // 自动补全
    var maxcount = 0;// 表示他最大的值
    var thisCount = 0;// 初始化他框的位置
    /*$(function () {
         $("#autoCompleteId").autocomplete({
             source:function(request,response){
                 $.ajax({
                     type:"POST",
                     url:baseOrderUrl+"/findAllCommodity.action",
                     dataType : "json",
                     cache : true,
                     async : false,
                     data : {
                         "commodityName" : encodeURI($(this).val())
                     },
                     success : function(json) {
                         var data = eval(json);//json数组

                         response($.map(data,function(item){
                             var name = item.name;
                             var id = item.id;
                             return {
                                 label:item.code+'--'+ item.name,//下拉框显示值
                                 value:item.name,//选中后，填充到下拉框的值
                                 id:item.id//选中后，填充到id里面的值
                             }
                         }));
                     }
                 });
             },
             delay: 500,//延迟500ms便于输入
             select : function(event, ui) {
                 $("#statId").val(ui.item.id);
             }
         });

     })*/
    $(function () {
        // 为运费绑定失焦事件
        $("#freightInput").blur(function () {
            var oldValue = parseFloat($("#moneyHiddenInput").val());
            var newFreiValue = parseFloat($("#freightInput").val());
            $("#moneyInput").val((oldValue + newFreiValue).toFixed(1));
        });
    })
    // 基本路径
    var baseOrderUrl = "${context_root}/order";
    var baseComUrl = "${context_root}/commodity";
    // 当前用户id
    var serviceId = ${currentUser.userId};
    var orderNumber = "${orders.orderNumber}"
    var orderId = "${orders.orderId}";

    // 获取到当前用户得信息
    var userNames = null;
    var phone = null;
    var area = null;
    var address = null;

    // 封装所有商品条形码的数组
    var comNoList = [];

    // 定义计数器
    var modifyCount = 0;
    var cancelCount = 0;

    // 双击后修改td
    function changeTd(obj) {
        var oldNum = $(obj).html();
        $(obj).html("<input id='comNumInput' type='text' value='" + $(obj).text() + "'/>");
        var trNode = $(obj).parent();
        $("#comNumInput").bind("blur", function () {
            var parent = $("#comNumInput").parent();
            parent.html($("#comNumInput").val());

            // 异步请求获取提交数量修改
            // 获取commodityNo
            var commodityNo = trNode.find("td").eq(1).html();
            var data = {
                "commodityNum": parent.html(),
                "orderNumber": parent.attr("id"),
                "commodityNo": commodityNo
            }
            //alert(JSON.stringify(data));
            $.ajax({
                url: baseOrderUrl + "/changeCommodityNum.action",
                type: 'post',
                data: {
                    "commodityNum": parent.html(),
                    "orderNumber": parent.attr("id"),
                    "commodityNo": commodityNo
                },
                async: true,
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.s == false) {
                        layer.msg(data.m);
                        // 将数量改为原数量
                        parent.html(oldNum);
                    } else {
                        var totalMoneyCell = trNode.find("td").eq(8);
                        var saleMoneyCell = trNode.find("td").eq(4);
                        //alert(parent.html() * saleMoneyCell.html());
                        var totalMoney = parent.html() * saleMoneyCell.html();
                        totalMoneyCell.html(totalMoney);
                        var allMoney = addAllMoney("orderTable");
                        var freightMoney = $("#freightInput").val();
                        $("#moneyInput").val(allMoney + Number(freightMoney));
                    }
                }
            });
        })
    }

    // 添加订单
    function addOrder() {
        // 获取表格对象
        var orderTable = document.getElementById("addTable");
        // 获取表格行数
        var rowsNum = orderTable.rows.length;
        // 插入新行
        var newRow = orderTable.insertRow(rowsNum);

        var cell0 = newRow.insertCell(0);
        $(cell0).html("<div class='wrap'><input style='width:100%;position:relative;' class='input-text auto-inp' autocomplete='off' type='text' id='autoCompleteId" + rowsNum + "' name='autoCompleteName" + rowsNum + "'/>" + "<div style='display: none' class='ac_results' id='autoCompleteHidden" + rowsNum + "' /></div>");
        var cell1 = newRow.insertCell(1);
        // 绑定失焦事件进行回显
        //cell1.setAttribute("onblur", "findCom(this)");
        var cell2 = newRow.insertCell(2);
        var cell3 = newRow.insertCell(3);
        cell3.setAttribute("onblur", "sumMoney(this)")
        var cell4 = newRow.insertCell(4);
        var cell5 = newRow.insertCell(5);
        var cell6 = newRow.insertCell(6);
        var cell7 = newRow.insertCell(7);
        var cell8 = newRow.insertCell(8);
        /*cell8.setAttribute("onchange", "showAllMoney(this)")*/

        /*cell0.setAttribute("jsonName", "rowsNum");
        cell0.innerHTML = rowsNum;*/
        cell0.setAttribute("jsonName", "commodityName");
        cell1.setAttribute("jsonName", "commodityNo");
        cell2.setAttribute("jsonName", "unit");
        cell3.setAttribute("jsonName", "commodityNum");
        cell4.setAttribute("jsonName", "salePrice");
        cell5.setAttribute("jsonName", "subPrice");
        cell6.setAttribute("jsonName", "subPriceUnit");
        cell7.setAttribute("jsonName", "weight");
        cell8.setAttribute("jsonName", "totalMoney");

        cell0.setAttribute("contentEditable", true);
        cell1.setAttribute("contentEditable", true);
        cell2.setAttribute("contentEditable", true);
        cell3.setAttribute("contentEditable", true);
        cell4.setAttribute("contentEditable", true);
        cell5.setAttribute("contentEditable", true);
        cell6.setAttribute("contentEditable", true);
        cell7.setAttribute("contentEditable", true);
        cell8.setAttribute("contentEditable", true);


        var insertCell = newRow.insertCell(9);
        insertCell.innerHTML = "<input class='btn btn-primary radius' type='button' onclick='deleteOrder(this)' value='删除'>";

        // 添加orderNumber隐藏行
        var cell9 = newRow.insertCell(10);
        cell9.setAttribute("jsonName", "orderNumber");
        cell9.style.display = "none";
        cell9.innerHTML = ${orders.orderNumber};

        if (rowsNum > 0) {
            $("#addOrderMoneyDiv").show();
        } else {
            $("#addOrderMoneyDiv").hide();
        }

        /*//自动补全
        $.ajax({
            url: baseOrderUrl + "/findAllCommodity.action",
            type: 'post',
            async: true,
            cache: false,
            dataType: "json",
            success: function (data) {
                alert(data);
                //自动补全
                var autoComplete = new AutoComplete("#autoCompleteId" + rowsNum, "#autoCompleteHidden" + rowsNum, data);
                /!* $(cell0).on("keyup", "#autoCompleteId" + rowsNum, function () {
                     alert(1);
                 });*!/
                $(cell0).on("keyup", "#autoCompleteId" + rowsNum, function (event) {
                    //alert(event);
                    autoComplete.start(event);
                    alert(1);
                });
            }
        });*/

        // 自动补全
        $("#autoCompleteId" + rowsNum).keyup(function (even) {
            var v = even.which;
            if (v == 38 || v == 40 || v == 13)// 当点击上下键或者确定键时阻止他传送数据
            {
                return;
            }
            var txt = $("#autoCompleteId" + rowsNum).val();//这里是取得他的输入框的值
            if (txt != "") {
                //拼装数据
                $.ajax({
                    url: baseOrderUrl + "/findCommodityByName.action",//从后台取得json数据
                    type: "post",
                    dataType: "json",
                    data: {
                        "commodityName": txt
                    },
                    success: function (ls) {
                        if (ls.length <= 0)
                            return;
                        //alert(ls);
                        var offset = $("#autoCompleteId" + rowsNum).offset();
                        $("#autoCompleteHidden" + rowsNum).show();
                        $("#autoCompleteHidden" + rowsNum).css("top", (offset.top + 30) + "px");
                        $("#autoCompleteHidden" + rowsNum).css("left", offset.left + "px");
                        var Candidate = "";
                        maxcount = 0;//再重新得值
                        $.each(ls, function (k, v) {
                            Candidate += "<li style='font-size:2px' id='" + maxcount + "'>" + v.commodityName + "</li>";
                            maxcount++;
                        });
                        $("#autoCompleteHidden" + rowsNum).html(Candidate);
                        $("#autoCompleteHidden" + rowsNum + "li:eq(0)").css("background", "#A8A5A5");
                        //高亮对象
                        //$('body').highLight();
                        //$('body').highLight($("#autoCompleteId" + rowsNum).val());
                        event.preventDefault();
                        //当单击某个ＬＩ时反映
                        $("#autoCompleteHidden" + rowsNum + " li").click(function () {
                            $("#autoCompleteId" + rowsNum).val($("#autoCompleteHidden" + rowsNum + " li:eq(" + this.id + ")").text());
                            $("#autoCompleteHidden" + rowsNum).html("");
                            $("#autoCompleteHidden" + rowsNum).hide();
                            var InputNode = $("#autoCompleteId" + rowsNum);
                            var trNode = InputNode.parent().parent().parent();
                            var comObj = ls[this.id];
                            //trNode.children("td").eq(0).html(comObj.commodityName);
                            // 获取商品信息table的所有商品条码
                            var comNoList = getAllCommodityNo("#orderTable");
                            // 如果商品已存在
                            if (comNoList.length > 0 && comNoList.indexOf(comObj.commodityNo) != -1) {
                                layer.msg("该商品已经添加,请不要重复添加");
                                return;
                            }
                            trNode.children("td").eq(1).html(comObj.commodityNo);
                            trNode.children("td").eq(2).html(comObj.unit);
                            trNode.children("td").eq(3).html(1);
                            trNode.children("td").eq(4).html((comObj.salePrice / 100).toFixed(1));
                            trNode.children("td").eq(5).html((comObj.subPrice / 100).toFixed(1));
                            trNode.children("td").eq(6).html(comObj.subPriceUnit);
                            trNode.children("td").eq(7).html(comObj.weight);
                            trNode.children("td").eq(8).html((comObj.salePrice / 100).toFixed(1));

                            if ($("#addOrderMoneyInput").val() == undefined || $("#addOrderMoneyInput").val() == "") {
                                $("#addOrderMoneyInput").val((comObj.salePrice / 100).toFixed(1));
                            }

                        });
                        //移动对象
                        $("#autoCompleteHidden" + rowsNum + " li").hover(function () {
                            $("#autoCompleteHidden" + rowsNum + " li").css("background", "#FFFFFF");
                            $("#autoCompleteHidden" + rowsNum + " li:eq(" + this.id + ")").css("background", "#A8A5A5");
                            thisCount = this.id;
                        }, function () {
                            $("#autoCompleteHidden" + rowsNum + " li").css("background", "#FFFFFF");
                        });
                    },
                    error: function () {
                        $("#autoCompleteHidden" + rowsNum).html("");
                        $("#autoCompleteHidden" + rowsNum).hide();
                        maxcount = 0;
                    }
                });
            } else {
                $("#autoCompleteHidden" + rowsNum).hide();
                maxcount = 0;
            }
        });
        //当单击ＢＯＤＹ时则隐藏搜索值
        $("body").click(function () {
            $("#autoCompleteHidden" + rowsNum).html("");
            $("#autoCompleteHidden" + rowsNum).hide();
            thisCount = 0;
        });

    }

    function showAllMoney(obj) {
        alert("1");
        var tableNode = $(obj).parent().parent();
        var tableId = tableNode.attr("id");
        alert(tableId);
        //alert(tableId == "orderTable");
        addAllMoney(tableId);
    }

    // 遍历所有的行的金额汇总
    function addAllMoney(tableId) {
        var sum = 0.0;
        var rowNum = $("#" + tableId).find("tr").length;
        for (var i = 1; i < rowNum; i++) {
            var orderMoney = $("#" + tableId).find("tr").eq(i).find("td").eq(8).html();
            alert(orderMoney);
            if (orderMoney == "") {
                orderMoney = 0;
            }
            sum = sum + parseFloat(orderMoney);
        }
        alert(sum);
        return sum;
    }

    // 商品条形码绑定失焦事件
    /*function findCom(obj) {
        var commodityNo = $(obj).html();

        if (commodityNo != null && commodityNo.trim() != "") {
            // 查询商品
            var url = baseOrderUrl + "/findCommodityByNo.action";
            $.ajax({
                url: url,
                type: 'get',
                data: {
                    "commodityNo": commodityNo
                },
                async: true,
                cache: false,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                //Accept:"text/html, application/xhtml+xml, *!/!*",
                accept: "*!/!*",
                success: function (data) {
                    //alert(data.s);
                    if (data.s == true) { // 如果返回成功将信息回显到表单
                        alert(data.resultObj.commodityNo);
                        var comObj = data.resultObj;
                        var trNode = $(obj).parent();
                        trNode.children("td").eq(0).html(comObj.commodityName);
                        trNode.children("td").eq(1).html(comObj.commodityNo);
                        trNode.children("td").eq(2).html(comObj.unit);
                        trNode.children("td").eq(3).html(1);
                        trNode.children("td").eq(4).html((comObj.salePrice / 100).toFixed(1));
                        trNode.children("td").eq(5).html((comObj.subPrice / 100).toFixed(1));
                        trNode.children("td").eq(6).html(comObj.subPriceUnit);
                        trNode.children("td").eq(7).html(comObj.weight);
                        trNode.children("td").eq(8).html((comObj.salePrice / 100).toFixed(1));

                        if ($("#addOrderMoneyInput").val() == undefined || $("#addOrderMoneyInput").val() == "") {
                            $("#addOrderMoneyInput").val((comObj.salePrice / 100).toFixed(1));
                        }
                    }
                },
            });
        }
    }*/

    // 获取table的商品条码
    function getAllCommodityNo(tableId) {
        var comNoList = [];
        var trs = $(tableId).find("tr");
        if (trs.length > 0) {
            for (var i = 1; i < trs.length; i++) {
                var comNo = trs.eq(i).find("td").eq("1").html();
                alert(comNo);
                comNoList.push(comNo);
            }
        }
        return comNoList;
    }

    // 编辑数量绑定失焦事件
    function sumMoney(obj) {
        var num = $(obj).text();
        var MoneyNode = $(obj).parent().children("td").eq(8);
        MoneyNode.html(num * MoneyNode.html());

        // 获取总金额
        var tableNode = $(obj).parent().parent().parent();
        var tableId = tableNode.attr("id");
        var allMoney = addAllMoney(tableId);
        $("#addOrderMoneyInput").val(allMoney);
    }

    // 获取订单信息
    function getOrder(tableID) {
        var args = {};
        $("#" + tableID + " tr:gt(0)").each(function (i) {
            var data = {};
            $(this).find("td:lt(9)").each(function (j) {
                /*alert($(this).attr("jsonName"));
                alert($(this).text());*/
                var name = $(this).attr("jsonName");
                if (j == 0) {
                    if ($("#autoCompleteId" + (j + 1)).length > 0) {
                        data[name] = $("#autoCompleteId" + (j + 1)).val();
                    } else {
                        data[name] = $(this).text();
                    }
                } else {
                    data[name] = $(this).text();
                }
            });
            args[i] = data;
        });
        //alert("data;" + JSON.stringify(args));
        return args;
    }

    // 修改订单
    function modifyOrder() {
        modifyCount++;

        // 获取id对应的对象
        var userNamesSpan = document.getElementById("userNamesSpan");
        var phoneSpan = document.getElementById("phoneSpan");
        var areaSpan = document.getElementById("areaSpan");

        var newObject1 = document.createElement('input');
        var newObject2 = document.createElement('input');
        var newObject3 = document.createElement('input');

        newObject1.value = userNamesSpan.innerText;
        newObject1.setAttribute("type", "text");
        newObject1.setAttribute("class", "Input_Text");
        newObject1.setAttribute("name", userNamesSpan.name);
        newObject1.setAttribute("id", userNamesSpan.id);

        newObject2.value = phoneSpan.innerText;
        newObject2.setAttribute("type", "text");
        newObject2.setAttribute("class", "Input_Text");
        newObject2.setAttribute("name", phoneSpan.name);
        newObject2.setAttribute("id", phoneSpan.id);

        newObject3.value = areaSpan.innerText;
        newObject3.setAttribute("type", "text");
        newObject3.setAttribute("class", "Input_Text");
        newObject3.setAttribute("name", areaSpan.name);
        newObject3.setAttribute("id", areaSpan.id);

        //插入替换后的Input
        userNamesSpan.parentNode.appendChild(newObject1);
        phoneSpan.parentNode.appendChild(newObject2);
        areaSpan.parentNode.appendChild(newObject3);
        //删除表单原控件
        //obj.removeNode();
        userNamesSpan.parentNode.removeChild(userNamesSpan);
        phoneSpan.parentNode.removeChild(phoneSpan);
        areaSpan.parentNode.removeChild(areaSpan);

        // 将链转换为保存
        var modifyDiv = document.getElementById("modifyDiv");
        var saveDiv = document.getElementById("saveDiv");
        modifyDiv.style.display = "none";
        saveDiv.style.display = "block";
    }

    // 保存订单的修改
    function saveOrder(orderNumber) {
        // 获取id对应的对象
        var userNamesSpan = document.getElementById("userNamesSpan");
        var phoneSpan = document.getElementById("phoneSpan");
        var areaSpan = document.getElementById("areaSpan");

        var newObject1 = document.createElement('span');
        var newObject2 = document.createElement('span');
        var newObject3 = document.createElement('span');

        newObject1.innerText = userNamesSpan.value;
        newObject1.setAttribute("name", userNamesSpan.name);
        newObject1.setAttribute("id", userNamesSpan.id);

        newObject2.innerText = phoneSpan.value;
        newObject2.setAttribute("name", phoneSpan.name);
        newObject2.setAttribute("id", phoneSpan.id);

        newObject3.innerText = areaSpan.value;
        newObject3.setAttribute("name", areaSpan.name);
        newObject3.setAttribute("id", areaSpan.id);

        //插入替换后的span
        userNamesSpan.parentNode.appendChild(newObject1);
        phoneSpan.parentNode.appendChild(newObject2);
        areaSpan.parentNode.appendChild(newObject3);
        //删除表单原控件
        //obj.removeNode();
        userNamesSpan.parentNode.removeChild(userNamesSpan);
        phoneSpan.parentNode.removeChild(phoneSpan);
        areaSpan.parentNode.removeChild(areaSpan);

        // 将修改div显示
        var modifyDiv = document.getElementById("modifyDiv");
        var saveDiv = document.getElementById("saveDiv");
        modifyDiv.style.display = "block";
        saveDiv.style.display = "none";

        userNames = userNamesSpan.value;
        phone = phoneSpan.value;
        var receiveAddress = areaSpan.value;

        // 获取地址
        getAddress(receiveAddress);
    }

    // 获取地址方法
    function getAddress(receiveAddress) {
        var splits = null;
        // 将地址分割
        if (receiveAddress.indexOf("-")) {
            splits = receiveAddress.split("-");
            area = splits[0] + "-" + splits[1];
            address = splits[2];
        } else {
            splits = receiveAddress.split(" ");
            area = splits[0] + "-" + splits[1];
            address = splits[2];
        }
    }

    /*   // 取消单个订单
       function cancelOrder(obj) {
           // 如果没有选中
           if (obj.checked) {
               // 获取当前行对象
               var trNode = $(obj).parent().parent();
               var tdCell = trNode.find("td").eq(3);
               tdCell.html("<input id='comNumInput' type='text' value='" + tdCell.text() + "'/>");
               $("#comNumInput").bind("blur", function () {
                   var parent = $("#comNumInput").parent();
                   parent.html($("#comNumInput").val());

                   var allMoney = addAllMoney("orderTable");
                   var freightMoney = $("#freightInput").val();
                   $("#moneyInput").val(parseFloat(allMoney) + parseFloat(freightMoney));
               })
           }
       }*/

    // 删除增加订单
    function deleteOrder(obj) {
        // 删除当前行
        $(obj).parent().parent().remove();
        if ($("#addTable").find("tr:gt(0)").size() <= 0) {
            $("#addOrderMoneyDiv").hide();
        }
    }

    // 删除表格第一行以外的所有行
    function remove() {
        var table = document.getElementById("cancelTable");
        trs = table.getElementsByTagName("tr");

        for (var i = trs.length - 1; i > 0; i--) {
            table.deleteRow(i);
        }
    }

    // 整单取消方法
    function cancelAllOrderFn() {
        var url = baseOrderUrl + "/cancelAllOrder.action";
        $.ajax({
            url: url,
            type: 'get',
            data: {
                "orderNumber": orderNumber,
                "orderId": orderId,
                "serviceId": serviceId,
                "serviceRemark": $("#serviceRemark").val()
            },
            async: true,
            cache: false,
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                if (data.s == true) {
                    layer.msg("整单取消成功", {time: 1000});
                    // 关闭窗口
                    layer_close();
                } else {
                    layer.msg("整单取消失败", {time: 1000});
                    window.location.href = "${context_root}/order/toAuditingInfo.action";
                }
            },
        });

    }

    // 关闭窗口
    function closeWin() {
        layer_close();
    }


    // 提交审核
    function submitModifyOrder() {

        var selectOrder = {};
        // 如果有选中取消的
        $("input[type='checkbox']").each(function (i) {
            if ($(this).is(':checked')) {
                var obj = {}
                var tds = $(this).parent().parent().find("td")
                var commodityNo = tds.eq(1).text();
                var commodityNum = tds.eq(3).text();
                //obj["orderNumber"] = "${orders.orderNumber}";
                obj["commodityNo"] = commodityNo;
                obj["commodityNum"] = commodityNum;
                selectOrder[i] = obj;
            }
        })

        var addOrderTable = {};
        //alert($("#addTable tr:gt(0)").size())
        if ($("#addTable tr:gt(0)").size() > 0) {
            addOrderTable = getOrder("addTable");
        }

        // 运费和总金额
        var freight = $("#freightInput").val() * 100;
        var orderPrice = (Number($("#moneyInput").val()) + Number($("#addOrderMoneyInput").val())) * 100;

        var data = {
            "serviceRemark": $("#serviceRemark").val(),
            "cancelOrder": JSON.stringify(selectOrder),
            "serviceId":  ${currentUser.userId},
            "orderId": orderId,
            "addOrderTable": addOrderTable,
            "freight": freight,
            "orderPrice": orderPrice,
            "orderNumber": "${orders.orderNumber}"
        };

        alert(JSON.stringify(data));
        var url = baseOrderUrl + "/orderModifySave.action";
        // 异步请求
        $.ajax({
            url: url,
            type: 'post',
            data: {
                "serviceRemark": $("#serviceRemark").val(),
                "cancelOrder": JSON.stringify(selectOrder),
                "serviceId": serviceId,
                "orderId": orderId,
                "addOrderTable": JSON.stringify(addOrderTable),
                "freight": freight,
                "orderPrice": orderPrice,
                "orderNumber": "${orders.orderNumber}"
            },
            async: true,
            cache:
                false,
            dataType:
                "json",
            /*contentType:
                "application/json;charset=utf-8",*/
            success:

                function (data) {
                    if (data.s == true) {
                        layer.msg('审核成功');
                        layer_close();
                    } else {
                        // 如果修改失败重新加载审核页面
                        layer.msg(data.m);
                        window.location.href = "${context_root}/order/toAuditingInfo.action?orderNumber=${orders.orderNumber}";
                    }
                }
        })
        ;
    }

</script>
</html>
