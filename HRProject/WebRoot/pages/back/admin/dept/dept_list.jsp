<%@ page language="java" import="lyk.vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String deleteURL = "pages/back/admin/dept/DeptServletBack/delete?1=1";
	String updateURL = basePath + "pages/back/admin/dept/DeptServletBack/update";
%>


<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/dept.js"></script>
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>
	<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2"
		width="80%">
		<tr bgcolor="00B185">
			<td colspan="5">部门列表页面</td>
		</tr>
		<tr>
			<td width="5%"><input type="checkbox" id="selectAllDepts"
				name="selectAllDepts" onclick="checkboxSelect(this, 'eachDept')">
			</td>
			<td width="20%">编号</td>
			<td width="20%">姓名</td>
			<td width="20%">当前人数</td>
			<td width="20%">操作</td>
		</tr>
		<c:forEach items="${allDepts }" var="dept">
			<tr>
				<td><input type="checkbox" name="eachDept" id="eachDept"
					value="${dept.did }"></td>
				<td>${dept.did }</td>
				<td><input type="text" id="dept-${dept.did }"
					name="dept-${dept.did }" value="${dept.dname }" onblur="validateDnameWhenUpdate(${dept.did})"></td>
				<td>${dept.current }</td>
				<td><button
						onclick="updateDept('${dept.did}','<%=updateURL%>')">更新</button></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5">
				<button onclick="deleteAll('<%=deleteURL%>', 'ids', 'eachDept')">删除选中</button>
			</td>
		</tr>
	</table>
</body>
</html>
