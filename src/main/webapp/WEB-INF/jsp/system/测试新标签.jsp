<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader/>
<body>
	 
	<y:table url="${context_root}/system/userList.action" id="userform" width="100%" sortName="signId" sortOrder="desc" pageSize="20">
		<y:column width ="10%"   name ="userId"          title = '用户ID' visible="false"/>
		<y:column width ="10%"   name ="userName"        title = '用户名' sortable="true"/>
		<y:column width ="10%"   name ="email"         	 title = '邮箱'/>
		<y:column width ="10%"   name ="mobilePhone"     title = '手机号码'/>
		<y:column width ="10%"   name ="createTime"      title = '创建时间'    colType="date"   format="yyyy-MM-dd hh:mm:ss"/>
		<y:column width ="10%"   name =""                title = '操作'       colType="href"  colInnerHtml='<a href="#" onclick="detail($T.userId,$T.mobilePhone)">删除</a>'/>
	</y:table>
	
	</div>
</div>
<script>
	function detail(userId,mobilePhone)
	{
		alert(userId);
		alert(mobilePhone);
	}
</script>
</body>
</html>