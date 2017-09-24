<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String arrayInsertURL = basePath + "pages/back/converter/converterAction!insert.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=arrayInsertURL %>"  method="post">
		爱好:<input type="checkbox" name="hobi" id="chifan" value="吃饭"><label for="chifan">吃饭</label>
		<input type="checkbox" name="hobi" id="shuijiao" value="睡觉"><label for="shuijiao">睡觉</label>
		<input type="checkbox" name="hobi" id="dadoudou" value="打豆豆"><label for="dadoudou">打豆豆</label>
		<br>
		<input type="submit" value="提交"> 
	</form>
</body>
</html>