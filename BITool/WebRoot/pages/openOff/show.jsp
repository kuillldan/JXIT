<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://liuyuankui.cn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<%
	String changeTimeURL = basePath + "svc_state_manage/updateTime?modtime="+request.getAttribute("modtime");
	String changeModeURL = basePath + "svc_state_manage/updateMode?modtime="+request.getAttribute("modtime");
%>

<%
	List<String> allHours = new ArrayList<String>();
	for (int i = 0; i <= 23; i++)
	{
		if (i < 10)
		{
			allHours.add("0" + i);
		} else
		{
			allHours.add(String.valueOf(i));
		}
	}

	List<String> allMinutes = new ArrayList<String>();
	allMinutes.add("00");
	for (int i = 1; i <= 59; i++)
	{
		if (i <= 9)
		{
			allMinutes.add("0" + i);
		} else
		{
			allMinutes.add(String.valueOf(i));
		}
	}

	pageContext.setAttribute("allHours", allHours);
	pageContext.setAttribute("allMinutes", allMinutes);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'show.jsp' starting page</title>
<script type="text/javascript" src="js/openOffManagement.js"></script>
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
						<td><select id="currentStartHour">
								<c:forEach items="${allHours }" var="hour">
									<option value="${hour }" ${hour==startHour?"selected":"" }>${hour }</option>
								</c:forEach>
						</select></td>
						<td colspan="2"><select id="currentStartMinute">
								<c:forEach items="${allMinutes }" var="minute">
									<option value="${minute }"
										${minute==startMinute?"selected":"" }>${minute }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>终了时刻</td>


						<td><select id="currentEndHour">
								<c:forEach items="${allHours }" var="hour">
									<option value="${hour }" ${hour==endHour?"selected":"" }>${hour }</option>
								</c:forEach>
						</select></td>
						<td><select id="currentEndMinute">
								<c:forEach items="${allMinutes }" var="minute">
									<option value="${minute }" ${minute==endMinute?"selected":"" }>${minute }</option>
								</c:forEach>
						</select></td>
						<td><button
								onclick="checkAutoManagementTime('${startHour}','${startMinute }','${endHour }','${endMinute }','<%=changeTimeURL%>')">变更</button></td>
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
						<td><c:if test="${mode=='AUTO' }">
							自动
						</c:if> <c:if test="${mode=='MANUAL' }">
							手动
						</c:if> <c:if test="${mode=='MAINTAINANCE' }">
							 MAINTANANCE开闭局管理
						</c:if></td>
						<td><select id="currentMode">
								<option value="AUTO" ${mode=='AUTO' ? "SELECTED":"" }>自动开闭局管理</option>
								<option value="MANUAL" ${mode=='MANUAL' ? "SELECTED":"" }>手动开闭局管理</option>
								<option value="MAINTAINANCE"
									${mode=='MAINTAINANCE' ? "SELECTED":"" }>Maintanance开闭局管理</option>
						</select></td>
						<td><button
								onclick="changeMode('${mode }','<%=changeModeURL%>')">变更</button></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
