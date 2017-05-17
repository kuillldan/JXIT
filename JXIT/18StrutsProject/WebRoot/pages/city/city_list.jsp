<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'city_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		$(function() 
		{
			$.post("pages/city/cityAction!list.action",{},function(data)
			{ 
				for(var i = 0; i < data.cityList.length; i++)
				{
					var eachCity = data.cityList[i]; 
					$("#cityListTable").append("<tr><td>"+eachCity.cid+"</td><td>"+eachCity.title+"</td></tr>");
				}
			},"json");
		});
		
	</script>
  </head>
  
  <body>
    <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" id="cityListTable">
    	<tr>
    		<td>城市编号</td>
    		<td>城市名称</td>
    	</tr>
    </table>
  </body>
</html>