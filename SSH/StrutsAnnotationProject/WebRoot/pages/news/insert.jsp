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

<title>My JSP 'insert.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 
</head>

<body>
	<form action="pages/news/NewsAction!insert.action" method="post">
		新闻编号:<input type="text" name="news.nid" value="15">${fieldErrors['news.nid'] }<br>
		新闻标题:<input type="text" name="news.title" value="沙坪坝确诊一名H7N9患者">${fieldErrors['news.title']}<br>
		新闻内容:<input type="text" name="news.content" value="重庆市沙坪坝区确诊一名人感染H7N9病毒患者">${fieldErrors['news.content'] }<br>
		发布日期:<input type="text" name="news.pubdate" value="2017-05-18">${fieldErrors['news.pubdate'] }<br>
		<input type="submit" value="提交"/>&nbsp;<input type="reset" value="重置"/>
	</form>
</body>
</html>