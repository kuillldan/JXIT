<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8");
%>



<% 
	String searchUrl = basePath + "pages/back/admin/orders/OrdersServletBack/list";
	 
%>

<%
	String detailsURL = "pages/back/admin/orders/OrdersServletBack/show";
 %>


<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script> 
</head>

<body>
	<c:if test="${allOrdersCount > 0 }">
		<jsp:include page="/pages/common/search.jsp">
		<jsp:param value="${columns }" name="columns" />
		<jsp:param value="${columnName }" name="columnName" />
		<jsp:param value="${keyWord }" name="keyWord" />
	</jsp:include>

	<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2">
		<tr bgcolor="00B185">
			<td>订单编号</td>
			<td>会员名</td>
			<td>收货人姓名</td>
			<td>收货电话</td>
			<td>收货地址</td>
			<td>创建日期</td>
			<td>总金额</td>
		</tr>
		<c:forEach items="${allOrders }" var="order">
			<tr>
				<td><a href="<%=detailsURL%>?oid=${order.oid}">${order.oid }</a></td>
				<td>${order.mid }</td>
				<td>${order.name }</td>
				<td>${order.phone }</td>
				<td>${order.address }</td>
				<td>${order.credate }</td>
				<td>${order.pay }</td>
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
