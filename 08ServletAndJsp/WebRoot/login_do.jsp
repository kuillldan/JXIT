<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<%
	String userName = request.getParameter("userName");
	String passWord = request.getParameter("passWord");
	String code = request.getParameter("code");
	String rand = (String) session.getAttribute("rand");
%>

<%
	String msg = "";
	String url = "";
%>


<%
	if (rand != null && rand.equals(code)) 
	{
		if ("mldn".equals(userName) && "java".equals(passWord)) 
		{
			msg = "欢迎你，登录成功!";
			url = basePath + "welcom.jsp";
			session.setAttribute("uid", "mldn");
		} else {
			msg = "对不起,用户名或密码错误!";
			url = basePath + "login.jsp";
		}
	} else 
	{
		msg = "验证码错误";
		url = basePath + "login.jsp";	
	}
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>
	<script type="text/javascript">
	alert("<%=msg%>");
	window.location = "<%=url%>";
	</script>
</body>
</html>
