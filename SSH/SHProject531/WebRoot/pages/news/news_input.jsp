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

<title>My JSP 'news_input.jsp' starting page</title>

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
	<form action="pages/news/NewsAction!insert.action">
		ID:<input type="text" name="news.nid" id="news.nid">${fieldErrors['news.nid'] }<br>
		标题:<input type="text" name="news.title" id="news.title">${fieldErrors['news.title'] }<br>
		内容:<input type="text" name="news.content" id="news.content">${fieldErrors['news.content'] }<br>
		日期:<input type="text" name="news.pubdate" id="news.pubdate">${fieldErrors['news.pubdate'] }<br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>
