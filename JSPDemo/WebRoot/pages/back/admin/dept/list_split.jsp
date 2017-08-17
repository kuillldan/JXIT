<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="dbc.*" %>
<%@ page import="vo.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String insertDeptJSP = basePath + "pages/back/admin/dept/insert.jsp";
	String updateJSP = basePath + "pages/back/admin/dept/update.jsp";
	String deptDeleteAllURL = basePath + "pages/back/admin/dept/delete_do.jsp?p=1";
	String deptListSplitJSP = basePath + "pages/back/admin/dept/list_split.jsp";
 %>

<%
	Integer currentPage = 1;
	Integer lineSize = 5;
	try
	{
		currentPage = Integer.parseInt(request.getParameter("cp"));
	}
	catch(Exception e){}
	
	try
	{
		lineSize = Integer.parseInt(request.getParameter("ls"));
	}
	catch(Exception e)
	{}
	
	
	if(currentPage <= 0)
	{
		currentPage = 1;
	}
	if(lineSize <= 0)
	{
		lineSize = 5;
	}
 %>

<%
	Connection conn = DatabaseConnection.getConnection();
	String sql = " SELECT deptno,dname,loc FROM dept LIMIT ?,? ";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setInt(1, (lineSize * (currentPage-1)));
	ps.setInt(2,lineSize);
	ResultSet rs = ps.executeQuery();
	List<Dept> allDepts = new ArrayList<Dept>();
	while(rs.next())
	{
		Dept vo = new Dept();
		vo.setDeptno(rs.getInt("deptno"));
		vo.setDname(rs.getString("dname"));
		vo.setLoc(rs.getString("loc"));
		allDepts.add(vo);
	}
	System.out.println(allDepts.size());
	
	DatabaseConnection.close(conn);
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'template.jsp' starting page</title>
	<link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
	<script type="text/javascript" src="js/dept.js"></script> 
</head>

<body>
	<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2" width="100%">
		<tr>
			<td colspan="5">部门列表</td>
		</tr>
		<tr>
			<td><input type="checkbox" id="selectAll" name="selectAll"
				onclick="checkboxSelect(this,'dept')"></td>
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
			<td><input type="checkbox" id="dept" name="dept" value="<%=vo.getDeptno() %>"></td>
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
				<input type="button" id="deleteAll" name="deleteAll" onclick="deleteAll('<%=deptDeleteAllURL %>','deptno','dept')" value="删除部门信息">
				<button id="gotoFirstPage" name="gotoFirstPage" onclick="gotoPage(1,'<%=deptListSplitJSP%>')" <%=currentPage==1?"disabled":""%>>首页</button>
				<button id="gotoPreviousPage" name="gotoPreviousPage" onclick="gotoPage('<%=currentPage-1%>','<%=deptListSplitJSP%>')" <%=currentPage==1?"disabled":""%>>上一页</button>
				<button id="gotoNextPage" name="gotoNextPage" onclick="gotoPage()">下一页</button>
				<button id="gotoLastPage" name="gotoLastPage" onclick="gotoPage()">末页</button>
				<a href="<%=insertDeptJSP%>">增加部门</a>
			</td>
		</tr>
	</table>
</body>
</html>
