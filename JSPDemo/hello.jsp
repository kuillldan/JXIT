<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
 
<html>
	<head>
		<title></title>
	</head>
	<body>
		<form action="request_demo.jsp" method="post">
			姓名:<input type="text" name="name"/><br/>
			电话:<input type="text" name="phone"/><br/>
			爱好:<input type="checkbox" name="inst" value="游泳">游泳
			<input type="checkbox" name="**inst" value="跑步">跑步
			<input type="checkbox" name="**inst" value="看书">看书
			<input type="checkbox" name="**inst" value="写字">写字
			<input type="checkbox" name="**inst" value="画画">画画
			<input type="submit" value="提交" />
		</form>
	</body>
</html>