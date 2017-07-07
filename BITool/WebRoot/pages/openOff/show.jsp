<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://liuyuankui.cn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'show.jsp' starting page</title>
<script type="text/javascript">
	function showAlert() {
		alert("你没有选择任何改变,该操作不能执行。");
	}
</script>
</head>

<body>
	<table border="1" cellpadding="5" cellspacing="0" width="600px"
		align="center">
		<tr>
			<td align="center">开闭局管理</td>
		</tr>
		<tr>
			<td>
				<table border="1" cellpadding="5" cellspacing="0" width="100%">
					<tr>
						<td colspan="4" align="center">自动开闭局管理</td>
					</tr>
					<tr>
						<td>开始时刻</td>
						<td><select>
								<c:forEach items="${allHours }" var="hour">
									<option value="${hour }" ${hour==startHour?"selected":"" }>${hour }</option>
								</c:forEach>
						</select></td>
						<td colspan="2"><select>
								<c:forEach items="${allMinutes }" var="minute">
									<option value="${minute }"
										${minute==startMinute?"selected":"" }>${minute }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>终了时刻</td>


						<td><select>
								<c:forEach items="${allHours }" var="hour">
									<option value="${hour }" ${hour==endHour?"selected":"" }>${hour }</option>
								</c:forEach>
						</select> </select></td>
						<td><select>
								<c:forEach items="${allMinutes }" var="minute">
									<option value="${minute }"
										${minute==endMinute?"selected":"" }>${minute }</option>
								</c:forEach>
						</select></td>
						<td><button onclick="showAlert()">变更</button></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="1" cellpadding="5" cellspacing="0" width="100%">
					<tr>
						<td colspan="3" align="center">手动状态变更</td>
					</tr>
					<tr>
						<td>现在状态</td>
						<td>变更后状态</td>
						<td></td>
					</tr>
					<tr>
						<c:if test="${status=='OPEN' }">
							<td>开局</td>
						</c:if>
						<c:if test="${status=='CLOSED' }">
							<td>闭局</td>
						</c:if>
						<c:if test="${status=='ADMIN_OPEN' }">
							<td>管理员闭局</td>
						</c:if>
						<td><select>
								<option ${mode=='AUTO' ? "SELECTED":"" }>自动开闭局管理</option>
								<option ${mode=='MANUAL' ? "SELECTED":"" }>手动开闭局管理</option>
								<option ${mode=='MAINTAINANCE' ? "SELECTED":"" }>Maintanance开闭局管理</option>
						</select></td>
						<td><button>变更</button></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
