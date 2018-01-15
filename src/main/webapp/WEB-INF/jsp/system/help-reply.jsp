<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="帮助与反馈"/>
<body>
<article class="page-container">
    <div class="text-c">
        <table>
            <c:forEach items="${helpInfo}" var="a" varStatus="n">
                <tr>
                    <td>问:</td>
                    <td>${a.question}</td>
                    <td>${a.addTimes}</td>
                    <td>${a.userName}</td>
                </tr>
                <tr>
                    <td>答:</td>
                    <td>${a.reply}</td>
                    <td>${a.replyTimes}</td>
                    <td>${a.answer}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <form action="" method="post" class="form form-horizontal" id="form-help-reply">

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>追问：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="" id="question" name="question" value="${unHelpInfo.question}">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">回复：</label>
            <div class="formControls col-xs-8 col-sm-6">
                <textarea name="reply" id="reply" cols="" rows="" class="textarea"  placeholder="请输入回复内容" datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,200)"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
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
    $("#form-help-reply").validate({
        rules:{
            reply:{
                required:true,
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            var index = parent.layer.load();
            $.ajax({
                url:"${context_url}/system/helpReply.action?feedBackId=${unHelpInfo.feedBackId}",
                type:'post',
                async:true ,
                cache:false ,
                data:$(form).serialize(),
                dataType:"json",
                success:function(data){
                    parent.layer.close(index);
                    if(data.s == true){
                        index = parent.layer.getFrameIndex(window.name);
                        parent.layer.msg("回复成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
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