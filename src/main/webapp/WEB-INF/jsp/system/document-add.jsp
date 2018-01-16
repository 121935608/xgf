<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-document-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>公文标题：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" placeholder="" id="title" name="title">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>公文内容：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="file" id="file" name="file">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-7 col-sm-4 skin-minimal">
				<y:radio codeGroup="sys-locked" name="status" selectedValue="0"/>
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
    jQuery.validator.addMethod("checkTitle", function (value, element) {
        var chrnum =/[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/;
        return this.optional(element) || (!chrnum.test(value));
    }, "不能含有特殊字符");
$("#form-document-add").validate({
	rules:{
        title:{
			required:true,
			isSpace:true,
            checkTitle:true,
            remote: {
                url: "${context_root}/system/checkTitleUnique.action",
                type: "post",
                dataType: "text",
                data: {
                    name: function () {
                        return $.trim($("#title").val());
                    }
                },
                dataFilter: function (data, type) {
                    if (data == "0") return true;
                    else return "已存在该标题的公文";
                }
            }
		},
        file:{
			required:true,
			isSpace:true,
		},
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		var index = parent.layer.load();
        var formData = new FormData($('#form-document-add')[0]);
		$.ajax({
			url:"${context_url}/system/saveDocument.action",
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