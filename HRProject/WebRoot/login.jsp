<%@ page language="java" import="lyk.vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String backLoginJSP = basePath + "backLogin.jsp";
 %>

<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function fliptoBackLogin()
	{
		window.location = "<%=backLoginJSP%>";
	}
</script>
</head>

<body>
	<div style="text-align:right;">
	<br/><br/>
		<button onclick="fliptoBackLogin()">后台登陆</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<div id="logo">
		<img src="images/logo-head.jpg">
	</div>
	<form id="slick-login"
		action="login/front/admin/AdminLoginServletFront/login" method="post">
		<label for="username">username</label><input type="text"
			name="admin.aid" class="placeholder" placeholder="username">
		<label for="password">password</label><input type="password"
			name="admin.password" class="placeholder" placeholder="password">
		<label for="code">验证码</label><input type="text" name="code"
			class="placeholder"><img src="pages/common/image.jsp">
		<input type="submit" value="登录">
	</form>

</body>
</html>
