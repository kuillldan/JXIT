<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'springUploadDemo.jsp' starting page</title>

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
	<form action="pages/empServlet/insert.action" method="post" enctype="multipart/form-data">
		编号:<input type="text" name="empno" value="2159"><br/>
		姓名:<input type="text" name="ename" value="刘远奎"><br/>
		薪水:<input type="text" name="salary" value="5565.5"><br/>
		入职日期:<input type="text" name="dateOfBirthday" value="1987-11-21"><br>
		相片:<input type="file" name="photo"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>