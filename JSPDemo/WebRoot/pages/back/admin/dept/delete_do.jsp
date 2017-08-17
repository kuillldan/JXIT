<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="factory.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String deptListJSP = basePath + "pages/back/admin/dept/list.jsp";
 %>

<%
	String deptno = request.getParameter("deptno");
	String[] deptnos = deptno.split("\\|");
	Set<Integer> ids = new HashSet<Integer>();
	for(String item : deptnos)
	{
		ids.add(Integer.parseInt(item));
	}
	String msg ="部门信息删除成功";
	if(!ServiceFactory.getDeptServiceInstance().remove(ids))
	{
		msg = "部门信息删除失败";
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
		window.location = "<%=deptListJSP%>";
	</script> 
</head>

<body>
</body>
</html>
