<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.*" %>
<%@ page import="vo.*" %>
<%@ page import="service.*" %>
<%@ page import="factory.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
			
%>

<%
	String empBackJSP = basePath + "pages/back/admin/emp/emp_update.jsp";
 %>

<%
	request.setCharacterEncoding("UTF-8");
	Emp emp = new Emp();
	emp.setEmpno(Integer.parseInt(request.getParameter("empno")));
	emp.setEname(request.getParameter("ename"));
	emp.setJob(request.getParameter("job"));
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date hiredate = sdf.parse(request.getParameter("hiredate"));
	emp.setHiredate(hiredate);
	emp.setSal(Double.parseDouble(request.getParameter("sal")));
	emp.setComm(Double.parseDouble(request.getParameter("comm")));
	String msg = "雇员修改成功";
	
	if(!ServiceFactory.getEmpServiceInstance().insert(emp))
	{
		msg = "雇员增加失败";
	}
	else
	{
		empBackJSP = basePath + "pages/back/admin/emp/emp_list.jsp";
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
		window.location = "<%=empBackJSP%>";
	</script> 
</head>

<body>
</body>
</html>
