<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-coupon-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>优惠券名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" maxlength="20"  value="" placeholder="" id="name" name="couponName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>优惠券金额：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" maxlength="20"  value="" placeholder="" id="money" name="money">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>有效期：</label>
            <div class="formControls col-xs-7 col-sm-4">
                <input type="radio" name="timeType" value="0" checked="checked" onclick="toggleDiv(this.value);" >按天
                <input type="radio" name="timeType" value="1" onclick="toggleDiv(this.value);">时段
                <%--<y:radio codeGroup="sys-locked" name="status" selectedValue="0" onclick="alert(11);"/>--%>
            </div>
        </div>
        <div class="row cl" style="display: block;" id="dayDiv">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span></label>
            <div class="formControls col-xs-8 col-sm-4">
                <input  style="width:210px;" type="text" class="input-text" maxlength="20"  value="" placeholder="" id="useDays" name="useDays">天

            </div>
        </div>
        <div class="row cl" style="display: none;" id="dateDiv">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span></label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" id="startTime" name="startTime"
                       class="input-text Wdate" style="width:100px;" placeholder="开始时间">一
                <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" id="endTime" name="endTime"
                       class="input-text Wdate" style="width:100px;" placeholder="结束时间">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>发布数量：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" maxlength="20" value="" placeholder="" id="num" name="num">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>领取方式：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <select id="receiveType" name="receiveType">
                    <option value="0">自动领取</option>
                    <option value="1">手动领取</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>使用商品：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <%--<input type="text" class="input-text" maxlength="20" value="" placeholder="" id="accountName" name="accountName">--%>
                <input type="radio" name="useForAll" value="0" checked="checked" onclick="$('#tableDiv').css('display','none')">全部
                <input type="radio" name="useForAll" value="1" onclick="$('#tableDiv').css('display','block')">部分
            </div>
        </div>
        <div id="tableDiv" style="display: none;" class="mt-20">
            <input type="button" class="btn btn-primary radius" value="添加行" id="addRow"/>
            <table class="table table-border table-bordered table-hover table-bg table-sort" id="orderTab">
                <thead>
                <tr class="text-c">
                    <th width="3%">序号</th>
                    <th width="14%">商品编码</th>
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
                满<input type="text" class="input-text"  style="width:195px;" maxlength="20" value="" placeholder="" id="useCondition" name="useCondition">元
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>订单SKU：</label>
            <div class="formControls col-xs-8 col-sm-4">
                满<input type="text" class="input-text"  style="width:185px;" maxlength="20" value="" placeholder="" id="orderSKU" name="orderSKU">SKU
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">使用说明：</label>
            <div class="formControls col-xs-8 col-sm-6">
                <textarea name="useDescription" cols="" rows="" class="textarea" placeholder="优惠券的使用说明"
                          datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,200)"></textarea>
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
    var trIndex = 0;



    /* 添加行 */
    $("#addRow").click(function(){
        trIndex++;
        var id1='no'+trIndex;
        var id2='commodityNo'+trIndex;
        var id5='auto'+trIndex;
        var tr=document.createElement('tr');
        tr.id=trIndex;


        tr.innerHTML = "<tr id='"+id1+"'><td><input class='input-text'   disabled='disabled' value='"+trIndex+"' type='text'></td>"+
        "<td><div onmouseleave='completeField("+trIndex+",this)' class='wrap'>"+
                 "<input style='width:85%;margin-left: 5px;position:relative;bottom: 4px;' class='input-text auto-inp' autocomplete='off' id='"+id2+"' name='"+id2+"' type='text'>"+
                 "<div class='auto hidden' id='"+id5+"'>"+
                      "<div class='auto_out'>1</div>"+
                      "<div class='auto_out'>2</div>"+
                 "</div>"+
             "</div>"+
        "</td>"+
        "<td><input class='input-text' style='border: 0px;text-align:center;'  readonly='' type='text'></td>"+
        "<td><input class='input-text' style='border: 0px;text-align:center;'  readonly='' type='text'></td>"+
        "<td><input class='btn btn-primary radius' value='删除' onclick='trIndex--;deleteRow(this)' type='button'></td></tr>"

        $("#orderTab").children("tbody")[0].appendChild(tr);



        //自动补全
        $.ajax({
            url:"${context_root}/coupon/getCommodityNos.action",
            type:'post',
            async:true ,
            cache:false ,
            dataType:"json",
            success:function(data){
                //自动补全
                var autoComplete = new AutoComplete(id2,id5,data);
                document.getElementById(id2).onkeyup = function(event){
                    autoComplete.start(event);
                }
            },
        });
    });

    function completeField(trid,div) {
        var tr = $("#"+trid);
        var commodityNo = $(div).children().eq(0).val();
        $.ajax({
            url:"${context_root}/coupon/getCommodityByNo.action?commodityNo="+commodityNo,
            success:function(data){
                tr.children().eq(2).children().eq(0).val(data.commodityName);
                tr.children().eq(3).children().eq(0).val(data.barCode);
            },
        });
    }

    function deleteRow(a){
        a.parentNode.parentNode.remove();
    }

    $("#form-coupon-add").validate({
        rules: {
            name: {
                required: true,
            },
            money: {
                required: true,
                digits:true,
            },
            useDays: {
                digits:true,
                min:1,
            },
            num: {
                required: true,
                digits:true,
                min:1,
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
            var data = $(form).serialize();
            var trs = $("tbody tr");
            if(trs.length>0){
                var commodityNos = "&commodityNos=";
                for(var i=0;i<trs.length;i++){
                    var div = $(trs[i]).find("td").eq(1);
                    var no = div.find("input").eq(0).val();
                    commodityNos += no+","
                }
                commodityNos = commodityNos.substring(0,commodityNos.length-1);
                data += commodityNos;
            }


            $.ajax({
                url: "${context_root}/coupon/add.action",
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