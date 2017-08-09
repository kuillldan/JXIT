<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>


<%
	String name = (String)session.getAttribute("name");
	Date birthday = (Date)session.getAttribute("birthday");
%>

<h1>NAME:<%=name%></h1>
<h1>DATE:<%=birthday%></h1>