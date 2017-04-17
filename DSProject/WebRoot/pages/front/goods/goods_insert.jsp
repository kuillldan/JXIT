<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title></title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
  </head>
  
  <body>
  <form action="pages/front/goods/GoodsServlet/insert" method="post">
  	<table border="1" bgcolor="F2F2F2" cellpadding="5" cellspacing="0">
  		<tr>
  			<td>操作员id</td>
  			<td><input type="text" id="goods.aid" name="goods.aid" value="yuankui"></td>
  		</tr>
  		<tr>
  			<td>发布日期</td>
  			<td><input type="text" id="goods.pubdate" name="goods.pubdate" value="2016-12-13"></td>
  		</tr>
  		<tr>
  			<td>库存量</td>
  			<td><input type="text" id="goods.amount" name="goods.amount" value="50"></td>
  		</tr>
  		<tr>
  			<td colspan="2">
  				<input type="submit" value="提交">
  			</td>
  		</tr>
  		<tr>
  			<td>地点</td>
  			<td>
  				<input type="checkbox" name="goods.item.otherInfo.locs" value="10">北京 
  				<input type="checkbox" name="goods.item.otherInfo.locs" value="12">天津 
  				<input type="checkbox" name="goods.item.otherInfo.locs" value="13">上海 
  				<input type="checkbox" name="goods.item.otherInfo.locs" value="14">重庆 
  				<input type="checkbox" name="goods.item.otherInfo.locs" value="15">成都
  			</td>
  		</tr>
  	</table>
  </form> 
  </body>
</html>
