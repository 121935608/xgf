<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>

<script>
/*$(document).ready(function(){
	 $.get("${context_root}/commodity/findProductById.action",function(data){
    	for(var i=0;i<data.length;i++){
    		$("#tags").append("<input type='checkbox' name='taginfo' value="+data[i]+"  />"+data[i]+"<br/>");
   	 	}
	});
    
});*/
</script>
	<div id="ul">
		<ul>
			<li class="page"  index="0"><div style="font-weight:bold;width:80px;height:30px;color:white;border-radius:5px;padding:5px;cursor:pointer;background:#1dc21d;border:2.5px solid #2da32d;">基本信息</div></li>
			<li class="page"  index="1"><div style="color:white;cursor:pointer;padding:5px;border-radius:5px;width:80px;height:30px;background:#1dc21d;border:2.5px solid #2da32d;">商品详情</div></li>
			<li class="page"  index="2"><div style="color:white;cursor:pointer;padding:5px;border-radius:5px;width:80px;height:30px;border:2.5px solid #2da32d;background:#1dc21d;">上传图片</div></li>
			<%--<li class="page"  index="3"><div style="color:white;cursor:pointer;padding:5px;border-radius:5px;width:80px;height:30px;border:2.5px solid #2da32d;background:#1dc21d;">申请资料</div></li>--%>
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
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品名称：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="commodityName" name="commodityName" value="${product.commodityName}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品条码：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="commodityNo" name="commodityNo" value="${product.commodityNo}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品分类：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<span class="select-box" style="width: 100px;border: hidden;">
             <y:select id="classId" name="classId" codeGroup="${classes}" selectedValue="${product.categoryId}"
					   cssClass="select" headerKey="" headerValue="分类">
			 </y:select>
       		</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品标签：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<span class="select-box" style="width: 100px;border: hidden;">
             <y:select id="category" name="category" codeGroup="${category}" selectedValue="${product.categoryId}"
					   cssClass="select" headerKey="" headerValue="标签">
			 </y:select>
       		</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>国内/国外：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="country" name="country" value="${product.country}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>等级：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="grade" name="grade" value="${product.grade}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产地：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="origin" name="origin" value="${product.origin}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单位：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="unit" name="unit" value="${product.unit}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>重量：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input style="width: 100px;" type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="weight" name="weight" value="${product.weight}">kg
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>进价：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input style="width: 100px;" type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="inPrice" name="inPrice" value="${product.inPrice}">元
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>费率：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input style="width: 100px;" type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="taxRate" name="taxRate" value="${product.taxRate}">%
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>售价：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input style="width: 100px;" type="number" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="salePrice" name="salePrice" value="${product.salePrice}">元
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>规格：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<span class="select-box" style="width: 120px;">
				   <select name="specification" id="specification" class="select" autocomplete="off">
					    <option value="70" >70#</option>
						<option value="80" >80#</option>
				   </select>
       			</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>库存：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="stockNum" name="stockNum" value="${product.stockNum}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上下架：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<span class="select-box" style="width: 120px;">
				   <select name="commodityStatus" id="commodityStatus" class="select" autocomplete="off">
					   <option value="0">上架</option>
					   <option value="1" >下架</option>
				   </select>
       			</span>
			</div>
		</div>



		</div>
		<!-- 商品详情 -->
		<div class="showOrHide" style="display:none">
				<div style="text-align:center;margin-bottom:10px;">
					<div style="margin-left:50px;">
						<textarea name="loanIntro" id="loanIntro" style="width:650px;height:350px;" placeholder="" >${product.info}</textarea>
					</div>
				</div>
		</div>
		<!-- 上传图片 -->
		<div class="showOrHide" style="display:none;text-align:center;margin-left:50px;">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品名称：</label>
				<div class="formControls col-xs-8 col-sm-4">
					<input type="file" id="file" name="file">
				</div>
			</div>
			<div class="col-xs-2 col-sm-2">
				<img src="${product.imgMain}">
				<p>主图</p>
			</div>
			<div class="col-xs-2 col-sm-2">
				<img src="${product.imgOther}">
				<p><a>设为主图</a></p>
			</div>
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
		//富文本
		var editor;
		KindEditor.ready(function(K) {
             editor = K.create( '#loanIntro',{
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
			url:"${context_root}/product/saveProduct.action",
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
<link rel="stylesheet" href="${context_url}/uiloader/static/kindeditor/themes/default/default.css" />

<script type="text/javascript" src="${context_url}/uiloader/static/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${context_url}/uiloader/static/kindeditor/lang/zh_CN.js"></script>
</body>
</html>