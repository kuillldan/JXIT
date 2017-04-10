<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
 
 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title></title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
	<script type="text/javascript" src="js/goods.js"></script>
  </head>
  
  <body>  
  		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="80%">
  			 <tr>
  			 	<td rowspan="8"><img src="photos/goods/${goods.photo }"></td>
  			 	<td>商品名称</td>
  			 	<td>${goods.title }</td>
  			 </tr>
  			 <tr>
  			 	<td>所属分类</td>
  			 	<td>${goods.item.title }</td>
  			 </tr>
  			 <tr>
  			 	<td>价格</td>
  			 	<td>${goods.price }</td>
  			 </tr>
  			 <tr>
  			 	<td>上架日期</td>
  			 	<td>${goods.pubdate }</td>
  			 </tr>
  			 <tr>
  			 	<td>库存数量</td>
  			 	<td>${goods.amount }</td>
  			 </tr>
  			 <tr>
  			 	<td>浏览次数</td>
  			 	<td>${goods.bow }</td>
  			 </tr> 
  			 <tr>
  			 	<td colspan="2">${goods.note }</td>
  			 </tr>
  			 <tr>
  			 	<td colspan="2"><button>加入购物车</button></td>
  			 </tr>
  		</table> 
  </body>
</html>
