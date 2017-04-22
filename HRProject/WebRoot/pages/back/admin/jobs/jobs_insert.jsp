<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String insertJobsURL = "pages/back/admin/jobs/JobsServletBack/insert";
 %>

<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/jobs.js"></script>
</head>

<body>
	<form action="<%=insertJobsURL%>" method="post" onsubmit="return validateInsert();">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2"
			width="50%">
			<tr bgcolor="00B185">
				<td colspan="3">职位增加页面</td>
			</tr>
			<tr>
				<td width="20%">职位名称</td>
				<td width="30%"><input type="text" name="jobs.title"
					id="jobs.title" onblur="validateTitle()"></td>
				<td width="30%"><span id="jobs.titleMsg" name="jobs.titleMsg"></span>
				</td>
			</tr>
			<tr>
				<td width="20%">职位描述</td>
				<td width="30%"><input type="text" name="jobs.note"
					id="jobs.note"></td>
				<td width="30%">&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>
