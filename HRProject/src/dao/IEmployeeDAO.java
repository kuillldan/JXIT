package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import vo.Employee;

public interface IEmployeeDAO extends IDAO<Integer, Employee> 
{
	/**
	 * 更新雇员状态（在职、离职）
	 * @param ids
	 * @param outdate
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateStatus(List<Integer> ids, Date outdate) throws Exception;
	/**
	 * 根据雇员状态分页查询雇员
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @param status
	 * @return
	 */
	public List<Employee> findAllSplitByStatus(Integer currentPage, Integer lineSize, String column, String keyWord, Integer status) throws SQLException;
	
	public Integer getAllCountByStatus(String column, String keyWord, Integer status) throws Exception;
}
