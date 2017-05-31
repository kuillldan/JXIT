<%@ page language="java" import="lyk.vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String deleteURL = "pages/back/admin/jobs/JobsServletBack/delete?1=1";
	String updateURL = basePath + "pages/back/admin/jobs/JobsServletBack/update";
%>


<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/jobs.js"></script>
</head>

<body>
	<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2"
		width="80%">
		<tr bgcolor="00B185">
			<td colspan="5">职位列表页面</td>
		</tr>
		<tr>
			<td width="5%"><input type="checkbox" id="selectAllJobs"
				name="selectAllJobs" onclick="checkboxSelect(this, 'eachJobs')">
			</td>
			<td width="20%">编号</td>
			<td width="20%">名称</td>
			<td width="20%">描述</td>
			<td width="20%">操作</td>
		</tr>
		<c:forEach items="${allJobs }" var="jobs">
			<tr>
				<td><input type="checkbox" name="eachJobs" id="eachJobs"
					value="${jobs.jid }"></td>
				<td>${jobs.jid }</td>
				<td><input type="text" id="jobs-title-${jobs.jid }"
					name="jobs-title-${jobs.jid }" value="${jobs.title }" onblur="validateTitleWhenUpdate('${jobs.jid}')"></td>
				<td><input type="text" value="${jobs.note }" id="jobs-note-${jobs.jid }" name="jobs-note-${jobs.jid }"/></td>
				<td><button
						onclick="updateJobs('${jobs.jid}','<%=updateURL%>')">更新</button></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5">
				<button onclick="deleteAll('<%=deleteURL%>', 'ids', 'eachJobs')">删除选中</button>
			</td>
		</tr>
	</table>
</body>
</html>
