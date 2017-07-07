<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'confirm.jsp' starting page</title>
</head>

<body>
	<table border="1" cellpadding="5" cellspacing="0" width="500" align="center">
		<tr>
			<td align="center">开闭局管理</td>
		</tr>
		<tr>
			<td>
				<table border="1" cellpadding="5" cellspacing="0" width="100%">
					<tr>
						<td colspan="2">自动开闭局管理</td>
					</tr>
					<tr>
						<td>变换前</td>
						<td>变换后</td>
					</tr>
					<tr>
						<td>开始时：09:00</td>
						<td>结束时：22:00</td>
					</tr>
					<tr>
						<td>开始时：10:30</td>
						<td>结束时：21:00</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="1" cellpadding="5" cellspacing="0"  width="100%">
					<tr>
						<td colspan="2">手动状态更新</td>
					</tr>
					<tr>
						<td>变换前状态</td>
						<td>变换后状态</td>
					</tr>
					<tr>
						<td>自动开闭局管理</td>
						<td>手动开闭局管理</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="提交" ><button>取消</button></td>
		</tr>
	</table>
</body>
</html>
