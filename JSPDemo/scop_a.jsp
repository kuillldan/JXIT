<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	pageContext.setAttribute("name","sheldon");
	pageContext.setAttribute("birthday",new Date());
%>

<%
	String name = (String)pageContext.getAttribute("name");
	Date birthday = (Date)pageContext.getAttribute("birthday");
%>

<jsp:forward page="scop_b.jsp" />