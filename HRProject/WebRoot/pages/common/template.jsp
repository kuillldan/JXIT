<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title></title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
  </head> 
  
  <body> 
  	<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
  		<tr bgcolor="00B185">
  			<td colspan="2"></td>
  		</tr>
  		<tr>
  			<td></td>
  			<td></td>
  		</tr>
  		<tr>
  			<td></td>
  			<td></td>
  		</tr>
  		<tr>
  			<td></td>
  			<td></td>
  		</tr>
  	</table>
  </body>
</html>
