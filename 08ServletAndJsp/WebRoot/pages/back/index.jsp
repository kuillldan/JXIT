<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="utils.*"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
%>


<%
	String aid = (String)session.getAttribute("aid");
%>

<%
	String msg = "";
	String url = "";
%>

<%
	if (StringUtils.isEmpty(aid)) {
		msg = "请先登录";
		url = basePath + "pages/back/login.jsp";
%>
<script type="text/javascript">
	alert("<%=msg%>");
	window.location = "<%=url%>";
</script>
<%
	}
%>

<html>
<head>
<title>管理中心</title>
<meta http-equiv=Content-Type content=text/html;charset=gb2312>
</head>



<frameset rows="64,*" frameborder="NO" border="0" framespacing="0">
	<frame src="admin_top.jsp" noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="main" />
	<frameset cols="200,*" rows="560,*" id="frame">
		<frame src="left.jsp" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
		<frame src="right.jsp" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
	</frameset>
	<noframes>
		<body></body>
	</noframes>
</html>
