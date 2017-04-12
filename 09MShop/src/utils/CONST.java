package utils;

public class CONST
{ 
	public static final String pageMemberLogin = "/pages/member_login.jsp";
	public static final String pageAdminLogin = "/pages/back/login.jsp";
	public static final String pageForward = "/pages/common/forward.jsp";
	public static final String pageMemberIndex = "/index.jsp";
	public static final String pageAdminIndex = "/pages/back/admin/index.jsp";
	public static final String pageRegist = "/pages/member_regist.jsp";
	public static final String noPhoto = "nophoto.jpg";
	public static final String pageError = "/pages/common/error.jsp";
	public static final String pageMemberList = "/pages/back/admin/member/list.jsp";
	public static final String pageMemberListByStatus = "/pages/back/admin/member/listByStatus.jsp";
	public static final String pageMemberShow = "/pages/back/admin/member/show.jsp";
	public static final String memberServletBackURL = "/pages/back/admin/member/MemberServletBack/";
	public static final String itemListURL = "/pages/back/admin/item/item_list.jsp";
	public static final String pageItemInsertJSP = "/pages/back/admin/item/item_insert.jsp";
	public static final String pageItemListURL = "/pages/back/admin/item/ItemServletBack/list";
	public static final String pageGoodsInsertJSP = "/pages/back/admin/goods/goods_insert.jsp";
	public static final String pageGoodsInsertPreURL = "/pages/back/admin/goods/GoodsServletBack/insertPre";
	public static final String pageGoodsBackListJSP = "/pages/back/admin/goods/goods_list.jsp";
	public static final String pageGoodsFrontListJSP = "/pages/front/goods/goods_list.jsp";
	public static final String pageGoodsUpdateJSP = "/pages/back/admin/goods/goods_update.jsp";
	public static final String pageGoodsServletURL = "/pages/back/admin/goods/GoodsServletBack/";
	public static final String pageGoodsShowJSP = "/pages/front/goods/goods_show.jsp";
	public static final String pageGoodsServletFontURL = "/pages/front/goods/GoodsServletFront/";
	public static final String pageCartListJSP = "/pages/front/shopCart/cart_list.jsp";
	public static final String pageCartServletListURL = "/pages/front/shopCart/ShopCarServletFront/list";
	public static final String pageMemberFrontShowJSP = "/pages/front/member/member_show.jsp";
	
	public enum MemberStatus
	{
		LOCKED,ACTIVED,PENDING
	}
  
	public enum GoodsStatus
	{
		DOWN,UP,DELETED
	}
 
}
