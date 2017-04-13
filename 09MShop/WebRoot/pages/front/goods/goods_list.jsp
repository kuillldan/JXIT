<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8");
%>



<%
	String searchUrl = basePath + "pages/front/goods/GoodsServletFront/list";
	String searchByItemURL =  basePath + "pages/front/goods/GoodsServletFront/list";
	String goodsShowURL = basePath + "pages/front/goods/GoodsServletFront/show";
	String addToShopCartURL = basePath + "pages/front/shopCart/ShopCarServletFront/add";
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
<jsp:include page="/pages/common/header.jsp"></jsp:include>
	<select id="iid" name="iid"  onchange="findAllByItem('<%=searchByItemURL%>')">
		<option value="0">选择分类</option>
		<c:forEach items="${allItems }" var="item">
			<option value="${item.iid }" ${item.iid==parameterValue?"selected":""}>${ item.title}</option>
		</c:forEach>
	</select>
	<c:if test="${allCount > 0 }">
		<jsp:include page="/pages/common/search.jsp">
			<jsp:param value="${columns }" name="columns" />
			<jsp:param value="${columnName }" name="columnName" />
			<jsp:param value="${keyWord }" name="keyWord" />
		</jsp:include>
		
		<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2" width="70%">

			<c:forEach items="${allGoods }" var="goods">
				<tr>
					<td rowspan="3"><a href="<%=goodsShowURL%>?gid=${goods.gid}"><img style="width:40px;height:60px" src="photos/goods/${goods.photo }"></a></td>
					<td>商品名称</td>
					<td>${goods.title }</td>
					<td>上架日期</td>
					<td>${goods.pubdate }</td>
				</tr>
				<tr>
					<td>商品价格</td>
					<td>${goods.price }</td>
					<td>浏览次数</td>
					<td>${goods.bow }</td>
				</tr>
				<tr>
					<td colspan="3">${goods.note }</td>
					<td><a href="<%=addToShopCartURL%>?gid=${goods.gid }">加入购物车</a></td>
				</tr>
			</c:forEach>
		</table>

		<jsp:include page="/pages/common/paging.jsp">
			<jsp:param value="${currentPage }" name="currentPage" />
			<jsp:param value="${lineSize }" name="lineSize" />
			<jsp:param value="${totalPages }" name="totalPages" />
		</jsp:include>
	</c:if>
	<c:if test="${allCount <= 0 }">
		<h1>未找到查询记录</h1>
	</c:if>
<jsp:include page="/pages/common/footer.jsp"></jsp:include>
	<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
	<input type="hidden" name="lineSize" id="lineSize" value="${lineSize }">
	<input type="hidden" name="columnName" id="columnName" value="${columnName }">
	<input type="hidden" name="keyWord" id="keyWord" value="${keyWord }">
	<input type="hidden" name="totalPages" id="totalPages" value="${totalPages }">

	<input type="hidden" name="parameterKey" id="parameterKey" value="${parameterKey }">
	<input type="hidden" name="parameterValue" id="parameterValue" value="${parameterValue }">
	<input type="hidden" name="searchUrl" id="searchUrl" value="<%=searchUrl%>">
</body>
</html>
