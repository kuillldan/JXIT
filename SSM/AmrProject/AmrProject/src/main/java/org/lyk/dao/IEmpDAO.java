package org.lyk.dao;

import java.util.List;

import org.lyk.vo.Emp;

public interface IEmpDAO extends IDAO<Integer, Emp>
{
	public boolean findLogin(Emp emp);

	public List<Emp> findAllAdminSplit(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception;

	public Integer findAllAdminCount(String column, String keyWord) throws Exception;
	public List<Emp> findAllEmpSplit(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception;

	public Integer findAllEmpCount(String column, String keyWord) throws Exception;
}
