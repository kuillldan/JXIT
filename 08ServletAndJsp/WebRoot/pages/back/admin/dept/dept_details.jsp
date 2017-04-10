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
	String searchUrl = basePath + "pages/back/admin/dept/dept_details.jsp";
%>


<%
	String deptInsertUrl = basePath + "pages/back/admin/dept/dept_insert.jsp";
	String deptUpdateUrl = basePath + "pages/back/admin/dept/dept_update.jsp";
	String deptDeleteUrl = basePath + "pages/back/admin/dept/dept_delete_do.jsp";
	String empInsertUrl = basePath + "pages/back/admin/emp/emp_insert.jsp";
	String empDeleteUrl = "pages/back/admin/emp/emp_delete_do.jsp";
	String backUrl= "pages/back/admin/dept/dept_details.jsp";
	String insertEmpUrl = "pages/back/admin/emp/emp_insert.jsp";
%>

<%
	Integer deptno = Integer.parseInt(request.getParameter("deptno"));
%>


<%
	String columns = "ENAME:姓名|JOB:职位";
	String columnName = "ENAME";
	String keyWord = "";

	String _columns = request.getParameter("columns");
	String _columnName = request.getParameter("columnName");
	String _keyWord = request.getParameter("keyWord");

	if (!("".equals(_columns) || null == _columns))
	{
		columns = _columns;
	}

	if (!("".equals(_columnName) || null == _columnName))
	{
		columnName = _columnName;
	}

	if (!("".equals(_keyWord) || null == _keyWord))
	{
		keyWord = _keyWord;
	}
%>

<%
	Integer currentPage = 1;
	Integer lineSize = 5;
	//需要计算
	Integer totalRecords = 0;
	Integer totalPages = 0;

	String _currentPage = request.getParameter("currentPage");
	String _lineSize = request.getParameter("lineSize");
	try
	{
		currentPage = Integer.parseInt(_currentPage);
	} catch (Exception e)
	{
	}

	try
	{
		lineSize = Integer.parseInt(_lineSize);
	} catch (Exception e)
	{
	}
%>

<%
	
	Dept dept = ServiceFactory.getIDeptServiceInstance().show(deptno, columnName, keyWord,
			currentPage, lineSize);
	Map<String, Object> map = dept.getStat();
	totalRecords = (Integer) map.get("count");
	totalPages = (totalRecords + lineSize - 1) / lineSize;
	List<Emp> allEmps = dept.getAllEmps();
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
			<td width="10%">最高工资</td>
			<td width="10%">最低工资</td>
			<td width="10%">总员工数</td>
			<td width="5%">操作</td>
		</tr>
		<tr>
			<td><input type="checkbox" id="deptno" name="deptno" value="<%=dept.getDeptno()%>"></td>
			<td><%=dept.getDeptno()%></td>
			<td><%=dept.getDname()%></td>
			<td><%=dept.getLoc()%></td>
			<td><%=dept.getStat().get("sum")%></td>
			<td><%=dept.getStat().get("avg")%></td>
			<td><%=dept.getStat().get("max")%></td>
			<td><%=dept.getStat().get("min")%></td>
			<td><%=dept.getStat().get("count")%></td>
			<td><a href="<%=empInsertUrl%>?deptno=<%=dept.getDeptno()%>">增加雇员</a></td>
		</tr>

		<tr>
			<td colspan="10"><input type="button" value="删除选中部门" onclick="deleteAll('<%=deptDeleteUrl%>?p=1','ids','deptno')" /> <input type="button" value="增加新部门" onclick="gotoAddNewDeptPage('<%=deptInsertUrl%>')" /></td>
		</tr>

	</table>
	
	<hr/><br/>
	<jsp:include page="/pages/common/search.jsp">
		<jsp:param value="<%=columns%>" name="columns" />
		<jsp:param value="<%=columnName%>" name="columnName" />
		<jsp:param value="<%=keyWord%>" name="keyWord" />
	</jsp:include>
	
	<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2">
		<tr>
			<td width="5%"><input type="checkbox" id="selectAll" name="selectAll" onclick="checkboxSelect(this,'empno')"></td>
			<td width="5%">员工编号</td>
			<td width="10%">姓名</td>
			<td width="10%">职位</td>
			<td width="10%">雇佣日期</td> 
			<td width="15%">薪水</td>
			<td width="10%">佣金</td>
		</tr>
		<%
			for (Emp emp : allEmps)
			{
		%>
		<tr>
			<td><input type="checkbox" name="empno" value="<%=emp.getEmpno()%>"></td>
			<td><%=emp.getEmpno()%></td>
			<td><%=emp.getEname()%></td>
			<td><%=emp.getJob()%></td>
			<td><%=emp.getHiredate()%></td> 
			<td><%=emp.getSal()%></td>
			<td><%=emp.getComm()%></td>
			
		</tr>
		<%
			}
		%>

		<tr>
			<td colspan="10"><input type="button" value="删除选中员工信息" onclick="deleteAll('<%=empDeleteUrl%>?p=1&backUrl=<%=backUrl%>&deptno=<%=dept.getDeptno() %>&currentPage=<%=currentPage %>&lineSize=<%=lineSize %>&columnName=<%=columnName %>&keyWord=<%=keyWord %>','ids','empno')" /> 
			<input type="button" value="增加员工信息" onclick="forward('<%=insertEmpUrl%>?deptno=<%=dept.getDeptno() %>')" /></td>
		</tr>
	</table>
	<jsp:include page="/pages/common/paging.jsp">
		<jsp:param value="<%=currentPage%>" name="currentPage" />
		<jsp:param value="<%=lineSize%>" name="lineSize" />
		<jsp:param value="<%=totalPages%>" name="totalPages" />
	</jsp:include>
	
	
	
	<input type="hidden" name="searchUrl" id="searchUrl" value="<%=searchUrl%>">
	<input type="hidden" name="currentPage" id="currentPage" value="<%=currentPage%>">
	<input type="hidden" name="lineSize" id="lineSize" value="<%=lineSize%>">
	<input type="hidden" name="columnName" id="columnName" value="<%=columnName%>">
	<input type="hidden" name="keyWord" id="keyWord" value="<%=keyWord%>">
	<input type="hidden" name="totalPages" id="totalPages" value="<%=totalPages%>">
	<input type="hidden" name="parameterKey" id="parameterKey" value="deptno">
	<input type="hidden" name="parameterValue" id="parameterValue" value="<%=dept.getDeptno()%>">
	 
</body>
</html>
