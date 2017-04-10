package service.back;

import java.util.List;
import java.util.Map;
import java.util.Set;

import vo.Goods;

public interface IGoodsServiceBack
{
	/**
	 * 返回所有的Item
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertPre()throws Exception;
	
	/**
	 * 增加一条商品信息
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Goods vo) throws Exception;
	
	public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;
	public Map<String, Object> listByStatus(Integer currentPage, Integer lineSize, String column, String keyWord,Integer status) throws Exception;
	
	public boolean updateUp(Set<Integer> ids) throws Exception;
	public boolean updateDown(Set<Integer> ids) throws Exception;
	public boolean updateDelete(Set<Integer> ids) throws Exception;
	
	public Map<String, Object> updatePre(Integer gid) throws Exception;
	public boolean update(Goods vo ) throws Exception;
	public boolean deleteAll(Set<Integer> ids) throws Exception; 
}