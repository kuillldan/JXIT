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

<title>My JSP 'news_insert.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 

</head>

<body>
	<form action="pages/news/newsAction!insert.action" method="post">
		新闻编号:<input type="text" id="news.nid" name="news.nid" value="1">
		<span>${fieldErrors['news.nid'][0] }</span>
		<br/>
		新闻标题:<input type="text" id="news.title" name="news.title" value="重庆爆发禽流感疫情">
		<span>${fieldErrors['news.title'][0] }</span>
		<br/>
		发布日期:<input type="text" id="news.pubdate" name="news.pubdate" value="2016-11-23">
		<span>${fieldErrors['news.pubdate'][0] }</span>
		<br/>
		新闻内容:<input type="text" id="news.content" name="news.content" value="重庆多地爆发禽流感疫情病毒">
		<span>${fieldErrors['news.content'][0] }</span>
		<br/>
		其它信息:<input type="text" id="otherInfo" name="otherInfo" value="请不要造谣"><br>
		<input type="submit" value="增加">
	</form>
	
</body>
</html>
