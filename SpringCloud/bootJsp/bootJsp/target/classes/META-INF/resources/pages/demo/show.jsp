<%@ page language="java" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String insertDeptURL = "pages/back/admin/dept/DeptServletBack/insert";
%>

<html>
<head>
<title>SpringBoot JSP Demo</title>
</head>
<body>
	<h1>welcome</h1>
	<h1>${msg }</h1>
</body>
</html>