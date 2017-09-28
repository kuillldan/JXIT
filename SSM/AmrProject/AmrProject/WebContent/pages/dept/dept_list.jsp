<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="/pages/plugins/include_javascript_head.jsp" />
<script type="text/javascript" src="js/pages/dept/dept_list.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/include_menu_item.jsp" />
		<div class="content-wrapper">
			<!-- 此处编写需要显示的页面 -->
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title"><strong>部门列表</strong></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover">
								<tr>
									<th width="80%">名称</th>
									<th colspan="2">操作</th>
								</tr>
								<tr>
									<td><input type="text" name="title-1" id="title-1" value="管理部" class="form-control"></td>
									<td><button class="btn btn-info" id="editBtn-1"><span class="fa fa-pencil-square"></span> 修改</button></td>
									<td><a class="btn btn-warning" href="pages/groups/groups_list.jsp">查看权限</a></td>
								</tr>
								<tr>
									<td><input type="text" name="title-2" id="title-2" value="人事部" class="form-control"></td>
									<td><button class="btn btn-info" id="editBtn-2"><span class="fa fa-pencil-square"></span> 修改</button></td>
									<td><a class="btn btn-warning" href="pages/groups/groups_list.jsp">查看权限</a></td>
								</tr>
								<tr>
									<td><input type="text" name="title-3" id="title-3" value="行政部" class="form-control"></td>
									<td><button class="btn btn-info" id="editBtn-3"><span class="fa fa-pencil-square"></span> 修改</button></td>
									<td><a class="btn btn-warning" href="pages/groups/groups_list.jsp">查看权限</a></td>
								</tr>
								<tr>
									<td><input type="text" name="title-4" id="title-4" value="市场部" class="form-control"></td>
									<td><button class="btn btn-info" id="editBtn-4"><span class="fa fa-pencil-square"></span> 修改</button></td>
									<td><a class="btn btn-warning" href="pages/groups/groups_list.jsp">查看权限</a></td>
								</tr>
								<tr>
									<td><input type="text" name="title-5" id="title-5" value="财务部" class="form-control"></td>
									<td><button class="btn btn-info" id="editBtn-5"><span class="fa fa-pencil-square"></span> 修改</button></td>
									<td><a class="btn btn-warning" href="pages/groups/groups_list.jsp">查看权限</a></td>
								</tr>
							</table>
							<jsp:include page="/pages/plugins/include_alert.jsp"/> 
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
			</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/pages/plugins/include_javascript_foot.jsp" />
</body>
</html>
