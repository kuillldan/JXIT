<%@ page language="java" import="vo.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	request.setCharacterEncoding("UTF-8");
%>
 
<% 
	String columns = request.getParameter("columns");
	String columnName = request.getParameter("columnName");
	String keyWord = request.getParameter("keyWord");
%>


选择查询条件:
<select onchange="setColumnName(this.value)">
	<%
		String[] eachColumn = columns.split("\\|");
		for (String item : eachColumn)
		{
			String[] columnDetails = item.split(":");
			String display = columnDetails[0];
			String value = columnDetails[1];
	%>
	<option value="<%=value%>" <%=value.equals(columnName) ? "selected" : ""%>><%=display%></option>
	<%
		}
	%>
</select>
&nbsp;
<input type="text" name="keyWordInput" id="keyWordInput" value="<%=("".equals(keyWord) || null == keyWord) ? "" : keyWord%>" size="5">

<button onclick="search()">查询</button>




