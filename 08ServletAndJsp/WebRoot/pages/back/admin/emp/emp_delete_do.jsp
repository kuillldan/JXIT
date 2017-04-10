<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.lyk.vo.*" %>
<%@page import="org.lyk.factory.*" %>
<%@page import="utils.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setCharacterEncoding("UTF-8");
%>


<%
	String msg = "";
	String url = "";
	
	String backUrl = request.getParameter("backUrl");
	String currentPage = request.getParameter("currentPage");
	String lineSize = request.getParameter("lineSize");
	String columnName = request.getParameter("columnName");
	String keyWord = request.getParameter("keyWord");
	String deptno = request.getParameter("deptno");
	
	url = backUrl + "?currentPage=" + currentPage + "&lineSize=" + lineSize + "&columnName=" + columnName + "&keyWord=" + keyWord + "&deptno=" + deptno;
 
 %>

 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>My JSP 'dept_insert.jsp' starting page</title> 
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
	<script type="text/javascript" src="js/lyk.js"></script>
  </head>
  
  <body> 
  	<%
  		String _ids = request.getParameter("ids");
  		String[] deptnos = _ids.split("\\|");
  		List<String> photos = new ArrayList<String>();
  		Set<Integer> ids = new HashSet<Integer>();
  		for(String id : deptnos)
  		{
  		//3:fdsafdasg.jpg
  			System.out.println("[debug]" + id);
  			String[] idAndPhoto = id.split(":");
  			ids.add(Integer.parseInt(idAndPhoto[0]));
  			if(!"nophoto.jpg".equals(idAndPhoto[1]))
  			{
  				photos.add(this.getServletContext().getRealPath("/upload/") + idAndPhoto[1]);
  			}
  		}
  		
  		General.removePhotos(photos);
  		
  		if(ServiceFactory.getIEmpServiceInstance().delete(ids))
  		{
  			msg = "员工信息删除成功"; 
  		}
  		else
  		{
  			msg = "员工信息删除失败";
  		}
  	%>
  	
  	<script type="text/javascript">
  		alert("<%=msg%>");
  		window.location = "<%=url%>";
  	</script>
  </body>
</html>
