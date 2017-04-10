package org.lyk.service;

import java.util.List;
import java.util.Set;

import org.lyk.vo.Dept;

public interface IDeptService
{
	public boolean insert(Dept vo) throws Exception;
	public boolean update(Dept vo) throws Exception;
	public boolean delete(Set<Integer> ids) throws Exception;
	public List<Dept> list() throws Exception;
	public Dept updatePre(Integer id) throws Exception;
	public List<Dept> listAllDetails()throws Exception;
	public Dept show(Integer deptno, String column,String keyWord,Integer currentPage,Integer lineSize)throws Exception;
}
