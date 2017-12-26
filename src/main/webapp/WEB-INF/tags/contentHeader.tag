<%@tag pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<%@attribute name="title" type="java.lang.String" required="false" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <title>${title}</title>
    <link rel="Bookmark" href="/favicon.ico" >
	<link rel="Shortcut Icon" href="/favicon.ico" />
    <%@include file="/WEB-INF/jsp/common/import-css.jspf"%>
    <%@include file="/WEB-INF/jsp/common/import-js.jspf"%>
</head>
