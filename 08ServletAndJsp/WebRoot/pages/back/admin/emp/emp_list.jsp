<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.lyk.vo.*"%>
<%@page import="org.lyk.factory.*"%>


<%
	request.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	List<Emp> allEmps = ServiceFactory.getIEmpServiceInstance().listAll();
	String empDeleteUrl = "pages/back/admin/emp/emp_delete_do.jsp";
	String updateEmpUrl = "pages/back/admin/emp/emp_update.jsp";
	String insertEmpUrl = "pages/back/admin/emp/emp_insert.jsp";
	String currentPage = "pages/back/admin/emp/emp_list.jsp";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'emp_list.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>
	<h1>员工列表</h1>
	<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2">
		<tr>
			<td width="5%"><input type="checkbox" id="selectAll" name="selectAll" onclick="checkboxSelect(this,'empno')"></td>
			<td width="5%">员工编号</td>
			<td width="10%">姓名</td>
			<td width="10%">职位</td>
			<td width="10%">雇佣日期</td>
			<td width="15%">薪水</td>
			<td width="10%">佣金</td>
			<td width="5%">操作</td>
			
		</tr>
		<%
			for (Emp emp : allEmps)
			{
		%>
		<tr>
			<td><input type="checkbox" name="empno" value="<%=emp.getEmpno()%>:<%=emp.getPhoto()%>"></td>
			<td><%=emp.getEmpno()%></td>
			<td><%=emp.getEname()%></td>
			<td><%=emp.getJob()%></td>
			<td><%=emp.getHiredate()%></td>
			<td><%=emp.getSal()%></td>
			<td><%=emp.getComm()%></td>
			<td><button onclick="forward('<%=updateEmpUrl%>?empno=<%=emp.getEmpno()%>&backUrl=<%=currentPage%>')">修改</button></td>
		</tr>
		<%
			}
		%>
		
		<tr>
			<td colspan="5"><input type="button" value="删除选中员工信息" onclick="deleteAll('<%=empDeleteUrl%>?p=1&backUrl=<%=currentPage %>','ids','empno')" /> 
			<input type="button" value="增加员工信息" onclick="forward('<%=insertEmpUrl%>')" /></td>
		</tr>
	</table>
</body>
</html>
