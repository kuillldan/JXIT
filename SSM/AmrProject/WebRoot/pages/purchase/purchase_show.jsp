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
							<h3 class="box-title"><strong>办公用品购买详单</strong></h3>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-1"><label><strong>申请名称：</strong></label></div>
							<div class="col-xs-7">就是买电视，看球啊。</div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-1"><label><strong>申请人：</strong></label></div>
							<div class="col-xs-7">老李</div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-1"><label><strong>购买总额：</strong></label></div>
							<div class="col-xs-7"><span class="text-danger h3">￥8902390.90</span></div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-1"><label><strong>购入商品：</strong></label></div>
							<div class="col-xs-7">
								<div class="box-body table-responsive no-padding">
									<table class="table table-hover">
										<tr>
											<th>名称</th>
											<th>单价</th>
											<th>购买数量</th>
										</tr>
										<tr>
											<td><img src="upload/res/nophoto.png" class="img" style="width:30px;"> 记事本</td>
											<td>1.2</td>
											<td>20</td>
										</tr>
										<tr>
											<td><img src="upload/res/nophoto.png" class="img" style="width:30px;"> 记事本</td>
											<td>1.2</td>
											<td>20</td>
										</tr>
										<tr>
											<td><img src="upload/res/nophoto.png" class="img" style="width:30px;"> 记事本</td>
											<td>1.2</td>
											<td>20</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-1"><label><strong>购买说明：</strong></label></div>
							<div class="col-xs-7">我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买
							我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买我就是要买</div>
						</div> 
						<div class="row">&nbsp;</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-3">
								<a href="pages/purchase/purchase_list.jsp" type="button" class="btn btn-primary btn-lg">审核通过</a>
								<a href="pages/purchase/purchase_list.jsp" type="button" class="btn btn-danger btn-lg">审核拒绝</a>
							</div>  
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
