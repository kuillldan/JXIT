package service.front;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.Employee;

public interface IEmployeeServiceFront
{
	/**
	 * 增加员工前查询出所有的DEPT JOBS JOBLEVEL信息
	 * @param eid
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertPre() throws Exception;
	
	/**
	 * 更新前查询所有的DEPT JOBS JOBLEVEL信息
	 * @param eid
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updatePre(Integer eid) throws Exception;
	
	/**
	 * 查出部门信息
	 * 查出职位信息
	 * 查出工资等级是否与实际工资相匹配
	 * 判断雇员编号是否存在
	 * 只能增加雇员入职信息 不能直接插入离职员工信息
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Employee employee) throws Exception;
	
	/**
	 * 更新员工员工
	 * @param employee
	 * @return
	 * @throws Exception
	 */
	public boolean update(Employee employee) throws Exception;
	
	/**
	 * 批量离职员工
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean updateOut(List<Integer> ids) throws Exception;
	
	/**
	 * 全部雇员信息列出
	 * @param currentPage
	 * @param pageSize
	 * @param column
	 * @param keyword
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> list(Integer currentPage,Integer pageSize, String column, String keyWord) throws Exception;
	/**
	 * 指定状态雇员信息列出 包括雇员个数
	 * @param currentPage
	 * @param pageSize
	 * @param column
	 * @param keyword
	 * @param status
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> listByStatus(Integer currentPage,Integer pageSize, String column, String keyword, Integer status) throws Exception;
}
