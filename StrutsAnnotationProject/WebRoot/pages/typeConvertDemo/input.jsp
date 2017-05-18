<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'input.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="pages/typeConvertDemo/TypeConvertAction!convert.action" method="post" >
		<input type="checkbox" name="inst" value="1">爱好1
		<input type="checkbox" name="inst" value="2">爱好2
		<input type="checkbox" name="inst" value="3">爱好3
		<input type="checkbox" name="inst" value="4">爱好4
		<input type="checkbox" name="inst" value="5">爱好5
		<br>
		<input type="submit" value="提交">
	</form>
</body>
</html>
