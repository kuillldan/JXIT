<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function getCityList() {
		$.post("pages/city/CityAction!list.action", {}, function(data) {
			console.log(data.allCities.length);
			for (var i = 0; i < data.allCities.length; i++) 
			{
				console.log((data.allCities)[i].cityID + ":" + (data.allCities)[i].cityName);
				$("#cityType").append(
						"<tr><td>" + (data.allCities)[i].cityID + "</td><td>"
								+ (data.allCities)[i].cityName + "</td></tr>");
			}
		}, "json");
	}
</script>

</head>

<body>
	城市列表页
	<a href="javascript:void();" onclick="getCityList()">点击加载</a>
	<table id="cityType">
		<tr>
			<td>城市编号</td>
			<td>城市名称</td>
		</tr>
	</table>
</body>
</html>
