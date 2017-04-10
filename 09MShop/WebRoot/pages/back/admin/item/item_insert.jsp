<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String itemInsertURL = "pages/back/admin/item/ItemServletBack/insert";
%>
<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/item.js"></script>
</head>

<body>
	<form action="<%=itemInsertURL%>" method="post" onsubmit="return validateInsert()">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="35%">
			<tr>
				<td>增加商品分类</td>
			</tr>
			<tr>
				<td><input id="title" name="title" onblur="validateTitle()"><span id="titleMsg" name="titleMsg"></span></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="提交">
					<input type="reset" value="重置"> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
