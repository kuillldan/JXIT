<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%

	Cookie[] cookies = request.getCookies();
	
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'get_cookies.jsp' starting page</title>
    
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
    	for(Cookie c: cookies)
    	{
    	%>
    		<h3><%=c.getName() %> = <%=c.getValue() %></h3>
    	<%
    	}
     %>
  </body>
</html>
