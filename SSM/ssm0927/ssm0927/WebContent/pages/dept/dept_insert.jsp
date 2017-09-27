<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%

	String deptInsertURL = basePath + "pages/dept/insert.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=deptInsertURL%>" method="post">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
			<tr>
				<td>编号:</td><td><input type="text" name="deptno"/></td>
			</tr>
			<tr>
				<td>名称:</td><td><input type="text" name="dname"/></td>
			</tr>
			<tr>
				<td>地点:</td><td><input type="text" name="loc"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="提交">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>