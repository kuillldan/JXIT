<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.lyk.vo.*"%>
<%@page import="org.lyk.factory.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
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
%>

<%
	Integer empno = Integer.parseInt(request.getParameter("empno"));
	Map<String,Object> map = ServiceFactory.getIEmpServiceInstance().updatePre(empno);
	Emp emp = (Emp)map.get("emp");
	List<Emp> allEmps = (List<Emp>)map.get("allEmps");
	List<Dept> allDepts = (List<Dept>)map.get("allDepts");
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
	<form action="<%=updateUrl%>" method="post" onsubmit="return validateUpdate()" enctype="multipart/form-data">
		<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2" width="100%">
			<tr>
				<td colspan="4"><h1>员工信息修改页面</h1></td>
			</tr>
			
			<tr>
				<td width="15%">员工编号</td>
				<td width="30%"><%=emp.getEmpno() %></td>
				<td width="30%"><span id="empnoMsg" name="empnoMsg"></span></td>
				<td width="25%" rowspan="11"><div id="previewDiv"><img alt="" src="<%=photoPath%>"></div></td>
			</tr>

			<tr>
				<td >员工姓名</td>
				<td ><input type="text" id="ename" name="ename" onblur="validateEname()" value="<%=emp.getEname() %>" /></td>
				<td ><span id="enameMsg" name="enameMsg"></span></td>
			</tr>

			<tr>
				<td >员工职位</td>
				<td ><input type="text" id="job" name="job" onblur="validateJob()" value="<%=emp.getJob() %>" /></td>
				<td ><span id="jobMsg" name="jobMsg"></span></td>
			</tr>
			
			
			<tr>
				<td >部门</td>
				<td ><select name="deptno">
						<option value="0">=====请选择=====</option>
						<%
							for (Dept item : allDepts)
							{
							%>
								<option value="<%=item.getDeptno()%>" <%=item.getDeptno().equals(emp.getDept().getDeptno()) ? "selected":"" %> ><%=item.getDname() %></option>
							<%
							}
						%>
				</select></td>
				<td ><span id="jobMsg" name="jobMsg"></span></td>
			</tr>
			
			
			
			<tr>
				<td >经理</td>
				<td ><select name="mgrno">
						<option value="0">=====请选择=====</option>
						<%
							for (Emp item : allEmps)
							{
							%>
								<option value="<%=item.getEmpno()%>" <%=item.getEmpno().equals(emp.getMgr().getEmpno()) ? "selected":"" %> ><%=item.getEname() %></option>
							<%
							}
						%>
				</select></td>
				<td ><span id="jobMsg" name="jobMsg"></span></td>
			</tr>

			<tr>
				<td >雇佣日期</td>
				<td ><input type="text" id="hiredate" name="hiredate" onclick="laydate()" value="<%=emp.getHiredate() %>" /></td>
				<td ><span id="hiredateMsg" name="hiredateMsg"></span></td>
			</tr>

			<tr>
				<td >基本工资</td>
				<td ><input type="text" id="sal" name="sal" onblur="validateSal()" value="<%=emp.getSal() %>" /></td>
				<td ><span id="salMsg" name="salMsg"></span></td>
			</tr>

			<tr>
				<td >佣金</td>
				<td ><input type="text" id="comm" name="comm" onblur="validateComm()" value="<%=emp.getComm() %>" /></td>
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
					<textarea name="note" cols="60" rows="10"><%=emp.getNote() %></textarea>
				</td>
			</tr>
 
			<tr>
				<td colspan="3">
				<input type="submit" value="修改" /> <input type="reset" value="重置" />
				</td>
				
			</tr>
			
			

		</table>

		<input type="hidden" name="empno" value="<%=emp.getEmpno()%>">
		<input type="hidden" name="backUrl" value="<%=backUrl%>">
		<input type="hidden" name="currentPage" value="<%=currentPage %>">
		<input type="hidden" name="lineSize" value="<%=lineSize%>">
		<input type="hidden" name="columnName" value="<%=columnName%>">
		<input type="hidden" name="keyWord" value="<%=keyWord%>">
		<input type="hidden" name="oldPhoto" value="<%=emp.getPhoto()%>">
		
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
