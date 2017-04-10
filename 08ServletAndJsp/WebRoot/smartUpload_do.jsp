<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="com.jspsmart.upload.*"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<%
	SmartUpload smart = new SmartUpload();
	smart.initialize(config, request, response);
	smart.upload();

	SmartFiles files = smart.getFiles();
	if (files.getSize() > 0)
	{
		for (int i = 0; i < files.getCount(); i++)
		{
			SmartFile file = files.getFile(i);
			if (file.getContentType().contains("image"))
			{
				String fileName = UUID.randomUUID() + "." + file.getFileExt();
				String savePath = this.getServletContext().getRealPath("/upload/") + fileName;
				
				file.saveAs(savePath);
			}
		}
	}
%>


<%
	
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>
	<h1><%=smart.getRequest().getParameter("name") %></h1>
	<h1><%=smart.getRequest().getParameter("age") %></h1>
</body>
</html>
