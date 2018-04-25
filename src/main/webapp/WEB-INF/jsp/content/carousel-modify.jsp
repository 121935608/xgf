<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>

<script type="text/javascript" src="${context_root}/js/Hselect.js"></script>
<link rel="stylesheet" href="${context_root}/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${context_root}/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<body>
<article class="page-container">
<style>
	.picture{width: 150px;height:150px;position:absolute;left: 2;cursor:pointer;border-color: orange;
             filter:alpha(opacity:0);opacity: 0  }
        .image{position:absolute; border-color: red;left: 2;cursor:pointer;} 
</style>
	<form action="" method="post"  class="form form-horizontal" id="form-carousel-modify">
	<input type="hidden" class="input-text" id="carouselId" name="carouselId" value="${carousel.carouselId }">
	
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${carousel.carouselName }" placeholder="" id="carouselName" name="carouselName">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">图片：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<img src="${imgPath}${carousel.carouselImg}" width="120px" height="80px" class="image" id="image">
	        	<input type="file" class="picture" id="picture" accept="image/*" name="picture" onchange="changImg(event)">
			</div>
		</div>

		<div class="row cl">
			<div class="formControls col-xs-8 col-sm-4">
				<span style="margin-left:50%;font-size:12px;">750*318</span>
			</div>
		</div>
		
		<%--<div class="row cl"style="margin-top:80px">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>URL：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="url" class="input-text" value="${carousel.url }" placeholder="" id="url" name="url">
			</div>
		</div>--%>

		<div class="mt-20 skin-minimal">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>URL：</label>
				<div class="radio-box">
					<input type="radio" id="url" name="radio" value="1" onchange="fun1()">
					<input type="url" class="input-text" value="" placeholder="" name="url" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>商品：</label>
				<div class="radio-box">
					<input type="radio" id="commodity" name="radio" value="2" onchange="fun1()">
					<div>
						<input type="text" showfor="categoryHiddenTree" class="input-text" value="" placeholder="" name="commodity" disabled="disabled" readonly onclick="showMenu(this)">
						<input type="hidden" showValue="categoryHiddenTree" value="" name="commodity1">
						<ul id="categoryHiddenTree" forInput="" class="ztree" style="display:none;background-color: white; border: solid 1px rgb(150,150,150);"></ul>
					</div>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>分类：</label>
				<div class="radio-box">
					<input type="radio" id="category" name="radio" value="3" onchange="fun1()">
					<div>
						<input type="text" showfors="commodityHiddenTree" class="input-text" value="" placeholder="" name="category" disabled="disabled" readonly onclick="showMenus(this)">
						<input type="hidden" showValues="categoryHiddenTree" value="" name="category1">
						<ul id="commodityHiddenTree" forInput="" class="ztree" style="display:none;background-color: white; border: solid 1px rgb(150,150,150);"></ul>
					</div>
				</div>
			</div>
		</div>


		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="status" name="status"
					codeGroup="${statusList}" selectedValue="${carousel.status }"
					cssClass="select" headerKey="" headerValue="状态">
			</y:select>
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;确定&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>


<script type="text/javascript">
//校验上传文件是否为图片格式
function changImg(e){
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
            $("#image").attr("src",e.target.result);
        }
    }
}
$("#form-carousel-modify").validate({
	rules:{
		url: {
            required: true,
            isSpace: true,
            maxlength:250
        },
		status:{
			required:true,
			isSpace:true,
		},
		carouselName:{
			required:true,
			isSpace:true,
			maxlength:20
		},
		
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
		var formData = new FormData($('#form-carousel-modify')[0]);
		$.ajax({
			url:"${context_root}/content/saveCarousel.action", 
			type:'post',
			data:formData,
			mimeType: "multipart/form-data",
			contentType: false,
			cache:false,
			processData: false,			
			success:function(data){
				var data = JSON.parse(data);
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

function fun1() {
    var valve = document.getElementsByName("radio");
    for(var i=0;i<valve.length;i++){
        if (!valve[i].checked) {
            document.getElementsByName(valve[i].getAttribute("id"))[0].disabled = true;
        } else {
            document.getElementsByName(valve[i].getAttribute("id"))[0].disabled = false;
        }
    }
}



var setting = {
    view: {
        selectedMulti: false
    },
    edit: {
        enable: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: onClick
    }
};

var settings = {
    view: {
        selectedMulti: false
    },
    edit: {
        enable: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: onClicks
    }
};
$(document).ready(function(){
    $.post("${context_root}/content/getProductTree.action", function(data){
        var ht = $("#categoryHiddenTree");
        ht = $.fn.zTree.init(ht,setting,data);
        ht.expandAll(false);
    });
    $.post("${context_root}/content/getCommodityTree.action", function(datas){
        var ht = $("#commodityHiddenTree");
        ht = $.fn.zTree.init(ht,settings,datas);
        ht.expandAll(false);
    });
    var valve = document.getElementsByName("radio");
    var type=${carousel.type}
    for(var i=0;i<valve.length;i++){
        if (type==valve[i].value) {
            if(valve[i].value==1){
                valve[i].checked=true;
                document.getElementsByName("url")[0].value="${name}";
                document.getElementsByName("url")[0].disabled=false;//这里赋值
			}else{
            document.getElementsByName(valve[i].getAttribute("id"))[0].value="${name}";
            document.getElementsByName(valve[i].getAttribute("id")+"1")[0].value="${id}";
            document.getElementsByName(valve[i].getAttribute("id"))[0].disabled = false;
            valve[i].checked=true;
            }
        }
    }

})

function onBodyDown(ultreeId)
{
    return function(event)
    {
        if (!(event.target.id == ultreeId || $(event.target).parents("#"+ultreeId).length>0)) {
            hideMenu(ultreeId);
        }
    }
}

function showMenu(a) {
    var ultree=$("#categoryHiddenTree");
    /* a.parentElement.append(ultree[0]); */
    $(a.parentElement).append(ultree[0]);
    ultree.css({left:"30px", top:a.offsetHeight+ "px",zIndex: 100,position: "absolute"}).slideDown("fast");
    ultree.attr("forInput",$(a).attr("showfor"));
    $("body").bind("mousedown",onBodyDown(ultree[0].id))
}
function showMenus(a) {
    var ultree=$("#commodityHiddenTree");
    /* a.parentElement.append(ultree[0]); */
    $(a.parentElement).append(ultree[0]);
    ultree.css({left:"30px", top:a.offsetHeight+ "px",zIndex: 100,position: "absolute"}).slideDown("fast");
    ultree.attr("forInput",$(a).attr("showfors"));
    $("body").bind("mousedown",onBodyDown(ultree[0].id))
}
function hideMenu(ultreeId) {
    $("#"+ultreeId).fadeOut("fast");
    $("#"+ultreeId).attr("forInput","");
    $("body").unbind("mousedown",onBodyDown(ultreeId) );
}


//单击菜单触发
function onClick(e,treeId, treeNode) {

    if(treeNode.isCategory){
        parent.layer.alert("不能选择商品类型，请选择具体商品！" , {icon: 2,title:"提示"});
        return false;
    }
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    nodes = zTree.getSelectedNodes(),
        v = nodes[0].name;
    w = nodes[0].id;
    alert(w);
    var ultree=$("#categoryHiddenTree");
    var inputid=ultree.attr("forInput");
    $("#"+inputid).attr("value", w);
    $("[showfor='"+inputid+"']").val(v);
    $("[showValue='"+inputid+"']").val(w);//选取树状节点反显到文本框
    hideMenu(treeId);
}
function onClicks(e,treeId, treeNode) {
    if(treeNode.isCategory){
        parent.layer.alert("不能选择商品类型，请选择具体商品！" , {icon: 2,title:"提示"});
        return false;
    }
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    nodes = zTree.getSelectedNodes(),
        v = nodes[0].name;
    w = nodes[0].id;
    alert(w);
    var ultree=$("#commodityHiddenTree");
    var inputid=ultree.attr("forInput");
    $("#"+inputid).attr("value", w);
    $("[showfors='"+inputid+"']").val(v);
    $("[showValues='"+inputid+"']").val(w);
    hideMenu(treeId);
}
</script> 
</body>
</html>