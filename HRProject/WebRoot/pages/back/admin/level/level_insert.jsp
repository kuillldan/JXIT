<%@ page language="java" import="lyk.vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String insertLevelURL = "pages/back/admin/level/LevelServletBack/insert";
 %>

<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/level.js"></script>
</head>

<body>
	<form action="<%=insertLevelURL%>" method="post" onsubmit="return validateInsert();">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2"
			width="50%">
			<tr bgcolor="00B185">
				<td colspan="3">职位级别增加页面</td>
			</tr>
			<tr>
				<td width="20%">级别名称</td>
				<td width="30%"><input type="text" name="level.title"
					id="level.title" onblur="validateTitle()"></td>
				<td width="30%"><span id="level.titleMsg" name="level.titleMsg"></span>
				</td>
			</tr>
			<tr>
				<td width="20%">最低工资</td>
				<td width="30%"><input type="text" name="level.losal"
					id="level.losal" onblur="validateLosal()"></td>
				<td width="30%"><span id="level.losalMsg"></span>
				</td>
			</tr>
			<tr>
				<td width="20%">最高工资</td>
				<td width="30%"><input type="text" name="level.hisal"
					id="level.hisal" onblur="validateHisal()"></td>
				<td width="30%"><span id="level.hisalMsg"></span>
				</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>
