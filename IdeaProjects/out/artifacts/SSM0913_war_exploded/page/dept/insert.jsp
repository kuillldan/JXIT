<%@ page language="java" import="lyk.vo.*" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: liuyuank
  Date: 9/14/2017
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    String deptInsertURL = basePath + "pages/dept/upload.action";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <script type="text/javascript" src="js/lyk.js"></script>
    <script type="text/javascript" src="js/dept.js"></script>
</head>
<body>
 <form action="<%=deptInsertURL%>" method="post" enctype="multipart/form-data">
     部门编号:<input type="text" name="deptno" ><br>
     部门名称:<input type="text" name="dname"> <br>
     部门位置:<input type="text" name="loc" ><br>
     上传图片:<input type="file" name="photo"><br>
     <input type="submit" value="提交">
     <input type="reset" value="重置">
 </form>
</body>
</html>
