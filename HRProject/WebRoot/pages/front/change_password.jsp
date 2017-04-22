<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String changePasswordURL = "login/front/admin/AdminLoginServletFront/changePassword";
%>
 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title></title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
  </head> 
  
  <body> 
  
   <form action="<%=changePasswordURL%>">
   <c:if test="${fAdmin!=null }">
  	<h1>当前用户(前台):${fAdmin.aid }</h1>
  	<input type="hidden" name="aid" value="${fAdmin.aid }">
  </c:if> 
   <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
   	
   	<tr>
   		<td>旧密码</td>
   		<td><input type="password" name="oldPassword"></td>
   	</tr>
   	
   	<tr>
   		<td>新密码</td>
   		<td><input type="password" name="newPassword"></td>
   	</tr>
   	
   	<tr>
   		<td colspan="2" >
   			<input type="submit" value="提交">
   		</td>
   	</tr>
   </table>
   
   </form>
  </body>
</html>