<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String deleteURL = basePath + "pages/back/admin/item/ItemServletBack/delete?p=1";
	String updateItemURL = basePath + "pages/back/admin/item/ItemServletBack/update";
%>

<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/item.js"></script>
</head>

<body>
	<c:if test="${allItems!=null }">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="80%">
			<tr>
				<td><input type="checkbox" onclick="checkboxSelect(this,'tiid')"></td>
				<td>名称</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${allItems}" var="item">
				<tr>
					<td><input type="checkbox" id="tiid" name="tiid" value="${item.iid }"></td>
					<td><input value="${item.title }" id="title-${item.iid }" name="title-${item.iid }"><span id="title-${item.iid }Msg" name="title-${item.iid }Msg"></span></td>
					<td><button onclick="updateItem('${item.iid}','${item.title }')">更新</button></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3">
					<button onclick="deleteAll('<%=deleteURL%>', 'ids', 'tiid')">删除选中</button>
				</td>
			</tr>
		</table>
	</c:if>

	<form action="<%=updateItemURL%>" method="post" id="itemInfoForm">
		<input type="hidden" name="iid" id="iid"> <input type="hidden" name="title" id="title">
	</form>
</body>
</html>
