<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setCharacterEncoding("UTF-8");
%>
 
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>My JSP 'dept_insert.jsp' starting page</title> 
    <style type="text/css">
		 #preview, .img, img  {
			width:200px;
			height:200px;
		}
		#preview {
			border:1px solid #000;
		}
	</style>
    
    
    <link type="text/css" rel="stylesheet" href="css/lyk.css">
    <script type="text/javascript">  
		function preview(file) {
			var prevDiv = document.getElementById('preview');
			if (file.files && file.files[0]) {
				var reader = new FileReader();
				reader.onload = function(evt) {
					prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
				} 
				reader.readAsDataURL(file.files[0]);
			} else {
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			}
		}
	</script>
    
    
	<script type="text/javascript" src="js/lyk.js"></script>
  </head>
  	
  <body>
  	<form action="smartUpload_do.jsp" method="post" enctype="multipart/form-data">
  	姓名：<input type="text" name="name"><br>
  	年龄:<input type="text" name="age"><br>
  		图片1<input type="file" name="pic" onchange="preview(this)"  /><br/>
  		
  		<input type="submit" value="提交"> 
  	</form>
  	
  	<div id="preview"></div>
  	
  </body>
</html>
