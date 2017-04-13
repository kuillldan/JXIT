<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	String goodsInsertURL = "pages/back/admin/goods/GoodsServletBack/insert";
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
  	<form action="<%=goodsInsertURL%>" method="post" enctype="multipart/form-data">
  		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="80%">
  			<tr>
  				<td colspan="3">增加商品信息</td>
  			</tr>
  			<tr>
  				<td width="15%">商品名称</td>
  				<td width="30%"><input type="text" id="title" name="title" onblur="validateTitle()"><span id="titleMsg" name="titleMsg"></span></td>
  				<td width="55%" rowspan="7"><div id="previewDiv"></div></td>
  			</tr>
  			<tr>
  				<td>所属分类</td>
  				<td>
  					<select id="iid" name="iid">
  						<c:forEach items="${allItems }" var="item">
  							<option value="${item.iid }">${item.title }</option>
  						</c:forEach>
  					</select>
  				</td>
  			</tr>
  			<tr>
  				<td>商品价格</td>
  				<td><input id="price" name="price" onblur="validatePrice()"><span id="priceMsg" name="priceMsg"></span></td>
  			</tr>
  			<tr>
  				<td>库存数量</td>
  				<td><input id="amount" name="amount" onblur="validateAmount()"> <span id="amountMsg" name="amountMsg"></span></td>
  			</tr>
  			<tr>
  				<td>商品图片</td>
  				<td><input type="file" onchange="showPreview(this)" id="photo" name="photo"></td>
  			</tr>
  			<tr>
  				<td>发布状态</td>
  				<td>
  					<input type="radio" name="status" value="0" checked>下架<input type="radio" name="status" value="1">上架
  				</td>
  			</tr>
  			<tr>
  				<td>商品描述</td>
  				<td>
  					<textarea rows="5" cols="60" id="note" name="note"></textarea>
  				</td>
  			</tr>
  			<tr>
  				<td colspan="3">
  					<input type="submit" value="增加">
  					<input type="reset" value="重置">
  				</td> 
  			</tr>
  		</table>
  	</form>
  </body>
</html>
