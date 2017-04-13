<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<%
	Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
	Integer lineSize = Integer.parseInt(request.getParameter("lineSize"));
	Integer totalPages = Integer.parseInt(request.getParameter("totalPages")); 
%>

<button onclick="gotoFirstPage()" id="btnGotoFirstPage" <%=currentPage == 1 ? "disabled" : ""%>>首页</button>
<button onclick="gotoPreviousPage()" id="btnGotoPreviousPage" <%=currentPage == 1 ? "disabled" : ""%>>上一页</button>
<button onclick="gotoNextPage()" id="btnGotoNextPage" <%=currentPage == totalPages ? "disabled" : ""%>>下一页</button>
<button onclick="gotoLastPage()" id="btnGotoLastPage" <%=currentPage == totalPages ? "disabled" : ""%>>末页</button>
