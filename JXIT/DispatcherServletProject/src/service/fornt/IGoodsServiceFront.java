package service.fornt;

import java.util.Map;

import vo.Goods;

public interface IGoodsServiceFront
{
	 

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
	
	 
}
