<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-couponUser-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>选择优惠券：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <span class="select-box" style="width: 200px;">
                    <select name="couponId" id="couponId" class="select" autocomplete="off">
                        <c:forEach items="${aCoupons}" var="coupon">
                            <option value="${coupon.id}">${coupon.couponName}</option>
                        </c:forEach>
                   </select>
                </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>发放对象：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <%--<input type="text" class="input-text" maxlength="20" value="" placeholder="" id="accountName" name="accountName">--%>
                <input type="radio" name="useForAll" value="0" checked="checked" onclick="$('#tableDiv').css('display','none')">全部商家
                <input type="radio" name="useForAll" value="1" onclick="$('#tableDiv').css('display','block')">部分商家
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>选择商家：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input style='width:85%;margin-left: 5px;position:relative;bottom: 4px;' class='input-text auto-inp' autocomplete='off' type="text"  maxlength="20"  placeholder="" id="storeName" name="storeName">
                <div class='auto hidden' id='autoDiv'>
                    <div class='auto_out'>1</div>
                    <div class='auto_out'>2</div>
                </div>
                <input  class="btn btn-primary radius" onclick="addStore()" value="确定" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span> ：</label>
            <div id="storeDiv" class="formControls col-xs-8 col-sm-4">
                <div value="111111">商家1</div><div value="1">商家1</div><div value="222222">商家1</div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>每人发放数量：</label>

                <div class="formControls col-xs-8 col-sm-4">
                    <input type="text" class="input-text" maxlength="20"  value="" placeholder="" id="perNum" name="perNum">
                    发放人数<input type="text" class="input-text" maxlength="20"    name="personNum">
                    本次发放数量<input type="text" class="input-text" maxlength="20"   name="giveNum">

                </div>
                <%--<div class="formControls col-xs-8 col-sm-5"><span>发放人数</span><input type="text" class="input-text col-sm-2" maxlength="20"    name="personNum"></div>--%>
                <%--<div class="formControls col-xs-8 col-sm-4"><span>发放数目</span><input type="text" class="input-text" maxlength="20"   name="giveNum"></div>--%>

        </div>


        <div class="row cl"  id="dateDiv">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>发放时间</label>
            <div class="formControls col-xs-8 col-sm-4">
                <span class="select-box" style="width: 120px;">
                    <select name="receiveType" id="receiveType" class="select" autocomplete="off">
                        <option value="0">定时发放</option>
                        <option value="1">立即方放</option>
                   </select>
                </span>
                <input type="text" onfocus="WdatePicker()" id="giveTime" name="giveTime"
                       class="input-text Wdate" style="width:100px;" placeholder="请选择时间">
            </div>
        </div>

        
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;确定&nbsp;&nbsp;">
                <input class="btn btn-primary radius" type="submit" id="cancel" value="&nbsp;&nbsp;取消&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>


<script type="text/javascript">
    $(function () {
        //自动补全
        $.ajax({
            url:"${context_root}/couponUser/getCommodityNos.action",
            type:'post',
            async:true ,
            cache:false ,
            dataType:"json",
            success:function(data){
                //自动补全
                var autoComplete = new AutoComplete("storeName","autoDiv",data);
                document.getElementById(id2).onkeyup = function(event){
                    autoComplete.start(event);
                }
            },
        });

    })

    function addStore() {



    }

    var trIndex = 0;






    $("#form-couponUser-add").validate({
        rules: {
            perNum: {
                required: true,
            },
            personNum: {
                required: true,
                digits:true,
            },
            giveNum: {
                digits:true,
                min:1,
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
            var data = $(form).serialize();

            var divs = $("#storeDiv div");
            if(divs.length>0){
                var storeIds = "&storeIds=";
                for(var i=0;i<divs.length;i++){
                    var storeId = divs[i].value;
                    storeIds+= storeId+",";
                }
                storeIds = storeIds.substring(0,storeIds.length-1);

                storeIds ="&storeIds=111111,222222";
                data += storeIds;
            }



            $.ajax({
                url: "${context_root}/couponUser/add.action",
                type: 'post',
                async: true,
                cache: false,
                data: data,
                dataType: "json",
                success: function (data) {
                    parent.layer.close(index);
                    if (data.s == true) {
                        index = parent.layer.getFrameIndex(window.name);
                        parent.layer.msg("保存成功,正在刷新数据请稍后……", {icon: 1, time: 1000, shade: [0.1, '#fff']}, function () {
                            window.parent.location.reload();
                        });
                    } else {
                        parent.layer.alert(data.m, {icon: 2, title: "系统提示"});
                    }
                },
            });
        }
    });


   function toggleDiv(value){
       if(value==0){
           $("#dayDiv").css("display","block");
           $("#dateDiv").css("display","none");
       }else{
           $("#dayDiv").css("display","none");
           $("#dateDiv").css("display","block");
       }
   }

   function toggleUseDiv(value) {
       if(value==0){
           $("#dayDiv").css("display","block");
       }else{
           $("#dayDiv").css("display","none");
       }

   }
</script>
</body>
</html>