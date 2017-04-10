<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setCharacterEncoding("UTF-8");
%>

<%
	Cookie[] cookies = request.getCookies();
	
 %>
 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>My JSP 'dept_insert.jsp' starting page</title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
  </head>
  
  <body>
  	<%
  		for(Cookie c : cookies)
  		{
  		%>
  			<h1><%=c.getName() %> = <%=c.getValue() %></h1>
  		<%
  		}
  	 %> 
  </body>
</html>
