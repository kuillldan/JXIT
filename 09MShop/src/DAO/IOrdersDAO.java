package DAO;

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
}
