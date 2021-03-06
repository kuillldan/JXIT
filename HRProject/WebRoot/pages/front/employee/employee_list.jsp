<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.kuillldan.cn" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String searchUrl = basePath
			+ "pages/front/employee/EmployeeServletFront/list";
	String employeeUpdateURL = basePath + "pages/front/employee/EmployeeServletFront/updatePre";
%>
 


<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HR人力资源管理系统-雇员信息添加</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>

<body>
	<div class="nav-top">
		<jsp:include page="/pages/front/menu-top.jsp"></jsp:include>
	</div>

	<div class="nav-down">
		<jsp:include page="/pages/front/left.jsp"></jsp:include>
		<div class="right_con"> 
		<br/><br/> 
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<jsp:include page="/pages/common/search.jsp">
					<jsp:param value="${columns }" name="columns" />
					<jsp:param value="${columnName }" name="columnName" />
					<jsp:param value="${keyWord }" name="keyWord" />
				</jsp:include>
				 
			<form>
				<div class="form_add">
					<table width="100%" border="0">
						<tr>
							<td width="66" bgcolor="#EEEEEE">编号</td>
							<td width="119" bgcolor="#EEEEEE">姓名</td>
							<td width="66" bgcolor="#EEEEEE">性别</td>
							<td width="84" bgcolor="#EEEEEE">出生日期</td>
							<td width="100" bgcolor="#EEEEEE">学历</td>
							<td width="120" bgcolor="#EEEEEE">部门</td>
							<td width="111" bgcolor="#EEEEEE">职位</td>
							<td width="73" bgcolor="#EEEEEE">工资</td>
							<td width="76" bgcolor="#EEEEEE">入职时间</td>
							<td width="37" bgcolor="#EEEEEE">在职</td>
							<td width="51" bgcolor="#EEEEEE">编辑</td>
						</tr>
						<c:forEach items="${allEmployees }" var="employee">
						<tr>
							<td>${employee.eid }</td>
							<td>${employee.ename }</td>
							<td>${employee.sex}</td>
							<td>${employee.birthday }</td>
							<td>${employee.edu}</td>
							<td>${employee.dname}</td>
							<td>${employee.job}</td>
							<td>${employee.sal}</td>
							<td>${employee.indate}</td>
							<td>${employee.status}</td>
							<td><a href="<%=employeeUpdateURL%>?employee.eid=${employee.eid}">编辑</a></td>
						</tr>
					</c:forEach>
					</table>



				</div>

			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<jsp:include page="/pages/common/paging.jsp">
		<jsp:param value="${currentPage }" name="currentPage" />
		<jsp:param value="${lineSize }" name="lineSize" />
		<jsp:param value="${totalPages }" name="totalPages" />
	</jsp:include>
	
	<c:if test="${allEmployeesCount <= 0 }">
		<h1>未找到查询记录</h1>
	</c:if>

	<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
	<input type="hidden" name="lineSize" id="lineSize" value="${lineSize }">
	<input type="hidden" name="columnName" id="columnName" value="${columnName }">
	<input type="hidden" name="keyWord" id="keyWord" value="${keyWord }">
	<input type="hidden" name="totalPages" id="totalPages" value="${totalPages }">

	<input type="hidden" name="parameterKey" id="parameterKey" value="${parameterKey }">
	<input type="hidden" name="parameterValue" id="parameterValue" value="${parameterValue }">
	<input type="hidden" name="searchUrl" id="searchUrl" value="<%=searchUrl%>">

			</form>
		</div>
		
</body>
</html>
