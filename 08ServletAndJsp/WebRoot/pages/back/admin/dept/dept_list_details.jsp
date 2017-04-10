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
	String empInsertUrl = basePath + "pages/back/admin/emp/emp_insert.jsp";
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
			<td width="10%">部门编号</td>
			<td width="10%">部门名称</td>
			<td width="10%">部门地点</td>
			<td width="15%">总工资</td>
			<td width="15%">平均工资</td>
			<td width="15%">最高工资</td>
			<td width="15%">最低工资</td>
			<td width="5%">操作</td>
		</tr>

		<%
			for (Dept dept : allDepts)
			{
		%>
		<tr>
			<td><input type="checkbox" id="deptno" name="deptno" value="<%=dept.getDeptno()%>"></td>
			<td><%=dept.getDeptno()%></td>
			<td><%=dept.getDname()%></td>
			<td><%=dept.getLoc()%></td>
			<td><%=dept.getStat().get("sum")%></td>
			<td><%=dept.getStat().get("avg")%></td>
			<td><%=dept.getStat().get("max")%></td>
			<td><%=dept.getStat().get("min")%></td>
			<td><a href="<%=empInsertUrl%>?deptno=<%=dept.getDeptno()%>">增加雇员</a></td>
		</tr>
		<%
			}
		%>

		<tr>
			<td colspan="9"><input type="button" value="删除选中部门" onclick="deleteAll('<%=deptDeleteUrl%>?p=1','ids','deptno')" /> <input type="button" value="增加新部门" onclick="gotoAddNewDeptPage('<%=deptInsertUrl%>')" /></td>
		</tr>

	</table>
</body>
</html>
