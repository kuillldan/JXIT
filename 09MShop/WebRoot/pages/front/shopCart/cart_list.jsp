<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String deleteShopCarURL = "pages/front/shopCart/ShopCarServletFront/delete?p=1";
	String updateShopCarURL = "pages/front/shopCart/ShopCarServletFront/update";
	String payURL = "pages/front/shopCart/OrdersServletFront/insert";
%>

<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/shopCart.js"></script>
 
</head>
<jsp:include page="/pages/common/header.jsp"></jsp:include>
<h1>我的购物车</h1>
<c:if test="${allGoods!=null }">
	<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="60%">
		<tr bgcolor="00B185">
			<td><input type="checkbox" id="selectAllGoods" name="selectAllGoods" onclick="checkboxSelect(this,'gid')"></td>
			<td>图片</td>
			<td>名称</td>
			<td>价格</td>
			<td>数量</td>
			<td>总价</td>
		</tr>
		<c:forEach items="${allGoods }" var="goods">
			<tr>
				<td><input type="checkbox" id="gid" name="gid" value="${goods.gid }"></td>
				<td><img style="width:40px;height:60px" src="photos/goods/${goods.photo }"></td>
				<td>${goods.title }</td>
				<td>${goods.price }</td>
				<input type="hidden" id="price-${goods.gid }" name="price-${goods.gid }" value="${goods.price }">
				<td><button onclick="addAmount('${goods.gid}')">+</button> <input type="text" id="amount-${goods.gid }" name="amount-${goods.gid }" value="${cart[goods.gid] }" length="3" size="3" onblur="validateAmount('${goods.gid}')">
					<button onclick="minusAmount('${goods.gid}')">-</button></td>
				<td><span id="totalPrice-${goods.gid }">${goods.price * cart[goods.gid] }</span></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6">
				<button onclick="deleteAll('<%=deleteShopCarURL%>', 'ids', 'gid')">删除选中</button>
				<button onclick="updateShopCar('<%=updateShopCarURL%>')">更新购物车</button>
				<button onclick="gotoPay('<%=payURL%>')">去结算</button>				
			</td>
		</tr>
	</table>
</c:if>
<jsp:include page="/pages/common/footer.jsp"></jsp:include>
<body>
</body>
</html>
