<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="vo.*"%>
<%@ page import="factory.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
			
	request.setCharacterEncoding("UTF-8");
%>


<%
	String deptListJSP = basePath + "pages/back/admin/dept/list.jsp";
	String updateURL = basePath + "pages/back/admin/dept/dept_update_do.jsp";
%>


<%
	Dept dept = ServiceFactory.getDeptServiceInstance().updatePre(Integer.parseInt(request.getParameter("deptno")));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'template.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/dept.js"></script>
</head>

<body>
	<%
		if (null != dept)
		{
	%>
	<form action="<%=updateURL %>" method="post"
		onsubmit="return validateInsert();">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2"
			width="100%">
			<tr>
				<td colspan="3">雇员修改</td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF'')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td width="15%">部门编号</td>
				<td width="45%"><input type="hidden" name="deptno" id="deptno"
					value="<%=dept.getDeptno()%>"><%=dept.getDeptno()%></td>
				<td width="45%">&nbsp;</td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF'')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td>部门名称</td>
				<td><input type="text" name="dname" id="dname"
					onblur="validateDname()" value="<%=dept.getDname()%>"></td>
				<td><span id="dnameMsg" name="dnameMsg"></span></td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF'')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td>部门位置</td>
				<td><input type="text" name="loc" id="loc"
					onblur="validateLoc()" value="<%=dept.getLoc()%>"></td>
				<td><span id="locMsg" name="locMsg"></span></td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF'')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td colspan="3"><input type="submit" value="修改"> <input
					type="reset" value="重置"> <a href="<%=deptListJSP%>">部门列表</a>
				</td>
			</tr>
		</table>
	</form>
	<%
		}
	%>
</body>
</html>
