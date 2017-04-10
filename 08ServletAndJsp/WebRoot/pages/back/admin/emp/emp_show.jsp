<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.lyk.vo.*"%>
<%@page import="org.lyk.factory.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	String showUrl = "pages/back/admin/emp/emp_details.jsp";
%>

<%
	String backUrl = request.getParameter("backUrl");

	String currentPage = request.getParameter("currentPage");
	String lineSize = request.getParameter("lineSize");
	String columnName = request.getParameter("columnName");
	String keyWord = request.getParameter("keyWord");
%>

<%
	String updateUrl = basePath + "pages/back/admin/emp/emp_update_do.jsp";
	String deptDetailsUrl = "pages/back/admin/dept/detp_details.jsp";
%>

<%
	Integer empno = Integer.parseInt(request.getParameter("empno"));
	Emp emp = ServiceFactory.getIEmpServiceInstance().show(empno);
	String photoPath = "upload/" + emp.getPhoto();
	
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/emp.js"></script>
<script type="text/javascript" src="js/laydate/laydate.js"></script>
</head>

<body>
	<%
		if (emp != null)
		{
	%>
	<form action="<%=updateUrl%>" method="post" onsubmit="return validateUpdate()">
		<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2" width="100%">
			<tr>
				<td colspan="4"><h1>员工信息修改页面</h1></td>
			</tr>
			<tr>
				<td width="10%">员工编号</td>
				<td width="30%"><%=emp.getEmpno()%></td>
				<td width="30%"><span id="empnoMsg" name="empnoMsg"></span></td>
				<td width="30%" rowspan="9"><img alt="" src="<%=photoPath%>"></td>
			</tr>

			<tr>
				<td >员工姓名</td>
				<td ><%=emp.getEname()%></td>
				<td ><span id="enameMsg" name="enameMsg"></span></td>
			</tr>

			<tr>
				<td >员工职位</td>
				<td ><%=emp.getJob()%></td>
				<td ><span id="jobMsg" name="jobMsg"></span></td>
			</tr>
			
			<tr>
				<td >部门</td>
				<td ><a onclick="openPage('<%=deptDetailsUrl%>?deptno=<%=emp.getDept().getDeptno()%>')"><%=emp.getDept().getDname()%></a></td>
				<td ><span id="jobMsg" name="jobMsg"></span></td>
			</tr>
			
			<tr>
				<td >经理</td>
				<td ><a onclick="openPage('<%=showUrl%>?empno=<%=emp.getMgr().getEmpno()%>')"><%=emp.getMgr().getEname()==null ? "" : emp.getMgr().getEname() %></a></td>
				<td ><span id="jobMsg" name="jobMsg"></span></td>
			</tr>

			<tr>
				<td >雇佣日期</td>
				<td ><%=emp.getHiredate()%>  </td>
				<td ><span id="hiredateMsg" name="hiredateMsg"></span></td>
			</tr>

			<tr>
				<td >基本工资</td>
				<td ><%=emp.getSal()%></td>
				<td ><span id="salMsg" name="salMsg"></span></td>
			</tr>

			<tr>
				<td >佣金</td>
				<td > <%=emp.getComm()%></td>
				<td ><span id="commMsg" name="commMsg"></span></td>
			</tr>

			<tr>
				<td colspan="3">
					<button onclick="closeCurrentWindow()">关闭</button>
				</td>

			</tr>

		</table>

		<input type="hidden" name="empno" value="<%=emp.getEmpno()%>"> <input type="hidden" name="backUrl" value="<%=backUrl%>"> <input type="hidden" name="currentPage" value="<%=currentPage%>"> <input type="hidden" name="lineSize" value="<%=lineSize%>"> <input
			type="hidden" name="columnName" value="<%=columnName%>"> <input type="hidden" name="keyWord" value="<%=keyWord%>">

	</form>
	<%
		} else
		{
	%>
	<h1>要修改的员工未找到</h1>
	<%
		}
	%>
</body>
</html>
