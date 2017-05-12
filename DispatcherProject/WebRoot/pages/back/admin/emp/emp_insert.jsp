<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<% 
	String insertURL = "pages/back/admin/emp/EmpServlet/insert";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<h1>员工增加页面</h1>
	<form action="<%=insertURL %>" method="post" enctype="multipart/form-data">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
			<tr>
				<td>雇员编号</td>
				<td>
					<input name="emp.empno" id="emp.empno" value="1">
				</td>
			</tr>
			<tr>
				<td>雇员姓名</td>
				<td>
					<input name="emp.ename" id="emp.ename" value="麦格惠特曼">
				</td>
			</tr>
			<tr>
				<td>雇员工资</td>
				<td>
					<input name="emp.sal" id="emp.sal" value="22.36">
				</td>
			</tr>
			<tr>
				<td>公司名称</td>
				<td>
					<input name="emp.dept.company.name" id="emp.dept.company.name" value="慧与有限公司">
				</td>
			</tr>
			<tr>
				<td>公司总部地址</td>
				<td>
					<input name="emp.dept.company.addr" id="emp.dept.company.addr" value="西园一路28号">
				</td>
			</tr>
			<tr>
				<td>分公司</td>
				<td>
					<input name="emp.dept.company.subCompany" id="emp.dept.company.subCompanyCQ" type="checkbox" value="23" checked><label for="emp.dept.company.subCompanyCQ">重庆</label>
					<input name="emp.dept.company.subCompany" id="emp.dept.company.subCompanyBJ" type="checkbox" value="10" ><label for="emp.dept.company.subCompanyBJ">北京</label>
					<input name="emp.dept.company.subCompany" id="emp.dept.company.subCompanySH" type="checkbox" value="21" checked><label for="emp.dept.company.subCompanySH">上海</label>
					<input name="emp.dept.company.subCompany" id="emp.dept.company.subCompanySZ" type="checkbox" value="755"><label for="emp.dept.company.subCompanySZ">深圳</label>
				</td>
			</tr>
			<tr>
				<td>公司创建日期</td>
				<td>
					<input name="emp.dept.company.foundDate" id="emp.dept.company.foundDate" value="2013-11-12">
				</td>
			</tr>
			<tr>
				<td>公司照片</td>
				<td>
					图片1<input type="file" name="emp.dept.company.photos" id="emp.dept.company.photos1"> <br>
					图片2<input type="file" name="emp.dept.company.photos" id="emp.dept.company.photos2"> <br>
					图片3<input type="file" name="emp.dept.company.photos" id="emp.dept.company.photos3"> <br>
					图片4<input type="file" name="emp.dept.company.photos" id="emp.dept.company.photos4"> <br>
					图片5<input type="file" name="emp.dept.company.photos" id="emp.dept.company.photos5"> <br>
				</td>
			</tr>
		</table>
		<input type="submit" value="提交">
	</form>
</body>
</html>
