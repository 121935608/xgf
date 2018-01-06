<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<script>
$(document).ready(function(){
	 $.get("${context_root}/commodity/findProductById.action",function(data){
    	for(var i=0;i<data.length;i++){
    		$("#tags").append("<input type='checkbox' name='taginfo' value="+data[i]+"  />"+data[i]+"<br/>");
   	 	}
	});
    
});
</script>
	<div id="ul">
		<ul>
			<li class="page"  index="0"><div style="font-weight:bold;width:80px;height:30px;color:white;border-radius:5px;padding:5px;cursor:pointer;background:#1dc21d;border:2.5px solid #2da32d;">基本信息</div></li>
			<li class="page"  index="1"><div style="color:white;cursor:pointer;padding:5px;border-radius:5px;width:80px;height:30px;background:#1dc21d;border:2.5px solid #2da32d;">产品详情</div></li>
			<li class="page"  index="2"><div style="color:white;cursor:pointer;padding:5px;border-radius:5px;width:80px;height:30px;border:2.5px solid #2da32d;background:#1dc21d;">资料补充</div></li>
			<li class="page"  index="3"><div style="color:white;cursor:pointer;padding:5px;border-radius:5px;width:80px;height:30px;border:2.5px solid #2da32d;background:#1dc21d;">申请资料</div></li>
		</ul>
	</div>
	<style> 
		body{padding: 0;margin:0;}
		#ul ul li{float:left;padding-left:35px;}
		li{list-style:none;font-size:20px;padding:5px;}
		ul{margin-left:100px;}
			.selectHYL{
			  border: solid 1px #c8ccc8;
			
			  appearance:none;
			  -moz-appearance:none;
			  -webkit-appearance:none;
			  height:30px;
			  width:35px;
			  cursor:pointer;
			  /*backgroud: ../product/select.png*/
}
	</style>
	<script type="text/javascript">
	 $("ul li").click(function(){
		 	var i = parseInt($(this).attr("index"));
	        var div = $(".showOrHide").eq(i);
		 	$(this).children("div").css("font-weight","bold");
		 	$(this).parent().children("li").not(this).children("div").css("font-weight","normal");
	        div.css("display","block");
	        $(".showOrHide").not(div).css("display","none");
	    });
	</script>
	<div style="clear:both"></div>
	<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-loan-add">
	<div class="showOrHide" style="display:block;margin-left:100px">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产品名称：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="loanName" name="loanName">
			</div>
		</div>
		<%--<div class="row cl">--%>
			<%--<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>资方：</label>--%>
			<%--<div class="formControls col-xs-8 col-sm-4">--%>
				<%--<y:select id="name" name="name" codeGroup="${business}" selectedValue="null"--%>
							  <%--cssClass="select" headerKey="" headerValue="--请选择--">--%>
				<%--</y:select>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>分类：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<y:select id="type" name="type" codeGroup="${tagFl}" selectedValue="null"
						  cssClass="select" headerKey="" headerValue="--请选择--">
				</y:select>
			</div>
		</div>--%>
		<%--<div class="row cl">--%>
			<%--<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>贷款类型：</label>--%>
			<%--<div class="formControls col-xs-8 col-sm-4">--%>
				<%--<y:select id="loanType" name="loanType" codeGroup="${product.commodityNo}" selectedValue="null"--%>
						  <%--cssClass="select" headerKey="" headerValue="--请选择--">--%>
				<%--</y:select>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3" style="display:inline"><span class="c-red" style="display:inline">*</span>贷款金额：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" style="display:inline" class="input-text" value="" placeholder="" name="moneyMin" id="moneyMin">&nbsp;&nbsp;
				——&nbsp;<input type="number" style="display:inline" class="input-text" value="" placeholder="" name="moneyMax" id="moneyMax">&nbsp;
				<select id="moneyType" name="moneyType" class="selectHYL" style="width:53px;height:30px">
					<option value="Y">元</option>
					<option value="WY">万元</option>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>贷款年限：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" value="" placeholder="" name="limitMin" id="limitMin">&nbsp;
				&nbsp;——&nbsp;
				<input type="number" class="input-text" value="" placeholder="" name="limitMax" id="limitMax">&nbsp;
				<select id="limitType" name="limitType" class="selectHYL" style="width:50px;height:30px">
					<option value="Y">年</option>
					<option value="M">月</option>
					<option value="D">日</option>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>标签：</label>
			<div class="formControls col-xs-8 col-sm-4" id="tags">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手续费：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" class="input-text" autocomplete="off" value="" placeholder="" id="fee" name="fee">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>费率：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<select id="rateType" name="rateType" class="selectHYL" style="width:50px;height:30px">
					<option value="Y">年化</option>
					<option value="M">月化</option>
					<option value="D">日化</option>
				</select>
				<input type="number" class="input-text" autocomplete="off" value="" placeholder="" id="rate" name="rate">&nbsp;%
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>逾期：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<select id="dueRateType" name="dueRateType" class="selectHYL" style="width:50px;height:30px">
					<option value="Y">年化</option>
					<option value="M">月化</option>
					<option value="D">日化</option>
				</select>
				<input type="number" class="input-text" autocomplete="off" value="" placeholder="" id="dueRate" name="dueRate">&nbsp;%
			</div>
		</div>
		<!-- <div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>还款方式：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<select id="repayWay" name="repayWay" class="selectHYL" style="width:150px;height:30px">
					<option value="先本后息">先本后息</option>
					<option value="先息后本">先息后本</option>
					<option value="等比还款">等比还款</option>
					<option value="一次性还款">一次性还款</option>
				</select>
			</div>
		</div> -->
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上下架：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<select id="status" name="status" class="selectHYL" style="width:100px;height:30px">
					<option  value="1">上架</option>
					<option value="-1">下架</option>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>贷款方式：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<select id="apiWay" name="apiWay" class="selectHYL" style="width:150px;height:30px">
					<option  value="TEL">门店贷款</option>
					<option value="H5">第三网站审核贷款</option>
				</select>
			</div>
		</div>
		<div class="row cl"  style="display:none" id="playURL">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>URL:</label>
			<div class="formControls col-xs-8 col-sm-4">
			   <input type="url" id="apiUrl" name="apiUrl" class="input-text">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>排序：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="number" id="sort" name="sort" class="input-text">
			</div>
		</div>
		<div class="row cl" style="display:none" id="add1">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>绑卡地址：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="url" id="bankUrl" name="bankUrl" class="input-text">
			</div>
		</div>
		<div class="row cl" style="display:none" id="add2">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>还款地址：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="url" id="repayUrl" name="repayUrl" class="input-text">
			</div>
		</div>
		<div class="row cl" style="display:none" id="add3" >
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>订单推送地址：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="url" id="orderUrl" name="orderUrl" class="input-text">
			</div>
		</div>
		<div class="row cl" style="display:none" id="add4" >
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>订单推送call：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="input" id="orderCall" name="orderCall" class="input-text">
			</div>
		</div>
		<!-- <div class="row cl" style="display:none" id="add5" >
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>签约地址：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="input" id="signUrl" name="signUrl" class="input-text">
			</div>
		</div> -->
		
</div>
<!-- 产品详情 -->
<div class="showOrHide" style="display:none">
		<div style="text-align:center;margin-bottom:10px;">
			<div style="margin-left:50px;">
				<textarea name="loanIntro" id="loanIntro" style="width:650px;height:350px;" placeholder="商品详情"></textarea>
			</div>
		</div>
</div>
<!-- 资料补充 -->
<div class="showOrHide" style="display:none;text-align:center;margin-left:50px;">
		<div style="align:absmiddle;padding:10px;">
			<span style="font-size:16px;">办理流程</span>&nbsp;&nbsp;
			<textarea name="loanFlow" id="loanFlow" style="width:400px;height:300px;vertical-align:top;"></textarea>
		</div>
		<div style="align:absmiddle;padding:10px">
			<span style="font-size:16px">申请条件</span>&nbsp;&nbsp;
			<textarea name="loanCondition" id="loanCondition" style="width:400px;height:300px;vertical-align:top;"></textarea>
		</div>
		<div style="align:absmiddle;padding:10px">
			<span style="font-size:16px">所需材料</span>&nbsp;&nbsp;
			<textarea name="loanDoc" id="loanDoc" style="width:400px;height:300px;vertical-align:top;"></textarea>
		</div>
</div>
<!-- 申请资料 -->
<div class="showOrHide" style="display:none;text-align:center">
	<div style="margin-right:220px;margin-top:10px;margin-left:150px;">三级标题</div>
	<table class="table table-border table-bordered table-hover table-bg table-sort" style="margin-left:150px;width:380px;display:inline" id="table">
			<tr class="text-c" style="height:50px">
				<td width="130px" rowspan="2" id="content" >
					<input type="text" autocomplete="off" value="" maxlength="100" style="border:0;background:transparent;height:50px;align:right" placeholder="自定义内容" id="parent" name="parent">
				</td>
				<td width="250px">
					<input type="text" autocomplete="off" value=""  maxlength="100" style="border:0;background:transparent;height:50px;align:right" placeholder="自定义选项"  name="child">
				</td>
			</tr>
			<tr class="text-c" style="height:50px">
				<td width="250px">
					<input type="text" autocomplete="off" value="" maxlength="100" style="border:0;background:transparent;height:50px;align:right" placeholder="自定义选项" name="child">
				</td>
			</tr>
	</table>
	<div class="cl pd-5 bg-1 bk-gray mt-20" id="table_add"> <span class="l"><a href="javascript:;" onclick="table_add()"  class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>添加自定义选项</a></span></div>
</div>


		<div class="row cl" style="margin-left:130px;margin-top:30px">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">&nbsp;&nbsp;&nbsp;&nbsp;
				<input class="btn btn-primary radius" type="button" onclick="cancelForm()" id="cancel" value="&nbsp;&nbsp;取消&nbsp;&nbsp;">
			</div>
		</div>
		</form>
</article>
	<style>
		#table,#table_add,#table_save{
			float:left;margin:20px;
		}
	</style>	
<script type="text/javascript">
		//添加自定义选项
		var num = 2;
		function table_add(){
			num++;
			$("#table"). append("<tr style=\"height:50px;text-align:center\";><td><input style=\"height:50px;border:0;background:transparent;align:right\" type=\"text\"  maxlength=\"100\" name=\"child\"></td></tr>");
			document.getElementById('content').rowSpan = num;
		}
		//URL
		document.getElementById('apiWay').onchange=function(){
			if(this.value == "H5"){
				$("#playURL").css("display","block");}
				else {
					$("#playURL").css("display","none");
				}
		}
		//精准贷快速贷
		document.getElementById('type').onchange=function(){
			if(this.value == "KSD"){
				$("#add1").css("display","block");
				$("#add2").css("display","block");
				$("#add3").css("display","block");
				$("#add4").css("display","block");
				$("#add5").css("display","block");
			}
			else{
				$("#add1").css("display","none");
				$("#add2").css("display","none");
				$("#add3").css("display","none");
				$("#add4").css("display","none");
				$("#add5").css("display","none");
			}
		}
		
		//富文本
		var editor;
		KindEditor.ready(function(K) {
             editor = K.create('#loanIntro',{
            	 resizeType : 1,
                 allowPreviewEmoticons : false,
                 allowImageUpload : true,
                 allowFileManager : true,
                 uploadJson : "${context_root}/consult/imgFile.action",
                 afterUpload : function () {
                     this.sync();
                 },
                 afterBlur: function () {
                     this.sync();
                 },
                 items : [ 'undo', 'redo', 'preview', 'justifyleft',
   						'justifycenter', 'justifyright', 'justifyfull',
   						'insertorderedlist', 'insertunorderedlist',
   						'fullscreen', 'formatblock', 'fontname', 'fontsize',
   						'|', 'forecolor', 'hilitecolor', 'bold', 'italic',
   						'underline', 'lineheight', 'removeformat', '|',
   						'image', 'table', 'emoticons', 'about' ],

             });
        });
		var editor2;
		KindEditor.ready(function(K) {
             editor2 = K.create('#loanFlow',{
            	 resizeType : 1,
                 allowPreviewEmoticons : false,
                 allowImageUpload : true,
                 allowFileManager : true,
                 uploadJson : "${context_root}/consult/imgFile.action",
                 afterUpload : function () {
                     this.sync();
                 },
                 afterBlur: function () {
                     this.sync();
                 },
                 items : [ 'undo', 'redo', 'preview', 'justifyleft',
   						'justifycenter', 'justifyright', 'justifyfull',
   						'insertorderedlist', 'insertunorderedlist',
   						'fullscreen', 'formatblock', 'fontname', 'fontsize',
   						'|', 'forecolor', 'hilitecolor', 'bold', 'italic',
   						'underline', 'lineheight', 'removeformat', '|',
   						'image', 'table', 'emoticons', 'about' ],
             });
        });
		var editor3;
		KindEditor.ready(function(K) {
             editor3 = K.create('#loanCondition',{
            	 resizeType : 1,
                 allowPreviewEmoticons : false,
                 allowImageUpload : true,
                 allowFileManager : true,
                 uploadJson : "${context_root}/consult/imgFile.action",
                 afterUpload : function () {
                     this.sync();
                 },
                 afterBlur: function () {
                     this.sync();
                 },
                 items : [ 'undo', 'redo', 'preview', 'justifyleft',
   						'justifycenter', 'justifyright', 'justifyfull',
   						'insertorderedlist', 'insertunorderedlist',
   						'fullscreen', 'formatblock', 'fontname', 'fontsize',
   						'|', 'forecolor', 'hilitecolor', 'bold', 'italic',
   						'underline', 'lineheight', 'removeformat', '|',
   						'image', 'table', 'emoticons', 'about' ],
             });
        });
		var editor4;
		KindEditor.ready(function(K) {
            editor4 = K.create('#loanDoc',{
            	resizeType : 1,
                allowPreviewEmoticons : false,
                allowImageUpload : true,
                allowFileManager : true,
                uploadJson : "${context_root}/consult/imgFile.action",
                afterUpload : function () {
                    this.sync();
                },
                afterBlur: function () {
                    this.sync();
                },
                items : [ 'undo', 'redo', 'preview', 'justifyleft',
  						'justifycenter', 'justifyright', 'justifyfull',
  						'insertorderedlist', 'insertunorderedlist',
  						'fullscreen', 'formatblock', 'fontname', 'fontsize',
  						'|', 'forecolor', 'hilitecolor', 'bold', 'italic',
  						'underline', 'lineheight', 'removeformat', '|',
  						'image', 'table', 'emoticons', 'about' ],
            });
        });
	//取消
	function cancelForm(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	
$("#form-loan-add").validate({
 	rules:{
		loanName:{
			required:true,
		},
		type:{
			required:true,
		},
		moneyMin:{
			required:true,
		},
		moneyMax:{
			required:true,
		},
		limitMin:{
			required:true,
		},
		limitMax:{
			required:true,
		},
		tag:{
			required:true,
		},
		fee:{
			required:true,
		},
		rate:{
			required:true,
		},
		sort:{
			required:true,
		},
		bankUrl:{
			required:true,
		},
		repayUrl:{
			required:true,
		},
		orderUrl:{
			required:true,
		},
		/* signUrl:{
			required:true,
		}, */
		orderCall:{
			required:true,
		},
	 }, 
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		/* var option1 =$('#name').find('option:selected').val();
		if(option1 == null){
			alert("请选择资方！");
			return;
		}
		var option2 =$('#type').find('option:selected').val();
		if(null == option2){
			alert("请选择分类！");
			return;
		} */
		//贷款金额
		var moneymin = $("#moneyMin").val();
		var moneymax = $("#moneyMax").val();
		var reg=/^[1-9]\d*$|^0$/; 
		if(parseInt(moneymin) > parseInt(moneymax) || moneymin<=0 || moneymax<=0){
			alert("请输入正确的贷款金额！");
			return;
		}
		if(reg.test(moneymin)==false || reg.test(moneymax)==false){
		    alert("请输入整数的贷款金额");
		    return;
		}
		var moneyType = $('#moneyType').find('option:selected').val();
		if(moneyType == "WY"){
			moneymin = parseInt(moneymin)*10000;
			moneymax = parseInt(moneymax)*10000;
			$("#moneyMin").val(moneymin);
			$("#moneyMax").val(moneymax);
		}
		//贷款年限
		var limitMin = $("#limitMin").val();
		var limitMax = $("#limitMax").val();
		if(parseInt(limitMin) > parseInt(limitMax) || limitMin<=0 || limitMax<=0){
			alert("请输入正确的贷款年限！");
			return;
		}
		//手续费
		var fee = $("#fee").val();
		if(fee < 0){
			alert("请输入正确的手续费！");
			return;
		}
		//费率
		var rate = $("#rate").val();
		if((rate < 0) || (rate > 100)){
			alert("请输入正确的费率！");
			return;
		}
		//逾期费率
		var dueRate = $("#dueRate").val();
		if((dueRate < 0) || (dueRate > 100)){
			alert("请输入正确的逾期！");
			return;
		}
		//排序
		var sort = $("#sort").val();
		if((sort < 0)){
			alert("请输入正确的排序！");
			return;
		}
		var index = parent.layer.load();
		$.ajax({
			<%--url:"${context_root}/product/saveLoan.action",--%>
			type:'post',
			async:true ,
			cache:false ,
			data:$(form).serialize(),
			dataType:"json",
			success:function(data){
				parent.layer.close(index);
				if(data.s == true){
					index = parent.layer.getFrameIndex(window.name);
					parent.layer.msg("保存成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
						window.parent.location.reload();
					}) ;
				}else{
					parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
				}
			},
		});
	}
});
</script> 
</body>
</html>