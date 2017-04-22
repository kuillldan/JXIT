package service.back;

import java.util.List;
import java.util.Set;

import dao.impl.DeptDAOImpl;
import vo.Dept;

public interface IDeptServiceBack
{
	/**
	 * 创建部门的时候需要确认部门名称是否存在
	 * 部门创建时候，部门人数需要设置为0
	 * @param dept
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Dept dept) throws Exception;
	
	
	public boolean delete(Set<Integer> ids) throws Exception;
	public boolean update(Dept dept) throws Exception;
	
	public List<Dept> list() throws Exception;
}
