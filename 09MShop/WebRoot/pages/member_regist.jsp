<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	//request.setCharacterEncoding("UTF-8");
%>

<%
	String registUrl = "pages/MemberServletFront/regist";
 %>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/member.js"></script>
</head>

<body>
	<form action="<%=registUrl%>" method="post" onsubmit="return validateRegist();">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="80%">
			<tr>
				<td colspan="3">用户注册</td>
			</tr>
			<tr>
				<td width="30%">用户名</td>
				<td width="45%"><input type="text" id="mid" name="mid" onblur="validateMid()"></td>
				<td width="45%"><span id="midMsg" name="midMsg"></span></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" id="password" name="password" onblur="validatePassword()"></td>
				<td><span id="passwordMsg" name="passwordMsg"></span></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="提交"> <input type="reset" value="清空"></td>
			</tr>
		</table>
	</form>
</body>
</html>
