<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>

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
	<%
		Map<String,List<String>> fieldErrors = (Map<String,List<String>>)request.getAttribute("fieldErrors");
		Set<Map.Entry<String,List<String>>> entries = fieldErrors.entrySet();
		for(Map.Entry<String,List<String>> entry : entries)
		{
			%>
			<h3><%=entry.getKey() %>:<%=entry.getValue() %></h3>
			<%
		}
	 %>
	<form action="NewsAction!forJmeter.action" method="post">
		请输入:<input type="text" name="msg"><input type="submit" value="提交">
	</form>
</body>
</html>
