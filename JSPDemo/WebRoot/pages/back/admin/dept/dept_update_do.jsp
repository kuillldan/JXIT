<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="vo.*"%>
<%@ page import="service.*"%>
<%@ page import="factory.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	request.setCharacterEncoding("UTF-8");
%>

<%
	String deptUpdateJSP = basePath
			+ "pages/back/admin/dept/update.jsp?deptno="
			+ request.getParameter("deptno");
	
%>

<%
	Dept dept = new Dept();
	dept.setDeptno(Integer.parseInt(request.getParameter("deptno")));
	dept.setDname(request.getParameter("dname"));
	dept.setLoc(request.getParameter("loc"));
	String msg = "部门修改成功";

	if (!ServiceFactory.getDeptServiceInstance().update(dept)) {
		msg = "部门修改失败";
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
		window.location = "<%=deptUpdateJSP%>";
</script>
</head>

<body>
</body>
</html>
