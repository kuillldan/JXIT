<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@page import="java.text.*" %>
<%@page import="java.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	Admin bAdmin = (Admin)session.getAttribute("bAdmin");
	String lastdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bAdmin.getLastdate()).toString();
 %>
<link href="images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="center">
				<h1>欢迎光临后台管理程序</h1>
				<h2>上次登录时间:<%=lastdate %></h2>
			</td>
		</tr>
	</table>
</body>
