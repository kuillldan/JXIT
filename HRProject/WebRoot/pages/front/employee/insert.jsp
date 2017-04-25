<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<%
	String insertURL = "pages/front/employee/EmployeeServletFront/insert";
 %>


<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/laydate/laydate.js"></script>
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
				<form action="<%=insertURL %>" method="post">
					<div class="form_add">

						<span class="list">姓名</span> <span class="list"> <input name="employee.ename" id="employee.ename" type="text" /></span>

					</div>

					<div class="form_add">

						<span class="list">性别</span> <span class="list"> 男 <input name="employee.sex" type="radio" value="男" checked />女 <input name="employee.sex" type="radio" value="女" />
						</span>

					</div>

					<div class="form_add">

						<span class="list">身份证</span> <span class="list"> <input name="employee.idcard" type="text" id="employee.idcard" /></span>

					</div>

					<div class="form_add">

						<span class="list">出生年月</span> <span class="list"> <input name="employee.birthday" id="employee.birthday" type="text" onclick="laydate()" /></span>

					</div>

					<div class="form_add">

						<span class="list">毕业院校</span> <span class="list"> <input name="employee.school" id="employee.school" type="text" /></span>

					</div>

					<div class="form_add">

						<span class="list">学历</span> <span class="list"> <select name="employee.edu">
								<option>专科以下</option>
								<option>专科</option>
								<option selected="selected">本科</option>
								<option>硕士</option>
								<option>博士</option>
						</select></span>

					</div>
					<div class="form_add">

						<span class="list">专业</span> <span class="list"> <input name="employee.profession" id="employee.profession" type="text" /></span>

					</div>
					<div class="form_add">

						<span class="list">电话</span> <span class="list"> <input name="employee.phone" type="text" /></span>

					</div>
					<div class="form_add">

						<span class="list">邮箱</span> <span class="list"> <input name="employee.email" type="text" /></span>

					</div>
					<div class="form_add">

						<span class="list">入职日期</span> <span class="list"> <input name="employee.indate" id="employee.indate" type="text" /></span>

					</div>

					<div class="form_add">

						<span class="list">部门</span> <span class="list"> <select name="employee.dept.did">
								<c:forEach items="${allDepts }" var="dept">
									<option value="${dept.did }">${dept.dname }</option>
								</c:forEach>
						</select></span>

					</div>

					<div class="form_add">
						<span class="list">职位</span> <span class="list"> <select name="employee.jobs.jid">
								<c:forEach items="${allJobs }" var="jobs">
									<option value="${jobs.jid }">${jobs.title }</option>
								</c:forEach>
						</select></span>

					</div>

					<div class="form_add">
						<span class="list">工资级别</span> <span class="list"> <select name="employee.level.levid">
								<c:forEach items="${allLevels }" var="level">
									<option value="${level.levid }">${level.title }</option>
								</c:forEach>
						</select></span>

					</div>

					<div class="form_add">
						<span class="list">税前月薪</span> <span class="list"> <input name="employee.sal" id="employee.sal" type="text" />
						</span> 元
					</div>

					<div class="form_add">
						<span class="list">备注</span> <span class="list"> <textarea name="employee.note" id="employee.note" cols="100" rows="5"></textarea></span>
					</div>
					<div class="form_add">

						<span class="list"> </span> <span class="list"> <input type="submit" value="添加" />
						</span> <span class="list"><input type="reset" value="重置"> </span>
					</div>


				</form>
			</div>


		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	
</script>