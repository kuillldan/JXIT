<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'insert.jsp' starting page</title>

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
	<form action="pages/back/message/insert.action" method="post" enctype="multipart/form-data">
		消息编号:<input type="text" name="nid" id="nid" value="99" /><br>
		消息名称:<input type="text" name="title" id="title" value="重庆即将发生高温天气" /><br>
		消息价格:<input type="text" name="price" id="price" value="36.8" /><br>
		发布日期:<input type="text" name="pubdate" id="pubdate" value="2016-11-12" /><br>
		消息类型:<input type="text" name="type.name" id="type.name" value="时政新闻" /><br>
		选择图片:<input type="file" name="photo" id="photo"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>
