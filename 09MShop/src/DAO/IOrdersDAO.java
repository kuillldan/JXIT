package DAO;

import java.util.List;

import vo.Orders;

public interface IOrdersDAO extends IDAO<Integer, Orders>
{
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer findLastInsertId() throws Exception;
	public boolean doCreateOrders(Orders vo) throws Exception;
	
	public List<Orders> findAllByMember(String mid,Integer currentPage, Integer lineSize) throws Exception;
	public Integer getAllCountByMember(String mid) throws Exception;
	public Orders findByIdAndMember(String mid, Integer oid) throws Exception;
}
