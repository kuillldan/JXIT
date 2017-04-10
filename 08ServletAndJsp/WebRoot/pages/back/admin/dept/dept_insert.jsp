<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<%
	String insertUrl = basePath + "pages/back/admin/dept/DeptServlet/insert";

 %>
 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>My JSP 'dept_insert.jsp' starting page</title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
	<script type="text/javascript" src="js/dept.js"></script>
  </head>
  
  <body> 
  	<form action="<%=insertUrl %>" method="post" onsubmit="return validateInsert()">
  		<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2" width="100%">
  			<tr>
  				<td colspan="3">部门增加页面</td>
  			</tr>
  			<tr >
  				<td width="15%">部门编号</td>
  				<td width="45%">
  					<input type="text" id="deptno" name="deptno" onblur="validateDeptno()"/>
  				</td>
  				<td width="40%">
  					<span id="deptnoMsg" name="deptnoMsg"></span>
  				</td>
  			</tr>
  			 
  			<tr >
  				<td width="15%">部门名称</td>
  				<td width="45%">
  					<input type="text" id="dname" name="dname" onblur="validateDname()"/>
  				</td>
  				<td width="40%">
  					<span id="dnameMsg" name="dnameMsg"></span>
  				</td>
  			</tr>
  			
  			<tr >
  				<td width="15%">部门地点</td>
  				<td width="45%">
  					<input type="text" id="loc" name="loc" onblur="validateLoc()"/>
  				</td>
  				<td width="40%">
  					<span id="locMsg" name="locMsg"></span>
  				</td>
  			</tr>
  			
  			<tr>
  				<td colspan="3">
  					<input type="submit" value="提交"/>
  					<input type="reset" value="重置"/>
  				</td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>
