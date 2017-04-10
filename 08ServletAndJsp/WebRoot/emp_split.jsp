<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8");
	String searchUrl = basePath + "emp_split.jsp";
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
	String DBDRIVER = "com.mysql.jdbc.Driver";
	String USER = "root";
	String PASSWD = "admin";
	String DBURL = "jdbc:mysql://localhost:3306/mldn";

	Connection conn = null;
	PreparedStatement ps = null;
	String sql = "";
	ResultSet rs = null;

	Class.forName(DBDRIVER);
	conn = DriverManager.getConnection(DBURL, USER, PASSWD);
	sql = "SELECT COUNT(*) FROM emp WHERE " + columnName + " LIKE ? ";
	ps = conn.prepareStatement(sql);
	ps.setString(1, "%" + keyWord + "%");
	rs = ps.executeQuery();
	if (rs.next())
	{
		totalRecords = rs.getInt(1);
		totalPages = (totalRecords + lineSize - 1) / lineSize;
	}

	sql = "  SELECT EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO FROM EMP WHERE " + columnName
			+ " LIKE ?  LIMIT ?,? ";
	ps = conn.prepareStatement(sql);
	ps.setString(1, "%" + keyWord + "%");
	ps.setInt(2, (currentPage - 1) * lineSize);
	ps.setInt(3, lineSize);
	rs = ps.executeQuery();
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>

	<h5>
		总记录条数:<%=totalRecords%></h5>
	<h5>
		当前页数:<%=currentPage%>
		总页数:<%=totalPages%>
		每页显示:<%=lineSize%>行
	</h5>
	<h5>
		查询字段:<%=columnName%>
		查询关键字:<%=keyWord%></h5>






	<jsp:include page="pages/common/search.jsp">
		<jsp:param value="<%=columns %>" name="columns"/>
		<jsp:param value="<%=columnName %>" name="columnName"/>
		<jsp:param value="<%=keyWord %>" name="keyWord"/>
	</jsp:include>

	<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2">
		<tr>
			<td>EMPNO</td>
			<td>ENAME</td>
			<td>JOB</td>
			<td>MGR</td>
			<td>HIREDATE</td>
			<td>SAL</td>
			<td>COMM</td>
			<td>DEPTNO</td>
		</tr>
		<%
			while (rs.next())
			{
		%>
		<tr>
			<td><%=rs.getInt("EMPNO")%></td>
			<td><%=rs.getString("ENAME")%></td>
			<td><%=rs.getString("JOB")%></td>
			<td><%=rs.getInt("MGR")%></td>
			<td><%=rs.getDate("HIREDATE")%></td>
			<td><%=rs.getInt("SAL")%></td>
			<td><%=rs.getInt("COMM")%></td>
			<td><%=rs.getInt("DEPTNO")%></td>
		</tr>
		<%
			}

			conn.close();
		%>
	</table>

	<jsp:include page="pages/common/paging.jsp">
		<jsp:param value="<%=currentPage %>" name="currentPage"/>
		<jsp:param value="<%=lineSize %>" name="lineSize"/>
		<jsp:param value="<%=totalPages %>" name="totalPages"/>
	</jsp:include>

	<input type="hidden" name="searchUrl" id="searchUrl" value="<%=searchUrl%>">
	<input type="hidden" name="currentPage" id="currentPage" value="<%=currentPage%>">
	<input type="hidden" name="lineSize" id="lineSize" value="<%=lineSize%>">
	<input type="hidden" name="columnName" id="columnName" value="<%=columnName%>">
	<input type="hidden" name="keyWord" id="keyWord" value="<%=keyWord%>">
	<input type="hidden" name="totalPages" id="totalPages" value="<%=totalPages%>">
</body>
</html>
