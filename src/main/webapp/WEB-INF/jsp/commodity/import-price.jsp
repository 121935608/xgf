﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body style="margin:0 auto;line-height:40px;"> 
<style>
	span{line-height:35px;}
	span{font-size:12px;}
	span:hover{cursor:pointer;color:blue;}
	#btn:hover{cursor:pointer}
</style>
<center>
	<div id="imp_container" > 
		<br/> 
		<form id="import-form" name="import-form" action="" method="post"> 
			导入文件：
            <input type="file" id="file" name="file" value="选择文件" accept=".csv, application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
            
        
        <br/>
        <a id="download" href=""  >下载导入样本格式.xls</a><br/>
        <input type="radio" name="type" value="1">一键改价格
        <div class="text-c" style="margin-bottom:20px;">
        <input type="button" id="btn" class="l" value="确认" style="width:60px;height:40px;background-color:#5A98DE;margin-left:40%;margin-top:5%;border-radius:15px;color:#fff;">
        </form>
        </div>
	</div>
	</center>
</body>
<script type="text/javascript">
var molUrl=GetQueryString("molUrl");
$("#download").attr("href", '${context_root}'+molUrl);
 $("input[type=button]").click(function(){
	 var impUrl=GetQueryString("impUrl");
	 /* 条件和文件必选 */
	 var type = $("input[type=radio]:checked").val();
	 if(type == null){
		 alert("请选择导入条件！");
		 return;
	 }
	 var file = $("input[type=file]").val();
	 if(file == ''){
		 alert("请选择导入文件！");
		 return;
	 }
	 var index = parent.layer.load();
	 var formData = new FormData($('#import-form')[0]);
	$.ajax({
        url: '${context_root}'+impUrl,
        type: 'POST',
        data:formData,
        mimeType: "multipart/form-data",
        contentType: false,
        cache:false,
        processData: false,
        success: function (data) {
        	var data = JSON.parse(data);
        	if(data.s == true){
        		index = parent.layer.getFrameIndex(window.name);
				parent.layer.msg(data.m,{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
				window.parent.location.reload();
				});
			}else if(data.s == false){
				index = parent.layer.getFrameIndex(window.name);
				parent.layer.msg(data.m,{icon:2,time: 4000,shade: [0.1,'#fff']},function(){
				window.parent.location.reload();
				});
			}
        },
        error:function(data){
        	alert(data);
        }
   });
 })
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
</script>
</html>