<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>

<script>
$(document).ready(function(){
		var expresslist = '${product.imgOther}';
		var express = expresslist.split(';');
		$.each(express, function(index,ex){
		 if(express.length <= 1){
			return;
		}
		 if(index+1 == express.length){
			 return;
		 }
		var div = document.getElementById("otherImg");
		var path = '${imgPath}'+ex;
		div.innerHTML += "<img src="+path+" style=\"margin-left:30px;height:100px;width:100px;\" name=\"imgs\" id=\"imgs\" ondblclick=\"todelImgs(this)\"><span>设为主图</span>";
		});
		var main = '${product.imgMain}';
		if(main!=null && main !=""){
			$("#main img").attr("src",main);
		}else{
			$("#main").remove();
		}
})
</script>
	<div id="ul">
		<ul>
			<li class="page"  index="0"><div style="font-weight:bold;width:80px;height:30px;color:white;border-radius:5px;padding:5px;cursor:pointer;background:#1dc21d;border:2.5px solid #2da32d;">基本信息</div></li>
			<li class="page"  index="1"><div style="color:white;cursor:pointer;padding:5px;border-radius:5px;width:80px;height:30px;background:#1dc21d;border:2.5px solid #2da32d;">商品详情</div></li>
			<li class="page"  index="2"><div style="color:white;cursor:pointer;padding:5px;border-radius:5px;width:80px;height:30px;border:2.5px solid #2da32d;background:#1dc21d;">上传图片</div></li>
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
		.imgDiv span:hover{cursor:pointer;color:blue;}
		.file{  filter:alpha(opacity:0);opacity: 0;width:0px }
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
	 /* 动态元素绑定事件 */
	 $(document).on('click','.imgDiv span',function(){
		 if($(this).text() == "主图"){
			 return;
		 }else{
			 $(this).text("主图");
			 $(".imgDiv span").not(this).text("设为主图");
		 }
	 })
	</script>
	<div style="clear:both"></div>
	<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-product-update">
	<div class="showOrHide" style="display:block;margin-left:100px">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品名称：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="commodityName" name="commodityName" value="${product.commodityName}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品条码：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="commodityNo" name="commodityNo" value="${product.commodityNo}">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品描述：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off"  maxlength="20"  placeholder="" id="commodityDes" name="commodityDes" value="${product.commodityDes}">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品分类：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<span class="select-box" style="width: 100px;border: hidden;">
             <y:select id="categoryId" name="categoryId" codeGroup="${classes}" selectedValue="${product.categoryId}"
					   cssClass="select" headerKey="" headerValue="分类">
			 </y:select>
       		</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品标签：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<span class="select-box" style="width: 100px;border: hidden;">
             <y:select id="tag" name="tag" codeGroup="${category}" selectedValue="${product.tag}"
					   cssClass="select" headerKey="" headerValue="标签">
			 </y:select>
       		</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>国内/国外：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="country" name="country" value="${product.country eq 1 ?"国内":"国外"}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>等级：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<c:if test="${product.grade==1}">
				<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="grade" name="grade" value="一等品">
				</c:if>
				<c:if test="${product.grade==0}">
					<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="grade" name="grade" value="不良品">
				</c:if>
				<c:if test="${product.grade==2}">
					<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="grade" name="grade" value="二等品">
				</c:if>
				<c:if test="${product.grade==null}">
					<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="grade" name="grade" value="${product.grade}">
				</c:if>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产地：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="origin" name="origin" value="${product.origin}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单位：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="unit" name="unit" value="${product.unit}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>重量：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input style="width: 100px;" type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="weight" name="weight" value="${product.weight}">kg
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>进价：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input style="width: 100px;" type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="inPrice" name="inPrice" value="${product.inPrice}">元
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
				<input style="width: 100px;" type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="specification" name="specification" value="${product.specification}">#
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>价格规格：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input style="width: 100px;" type="text" class="input-text" autocomplete="off"  maxlength="20"  placeholder="" id="priceSpecification" name="priceSpecification" value="${product.priceSpecification}">#
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>库存：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="stockNum" name="stockNum" value="${product.stockNum}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>存储方式：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" autocomplete="off" disabled="disabled" maxlength="20"  placeholder="" id="storage" name="storage" value="${product.storage}">
			</div>
		</div>
		<input type="hidden" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="type" name="type" value="${product.type}">
		<input type="hidden" class="input-text" autocomplete="off" maxlength="20"  placeholder="" id="commodityId" name="commodityId" value="${product.commodityId}">

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上下架：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<span class="select-box" style="width: 120px;">
				   <select name="commodityStatus" id="commodityStatus" class="select" autocomplete="off">
					   <option value="0" ${product.commodityStatus=="0"?"selected='selected'":''}>上架</option>
					   <option value="1" ${product.commodityStatus=="1"?"selected='selected'":''}>下架</option>
				   </select>
       			</span>
			</div>
		</div>



		</div>
		<!-- 商品详情 -->
		<div class="showOrHide" style="display:none">
				<div style="text-align:center;margin-bottom:10px;">
					<div style="margin-left:50px;">
						<textarea name="info" id="info" style="width:650px;height:350px;" placeholder="" >${product.info}</textarea>
					</div>
				</div>
		</div>
		<!-- 上传图片 -->
		<div class="showOrHide" style="display:none;text-align:center;margin-left:50px;">
			<div class="row cl">
				<div class="formControls col-xs-8 col-sm-4">
				上传图片：
					<input type='button' class='btn' value='浏览...'onclick="toChoose()" />
					<input  type="file" id="picture" class="file" size="28" onchange="pic(event)"  name="picture" accept="image/*"/>
				</div>
			<div style="clear:both;margin-right:70%;font-size:12px;">温馨提示：双击图片删除</div>
			</div>
			<div style="margin:0 auto" class="imgDiv" id="main">
				<img style="height:100px;width:100px;" name="imgs" ondblclick="todelImgs(this)">
				<span>主图</span>
			</div>
			
			<div class="imgDiv" id="otherImg" >
				
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
             editor = K.create( '#info',{
            	 resizeType : 1,
                 allowPreviewEmoticons : false,
                 allowImageUpload : true,
                 allowFileManager : true,
                 uploadJson : "${context_root}/commodity/imgFile.action",
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
        function todelImgs(obj) {
            var bbb = $(obj).next("span").text();
            var span = $(obj).next("span");
            if("主图"==bbb) {
                alert("主图不允许删除！");
                return;
            }else{
			var r=confirm("确认删除？");
            	if(!r){
            		return;
            	}
                obj.remove();
                span.remove();
            }
        }

        function toChoose(){
		var expresslist = '${product.imgOther}';
		var express = expresslist.split(';');
		var a = $("img").length;
    	if(express.length > 3 || (a >3)){
    		alert("最多只能选择四张图片!");
    		return;
    	}
		document.getElementById('picture').click();
	}
        //校验上传文件是否为图片格式
        function pic(e) {
            for (var i = 0; i < e.target.files.length; i++) {
                var file = e.target.files.item(i);
                if (!(/^image\/.*$/i.test(file.type))) {
                    continue; //不是图片 就跳出这一次循环
                }
                var imagSize =  document.getElementById("picture").files[0].size;
            	if(imagSize>1024*1024*3){
                    alert("图片最大为3M！");
                    document.getElementById("picture").value="";
                    return;
                }
                //实例化FileReader API
                var freader = new FileReader();
                freader.readAsDataURL(file);
                freader.onload = function(e) {
                	var formData = new FormData();
                	formData.append("file",$("input[type=file]")[0].files[0]);
                	$.ajax({
                        url: '${context_root}/commodity/imgFile.action',
                        type: 'POST',
                        data: formData,
                        async: false,
                        cache: false,
                        contentType: false,
                        processData: false,
                        success: function (returndata) {
                        	if(returndata.s == true){
                        		var path = '${imgPath}'+returndata.m;
                        		var div = document.getElementById("otherImg");
                        		div.innerHTML += "<img src="+path+" style=\"margin-left:30px;height:100px;width:100px;\" name=\"imgs\"  id=\"imgs\" ondblclick=\"todelImgs(this)\"><span>设为主图</span>";
            				}else{
            					alert(returndata.m);
            				}
                        },
                   });
                	
                }
            }
        }

        $("#form-product-update").validate({
 	rules:{
        commodityName:{
			required:true,
		},
        tag:{
            required:true,
		},
		categoryId:{
            required:true,
		},
		priceSpecification:{
            required:true,
        },
        commodityNo:{
			required:true,
		},
        country:{
			required:true,
		},
		commodityDes:{
            required:true,
        },
        /*grade:{
			required:true,
		},*/
        origin:{
			required:true,
		},
        unit:{
			required:true,
		},
       /* weight:{
			required:true,
		},*/
        inPrice:{
			required:true,
		},
        taxRate:{
			required:true,
		},
        salePrice:{
			required:true,
		},
		
        stockNum:{
			required:true,
		},
	 }, 
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var formData = new FormData($('#form-product-update')[0]);
		var allSpan = $(".imgDiv span").text().split("图");
		var imgs = "";
    	for(var i=0;i<allSpan.length;i++){
    		if(allSpan[i] == "主"){
    			var imgZ = $("img[name=imgs]").eq(i).attr("src");
    		}else{
    			imgs += $("img[name=imgs]").eq(i).attr("src")+",";
    		}
    	}
        var index = parent.layer.load();
		$.ajax({
			url:"${context_root}/commodity/saveProduct.action?imgZ="+imgZ+"&imgs="+imgs,
            type:'post',
            data:formData,
            mimeType: "multipart/form-data",
            contentType: false,
            cache:false,
            processData: false,
			success:function(data){
                //这种类型需要转成对象
                var data = JSON.parse(data);
                //成功提交
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