package org.lyk.service;

import java.util.List;
import java.util.Map;

import org.lyk.vo.Emp;
import org.lyk.vo.Level;

public interface IAdminService
{
	public boolean add(Emp admin) throws Exception;

	public List<Level> addPre() throws Exception;

	public Map<String, Object> getAllAdmin(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception;

	/**
	 * 检查数据库中是否有eid对应的员工信息存在
	 * 
	 * @param eid
	 * @return 返回true，说明校验员工存在通过(数据库中没有要增加的EID)。
	 *         返回false，说明校验员工存在没有通过(数据库中已经有改员工数据)
	 * @throws Exception
	 */
	public boolean checkEid(Integer eid) throws Exception;

	public boolean checkSalary(Double salary, Integer lid) throws Exception;
}
