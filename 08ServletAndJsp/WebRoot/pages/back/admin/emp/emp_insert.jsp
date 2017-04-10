<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.kuillldan.cn" %>
<%@page import="org.lyk.factory.*"%>
<%@page import="org.lyk.vo.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>


<%
	String insertUrl = basePath + "pages/back/admin/emp/EmpServlet/insert"; 
	String listUrl = "pages/back/admin/emp/emp_list_split.jsp";
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
	<form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()"  enctype="multipart/form-data">
		<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2" width="100%">
			<tr>
				<td colspan="4"><h1>员工信息增加页面</h1></td>
			</tr>
			<tr>
				<td width="15%">员工编号</td>
				<td width="30%"><input type="text" id="empno" name="empno" onblur="validateEmpno()" /></td>
				<td width="30%"><span id="empnoMsg" name="empnoMsg"></span></td>
				<td width="25%">员工照片</td>
			</tr>

			<tr>
				<td >员工姓名</td>
				<td ><input type="text" id="ename" name="ename" onblur="validateEname()" /></td>
				<td ><span id="enameMsg" name="enameMsg"></span></td>
				<td rowspan="10"><div id="previewDiv"></div></td>
			</tr>

			<tr>
				<td >员工职位</td>
				<td ><input type="text" id="job" name="job" onblur="validateJob()" /></td>
				<td ><span id="jobMsg" name="jobMsg"></span></td>
			</tr>
			
			<tr>
				<td >部门</td>
				<td ><select name="deptno">
						<option value="0">=====请选择=====</option>
						 <c:forEach items="${allDepts}" var="dept">
							<option value="${dept.deptno}" ${dept.deptno==deptno?"selected":"" }>${dept.dname}</option> 	
						 </c:forEach>
				</select></td> 
				<td ><span id="jobMsg" name="jobMsg"></span></td>
			</tr>

			<tr>
				<td >经理</td>
				<td ><select name="mgrno">
						<option value="0">=====请选择=====</option>
						<c:forEach items="${allEmps}" var="emp">
							<option value="${emp.empno}">${emp.ename }</option>
						</c:forEach>
				</select></td>
				<td ><span id="jobMsg" name="jobMsg"></span></td>
			</tr>

			<tr>
				<td >雇佣日期</td>
				<td ><input type="text" id="hiredate" name="hiredate" onclick="laydate()" /></td>
				<td ><span id="hiredateMsg" name="hiredateMsg"></span></td>
			</tr>

			<tr>
				<td >基本工资</td>
				<td ><input type="text" id="sal" name="sal" onblur="validateSal()" /></td>
				<td ><span id="salMsg" name="salMsg"></span></td>
			</tr>

			<tr>
				<td >佣金</td>
				<td ><input type="text" id="comm" name="comm" onblur="validateComm()" /></td>
				<td ><span id="commMsg" name="commMsg"></span></td>
			</tr>
			
			<tr>
				<td >照片</td>
				<td ><input type="file" id="photo" name="photo" onchange="showPreview(this)"  /></td>
				<td ><span id="photoMsg" name="photoMsg"></span></td>
			</tr>
			<tr>
				<td>简介</td>
				<td colspan="2">
					<textarea name="note" cols="60" rows="10"></textarea>
				</td>
			</tr>



			<tr>
				<td colspan="3"><input type="submit" value="提交" /> <input type="reset" value="重置" />
					<button onclick="forward('<%=listUrl%>')">查看列表</button></td>
			</tr>

		</table> 
	</form>
</body>
</html>
