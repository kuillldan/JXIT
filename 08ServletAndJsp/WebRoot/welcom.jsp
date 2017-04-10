<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="org.lyk.vo.*"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8");
	String loginUrl = basePath + "login.jsp";
	String logoutUrl = basePath + "logout.jsp";
	// 这是测试
%>


<%
	String uid = (String) session.getAttribute("uid");
	List<Dept> allDepts = (List<Dept>) request.getAttribute("allDepts");
	Map<String, Dept> map = (Map<String, Dept>) request.getAttribute("map");
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>
	<h1>Welcom Page</h1>
	<c:if test="${1==1}" var="result"></c:if>
	<h1>${result }</h1>
	
	<c:forEach items="${allDepts }" var="dept">
		<h1>${dept.dname }</h1>
	</c:forEach>
</body>
</html>
