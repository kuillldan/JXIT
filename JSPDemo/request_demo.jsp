<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<%
	request.setCharacterEncoding("UTF-8");
	Enumeration<String> allParameterNames =	request.getParameterNames();
%>

<html>
	<head></head>
	<body>
		<img src="<%=request.getContextPath()%>/image/cjk.jpg"/>
		<h1>IP地址:<%=request.getRemoteAddr()%></h1>
		<h1>协议模式:<%=request.getScheme()%></h1>
		<h1>服务器名称:<%=request.getServerName()%></h1>
		<h1>端口号:<%=request.getServerPort()%></h1>
	</body>
</html>