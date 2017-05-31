<%@ page language="java" import="lyk.vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String insertDeptURL = "pages/back/admin/dept/DeptServletBack/insert";
 %>

<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/dept.js"></script>
</head>

<body>
	<form action="<%=insertDeptURL%>" method="post">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2"
			width="50%">
			<tr bgcolor="00B185">
				<td colspan="3">部门增加页面</td>
			</tr>
			<tr>
				<td width="20%">部门名称</td>
				<td width="30%"><input type="text" name="dept.dname"
					id="dept.dname" onblur="validateDname()"></td>
				<td width="30%"><span id="dept.dnameMsg" name="dept.dnameMsg"></span>
				</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>
