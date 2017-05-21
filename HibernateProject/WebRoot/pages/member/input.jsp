<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'input.jsp' starting page</title>

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
	<form action="pages/member/MemberAction!insert.action" method="post">
		员工号:<input type="text" name="member.mid" value="21591930"><br>
		姓&nbsp;名:<input type="text" name="member.name" value="kui"><br>
		年&nbsp;龄:<input type="text" name="member.age" value="30"><br>
		工&nbsp;资:<input type="text" name="member.salary" value="5555.2"><br>
		生&nbsp;日:<input type="text" name="member.birthday" value="1922-12-23"><br>
		备&nbsp;注:<input type="text" name="member.note" value="good man"><br>
		<input type="submit" value="提交"> 
	</form>
</body>
</html>
