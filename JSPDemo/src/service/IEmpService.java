package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vo.Emp;

public interface IEmpService
{
	public boolean insert(Emp emp) throws SQLException;
	public boolean update(Emp emp) throws SQLException;
	public Emp updatePre(Integer empno) throws SQLException;
	public boolean delete(Set<Integer> ids) throws SQLException;
	public List<Emp> list() throws SQLException;
	public Map<String, Object> listSplit(Integer currentPage,Integer lineSize) throws SQLException;
	public Emp show(Integer empno) throws SQLException;
}
