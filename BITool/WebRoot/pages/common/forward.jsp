<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
<base href="<%=basePath%>">
<title></title>
</head>

<body>
	<script type="text/javascript">
  		alert("<%=(String) request.getAttribute("msg")%>");
  		window.location = "<%=basePath + request.getAttribute("url")%>";
	</script>
</body>
</html>
