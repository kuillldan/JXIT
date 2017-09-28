<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String showURL = basePath + "pages/index.jsp";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> 
	<table border="1" width="600px" align="center">
		<tr>
			<td align="center"><p class="title">結果
				<p></td>
		</tr>
		<tr align="center">
			<td>
				<p class="error_msg">${msg == null ? "UNKNOWN ERROR. WE ARE SORRY ABOUT THIS. PLEASE COTACT SYSTEM
		ADMINISTRATOR": msg}</p>
				<br>
			</td>
		</tr>
		<tr>
			<td align="center">
				<button onclick="location.href='<%=showURL%>'">返回主页</button>
			</td>
		</tr>

	</table>
</body>
</html>