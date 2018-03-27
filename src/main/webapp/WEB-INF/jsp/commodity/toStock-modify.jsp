<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>  
<ys:contentHeader/>
<link rel="stylesheet" href="${context_url}/uiloader/static/y/js/auto.css" />
<body>
<article  >
	<form action="" method="post" class="form form-horizontal" id="form-procurement-add">
		<div id="table">
			<table>
				<tr>
					<td>商品名称</td>
					<td>
						<input type="text" style="width:20%" class="input-text" value="${purchase.commodityName }" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>商品条码</td>
					<td>
						<input type="text" style="width:20%" class="input-text" value="${purchase.commodityNo }" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>单位</td>
					<td>
						<input type="text" style="width:20%" class="input-text" value="${purchase.unit }" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>数量</td>
					<td>
						<input type="text" style="width:20%" class="input-text" value="${purchase.commodityNum }" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>商品资料</td>
				</tr>
			</table>
			</div>
			<div class="mt-20"> 
			<input type="button" class="btn btn-primary radius" value="添加行" id="addRow"/>
			<table class="table table-border table-bordered table-hover table-bg table-sort" id="orderTab">
				<thead>
					<tr class="text-c">
						<th width="3%">序号</th>
						<th width="14%">商品名称</th>
						<th width="10%">商品条码</th>
						<th width="6%">单位</th> 
						<th width="10%">库存</th>  
						<th width="3%">删除</th>  
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div> 
		<div class="row col-xs-12 col-sm-12"> 
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3"> 
				<br />
				<input class="btn btn-primary radius"   type="submit"   id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<br /><br />
			</div>
		</div>
	</form>
</article>
  
<script type="text/javascript"> 
	var no = '${purchase.commodityNo }';
	var tabIndex=0;
	var unitdata=eval('${unitList}');
	var pageTable;
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
	
		$("#addRow").click(function(){
			tabIndex++;
			var id1='no'+tabIndex;
			var id2='commodityName'+tabIndex;
			var id3='commodityNo'+tabIndex;
			var id4='unitId'+tabIndex;
			var id5='stockNum'+tabIndex;
			var id6='auto'+tabIndex;
			
			var tr=document.createElement('tr');
			tr.id=tabIndex;
			tr.innerHTML = " <tr id='"+tabIndex+"'>"+
								"<td><input class='input-text' type='text' id="+id1+" name="+id1+" disabled='disabled' value='"+tabIndex+"'></td>"+
								"<td><div class='wrap'><input style='width:85%;margin-left: 5px;position:relative;bottom: 4px;' class='input-text auto-inp' autocomplete=\"off\" type='text' id="+id2+" name="+id2+"/>"+
									"<div class='auto hidden' id="+id6+"><div class='auto_out'>1</div><div class='auto_out'>2</div></div></div>&nbsp;"+
									"<span style='color:blue;cursor:pointer;left:30%;line-height:150%;position: absolute;' onclick='addIframe()'>添加</span></td>"+
								"<td><input class='input-text' style='border: 0px;text-align:center;' type='text' id="+id3+" name="+id3+" readonly></td>"+ 
								"<td><input class='input-text' style='border: 0px;text-align:center;' type='text' id="+id4+" name="+id4+" readonly></td>"+ 
								"<td><input class='input-text' type='text' id="+id5+" name="+id5+"></td>"+
								"<td><input class='btn btn-primary radius' type='button' value='删除' onclick='deleteRow(this)'></td>"+
							"</tr>";
			$("#orderTab").children("tbody")[0].appendChild(tr);
			
			//自动补全
			$.ajax({
						url:"${context_root}/commodity/getCommodityList.action", 
						type:'post',
						async:true ,
						cache:false ,
						dataType:"json",
						success:function(data){
							//自动补全
							var autoComplete = new AutoComplete(id2,id6,data);
							document.getElementById(id2).onkeyup = function(event){
								autoComplete.start(event);
							}
						},
					});
		});
 
		function addIframe(){
			var href = '${context_root}/commodity/toRegisterAdd.action?type='+1;
			var titleName = '添加商品'
			var index = layer.open({
				type:2,
				title:titleName,
				content:href
			});
			layer.full(index);
		}
function getSelStr(id,data){
		var Str="<select id='"+id+"' name='"+id+"' class='input-text' disabled='true'>"
		for(var a=0;a<data.length;a++){
			Str+="<option value ='"+data[a].codeid+"'>"+data[a].codevalue+"</option>";
		} 
		Str+="</select>"
		return Str;
	}
 
function deleteRow(a){ 
	a.parentNode.parentNode.remove(); 
}

function submitData(){ 
	var orderStr='[';
	var trs=$("#orderTab tbody tr"); 
	if(trs.length<1){
		parent.layer.alert("请添加行！" , {icon: 2,title:"系统提示"});
		return false;
	}
	for(i=0;i<trs.length;i++){ 
		orderStr+='{';
		orderStr+='"index":"'+trs[i].id+'",';
		for(j=0;j<$(trs[i]).find('td').length-1;j++){
			if(j == 1){
				var ele=$($(trs[i]).find('td')[j]).children().eq(0).children()[0];
				orderStr+='"'+ele.id+'":"'+ele.value+'",';
			}else{
				var ele=$($(trs[i]).find('td')[j]).children()[0];
				orderStr+='"'+ele.id+'":"'+ele.value+'",';
			}
		}
		orderStr=orderStr.substr(0,orderStr.length-1);
		orderStr+='},'; 
		} 
	orderStr=orderStr.substr(0,orderStr.length-1);
	orderStr+=']'; 
	var index = parent.layer.load();
	 $.ajax({
		url:"${context_root}/commodity/addCommodityToStock.action", 
		type:'post',
		async:true ,
		cache:false ,
		data:{
			"orders":orderStr,
			"no":no
		},
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


$("#form-procurement-add").validate({
    onkeyup: false,
    focusCleanup: true,
    success: "valid",
    submitHandler: function (form) {
    	var inputs=$("#orderTab tbody tr input");
    	for(var a=0;a<inputs.length;a++ ){
    		if(inputs[a].value==""){ 
    			parent.layer.alert("请将添加列的信息填写完整！", {icon: 2,title:"提示"});
    			return false;
    		}
    	}
    	submitData();
    }
});

function showMenu(a) {  
	var ultree=$("#categoryHiddenTree");
	/* a.parentElement.append(ultree[0]); */
	$(a.parentElement).append(ultree[0]);
	ultree.css({left:getElemPos(a).x + "px", top:getElemPos(a).y+30 + "px",zIndex: 100,position: "absolute"}).slideDown("fast");
	ultree.attr("forInput",$(a).attr("showfor"));
	$("body").bind("mousedown",onBodyDown(ultree[0].id))  
}
function hideMenu(ultreeId) {
	$("#"+ultreeId).fadeOut("fast");
	$("#"+ultreeId).attr("forInput","");
	$("body").unbind("mousedown",onBodyDown(ultreeId) );   
}
  
function onBodyDown(ultreeId)
{
	return function(event)
	{
		if (!(event.target.id == ultreeId || $(event.target).parents("#"+ultreeId).length>0)) {
			hideMenu(ultreeId);
		}
	}
}

//单击菜单触发
function onClick(e,treeId, treeNode) { debugger
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		nodes = zTree.getSelectedNodes(),
		v = nodes[0].name;
		w = nodes[0].id;  
		var ultree=$("#categoryHiddenTree");
		var inputid=ultree.attr("forInput");
		$("#"+inputid).attr("value", w);
		$("[showfor='"+inputid+"']").attr("value", v);
		
		hideMenu(treeId); 
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
		
 
</script> 
</body>
</html>