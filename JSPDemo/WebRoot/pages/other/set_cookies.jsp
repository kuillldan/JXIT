<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	Cookie c1 = new Cookie("username","root");
	Cookie c2 = new Cookie("password","admin");
	c1.setMaxAge(10);
	c1.setMaxAge(60);
	response.addCookie(c1);
	response.addCookie(c2);
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'set_cookies.jsp' starting page</title>
    
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
    Set Cookies<br>
  </body>
</html>
