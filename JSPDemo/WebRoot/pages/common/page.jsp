<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%--

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

//获取allRecorders代码
//获取所有记录代码
	
	//如:
	Map<String,Object> retVal = ServiceFactory.getEmpServiceInstance().listSplit(currentPage, lineSize);
	List<Emp> allEmps = (List<Emp>)retVal.get("allEmps");
	allRecorders = (Integer)retVal.get("allCount");

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
 --%>


<%--
			 <jsp:include page="/pages/common/page.jsp">
					<jsp:param value="<%=currentPage%>" name="currentPage" />
					<jsp:param value="<%=lineSize%>" name="lineSize" />
					<jsp:param value="<%=pageSize%>" name="pageSize" />
					<jsp:param value="<%=allRecorders%>" name="allRecorders" />
					<jsp:param value="<%=deptListSplitJSP%>" name="splitJSP" />
				</jsp:include> <a href="<%=insertDeptJSP%>">增加部门</a></td>
  --%>


<%
	//被调用处需要传递一下5个传输
	//<jsp:include/>
	Integer currentPage = 1;
	Integer lineSize = 5;
	Integer allRecorders = null;
	Integer pageSize = 0;
	String splitJSP = request.getParameter("splitJSP");

	try {
		currentPage = Integer.parseInt(request
				.getParameter("currentPage"));
	} catch (Exception e) {
	}

	try {
		lineSize = Integer.parseInt(request.getParameter("lineSize"));
	} catch (Exception e) {
	}

	try {
		allRecorders = Integer.parseInt(request
				.getParameter("allRecorders"));
	} catch (Exception e) {
	}

	if (currentPage <= 0) {
		currentPage = 1;
	}

	if (lineSize <= 0) {
		lineSize = 5;
	}

	if (lineSize >= 50) {
		lineSize = 50;
	}

	if (allRecorders == null) {
		allRecorders = 0;
	}
	if (allRecorders % lineSize == 0) {
		pageSize = allRecorders / lineSize;
	} else {
		pageSize = allRecorders / lineSize + 1;
	}

	if (currentPage >= pageSize) {
		currentPage = pageSize;
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title></title>
<script type="text/javascript">
	function gotoPage(currentPage, url) {
		if (currentPage <= 0) {
			currentPage = 1;
		}
		var lineSize = document.getElementById("selectLineSize").value;
		window.location = url + "?currentPage=" + currentPage + "&lineSize="
				+ lineSize;
	}

	function clearContent() {
		document.getElementById("redirectToPage").value = "";
	}

	function resetContent(currentPage, pageSize) {
		var val = document.getElementById("redirectToPage").value;
		if (val == "") {
			document.getElementById("redirectToPage").value = currentPage + "/"
					+ pageSize;
		}
	}

	function redirectTo(url) {
		var val = document.getElementById("redirectToPage").value;
		if (val != "") {
			gotoPage(val, url);
		}
	}

	//按下回车键进行页面跳转
	function redirectToWhenEnter(url) {
		var redirectToPage = document.getElementById("redirectToPage");
		if (window.event.keyCode == 13) {
			redirectTo(url);
		}
	}
</script>
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
		<%=currentPage == pageSize ? "disabled" : ""%>>末页</button>
	跳转到:
	<input type="text" id="redirectToPage" name="redirectToPage" size="1"
		value="<%=currentPage%>/<%=pageSize%>" onfocus="clearContent()"
		onblur="resetContent('<%=currentPage%>','<%=pageSize%>')"
		onkeypress="redirectToWhenEnter('<%=splitJSP%>')">页
	<button onclick="redirectTo('<%=splitJSP%>')">GO</button>
	每页显示:
	<select id="selectLineSize" onchange="gotoPage(1,'<%=splitJSP%>')">
		<option value="5" <%=lineSize == 5 ? "selected" : ""%>>5</option>
		<option value="10" <%=lineSize == 10 ? "selected" : ""%>>10</option>
		<option value="15" <%=lineSize == 15 ? "selected" : ""%>>15</option>
		<option value="30" <%=lineSize == 30 ? "selected" : ""%>>30</option>
	</select>页
</body>
</html>
