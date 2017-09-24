<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String loginURL = basePath +  "";
%>
<html>
<body>
	<h2>登录页面</h2>
	<form action="<%=loginURL%>" method="post"></form>
</body>
</html>
