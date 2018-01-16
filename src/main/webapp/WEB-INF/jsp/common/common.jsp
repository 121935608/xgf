<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    String imgPath="http://xrjf.oss-cn-shanghai.aliyuncs.com/";
    request.setAttribute("context_root", path);
    request.setAttribute("context_url", basePath);
    request.setAttribute("imgPath", imgPath);
%>

