<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="vo.*"%>
<%@ page import="factory.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String empInsertJSP = basePath + "pages/back/admin/emp/emp_insert.jsp";
	String empUpdateJSP = basePath + "pages/back/admin/emp/emp_update.jsp";
	String empDeleteAllURL = basePath + "pages/back/admin/emp/emp_delete_do.jsp?p=1";
 %>

<%
	List<Emp> allEmps = ServiceFactory.getEmpServiceInstance().list();
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'template.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>

	<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2" width="100%">
		<tr>
			<td colspan="5">员工列表</td>
		</tr>
		<tr>
			<td><input type="checkbox" id="selectAll" name="selectAll"
				onclick="checkboxSelect(this,'emp')"></td>
			<td>员工编号</td>
			<td>姓名</td>
			<td>入职日期</td>
			<td>职位</td>
			<td>薪水</td>
			<td>佣金</td>
			<td>操作</td>
		</tr>
		<%
			for (Dept vo : allDepts)
			{
		%>
		<tr>
			<td><input type="checkbox" id="dept" name="dept" value="<%=vo.getDeptno() %>"></td>
			<td><%=vo.getDeptno() %></td>
			<td><%=vo.getDname() %></td>
			<td><%=vo.getLoc() %></td>
			<td><a href="<%=updateJSP%>?deptno=<%=vo.getDeptno()%>">修改</a></td>
		</tr>
		<%
			}
		%>
		
		<tr>
			<td colspan="5">
				<input type="button" id="deleteAll" name="deleteAll" onclick="deleteAll('<%=deptDeleteAllURL %>','deptno','dept')" value="删除部门信息">
				<a href="<%=insertDeptJSP%>">增加部门</a>
			</td>
		</tr>
	</table>
</body>
</html>
