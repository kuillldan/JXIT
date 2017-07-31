<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://liuyuankui.cn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String openOffShowURL = basePath + "svc_state_manage/show";
 %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'show.jsp' starting page</title>
<script type="text/javascript" src="js/openOffManagement.js"></script>
</head>

<body>
	<h3>${msg }</h3>
	请<a href="<%=openOffShowURL%>">点击</a>此处跳转到开闭局管理页面。
</body>
</html>
