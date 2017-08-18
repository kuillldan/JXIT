<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="factory.*" %>
<%@ page import="java.text.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>


<%
	String empListJSP = basePath + "pages/back/admin/emp/emp_list.jsp";
	String empUpdateURL = basePath + "pages/back/admin/emp/emp_update_do.jsp";
 %>
 
 
 <%
 	Integer empno = Integer.parseInt(request.getParameter("empno"));
 	Emp emp = ServiceFactory.getEmpServiceInstance().updatePre(empno);
 	String hiredate = null;
 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 	hiredate = sdf.format(emp.getHiredate());
  %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'template.jsp' starting page</title>
	<link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
	<script type="text/javascript" src="js/emp.js"></script>
	<script type="text/javascript" src="js/laydate/laydate.js"></script>
</head>

<body>
	<form action="<%=empUpdateURL %>" method="post" onsubmit="return validateInsert();" >
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2" width="100%">
			<tr>
				<td colspan="3">雇员修改</td>
			</tr>
			<tr >
				<td>工号</td>
				<td><input type="text" name="empno" id="empno" disabled value="<%=emp.getEmpno()%>"></td>
				<td><span id="enameMsg" ></span></td>
			</tr>
			<tr >
				<td>姓名</td>
				<td><input type="text" name="ename" id="ename" onblur="validateEname()" value="<%=emp.getEname()%>"></td>
				<td><span id="enameMsg" ></span></td>
			</tr>
			<tr >
				<td>职位</td>
				<td><input type="text" name="job" id="job" onblur="validateJob()" value="<%=emp.getJob()%>"></td>
				<td><span id="jobMsg"></span></td>
			</tr>
			<tr >
				<td>入职日期</td>
				<td><input type="text" name="hiredate" id="hiredate" onclick="laydate()"  value="<%=hiredate%>"></td>
				<td><span id="hiredateMsg"></span></td>
			</tr>
			<tr >
				<td>薪水</td>
				<td><input type="text" name="sal" id="sal" onblur="validateSal()" value="<%=emp.getSal()%>"></td>
				<td><span id="salMsg"></span></td>
			</tr>
			<tr >
				<td>佣金</td>
				<td><input type="text" name="comm" id="comm" onblur="validateComm()" value="<%=emp.getComm()%>"></td>
				<td><span id="commMsg"></span></td>
			</tr>
			<tr >
				<td colspan="3">
					<input type="submit" value="修改">
					<input type="reset" value="重置">
					<a href="<%=empListJSP%>">雇员列表</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
