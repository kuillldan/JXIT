<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 
  </head>
  
  <body>
    <form action="dept/insert" method="post" enctype="multipart/form-data">
    	<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="60%">
    		<tr>
    			<td>部门名称</td>
    			<td><input type="text" id="dept.dname" name="dept.dname" value="人事部"></td>
    		</tr>
    		<tr>
    			<td>部门编号</td>
    			<td><input type="text" id="dept.deptno" name="dept.deptno" value="30"></td>
    		</tr>
    		<tr>
    			<td>部门地址</td>
    			<td>
    				<input type="checkbox" name="dept.locs" value="北京">北京
    				<input type="checkbox" name="dept.locs" value="苏州">苏州
    				<input type="checkbox" name="dept.locs" value="上海">上海
    				<input type="checkbox" name="dept.locs" value="深圳">深圳
    				<input type="checkbox" name="dept.locs" value="成都">成都
    			</td>
    		</tr>
    		<tr>
    			<td>公司名称</td>
    			<td><input type="text" name="dept.company.name" id="dept.company.name" value="中国慧与有限公司" ></td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="file" name="file1"> 
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="file" name="file2"> 
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="file" name="file3"> 
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="file" name="file4"> 
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="file" name="file5"> 
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="提交">
    			</td>
    		</tr> 
    	</table>
    </form>
  </body>
</html>
