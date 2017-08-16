package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import vo.Dept;

public interface IDeptService
{
	public boolean insert(Dept dept) throws Exception;
	public boolean remove(Set<Integer> ids) throws Exception;
	public Dept updatePre(Integer id) throws Exception;
	public boolean update(Dept dept) throws Exception;
	public Dept findById(Integer deptno) throws Exception;
	public List<Dept> list() throws Exception;
}
