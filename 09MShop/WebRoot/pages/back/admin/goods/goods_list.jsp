<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8"); 
%>
      


<%
	String searchUrl = basePath + "pages/back/admin/goods/GoodsServletBack/list";
	String updateStatusUp = basePath + "pages/back/admin/goods/GoodsServletBack/updateStatus?status=1";
	String updateStatusDown = basePath + "pages/back/admin/goods/GoodsServletBack/updateStatus?status=0";
	String updateStatusDelete = basePath + "pages/back/admin/goods/GoodsServletBack/updateStatus?status=2";
	String updateGoodsUrl = basePath + "pages/back/admin/goods/GoodsServletBack/updatePre";
	String deleteAllUrl = basePath + "pages/back/admin/goods/GoodsServletBack/deleteAll?p=1";
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
	<c:if test="${allCount > 0 }">
		<jsp:include page="/pages/common/search.jsp">
		<jsp:param value="${columns }" name="columns" />
		<jsp:param value="${columnName }" name="columnName" />
		<jsp:param value="${keyWord }" name="keyWord" />
	</jsp:include>

	<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2">
		<tr>
			<td><input type="checkbox" id="checkAll" name="checkAll" onclick="checkboxSelect(this,'gid')"></td>
			<td>商品编号</td>
			<td>所属分类</td>
			<td>上架人</td>
			<td>商品名称</td>
			<td>发布日期</td>
			<td>价格</td>
			<td>库存</td>
			<td>浏览次数</td>
			<td>描述</td>
			<td>图片</td>
			<td>状态</td>
		</tr>

		<c:forEach items="${allGoods }" var="goods">
			<tr>
				<td>
					<input type="checkbox" id="gid" name="gid" value="${goods.gid }:${goods.photo}">
				</td>
				<td>${goods.gid}</td>
				<td>${goods.item.title }</td>
				<td>${goods.aid }</td>
				<td><a href="<%=updateGoodsUrl%>?gid=${goods.gid}">${goods.title }</a></td>
				<td>${goods.pubdate }</td>
				<td>${goods.price }</td>
				<td>${goods.amount }</td>
				<td>${goods.bow }</td>
				<td>${goods.note }</td>
				<td><img style="width:30px;height:45px" src="photos/goods/${goods.photo }"></td>
				<c:if test="${goods.status==0 }">
					<td>下架</td>
				</c:if>
				<c:if test="${goods.status==1 }">
					<td>上架</td>
				</c:if>
				<c:if test="${goods.status==2 }">
					<td>删除</td>
				</c:if>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="12">
				<c:if test="${parameterValue==null || parameterValue==0 || parameterValue == 2}">
					<button onclick="updateStatus('<%=updateStatusUp %>', 'ids', 'gid')">全部上架</button>
				</c:if> 
				<c:if test="${parameterValue==null || parameterValue==1}">
					<button onclick="updateStatus('<%=updateStatusDown %>', 'ids', 'gid')">全部下架</button>
				</c:if>
				<c:if test="${parameterValue==null || parameterValue==0 || parameterValue == 1}">
					<button onclick="updateStatus('<%=updateStatusDelete %>', 'ids', 'gid')">全部删除</button>
				</c:if> 
				<c:if test="${parameterValue==2 }">
					<button onclick="deleteAll('<%=deleteAllUrl %>', 'ids', 'gid')">彻底删除</button>
				</c:if>
			</td>
		</tr>
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
