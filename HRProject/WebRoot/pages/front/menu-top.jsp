<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String logoutURL = "login/front/admin/AdminLoginServletFront/logout";
 %>

<html>
<head>
<base href="<%=basePath%>">
<title></title> 
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>
	<span>个人后台管理系统</span>
	<div class="nav-topright">
		<p>上次登陆时间：2015-04-15 22:33:50 登陆IP：192.168.1.1</p>
		<span>您好：${aid }</span><span><a href="<%=logoutURL%>">注销</a></span>
	</div>
</body>
</html>
