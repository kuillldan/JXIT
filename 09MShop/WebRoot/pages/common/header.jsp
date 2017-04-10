<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.kuillldan.cn"%>


<div id="divHeader">
	<a href="">商城首页</a>
	<a href="pages/front/goods/GoodsServletFront/list">浏览商品</a> 
	<a href="pages/front/shopCart/ShopCarServletFront/list">我的购物车</a>
	<c:if test="${mid==null}">
		<a href="pages/member_login.jsp">登录</a>
		<a href="pages/member_regist.jsp">注册</a>
	</c:if>
	<c:if test="${mid!=null }">
		<a href="">${mid }</a>
		<a href="pages/MemberServletFront/logout">退出登录</a>		
		<img alt="" style="width:40px;height:40px" src="photos/${photo}">
		
	</c:if>
	
</div>