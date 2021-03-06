<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<%
	String upateURL = "pages/front/employee/EmployeeServletFront/update";
%>
 


<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/laydate/laydate.js"></script>
<script type="text/javascript" src="js/lyk.js"></script>
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
				<h3>更新员工资料</h3>
			</div>

			<div class="right_con">
				<table width="100%" >
					<tr>
						<td width="30%">
							<form action="<%=upateURL%>" method="post" enctype="multipart/form-data">
								<div class="form_add">

									<span class="list">姓名</span> <span class="list"> <input name="employee.ename" id="employee.ename" type="text" value="${employee.ename }" /></span>

								</div>

								<div class="form_add">

									<span class="list">性别</span> <span class="list"> 男 
									<input name="employee.sex" type="radio" value="男" ${employee.sex == '男'?"checked":"" } />女 
									<input name="employee.sex" type="radio" value="女" ${employee.sex == '女'?"checked":"" } />
									</span>

								</div>

								<div class="form_add">

									<span class="list">身份证</span> <span class="list"> <input name="employee.idcard" type="text" id="employee.idcard"  value="${employee.idcard }"/></span>

								</div>

								<div class="form_add">

									<span class="list">出生年月</span> <span class="list"> <input name="employee.birthday" id="employee.birthday" type="text" onclick="laydate()" value="${employee.birthday }"  /></span>

								</div>

								<div class="form_add">

									<span class="list">毕业院校</span> <span class="list"> <input name="employee.school" id="employee.school" type="text" value="${employee.school }" /></span>

								</div>
								
								<div class="form_add">

									<span class="list">毕业日期</span> <span class="list"> 
									<input name="employee.grad" id="employee.grad" type="text"  onclick="laydate()" value="${employee.grad }" /></span>

								</div>

								<div class="form_add">

									<span class="list">学历</span> <span class="list"> <select name="employee.edu">
											<option ${employee.edu =="专科以下"?"selected":"" }>专科以下</option>
											<option ${employee.edu =="专科"?"selected":""} >专科</option>
											<option ${employee.edu =="本科"?"selected":""}  >本科</option>
											<option ${employee.edu =="硕士"?"selected":""} >硕士</option>
											<option ${employee.edu =="博士"?"selected":""} >博士</option>
									</select></span>

								</div>
								<div class="form_add">

									<span class="list">专业</span> <span class="list"> <input name="employee.profession" id="employee.profession" type="text" value="${employee.profession }" /></span>

								</div>
								<div class="form_add">

									<span class="list">电话</span> <span class="list"> <input name="employee.phone" type="text" value="${employee.phone }" /></span>

								</div>
								<div class="form_add">

									<span class="list">邮箱</span> <span class="list"> <input name="employee.email" type="text" value="${employee.email }"  /></span>

								</div>
								<div class="form_add">

									<span class="list">入职日期</span> <span class="list"> 
									<input type="hidden" name="employee.indate" id="employee.indate" type="text"  value="${employee.indate }" /><input type="text" disabled="true" value="${employee.indate }"></span>

								</div>

								<div class="form_add">

									<span class="list">部门</span> <span class="list"> <select name="employee.dept.did">
											<c:forEach items="${allDepts }" var="dept">
												<option value="${dept.did }" ${employee.dept.did==dept.did?"selected":"" } >${dept.dname }</option>
											</c:forEach>
									</select></span>

								</div>

								<div class="form_add">
									<span class="list">职位</span> <span class="list"> <select name="employee.jobs.jid">
											<c:forEach items="${allJobs }" var="jobs">
												<option value="${jobs.jid }" ${employee.job == jobs.title?"selected":"" } >${jobs.title }</option>
											</c:forEach>
									</select></span>

								</div>

								<div class="form_add">
									<span class="list">工资级别</span> <span class="list"> <select name="employee.level.levid">
											<c:forEach items="${allLevels }" var="level">
												<option value="${level.levid }" ${employee.level.levid==level.levid?"selected":"" } >${level.title }</option>
												
												
											</c:forEach>
									</select></span>

								</div>

								<div class="form_add">
									<span class="list">税前月薪</span> <span class="list"> <input name="employee.sal" id="employee.sal" type="text" value="${employee.sal }"  />
									</span> 元
								</div>
								<div class="form_add">
									<span class="list">当前状态</span> 
									<input name="employee.status" type="radio" value="0" ${employee.status==0?"checked":"" } />离职
									<input name="employee.status" type="radio" value="1"  ${employee.status==1?"checked":""} />在职
							 
								</div>
								
								<div class="form_add">
									<span class="list">上传照片</span> <span class="list"> <input name="photo" id="photo" type="file" onchange="showPreview(this)" />
									</span> 
								</div>
								<div class="form_add">
									<span class="list">备注</span> <span class="list"> <textarea name="employee.note" id="employee.note" cols="100" rows="5">${employee.note }</textarea></span>
								</div>
								<div class="form_add">
									<input type="submit" value="更新" />&nbsp;&nbsp;&nbsp;<input type="reset" value="重置">
								</div>
								<input type="hidden" id="oldPhoto" name="oldPhoto" value="${employee.photo }"> 
							</form>
						</td>
						<td width="70%">
							<img style="width:160px;height:200px" src="photos/employee/${employee.photo }">
						</td>
					</tr>
				</table>
			</div>


		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	
</script>