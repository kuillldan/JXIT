package DAO;

import java.util.List;
import java.util.Set;

import vo.Goods;

public interface IGoodsDAO extends IDAO<Integer, Goods>
{
	public List<Goods> findAllSplitByStatus(Integer currentPage, Integer lineSize, String column, String keyWord,Integer status) throws Exception;
	public Integer getAllCountByStatus(String column, String keyWord, Integer status) throws Exception;
	public boolean doUpdateStatus(Set<Integer> ids, Integer status) throws Exception; 
	
	public List<Goods> findAllByItem(Integer iid, Integer currentPage, Integer lineSize, String column, String keyWord,Integer status) throws Exception;
	public Integer getAllCountByItem(Integer iid, String column, String keyWord,Integer status) throws Exception;
	public boolean doUpdateBow(Integer gid) throws Exception;
	public List<Goods> findAllByIds(Set<Integer> ids) throws Exception;
}
