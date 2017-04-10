<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.lyk.vo.*"%>
<%@page import="org.lyk.factory.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>


<%
	Dept dept = (Dept)request.getAttribute("dept");
	String deptUpdateUrl = basePath + "pages/back/admin/dept/DeptServlet/update";

%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/dept.js"></script>
</head>

<body>

	<%
		if (dept != null)
		{
	%>
	<form action="<%=deptUpdateUrl%>" method="post" onsubmit="return validateUpdate()">
		<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2" width="100%">
			<tr>
				<td colspan="3">部门增加页面</td>
			</tr>
			
			<tr>
				<td width="15%">部门编号</td>
				<td width="45%"><%=dept.getDeptno()%> <input type="hidden" id="deptno" name="deptno" value="<%=dept.getDeptno()%>" /></td>
			</tr>

			<tr>
				<td width="15%">部门名称</td>
				<td width="45%"><input type="text" id="dname" name="dname" onblur="validateDname()" value="<%=dept.getDname()%>" /></td>
				<td width="40%"><span id="dnameMsg" name="dnameMsg"></span></td>
			</tr>

			<tr>
				<td width="15%">部门地点</td>
				<td width="45%"><input type="text" id="loc" name="loc" onblur="validateLoc()" value="<%=dept.getLoc()%>" /></td>
				<td width="40%"><span id="locMsg" name="locMsg"></span></td>
			</tr>

			<tr>
				<td colspan="3"><input type="submit" value="修改" /> <input type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
	<%
		} else
		{
	%>
	<h1>未查询到相关记录！</h1>
	<%
		}
	%>
</body>
</html>
