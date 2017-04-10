<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	//request.setCharacterEncoding("UTF-8");
%>

<%
	String loginUrl = "pages/MemberServletFront/login";
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
	<jsp:include page="/pages/common/header.jsp"></jsp:include>
	<form action="<%=loginUrl%>" method="post" onsubmit="return validateLogin();">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="60%">
			<tr>
				<td colspan="3">用户登录</td>
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
				<td>验证码</td>
				<td><input type="password" id="code" name="code" onblur="validateCode()"><img style="width:150px;height:50px" src="pages/common/image.jsp"></td>
				<td><span id="codeMsg" name="codeMsg"></span></td>
			</tr>
			<tr>
				<td colspan="3"><input type="radio" value="604800" name="autoLogin">一周内免登陆</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="提交"> <input type="reset" value="清空"></td>
			</tr>
		</table>
	</form>
	<jsp:include page="/pages/common/footer.jsp"></jsp:include>
</body>
</html>
