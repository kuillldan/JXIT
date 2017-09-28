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
<script type="text/javascript" src="js/pages/res/res_preget.js"></script> 
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
							<h3 class="box-title"><strong>待购用品</strong></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover">
								<tr>
									<th class="text-center" style="width:10%">
										<input type="checkbox" id="selectAll">
									</th>
									<th class="text-center" style="width:70%"><strong>商品名称</strong></th>
									<th class="text-center" style="width:20%"><strong>购买数量</strong></th>
								</tr>
								<tr>
									<td class="text-center">
										<input type="checkbox" id="rid" name="rid" value="1">
									</td>
									<td class="text-center"><img src="upload/res/nophoto.png" class="img" style="width:30px;"> 记事本</a>
									</td>
									<td class="text-center">
										<button class="btn btn-primary" id="sub-1">-</button>
										<input type="text" id="amount-1" name="amount-1" class="shopcar-form-control" size="4" maxlength="4" value="20">
										<button class="btn btn-primary" id="add-1">+</button> 
									</td>
								</tr>
								<tr> 
									<td class="text-center">
										<input type="checkbox" id="rid" name="goods.gid" value="2">
									</td>
									<td class="text-center"><img src="upload/res/nophoto.png" class="img" style="width:30px;"> 铅笔</a>
									</td>
									<td class="text-center">
										<button class="btn btn-primary" id="sub-2">-</button>
										<input type="text" id="amount-2" name="amount-2" class="shopcar-form-control" size="4" maxlength="4" value="20">
										<button class="btn btn-primary" id="add-2">+</button> 
									</td>
								</tr>
								<tr> 
									<td class="text-center">
										<input type="checkbox" id="rid" name="goods.gid" value="2">
									</td>
									<td class="text-center"><img src="upload/res/nophoto.png" class="img" style="width:30px;"> 橡皮</a>
									</td>
									<td class="text-center">
										<button class="btn btn-primary" id="sub-3">-</button>
										<input type="text" id="amount-3" name="amount-3" class="shopcar-form-control" size="4" maxlength="4" value="20">
										<button class="btn btn-primary" id="add-3">+</button> 
									</td> 
								</tr>
							</table>
							<div>
								<button class="btn btn-info" id="editBtn">修改数量</button>
								<button class="btn btn-danger" id="rmBtn">移出清单</button>
								<a href="pages/res/res_preget.jsp" class="btn btn-primary" id="getBtn">领取申请</a>
							</div>
						</div> 
						<!-- /.box-body -->
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
