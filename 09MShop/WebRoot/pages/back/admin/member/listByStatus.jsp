<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8");
%>



<%
	String searchUrl = basePath + "pages/back/admin/member/MemberServletBack/listByStatus";
	String activeAllMembersUrl = basePath
			+ "pages/back/admin/member/MemberServletBack/updateMembersStatus?type=active";
	String disableAllMembersUrl = basePath
			+ "pages/back/admin/member/MemberServletBack/updateMembersStatus?type=disable";
	String showMemberUrl = basePath = "pages/back/admin/member/MemberServletBack/show";
%>


<%
	String columns = (String) request.getAttribute("columns");
	String columnName = (String) request.getAttribute("columnName");
	String keyWord = (String) request.getAttribute("keyWord");
	Integer currentPage = (Integer) request.getAttribute("currentPage");
	Integer lineSize = (Integer) request.getAttribute("lineSize");

	Integer totalRecords = (Integer) request.getAttribute("allCount");
	Integer totalPages = (totalRecords + lineSize - 1) / lineSize;
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'dept_insert.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/member.js"></script>
</head>

<body>
	<jsp:include page="/pages/common/search.jsp">
		<jsp:param value="<%=columns%>" name="columns" />
		<jsp:param value="<%=columnName%>" name="columnName" />
		<jsp:param value="<%=keyWord%>" name="keyWord" />
	</jsp:include>

	<table border="1" cellpadding="5" cellspacing="0" bgColor="#F2F2F2">
		<tr>
			<td><input type="checkbox" onclick="checkboxSelect(this,'mid')"></td>
			<td>登录名</td>
			<td>姓名</td>
			<td>电话</td>
			<td>注册日期</td>
			<td>状态</td>
		</tr>
		<c:forEach items="${allMembers}" var="member">
			<tr>
				<td><input type="checkbox" id="mid" name="mid" value="${member.mid }"></td>
				<td><a href="<%=showMemberUrl%>?mid=${member.mid}">${member.mid }</a></td>
				<td>${member.name }</td>
				<td>${member.phone }</td>
				<td>${member.regdate }</td>
				<c:if test="${member.status==0 }">
					<td>锁定</td>
				</c:if>
				<c:if test="${member.status==1 }">
					<td>已激活</td>
				</c:if>
				<c:if test="${member.status==2 }">
					<td>待激活</td>
				</c:if>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6"><c:if test="${parameterValue==0 || parameterValue==2}">
					<button onclick="activeAllMembers('<%=activeAllMembersUrl%>', 'ids', 'mid')">激活选中</button>
				</c:if> <c:if test="${parameterValue==1}">
					<button onclick="disableAllMembers('<%=disableAllMembersUrl%>', 'ids', 'mid')">锁定选中</button>
				</c:if></td>
		</tr>
	</table>

	<jsp:include page="/pages/common/paging.jsp">
		<jsp:param value="<%=currentPage%>" name="currentPage" />
		<jsp:param value="<%=lineSize%>" name="lineSize" />
		<jsp:param value="<%=totalPages%>" name="totalPages" />
	</jsp:include>



	<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
	<input type="hidden" name="lineSize" id="lineSize" value="${lineSize }">
	<input type="hidden" name="columnName" id="columnName" value="${columnName }">
	<input type="hidden" name="keyWord" id="keyWord" value="${keyWord }">
	<input type="hidden" name="columns" id="columns" value="${columns }">

	<input type="hidden" name="searchUrl" id="searchUrl" value="<%=searchUrl%>">
	<input type="hidden" name="totalPages" id="totalPages" value="<%=totalPages%>">
	<input type="hidden" name="parameterKey" id="parameterKey" value="${parameterKey }">
	<input type="hidden" name="parameterValue" id="parameterValue" value="${parameterValue }">
</body>
</html>
