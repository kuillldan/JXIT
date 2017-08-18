<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.*" %>
<%@ page import="vo.*"%>
<%@ page import="factory.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<%
	String empInsertJSP = basePath + "pages/back/admin/emp/emp_insert.jsp";
	String empUpdateJSP = basePath + "pages/back/admin/emp/emp_update.jsp";
	String empDeleteAllURL = basePath + "pages/back/admin/emp/emp_delete_do.jsp?p=1";
	String empSplitListJSP = basePath + "pages/back/admin/emp/emp_list.jsp";
 %>
 
<%
	Integer currentPage = 1;
	Integer lineSize = 5;
	Integer pageSize = 0;
	Integer allRecorders = null;

	try
	{
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	} catch (Exception e)
	{
	}

	try
	{
		lineSize = Integer.parseInt(request.getParameter("lineSize"));
	} catch (Exception e)
	{
	}

	if (currentPage <= 0)
	{
		currentPage = 1;
	}

	if (lineSize <= 0)
	{
		lineSize = 5;
	}

	if (lineSize >= 50)
	{
		lineSize = 50;
	}
%>

<%
	Map<String,Object> retVal = ServiceFactory.getEmpServiceInstance().listSplit(currentPage, lineSize);
	List<Emp> allEmps = (List<Emp>)retVal.get("allEmps");
	allRecorders = (Integer)retVal.get("allCount");
%>

<%
	if (allRecorders == null)
	{
		allRecorders = 0;
	}
	if (allRecorders % lineSize == 0)
	{
		pageSize = allRecorders / lineSize;
	} else
	{
		pageSize = allRecorders / lineSize + 1;
	}
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'template.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="css/lyk.css">
<script type="text/javascript" src="js/lyk.js"></script>
</head>

<body>

	<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2" width="100%">
		<tr>
			<td colspan="8">员工列表</td>
		</tr>
		<tr>
			<td><input type="checkbox" id="selectAll" name="selectAll"
				onclick="checkboxSelect(this,'emp')"></td>
			<td>员工编号</td>
			<td>姓名</td>
			<td>入职日期</td>
			<td>职位</td>
			<td>薪水</td>
			<td>佣金</td>
			<td>操作</td>
		</tr>
		<%
			for (Emp vo : allEmps)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String hiredate = sdf.format(vo.getHiredate());
		%>
		<tr>
			<td><input type="checkbox" id="emp" name="emp" value="<%=vo.getEmpno() %>"></td>
			<td><%=vo.getEmpno() %></td>
			<td><%=vo.getEname() %></td>
			<td><%=hiredate %></td>
			<td><%=vo.getJob() %></td>
			<td><%=vo.getSal() %></td>
			<td><%=vo.getComm() %></td>
			<td><a href="<%=empUpdateJSP%>?empno=<%=vo.getEmpno()%>&currentPage=<%=currentPage%>">修改</a></td>
		</tr>
		<%
			}
		%>
		
		<tr>
			<td colspan="8">
				<input type="button"  onclick="deleteAll('<%=empDeleteAllURL %>','empno','emp')" value="删除员工信息">
				<jsp:include page="/pages/common/page.jsp">
					<jsp:param value="<%=currentPage%>" name="currentPage" />
					<jsp:param value="<%=lineSize%>" name="lineSize" />
					<jsp:param value="<%=pageSize%>" name="pageSize" />
					<jsp:param value="<%=allRecorders%>" name="allRecorders" />
					<jsp:param value="<%=empSplitListJSP%>" name="splitJSP" />
				</jsp:include>  
				<a href="<%=empInsertJSP%>">增加员工</a>
			</td>
		</tr>
	</table>
</body>
</html>
