<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.lyk.vo.*"%>
<%@page import="org.lyk.factory.*"%>



<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";

	request.setCharacterEncoding("UTF-8");
%>


<%
	List<Dept> allDepts = (List<Dept>)request.getAttribute("allDepts");
	
	String deptInsertUrl = basePath + "pages/back/admin/dept/dept_insert.jsp";
	String deptUpdateUrl = basePath + "pages/back/admin/dept/DeptServlet/updatePre";
	String deptDeleteUrl = basePath + "pages/back/admin/dept/DeptServlet/delete";
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

	<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2">
		<tr>
			<td width="5%"><input type="checkbox" id="selectAll" name="selectAll" onclick="checkboxSelect(this,'deptno')"></td>
			<td width="15%">部门编号</td>
			<td width="35%">部门名称</td>
			<td width="35%">部门地点</td>
			<td width="10%">操作</td>
		</tr>

		<%
			for (Dept dept : allDepts)
			{
		%>
		<tr>
			<td width="5%"><input type="checkbox" id="deptno" name="deptno" value="<%=dept.getDeptno()%>"></td>
			<td width="15%"><%=dept.getDeptno()%></td>
			<td width="35%"><%=dept.getDname()%></td>
			<td width="35%"><%=dept.getLoc()%></td>
			<td width="10%"><a href="<%=deptUpdateUrl%>?deptno=<%=dept.getDeptno()%>">修改</a></td>
		</tr>
		<%
			}
		%>

		<tr>
			<td colspan="5"><input type="button" value="删除选中部门" onclick="deleteAll('<%=deptDeleteUrl%>?p=1','ids','deptno')" /> <input type="button" value="增加新部门" onclick="gotoAddNewDeptPage('<%=deptInsertUrl%>')" /></td>
		</tr>

	</table>
</body>
</html>
