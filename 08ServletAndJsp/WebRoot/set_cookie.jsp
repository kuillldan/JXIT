<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setCharacterEncoding("UTF-8");
%>

<%
	Cookie c1 = new Cookie("mldn","www.mldn.cn");
	c1.setMaxAge(10);
	Cookie c2 = new Cookie("cnn","www.cnn.cn");
	response.addCookie(c1);
	response.addCookie(c2);
	
	
 %>
 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>My JSP 'dept_insert.jsp' starting page</title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
  </head>
  
  <body> 
  </body>
</html>
