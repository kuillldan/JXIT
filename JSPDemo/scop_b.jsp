<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>


<%
	String name = (String)pageContext.getAttribute("name");
	Date birthday = (Date)pageContext.getAttribute("birthday");
%>

<h1>NAME:<%=name%></h1>
<h1>DATE:<%=birthday%></h1>