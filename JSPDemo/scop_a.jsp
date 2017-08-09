<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	pageContext.setAttribute("name","远奎");
	pageContext.setAttribute("birthday",new Date());
%>

<%
	pageContext.setAttribute("name","MLDN",PageContext.SESSION_SCOPE);
	pageContext.setAttribute("birthday",new Date(),PageContext.SESSION_SCOPE);
%>

<a href="scop_b.jsp">跳转</a>