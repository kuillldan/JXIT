<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HR人力资源管理系统-雇员信息添加</title>
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
		<div class="right_con">
			<form>
				<div class="form_add">
					<table width="100%" border="0">
						<tr>
							<td width="66" bgcolor="#EEEEEE">编号</td>
							<td width="119" bgcolor="#EEEEEE">姓名</td>
							<td width="66" bgcolor="#EEEEEE">性别</td>
							<td width="84" bgcolor="#EEEEEE">年龄</td>
							<td width="100" bgcolor="#EEEEEE">学历</td>
							<td width="120" bgcolor="#EEEEEE">部门</td>
							<td width="111" bgcolor="#EEEEEE">职位</td>
							<td width="73" bgcolor="#EEEEEE">工资</td>
							<td width="76" bgcolor="#EEEEEE">入职时间</td>
							<td width="37" bgcolor="#EEEEEE">在职</td>
							<td width="51" bgcolor="#EEEEEE">编辑</td>
						</tr>
						<tr>
							<td>1</td>
							<td>xxx</td>
							<td>男</td>
							<td>25</td>
							<td>本科</td>
							<td>开发部门</td>
							<td>程序员</td>
							<td>8000元</td>
							<td>2014-1-1</td>
							<td>是</td>
							<td><a href="Editemp.html">编辑</a></td>
						</tr>
						<tr>
							<td>2</td>
							<td>xxx</td>
							<td>男</td>
							<td>25</td>
							<td>本科</td>
							<td>开发部门</td>
							<td>程序员</td>
							<td>8000元</td>
							<td>2014-1-1</td>
							<td>是</td>
							<td><a href="Editemp.html">编辑</a></td>
						</tr>
						<tr>
							<td>3</td>
							<td>xxx</td>
							<td>男</td>
							<td>25</td>
							<td>本科</td>
							<td>开发部门</td>
							<td>程序员</td>
							<td>8000元</td>
							<td>2014-1-1</td>
							<td>是</td>
							<td><a href="Editemp.html">编辑</a></td>
						</tr>
						<tr>
							<td>4</td>
							<td>xxx</td>
							<td>男</td>
							<td>25</td>
							<td>本科</td>
							<td>开发部门</td>
							<td>程序员</td>
							<td>8000元</td>
							<td>2014-1-1</td>
							<td>是</td>
							<td><a href="Editemp.html">编辑</a></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>



				</div>



			</form>
		</div>


	</div>
	</div>
</body>
</html>
<script type="text/javascript">
	
</script>