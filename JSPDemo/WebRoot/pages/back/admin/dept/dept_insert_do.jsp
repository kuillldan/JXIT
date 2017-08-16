<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="service.*" %>
<%@ page import="factory.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String deptInsertJSP = basePath + "pages/back/admin/dept/insert.jsp";
 %>

<%
	request.setCharacterEncoding("UTF-8");
	Dept dept = new Dept();
	dept.setDeptno(Integer.parseInt(request.getParameter("deptno")));
	dept.setDname(request.getParameter("dname"));
	dept.setLoc(request.getParameter("loc"));
	String msg = "部门增加成功";
	
	if(!ServiceFactory.getDeptServiceInstance().insert(dept))
	{
		msg = "部门增加失败";
	}

 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'template.jsp' starting page</title>
	<link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
	<script type="text/javascript">
		alert("<%=msg%>");
		window.location = "<%=deptInsertJSP%>";
	</script> 
</head>

<body>
</body>
</html>
