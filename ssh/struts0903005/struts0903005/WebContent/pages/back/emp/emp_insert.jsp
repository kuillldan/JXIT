<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String empInsertURL =  basePath + "pages/back/emp/empAction.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form action="<%=empInsertURL %>" method="post">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
			<tr>
				<td>员工号</td>
				<td><input type="text" name="emp.empno" value="2159" /></td>
			</tr>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="emp.ename" value="远奎" /></td>
			</tr>
			<tr>
				<td>入职日期</td>
				<td><input type="text" name="emp.hiredate" value="2011-07-06" /></td>
			</tr>
			
			<tr>
				<td>部门编号</td>
				<td><input type="text" name="emp.dept.deptno" value="10" /></td>
			</tr>
			
			<tr>
				<td>部门名称</td>
				<td><input type="text" name="emp.dept.dname" value="SSIT" /></td>
			</tr>
			
			<tr>
				<td>地址</td>
				<td><input type="text" name="emp.dept.loc" value="重庆市沙坪坝区" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>