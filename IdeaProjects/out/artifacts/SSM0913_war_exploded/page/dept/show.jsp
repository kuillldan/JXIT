<%@ page language="java" import="lyk.vo.*" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: liuyuank
  Date: 9/14/2017
  Time: 9:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <script type="text/javascript" src="js/lyk.js"></script>
    <script type="text/javascript" src="js/dept.js"></script>
</head>
<body>
 部门编号:${dept.deptno}
 部门名称:${dept.dname}
 部门地点:${dept.loc}
文件名称:${fileName}
 文件大小:${fileSize}
 文件类型:${fileType}
</body>
</html>
