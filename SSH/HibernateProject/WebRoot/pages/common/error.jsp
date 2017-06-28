<%@ page language="java" import="lyk.vo.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.kuillldan.cn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setCharacterEncoding("UTF-8");
%>
 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>My JSP 'dept_insert.jsp' starting page</title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
  </head>
  
  <body> 
  	<h1>系统异常，请联系管理员!</h1>
  	<c:if test="${errors!=null }">
  	<ul>
  		<c:forEach items="${errors }" var="error">
  			<li>${error }</li>
  		</c:forEach>
  		</ul>
  	</c:if>
  </body>
</html>
