package DAO;

import java.util.List;

import vo.Details;

public interface IDetailsDAO extends IDAO<Integer, Details>
{
	/**
	 * 批量创建定单详情记录
	 * @param vos
	 * @return
	 * @throws Exception
	 */
	public boolean doCreateBatch(List<Details> vos) throws Exception;
	
	public List<Details> findAllByOrders(Integer oid) throws Exception;
}
