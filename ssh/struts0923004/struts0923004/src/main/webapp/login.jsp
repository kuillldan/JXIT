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
	String loginURL = basePath +  "loginAction!login.action";
%>
<html>
<body>
	<h2>登录页面</h2>
	<form action="<%=loginURL%>" method="post">
		用户名:<input type="text" name="userName"><br>
		密&nbsp;码:<input type="password" name="password"><br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>
