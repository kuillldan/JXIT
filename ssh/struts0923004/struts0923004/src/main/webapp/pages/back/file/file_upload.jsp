<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String fileUploadActionURL = basePath + "pages/back/file/fileAction!insert.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=fileUploadActionURL %>" method="post" enctype="multipart/form-data">
		文件1:<input type="file" name="photo1"><br>
		描述:<input type="text" name="desc"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>