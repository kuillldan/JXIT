package service.back;

import java.util.Map;

import vo.Orders;

public interface IOrdersServiceBack
{
	public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;
	public Orders show(Integer oid) throws Exception;
}
