<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String goodsUpdateURL = "pages/back/admin/goods/GoodsServletBack/update";
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
	<form action="<%=goodsUpdateURL%>" method="post" enctype="multipart/form-data">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="80%">
			<tr>
				<td colspan="3">修改商品信息</td>
			</tr>
			<tr>
				<td width="15%">商品名称</td>
				<td width="30%"><input type="text" id="title" name="title" value="${goods.title }" onblur="validateTitle()"><span id="titleMsg" name="titleMsg"></span></td>
				<td width="55%" rowspan="7">
				<div id="previewDiv">
						<img src="photos/goods/${goods.photo }">
					</div>
					</td>
			</tr>
			<tr>
				<td>所属分类</td>
				<td><select id="iid" name="iid">
						<c:forEach items="${allItems }" var="item">
							<option value="${item.iid }" ${goods.item.iid == item.iid?"selected":"" }>${item.title }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td><input id="price" name="price" value="${goods.price }" onblur="validatePrice()"><span id="priceMsg" name="priceMsg"></span></td>
			</tr>
			<tr>
				<td>库存数量</td>
				<td><input id="amount" name="amount" value="${goods.amount }" onblur="validateAmount()"> <span id="amountMsg" name="amountMsg"></span></td>
			</tr>
			<tr>
				<td>发布日期</td>
				<td>${goods.pubdate }</td>
			</tr>
			<tr>
				<td>浏览次数</td>
				<td>${goods.bow }</td>
			</tr>
			<tr>
				<td>商品图片</td>
				<td><input type="file" onchange="showPreview(this)" id="photo" name="photo"></td>
			</tr>
			<tr>
				<td>发布状态</td>
				<td><input type="radio" name="status" ${goods.status==0?"checked":"" } value="0">下架 <input type="radio" name="status" ${goods.status==1?"checked":"" } value="1">上架 <input type="radio" name="status" ${goods.status==2?"checked":"" } value="2">删除</td>
			</tr>
			<tr>
				<td>商品描述</td>
				<td><textarea rows="5" cols="60" id="note" name="note">${goods.note }</textarea></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="修改"> <input type="reset" value="重置"></td>
			</tr>
		</table>

		<input type="hidden" id="backUrl" name="backUrl" value="${backUrl }"> <input type="hidden" id="gid" name="gid" value="${goods.gid }"> <input type="hidden" id="bow" name="bow" value="${goods.bow }"> <input type="hidden" id="oldPhoto" name="oldPhoto" value="${goods.photo }">
		<input type="hidden" id="pubdate" name="pubdate" value="${goods.pubdate }">
	</form>
</body>
</html>
