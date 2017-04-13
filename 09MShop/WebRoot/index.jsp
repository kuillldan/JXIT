<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	//request.setCharacterEncoding("UTF-8");
%>
 

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>
	<jsp:include page="/pages/common/header.jsp"></jsp:include>
	<h1>首页信息</h1>
	<h2>2017年4月11日11:11:42</h2> 
	<jsp:include page="/pages/common/footer.jsp"></jsp:include>
</body>
</html>
