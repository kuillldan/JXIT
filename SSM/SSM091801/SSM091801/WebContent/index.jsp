<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String msg = "HELLO SSM0915";
%>

<head>
<base href="<%=basePath%>">
<title>HELLO</title>
</head>

<body>
	<h1>HELLO SHIT</h1>
	<h1>${msg}</h1>
</body>
</html>
