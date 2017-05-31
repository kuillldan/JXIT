<%@ page language="java" import="lyk.vo.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	request.setCharacterEncoding("UTF-8");
%>
  
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>My JSP 'dept_insert.jsp' starting page</title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
  </head>
  
  <body>
  	<script type="text/javascript">
  		alert("<%=(String)request.getAttribute("msg")%>");
  		window.location = "<%=basePath + request.getAttribute("url")%>";
  	</script> 
  </body>
</html>
