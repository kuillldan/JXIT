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

<title>My JSP 'insert.jsp' starting page</title>

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
	<form action="pages/emp/EmpAction!insert.action" method="post">
		编号:<input type="text" name="emp.empno" ><br>
		姓名:<input type="text" name="emp.ename" ><br>
		薪水:<input type="text" name="emp.sal" ><br>
		入职日期:<input type="text" name="emp.hiredate" ><br>
		部门编号:<input type="text" name="emp.dept.deptno" ><br>
		部门名称:<input type="text" name="emp.dept.dname" ><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>