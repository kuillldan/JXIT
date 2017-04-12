<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String memberUpdateURL = "pages/front/member/MemberInfoServletFront/update";
%>

<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/member.js"></script>

</head>
<jsp:include page="/pages/common/header.jsp"></jsp:include>

<c:if test="${member!=null }">
	<form action="<%=memberUpdateURL%>" method="post" enctype="multipart/form-data">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="80%">
			<tr bgcolor="00B185">
				<td colspan="4">用户详细信息</td>
			</tr>
			<tr>
				<td width="10%">用户ID</td>
				<td width="20%">${member.mid }</td>
				<td width="20%"></td>
				<td width="30%"></td>

			</tr>

			<tr>
				<td>姓名:</td>
				<td><input type="text" id="name" name="name" value="${member.name }" /></td>
				<td><span id="nameMsg" name="nameMsg"></span></td>
				<td rowspan="4">
					<div id="previewDiv">
						<img src="photos/member/${member.photo }">
					</div>
				</td>
			</tr>
			<tr>
				<td>电话:</td>
				<td><input type="text" id="phone" name="phone" value="${member.phone }" /></td>
				<td><span id="phoneMsg" name="phoneMsg"></span></td>
			</tr>
			<tr>
				<td>住址:</td>
				<td><input type="text" id="address" name="address" value="${member.address }" /></td>
				<td><span id="addressMsg" name="addressMsg"></span></td>
			</tr>
			<tr>
				<td>照片:</td>
				<td><input type="file" id="photo" name="photo"   onchange="showPreview(this)"  /></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="4"><input type="submit" value="提交"> <input type="reset" value="重设"></td>
			</tr>
		</table>
		<input type="hidden" id="oldPhoto" name="oldPhoto" value="${member.photo }">
	</form>
</c:if>
<c:if test="${member==null }">
	<h1>未找到用户信息</h1>
</c:if>

<jsp:include page="/pages/common/footer.jsp"></jsp:include>
<body>
</body>
</html>
