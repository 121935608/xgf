<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

    request.setAttribute("context_root", path);
    request.setAttribute("context_url", basePath);
%>

