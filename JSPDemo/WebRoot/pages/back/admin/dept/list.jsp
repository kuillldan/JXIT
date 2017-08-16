<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="vo.*"%>
<%@ page import="factory.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String insertDeptJSP = basePath + "pages/back/admin/dept/insert.jsp";
	String updateJSP = basePath + "pages/back/admin/dept/update.jsp";
 %>

<%
	List<Dept> allDepts = ServiceFactory.getDeptServiceInstance().list();
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
			<td colspan="5">部门列表</td>
		</tr>
		<tr>
			<td><input type="checkbox" id="selectAll" name="selectAll"
				onclick="selectAllDepts()"></td>
			<td>部门编号</td>
			<td>部门名称</td>
			<td>部门位置</td>
			<td>操作</td>
		</tr>
		<%
			for (Dept vo : allDepts)
			{
		%>
		<tr>
			<td><input type="checkbox" id="dept-<%=vo.getDeptno() %>" name="dept-<%=vo.getDeptno() %>" value="<%=vo.getDeptno() %>"></td>
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
				<input type="button" id="deleteAll" name="deleteAll" onclick="deleteAllDepts()" value="删除部门信息">
				<a href="<%=insertDeptJSP%>">增加部门</a>
			</td>
		</tr>
	</table>
</body>
</html>
