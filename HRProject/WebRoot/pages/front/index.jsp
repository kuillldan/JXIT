<%@ page language="java" import="lyk.vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>

<body>
	<div class="nav-top">
		<jsp:include page="menu-top.jsp"></jsp:include>
	</div>
	<div class="nav-down">
		<jsp:include page="left.jsp"></jsp:include>
		
		<div class="rightcon">
			<div class="right_con">
				<p style="text-align:left; margin-top:50px">右侧内容自适应哦！我是左对齐</p>
				<p style="text-align:center">右侧内容自适应哦！我是居中</p>
				<p style="text-align:right">右侧内容自适应哦！我是右对齐</p>
				<h1>我是标题1。。。</h1>
				<h2>我是标题2。。。</h2>
				<h3>我是标题3。。。</h3>
				<h4>我是标题4。。。</h4>
				<h5>我是标题5。。。</h5>
			</div>
		</div>
		
		
	</div>
</body>
</html>
<script type="text/javascript">
	
</script>