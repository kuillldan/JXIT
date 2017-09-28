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
							<h3 class="box-title"><strong>我的申请记录</strong></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover">
								<tr>
									<th style="width:60%">申请标题</th>
									<th style="width:20%">申请日期</th>
									<th style="width:10%">花费总额</th>
									<th style="width:10%">状态</th>
								</tr>
								<tr>
									<td><a href="pages/purchase/purchase_show.jsp">缺少铅笔，需要采购</a></td>
									<td>2017-08-10</td>
									<td>￥309.2</td>
									<td><span class="text-info">等待审核</span></td>
								</tr>
								<tr>
									<td><a href="pages/purchase/purchase_show.jsp">购买看欧洲杯的电视</a></td>
									<td>2017-08-10</td>
									<td>￥39009.2</td>
									<td><span class="text-danger">审核拒绝</span></td>
								</tr>
								<tr>
									<td><a href="pages/purchase/purchase_show.jsp">购买笔记本电脑</a></td>
									<td>2017-08-10</td>
									<td>￥3909.2</td>
									<td><span class="text-success">审核通过</span></td>
								</tr>
							</table>
						</div>
						<!-- /.box-body -->
						<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/>
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
