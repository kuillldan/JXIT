<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.*"%>
<%@page import="org.lyk.vo.*"%>
<%@page import="org.lyk.factory.*"%>
<%@page import="org.lyk.service.*"%>
<%@page import="com.jspsmart.upload.*"%>
<%@page import="java.io.*" %>


<%
	request.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	SmartUpload smart = new SmartUpload();
	smart.initialize(config, request, response);
	smart.upload();
	SmartRequest _request = smart.getRequest();
%>


<%
	String backUrl = _request.getParameter("backUrl");

	String currentPage = _request.getParameter("currentPage");
	String lineSize = _request.getParameter("lineSize");
	String columnName = _request.getParameter("columnName");
	String keyWord = _request.getParameter("keyWord");
%>


<%
	String msg = "员工信息修改成功";
	String url = backUrl + "?currentPage=" + currentPage + "&lineSize="
			+ lineSize + "&columnName=" + columnName + "&keyWord="
			+ keyWord;
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
		Emp emp = new Emp();
		emp.setEmpno(Integer.parseInt(_request.getParameter("empno")));
		emp.setEname(_request.getParameter("ename"));
		emp.setJob(_request.getParameter("job"));

		Emp mgr = new Emp();
		mgr.setEmpno(Integer.parseInt(_request.getParameter("mgrno")));
		emp.setMgr(mgr);

		Dept dept = new Dept();
		dept.setDeptno(Integer.parseInt(_request.getParameter("deptno")));
		emp.setDept(dept);

		emp.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse(_request.getParameter("hiredate")));
		emp.setSal(Integer.parseInt(_request.getParameter("sal")));
		emp.setComm(Integer.parseInt(_request.getParameter("comm")));
		emp.setNote(_request.getParameter("note"));

		String oldPhoto = _request.getParameter("oldPhoto");
		SmartFiles files = smart.getFiles();
		if (files.getCount() > 0 && files.getSize() > 0
				&& files.getFile(0).getContentType().contains("image")) {
			//更新文件有图片
			if (!"nophoto.jpg".equals(oldPhoto)) 
			{
				//原来有 现在有： 删除原有图片，在保存新图片 
				File oldPic = new File(this.getServletContext().getRealPath("/upload/") + oldPhoto);
				if(oldPic.exists())
				{
					oldPic.delete();
				}
			}
			
			String photo = UUID.randomUUID() + "." + files.getFile(0).getFileExt();
			files.getFile(0).saveAs( this.getServletContext().getRealPath("/upload/") + photo);
			emp.setPhoto(photo);
				
				
		} else 
		{
			//更新时没上传图片
			emp.setPhoto(oldPhoto);
		}

		if (!ServiceFactory.getIEmpServiceInstance().update(emp)) {
			msg = "员工信息修改失败";
		}
	%>

	<script type="text/javascript">
		alert("<%=msg%>");
		window.location = "<%=url%> ";
	</script>
</body>
</html>
