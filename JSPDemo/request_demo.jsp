<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<%
	request.setCharacterEncoding("UTF-8");
	Enumeration<String> allParameterNames =	request.getParameterNames();
%>

<html>
	<head></head>
	<body>
		<%
			while(allParameterNames.hasMoreElements())
			{
				String parameterName = allParameterNames.nextElement();
				if(parameterName.startsWith("**")) 
				{ 
					%>
						<h1><%=Arrays.toString(request.getParameterValues(parameterName))%></h1>
					<% 
				}
				else
				{
					%>
						<h1><%=request.getParameter(parameterName)%></h1>
					<% 
				}
			}
		%>
	</body>
</html>