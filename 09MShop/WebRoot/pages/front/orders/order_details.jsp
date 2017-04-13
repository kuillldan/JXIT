<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="java.sql.*"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>

	<jsp:include page="/pages/common/header.jsp"></jsp:include>
	<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2"  width="80%">
		<tr bgcolor="00B185">
			<td>订单编号</td>
			<td>会员名</td>
			<td>收货人姓名</td>
			<td>收货电话</td>
			<td>收货地址</td>
			<td>创建日期</td>
			<td>总金额</td>
		</tr>
		<tr>
			<td>${orders.oid }</td>
			<td>${orders.mid }</td>
			<td>${orders.name }</td>
			<td>${orders.phone }</td>
			<td>${orders.address }</td>
			<td>${orders.credate }</td>
			<td>${orders.pay }</td>
		</tr>

	</table>

	<hr />
	<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2" width="80%">
		<tr bgcolor="00B185">
			<td>详情编号</td>
			<td>订单编号</td>
			<td>商品编号</td>
			<td>商品名称</td>
			<td>价格</td>
			<td>数量</td>
		</tr>
		<c:forEach items="${allDetails }" var="details">
			<tr>
				<td>${details.odid }</td>
				<td>${details.order.oid }</td>
				<td>${details.goods.gid }</td>
				<td>${details.title }</td>
				<td>${details.price }</td>
				<td>${details.amount }</td>
			</tr>
		</c:forEach>
	</table>

	<jsp:include page="/pages/common/footer.jsp"></jsp:include>
	<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
	<input type="hidden" name="lineSize" id="lineSize" value="${lineSize }">
	<input type="hidden" name="totalPages" id="totalPages" value="${totalPages }">


	<input type="hidden" name="columnName" id="columnName" value="1">
	<input type="hidden" name="keyWord" id="keyWord" value="1">
	<input type="hidden" name="totalPages" id="totalPages" value="1">
	<input type="hidden" name="parameterKey" id="parameterKey" value="deptno">
	<input type="hidden" name="parameterValue" id="parameterValue" value="XXXX">
</body>
</html>
