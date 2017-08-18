<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%> 


<%
	//被调用处需要传递一下5个传输
	//<jsp:include/>
	Integer currentPage = 1;
	Integer lineSize = 5;
	Integer allRecorders = null;
	Integer pageSize = 0;
	String splitJSP = request.getParameter("splitJSP");
	
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
	
	try
	{
		allRecorders = Integer.parseInt(request.getParameter("allRecorders"));
	}catch(Exception e)
	{}

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

	if (currentPage >= pageSize)
	{
		currentPage = pageSize;
	} 
	
%>


<%
	

 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title> </title> 
 
</head>

<body>
	<button id="gotoFirstPage" name="gotoFirstPage"
					onclick="gotoPage(1,'<%=splitJSP%>')"
					<%=currentPage == 1 ? "disabled" : ""%>>首页</button>

				<button id="gotoPreviousPage" name="gotoPreviousPage"
					onclick="gotoPage('<%=currentPage - 1%>','<%=splitJSP%>')"
					<%=currentPage == 1 ? "disabled" : ""%>>上一页</button>

				<button id="gotoNextPage" name="gotoNextPage"
					onclick="gotoPage('<%=currentPage + 1%>','<%=splitJSP%>')"
					<%=currentPage == pageSize ? "disabled" : ""%>>下一页</button>
				<button id="gotoLastPage" name="gotoLastPage"
					onclick="gotoPage('<%=pageSize%>','<%=splitJSP%>')"
					<%=currentPage == pageSize ? "disabled" : ""%>>末页</button> 跳转到:<input
				type="text" id="redirectToPage" name="redirectToPage" size="1"
				value="<%=currentPage%>/<%=pageSize%>" onfocus="clearContent()"
				onblur="resetContent('<%=currentPage%>','<%=pageSize%>')">页
				<button onclick="redirectTo('<%=splitJSP%>')">GO</button>每页显示:<select
				id="selectLineSize" onchange="gotoPage(1,'<%=splitJSP%>')">
					<option value="5" <%=lineSize == 5 ? "selected" : ""%>>5</option>
					<option value="10" <%=lineSize == 10 ? "selected" : ""%>>10</option>
					<option value="15" <%=lineSize == 15 ? "selected" : ""%>>15</option>
					<option value="30" <%=lineSize == 30 ? "selected" : ""%>>30</option>
			</select>页
</body>
</html>
