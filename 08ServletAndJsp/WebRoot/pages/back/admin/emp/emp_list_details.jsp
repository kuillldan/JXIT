<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="org.lyk.vo.*"%>
<%@page import="org.lyk.factory.*"%>


<%
	request.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	String showUrl = "pages/back/admin/emp/emp_show.jsp";
%>


<%
	String searchUrl = basePath + "pages/back/admin/emp/emp_list_details.jsp";
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
	Map<String, Object> result = ServiceFactory.getIEmpServiceInstance().listDetails(columnName,
			keyWord, currentPage, lineSize);
	List<Emp> allEmps = (List<Emp>) result.get("allEmps");
	totalRecords = (Integer) result.get("allRecords");
	totalPages = (totalRecords + lineSize - 1) / lineSize;

	String empDeleteUrl = "pages/back/admin/emp/emp_delete_do.jsp";
	String updateEmpUrl = "pages/back/admin/emp/emp_update.jsp";
	String insertEmpUrl = "pages/back/admin/emp/emp_insert.jsp";
	String backUrl = "pages/back/admin/emp/emp_list_details.jsp&columnName=" + columnName + "&keyWord="
			+ keyWord + "&currentPage=" + currentPage + "&lineSize=" + lineSize;
	String deptDetailsUrl = "pages/back/admin/dept/dept_details.jsp";
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
			<td width="10%">经理</td>
			<td width="10%">雇佣日期</td>
			<td width="15%">薪水</td>
			<td width="10%">佣金</td>
			<td width="5%">部门</td>
			<td width="5%">操作</td>

		</tr>
		<%
			for (Emp emp : allEmps)
			{
		%>
		<tr>
			<td><input type="checkbox" name="empno" value="<%=emp.getEmpno()%>"></td>
			<td><%=emp.getEmpno()%></td>
			<td><a onclick="openPage('<%=showUrl%>?empno=<%=emp.getEmpno()%>')"><%=emp.getEname()%></a></td>
			<td><%=emp.getJob()%></td>
			<td><%=emp.getMgr().getEname() == null ? "" : emp.getMgr().getEname()%></td>
			<td><%=emp.getHiredate()%></td>
			<td><%=emp.getSal()%></td>
			<td><%=emp.getComm()%></td>
			<td><a onclick="openPage('<%=deptDetailsUrl%>?deptno=<%=emp.getDept().getDeptno()%>')"><%=emp.getDept().getDname()%></a></td>
			<td><button onclick="forward('<%=updateEmpUrl%>?empno=<%=emp.getEmpno()%>&backUrl=<%=backUrl%>')">修改</button></td>
		</tr>
		<%
			}
		%>

		<tr>
			<td colspan="10"><input type="button" value="删除选中员工信息" onclick="deleteAll('<%=empDeleteUrl%>?p=1&backUrl=<%=backUrl%>','ids','empno')" /> <input type="button" value="增加员工信息" onclick="forward('<%=insertEmpUrl%>')" /></td>
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
	<input type="hidden" name="parameterValue" id="parameterValue" value="XXX">
</body>
</html>
