<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.*"%>
<%@page import="java.io.*"%>
<%@page import="java.math.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8");
%>




<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>
	<h1>request:<%=request.getAttribute("info") %></h1>
	<h1>request:<%=session.getAttribute("info") %></h1>
</body>
</html>
