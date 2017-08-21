<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>优拓教育（www.yootk.com）—— Bootstrap开发实战</title>
	<meta charset="UTF-8">
	<!-- 此时表示根据设备的大小调整页面的显示宽度 -->
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<!-- Bootstrap需要jQuery的支持，所以一定要导入jQuery开发包 -->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<!-- Bootstrap所需要的一些组件的*.js文件 -->
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<!-- Bootstrap所需要的一些基础样式 -->
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
	<div class=".container">
		<div class="row">
			<div class="col-md-1">栅格11</div>
			<div class="col-md-1">栅格12</div>
			<div class="col-md-1">栅格13</div>
			<div class="col-md-1">栅格14</div>
			<div class="col-md-1">栅格15</div>
			<div class="col-md-1">栅格16</div>
			<div class="col-md-1">栅格17</div>
			<div class="col-md-1">栅格18</div>
			<div class="col-md-1">栅格19</div>
			<div class="col-md-1">栅格10</div>
			<div class="col-md-1">栅格11</div>
			<div class="col-md-1">栅格12</div>  
		</div>
		<div class="row">
			<div class="col-md-3" style="background:red;">栅格1</div>
			<div class="col-md-3 col-md-push-3" style="background:grey;">栅格2</div>
		</div>
		<div class="row">
			<div class="col-md-3" style="background:red;">栅格1</div>
			<div class="col-md-6 col-md-pull-2" style="background:grey;">栅格2</div>
		</div>
	</div> 
	
	<span class="visible-md"></span>
</body>
</html>
