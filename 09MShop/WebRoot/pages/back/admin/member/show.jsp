<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	  
%>


<%
	String updateMemberURL = basePath + "pages/back/admin/member/MemberServletBack/updateStatus";
 %>
 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>My JSP 'dept_insert.jsp' starting page</title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
  </head>
  
  <body> 
  	<c:if test="${member!=null }">
  		<form action="<%=updateMemberURL %>" method="post">
  			<table border="1" cellpadding = "5" cellspacing="0" bgcolor="F2F2F2" width="60%">
  				<tr>
  					<td colspan="3">用户完整信息</td>
  				</tr>
  				<tr>
  					<td>用户ID</td>
  					<td>${member.mid }</td>
  					<td rowspan="6">
  						<img src="<%=basePath %>photos/${member.photo }" style="width:80px;height:120px">
  					</td>
  				</tr>
  				<tr>
  					<td>真实姓名</td>
  					<td>${member.name }</td> 
  				</tr>
  				<tr>
  					<td>联系电话</td>
  					<td>${member.phone }</td> 
  				</tr>
  				<tr>
  					<td>用户地址</td>
  					<td>${member.address }</td> 
  				</tr>
  				<tr>
  					<td>注册日期</td>
  					<td>${member.regdate }</td> 
  				</tr>
  				<tr>
  					<td>用户状态</td>
  					<td>
  						<select id="status" name="status">
  							<option value="locked" ${member.status==0?"selected":"" }>已锁定</option>
  							<option value="actived"  ${member.status==1?"selected":"" }>已激活</option>
  							<option value="pending"  ${member.status==2?"selected":"" }>待激活</option> 
  						</select>
  					</td> 
  				</tr>
  				<tr>
  					<td colspan="3">
  						<input type="submit" value="修改用户状态"/>
  					</td>  
  				</tr>
  			</table>
  			<input type="hidden" name="mid" value="${member.mid }">
  		</form>
  	</c:if>
  </body>
</html>
