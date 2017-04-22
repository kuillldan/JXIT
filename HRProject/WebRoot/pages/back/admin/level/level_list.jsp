<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String deleteURL = "pages/back/admin/level/LevelServletBack/delete?1=1";
	String updateURL = basePath + "pages/back/admin/level/LevelServletBack/update";
%>


<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
<script type="text/javascript" src="js/level.js"></script>
</head>

<body>
	<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2"
		width="80%">
		<tr bgcolor="00B185">
			<td colspan="6">职位级别列表页面</td>
		</tr>
		<tr>
			<td width="5%"><input type="checkbox" id="selectAlllevels"
				name="selectAlllevels" onclick="checkboxSelect(this, 'eachLevel')">
			</td>
			<td width="20%">编号</td>
			<td width="20%">名称</td>
			<td width="20%">最低工资</td>
			<td width="20%">最高工资</td>
			<td width="20%">操作</td>
		</tr>
		<c:forEach items="${allLevels }" var="level">
			<tr>
				<td><input type="checkbox" name="eachLevel" id="eachLevel"
					value="${level.levid }"></td>
				<td>${level.levid }</td>
				<td><input type="text" id="level-title-${level.levid }" name="level-title-${level.levid }" value="${level.title }" onblur="validateTitleWhenUpdate('${level.levid}')"></td>
				<td><input type="text" value="${level.losal }" id="level-losal-${level.levid }" name="level-losal-${level.levid}" onblur="validateLosalWhenUpdate('${level.levid}')" /></td>
				<td><input type="text" value="${level.hisal }" id="level-hisal-${level.levid }" name="level-hisal-${level.levid }" onblur="validateHisalWhenUpdate('${level.levid}')" /></td>
				<td><button
						onclick="updateLevel('${level.levid}','<%=updateURL%>')">更新</button></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6">
				<button onclick="deleteAll('<%=deleteURL%>', 'ids', 'eachLevel')">删除选中</button>
			</td>
		</tr>
	</table>
</body>
</html>
