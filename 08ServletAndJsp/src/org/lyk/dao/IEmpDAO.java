package org.lyk.dao;

import java.util.List;
import java.util.Set;

import org.lyk.vo.Emp;

public interface IEmpDAO extends IDAO<Integer, Emp>
{
	public void doRemoveByDeptno(Integer deptno) throws Exception;
	public Emp findByIdDetails(Integer empno)throws Exception;
	public List<Emp> findAllSplitDetails(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception;
	public List<Emp> findAllSplitDetailsByDeptno(Integer deptno, String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception;
	
	public List<String> findAllPhotosByDeptno(Set<Integer> ids) throws Exception;
}
