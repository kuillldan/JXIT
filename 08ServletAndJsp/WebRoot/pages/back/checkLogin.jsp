<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="utils.*"%>
<%@page import="org.lyk.factory.*"%>
<%@page import="org.lyk.vo.*"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<%
	String rand = (String) session.getAttribute("rand");
	String code = request.getParameter("code");
	String aid = request.getParameter("aid");
	String password = request.getParameter("password");
%>

<%
	String url = "";
	String msg = "";
%>


<%
	if (StringUtils.isEmpty(rand) || StringUtils.isEmpty(code)) {
		url = basePath + "pages/back/login.jsp";
		msg = "验证码错误";
	} else {
		if (!rand.equalsIgnoreCase(code)) {
			url = basePath + "pages/back/login.jsp";
			msg = "验证码错误";
		} else {
			if (StringUtils.isEmpty(aid)
					|| StringUtils.isEmpty(password)) {
				url = basePath + "pages/back/login.jsp";
				msg = "请提供用户名或密码";
			} else {
				Admin admin = new Admin();
				admin.setAid(aid);
				admin.setPassword(new MD5Code().getMD5ofStr(password));
				if (ServiceFactory.getIAdminServiceInstance().login(
						admin)) {
					session.setAttribute("aid", admin.getAid());
					url = basePath + "pages/back/index.jsp";
					msg = "登录成功";
				} else {
					url = basePath + "pages/back/login.jsp";
					msg = "登录失败-用户名或密码错误";
				}
			}
		}
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
		window.location="<%=url%>";
	</script>
</body>
</html>
