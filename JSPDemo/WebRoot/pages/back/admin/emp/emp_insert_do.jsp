<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.*" %>
<%@ page import="vo.*" %>
<%@ page import="java.io.*" %>
<%@ page import="service.*" %>
<%@ page import="factory.*" %>
<%@ page import="com.jspsmart.upload.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String empInsertJSP = basePath + "pages/back/admin/emp/emp_insert.jsp";
 %>
 
 <%
 	SmartUpload smartUpload = new SmartUpload();
 	smartUpload.initialize(pageContext.getServletConfig(), request, response);
 	smartUpload.upload();
 	SmartFiles smartFiles = smartUpload.getFiles();
 	System.out.println(smartFiles.getCount());
 	String realPath = application.getRealPath("/");
 	smartFiles.getFile(0).saveAs(realPath + "/hell.jpg");
  %>

<% 

 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'template.jsp' starting page</title>
	<link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
	<script type="text/javascript">
		alert("<%=empInsertJSP%>");
		window.location = "<%=empInsertJSP%>";
	</script> 
</head>

<body>
</body>
</html>
