<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<script type="text/javascript" src="${context_root}/js/Hselect.js"></script>
<link rel="stylesheet" href="${context_root}/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${context_root}/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<body>
<article class="page-container">
	<form action="" method="post"  class="form form-horizontal" id="form-carousel-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="carouselName" name="carouselName">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图片：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="file" onchange="pic(event)" id="picture" name="picture" accept="image/*">750*318
				<img alt="" id="myImg" src="" height="100px",width="100px">
			</div>
		</div>
		
		<%--<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>URL：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="url" class="input-text" value="" placeholder="" id="url" name="url">
			</div>
		</div>--%>

		<div class="mt-20 skin-minimal">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>提示：</label>
				<p class="c-red">请填写URL、商品、分类中的一种信息</p>
			</div>
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
					<input type="hidden" showValues="commodityHiddenTree" value="" name="category1">
					<ul id="commodityHiddenTree" forInput="" class="ztree" style="display:none;background-color: white; border: solid 1px rgb(150,150,150);"></ul>
				</div>
				</div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-4">
			<y:select id="status" name="status"
					codeGroup="${statusList}" selectedValue=""
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
    function getElemPos(obj){
        var pos = {"top":0, "left":0};
        if (obj.offsetParent){
            while (obj.offsetParent){
                pos.top += obj.offsetTop;
                pos.left += obj.offsetLeft;
                obj = obj.offsetParent;
            }
        }else if(obj.x){
            pos.left += obj.x;
        }else if(obj.x){
            pos.top += obj.y;
        }
        return {x:pos.left, y:pos.top};
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
        var ultree=$("#categoryHiddenTree");
        var inputid=ultree.attr("forInput");
        $("#"+inputid).attr("value", w);
        $("[showfor='"+inputid+"']").val(v);
        $("[showValue='"+inputid+"']").val(w);
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
        var ultree=$("#commodityHiddenTree");
        var inputid=ultree.attr("forInput");
        $("#"+inputid).attr("value", w);
        $("[showfors='"+inputid+"']").val(v);
        $("[showValues='"+inputid+"']").val(w);
        hideMenu(treeId);
    }
    function InitialZtree() {
        alert(1);
        $.post("${context_root}/content/getProductTree.action", function(data){
            var ht = $("#categoryHiddenTree");
            ht = $.fn.zTree.init(ht,settings,data);
            ht.expandAll(true);
        });
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
            $("#myImg").attr("src",e.target.result);  
        }  
    }  
} 
$("#form-carousel-add").validate({
	rules:{
		
		/* carouselName: {
            required: true,
            isSpace: true,
           remote: {
                url: "${context_root}/content/checkCarouselNameUnique.action",
                type: "post",
                dataType: "text",
                data: {
                    name: function () {
                        return $.trim($("#carouselName").val());
                    }
                },
                dataFilter: function (data, type) {
                    if (data == "0") return true;
                    else return "该名称已存在";
                }
            }
        }, */
		
        carouselName:{
			required:true,
			maxlength:20
		},
		picture:{
			required:true,
			isSpace:true,
		},
/*		url: {
            required: true,
            isSpace: true,
            maxlength:250
        },*/
		status:{
			required:true,
			isSpace:true,
		},
        radio:{
            required:true,
            isSpace:true,
        },
		/* messages: {
            "carouselName": {
                remote: "该名称已经存在"
            }
        }, */
		
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
        /*var valve = document.getElementsByName("radio");
        for(var i=0;i<valve.length;i++){
            if (valve[i].checked) {
                if(document.getElementsByName(valve[i].getAttribute("id"))[0].value.length==0||document.getElementsByName(valve[i].getAttribute("id"))[0].value==""){
                alert("请输入信息");
                 return error()
                }
            }
        }*/
		var formData = new FormData($('#form-carousel-add')[0]);
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


</script> 
</body>
</html>