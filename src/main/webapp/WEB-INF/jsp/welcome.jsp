<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="false" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="首页"/>
<body>
<div class="pd-20" style="padding-top:20px;">
  <span>注册数量: ${sessions}</span>
  <span>认证申请：${ipaddress}</span>
  <span>贷款申请：${sessions}</span>
  <table class="table table-border table-bordered table-bg mt-20">
    <thead>
      <tr>
        <th scope="col">服务器信息</th>
		<th><a href="#">更多</a></th>
      </tr>
    </thead>
    <tbody>
		<tr>
			<td class="left">项目路径</td>

			<td class="left" id="hostName">${pageContext.request.contextPath}</td>
		</tr>
		<tr>
			<td class="left">操作系统的名称</td>

			<td class="left" id="osName"><y:systemProperty key="os.name" /> </td>
		</tr>
		<tr>
			<td class="left">操作系统的构架</td>

			<td class="left" id="arch"><y:systemProperty key="os.arch" /> </td>
		</tr>
		<tr>
			<td class="left">操作系统的版本</td>

			<td class="left" id="osVersion"><y:systemProperty key="os.version" /> </td>
		</tr>
		<tr>
			<td class="left">Java的运行环境版本</td>

			<td class="left" id="javaVersion"><y:systemProperty key="java.version" /> </td>
		</tr>
		<tr>
			<td class="left">Java供应商的URL</td>

			<td class="left" id="javaUrl"><y:systemProperty key="java.vendor.url" /> </td>
		</tr>
		<tr>
			<td class="left">Java的安装路径</td>

			<td class="left" id="javaHome"><y:systemProperty key="java.home" /> </td>
		</tr>
		<tr>
			<td class="left">临时文件路径</td>

			<td class="left" id="tmpdir"><y:systemProperty key="java.io.tmpdir" /> </td>
		</tr>
      
    </tbody>
  </table>
</div>
<footer class="footer">
  <p>Copyright ©2020 星融金服公司  All Rights Reserved.</p>
</footer>
</body>
</html>