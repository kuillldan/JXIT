<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>

<body>
	<div class="nav-top">
		<jsp:include page="/pages/front/menu-top.jsp"></jsp:include>
	</div>
	
	<div class="nav-down"> 
	<jsp:include page="/pages/front/left.jsp"></jsp:include>		
		<div class="rightcon">
			<div class="right_con">
				<p style="text-align:left; margin-top:50px">
				<h3>增加员工资料</h3>
			</div>

			<div class="right_con">
				<form>
					<div class="form_add">

						<span class="list">姓名</span> <span class="list"> <input
							name="" type="text" /></span>

					</div>

					<div class="form_add">

						<span class="list">性别</span> <span class="list"> 男 <input
							name="" type="radio" value="" />女 <input name="" type="radio"
							value="" />
						</span>

					</div>

					<div class="form_add">

						<span class="list">身份证</span> <span class="list"> <input
							name="" type="text" /></span>

					</div>

					<div class="form_add">

						<span class="list">出生年月</span> <span class="list"> <input
							name="" type="text" /></span>

					</div>

					<div class="form_add">

						<span class="list">毕业院校</span> <span class="list"> <input
							name="" type="text" /></span>

					</div>

					<div class="form_add">

						<span class="list">学历</span> <span class="list"> <select
							name="">
								<option>专科以下</option>
								<option>专科</option>
								<option selected="selected">本科</option>
								<option>硕士</option>
								<option>博士</option>
						</select></span>

					</div>
					<div class="form_add">

						<span class="list">专业</span> <span class="list"> <input
							name="" type="text" /></span>

					</div>
					<div class="form_add">

						<span class="list">电话</span> <span class="list"> <input
							name="" type="text" /></span>

					</div>
					<div class="form_add">

						<span class="list">邮箱</span> <span class="list"> <input
							name="" type="text" /></span>

					</div>
					<div class="form_add">

						<span class="list">入职日期</span> <span class="list"> <input
							name="" type="text" /></span>

					</div>

					<div class="form_add">

						<span class="list">部门</span> <span class="list"> <select
							name="">
								<option>技术部</option>
								<option>市场部</option>
								<option selected="selected">销售部</option>
								<option>后勤部</option>

						</select></span>

					</div>

					<div class="form_add">
						<span class="list">职位</span> <span class="list"> <select
							name="">
								<option>经理</option>
								<option>总监</option>
								<option>项目经理</option>
								<option selected="selected">工程师</option>
								<option>美工</option>
								<option>网管</option>
								<option>销售</option>
						</select></span>

					</div>

					<div class="form_add">
						<span class="list">工资级别</span> <span class="list"> <select
							name="">
								<option>L1</option>
								<option>L2</option>
								<option>L3</option>
								<option selected="selected">L4</option>
								<option>L5</option>
								<option>L6</option>
								<option>L7</option>
						</select></span>

					</div>

					<div class="form_add">

						<span class="list">税前月薪</span> <span class="list"> <input
							name="" type="text" />
						</span> 元
					</div>

					<div class="form_add">

						<span class="list">备注</span> <span class="list"> <textarea
								name="" cols="100" rows="5"></textarea></span>

					</div>
					<div class="form_add">

						<span class="list"> </span> <span class="list"> <input
							name="" type="submit" value="添加" />
						</span> <span class="list"> <input type="button" name="button1"
							id="button1" value="返回" onclick="history.go(-1)"></span>
					</div>


				</form>
			</div>


		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	
</script>