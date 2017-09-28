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
<script type="text/javascript" src="js/pages/res/res_emp_list.js"></script> 
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
							<h3 class="box-title"><strong>我的领取记录</strong></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover">
								<tr>
									<th class="text-center"><strong>商品名称</strong></th>
									<th class="text-center"><strong>申请时间</strong></th>
									<th class="text-center"><strong>状态</strong></th>
									<th class="text-center"><strong>是否归还？</strong></th>
									<th class="text-center"><strong>操作</strong></th>
								</tr>
								<tr>
									<td class="text-center"><img src="upload/res/nophoto.png" class="img" style="width:30px;"> 记事本</a>
									</td>
									<td class="text-center"><span id="price-1">2017-10-09</span></td>
									<td class="text-center"><span class="text-success">申请通过</span></td>
									<td class="text-center">待归还</td>
									<td class="text-center"><a href="res_back.jsp?rid=1" class="btn btn-primary btn-xs">归还用品</a></td>
								</tr>
								<tr>
									<td class="text-center"><img src="upload/res/nophoto.png" class="img" style="width:30px;"> 记事本</a>
									</td>
									<td class="text-center"><span id="price-1">2017-10-09</span></td>
									<td class="text-center"><span class="text-danger">申请拒绝</span></td>
									<td class="text-center">已归还【2016-09-08】</td>
									<td class="text-center"><a href="res_back.jsp?rid=1" class="btn btn-primary btn-xs">归还用品</a></td>
								</tr>
								<tr>
									<td class="text-center"><img src="upload/res/nophoto.png" class="img" style="width:30px;"> 记事本</a>
									</td>
									<td class="text-center"><span id="price-1">2017-10-09</span></td>
									<td class="text-center"><span class="text-danger">申请拒绝</span></td>
									<td class="text-center">不需要归还</td>
									<td class="text-center">&nbsp;</td>
								</tr>
							</table>
						</div>
						<!-- /.box-body -->
						<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/>
						<jsp:include page="/pages/plugins/include_alert.jsp"/>
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
