<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	pageContext.setAttribute("name","远奎");
	pageContext.setAttribute("birthday",new Date());
%>

<%
	String name = (String)pageContext.getAttribute("name");
	Date birthday = (Date)pageContext.getAttribute("birthday");
%>

<h1>NAME:<%=name%></h1>