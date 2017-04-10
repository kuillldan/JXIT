package service.front;

import java.util.Map;

import vo.Goods;

public interface IGoodsServiceFront
{
	/**
	 * 根据商品分类查询出对应状态的所有商品（需要分页）
	 * 
	 * @param iid
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord 
	 * @return allItems allGoods allGoodsCount
	 * @throws Exception
	 */
	public Map<String, Object> listByItem(Integer iid, Integer currentPage, Integer lineSize, String column,
			String keyWord ) throws Exception;

	/**
	 * 
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord ) throws Exception;
	
	
	public Goods show(Integer gid) throws Exception;
}
