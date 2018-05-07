<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-coupon-edit">

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>优惠券名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="hidden" name="id" value="${acoupon.id}">
                <input type="text" class="input-text" maxlength="20" placeholder="" id="name" name="name" value="${acoupon.name}">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>优惠券金额：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" maxlength="20" placeholder="" id="money" name="money"
                       value="${acoupon.money}">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>有效期：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input id="timeType0" type="radio"  name="timeType" onclick="toggleDiv(this.value);" value="0">按天
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input id="timeType1" type="radio" name="timeType" onclick="toggleDiv(this.value);" value="1">时段
            </div>
        </div>
        <div class="row cl" style="display: none;" id="dayDiv">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span></label>
            <div class="formControls col-xs-8 col-sm-4">
                <input style="width:210px;" type="text" class="input-text" maxlength="20" placeholder="" id="useDays"
                       name="useDays" value="${acoupon.useDays}">天

            </div>
        </div>
        <div class="row cl" style="display: none;" id="dateDiv">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span></label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" onfocus="WdatePicker()" id="beginTime" name="startTime"
                       class="input-text Wdate" style="width:100px;" placeholder="开始时间" value="${acoupon.startTime}">一
                <input type="text" onfocus="WdatePicker()" id="endTime" name="endTime"
                       class="input-text Wdate" style="width:100px;" placeholder="结束时间" value="${acoupon.endTime}">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>发布数量：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" maxlength="20" placeholder="" id="num" name="num"
                       value="${acoupon.num}">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>领取方式：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <select id="receiveType" name="receiveType" value="${acoupon.useDays}">
                    <option value="0">自动领取</option>
                    <option value="1">手动领取</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>使用商品：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input id="useForAll0" type="radio" name="useForAll" value="0">全部
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input id="useForAll1" type="radio" name="useForAll" value="1">部分
            </div>
        </div>
        <div class="mt-20">
            <input type="button" class="btn btn-primary radius" value="添加行" id="addRow"/>
            <table class="table table-border table-bordered table-hover table-bg table-sort" id="orderTab">
                <thead>
                <tr class="text-c">
                    <th width="3%">序号</th>
                    <th width="14%">商品名称</th>
                    <th width="10%">商品条码</th>
                    <th width="3%">操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>使用条件：</label>
            <div class="formControls col-xs-8 col-sm-4">
                满<input type="text" class="input-text" style="width:195px;" maxlength="20" placeholder=""
                        id="useCondition" name="useCondition" value="${acoupon.useCondition}">元
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>订单SKU：</label>
            <div class="formControls col-xs-8 col-sm-4">
                满<input type="text" class="input-text" style="width:185px;" maxlength="20" placeholder="" id="orderSKU"
                        name="orderSKU" value="${acoupon.orderSKU}">SKU
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">使用说明：</label>
            <div class="formControls col-xs-8 col-sm-6">
                <textarea name="useDescription" cols="" rows="" class="textarea" placeholder="优惠券的使用说明"
                          datatype="*10-100" dragonfly="true"
                          onKeyUp="textarealength(this,200)">${acoupon.useDescription}</textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>


<script type="text/javascript">

    $(function(){
        var i = "${acoupon.timeType}";

        $("#timeType"+i).attr("checked","checked");

        if(i=="0"){
            $("#dayDiv").css("display", "block");
            $("#dateDiv").css("display", "none");
        }else if(i=="1"){
            $("#dayDiv").css("display", "none");
            $("#dateDiv").css("display", "block");
        }else{
            alert("timeType值错误");
        }

        $("#receiveType").val("${acoupon.receiveType}");

        i = "${acoupon.useForAll}";
        $("#useForAll"+i).val(i).attr("checked","checked");


    });

    /* 添加行 */
    $("#addRow").click(function () {
//        tabIndex++;
//        var id1='no'+tabIndex;
//
//        var id2='commodityNo'+tabIndex;
//        var id3='commodityName'+tabIndex;
//        var id4='unitId'+tabIndex;
//        var id5='stockNum'+tabIndex;
//        id6='auto'+tabIndex;
//
        var tr = document.createElement('tr');
//        tr.id=tabIndex;
//        tr.innerHTML = " <tr id='"+tabIndex+"'>"+
//            "<td><input class='input-text' type='text' id="+id1+" name="+id1+" disabled='disabled' value='"+tabIndex+"'></td>"+
//            "<td><div class='wrap'><input style='width:85%;margin-left: 5px;position:relative;bottom: 4px;' class='input-text auto-inp' autocomplete=\"off\" type='text' id="+id2+" name="+id2+"/>"+
//            "<div class='auto hidden' id="+id6+"><div class='auto_out'>1</div><div class='auto_out'>2</div></div></div>&nbsp;"+
//            "<span style='color:blue;cursor:pointer;left:30%;line-height:150%;position: absolute;' onclick='addIframe()'>添加</span></td>"+
//            "<td><input class='input-text' style='border: 0px;text-align:center;' type='text' id="+id3+" name="+id3+" readonly></td>"+
//            "<td><input class='input-text' style='border: 0px;text-align:center;' type='text' id="+id4+" name="+id4+" readonly></td>"+
//            "<td><input class='input-text' type='text' id="+id5+" name="+id5+"></td>"+
//            "<td><input class='btn btn-primary radius' type='button' value='删除' onclick='deleteRow(this)'></td>"+
//            "</tr>";
        tr.innerHTML = "<tr><td>2</td><td>3</td><td>4</td><td><input class='btn btn-primary radius' type='button' value='删除' onclick='deleteRow(this)'></td></tr>";
        $("#orderTab").children("tbody")[0].appendChild(tr);

        //自动补全
        $.ajax({
            url: "${context_root}/commodity/getCommodityList.action",
            type: 'post',
            async: true,
            cache: false,
            dataType: "json",
            success: function (data) {
                //自动补全
                var autoComplete = new AutoComplete(id2, id6, data);
                document.getElementById(id2).onkeyup = function (event) {
                    autoComplete.start(event);
                }
            },
        });
    });


    function deleteRow(a) {
        a.parentNode.parentNode.remove();
    }


    $("#form-coupon-edit").validate({
        rules: {
            name: {
                required: true,
            },
            money: {
                required: true,
                digits: true,
            },
            useDays: {
                digits: true,
                min: 1,
            },
            num: {
                required: true,
                digits: true,
                min: 1,
            },
            isUseForAll: {
                required: true,
            },
            useCondition: {
                required: true,
            },
            orderSKU: {
                required: true,
            },
        },
        messages: {
            "userName": {
                remote: "该用户已经存在"
            }
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var index = parent.layer.load();
            $.ajax({
                url: "${context_root}/coupon/edit.action",
                type: 'post',
                async: true,
                cache: false,
                data: $(form).serialize(),
                dataType: "json",
                success: function (data) {
                    parent.layer.close(index);
                    if (data.s == true) {
                        index = parent.layer.getFrameIndex(window.name);
                        parent.layer.msg("修改成功,正在刷新数据请稍后……", {icon: 1, time: 1000, shade: [0.1, '#fff']}, function () {
                            window.parent.location.reload();
                        });
                    } else {
                        parent.layer.alert(data.m, {icon: 2, title: "系统提示"});
                    }
                },
            });
        }
    });


    function toggleDiv(value) {

        if (value == 0) {
            $("#dayDiv").css("display", "block");
            $("#dateDiv").css("display", "none");
        } else {
            $("#dayDiv").css("display", "none");
            $("#dateDiv").css("display", "block");
        }
    }

    function toggleUseDiv(value) {
        if (value == 0) {
            $("#dayDiv").css("display", "block");
        } else {
            $("#dayDiv").css("display", "none");
        }

    }
</script>
</body>
</html>