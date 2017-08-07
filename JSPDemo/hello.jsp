<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>


<%!
	public static final String DRIVERNAME="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/mldn";
	public static final String USERNAME="root";
	public static final String PASSWORD ="admin";
%>


<%
	Class.forName(DRIVERNAME);
	Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	System.out.println(conn);
	conn.close();
%>

<h1>今天遇到一个傻逼</h1>
<h1><%=conn%></h1>