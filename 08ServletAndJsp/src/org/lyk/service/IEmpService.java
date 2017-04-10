package org.lyk.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.lyk.vo.Emp;

public interface IEmpService
{
	public boolean insert(Emp emp) throws Exception;
	public boolean delete(Set<Integer> empno) throws Exception;
	public Emp findById(Integer empno) throws Exception;
	public Map<String, Object> listBySplit(Integer currentPage, Integer lineSize, String column,String keyWord) throws Exception;
	public List<Emp> listAll() throws Exception;
	public boolean update(Emp emp) throws Exception;
	
	/**
	 * 返回雇员的详细信息
	 * @param empno
	 * @return
	 * @throws Exception
	 */
	public Emp show(Integer empno) throws Exception;
	
	/**
	 * 增加雇员前的准备信息，要返回所有的部门和雇员信息
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertPre() throws Exception;
	
	
	/**
	 * 返回雇员信息 所有雇员信息 所有部门信息
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updatePre(Integer empno) throws Exception;
	
	/**
	 * 列出雇员的详细信息 列出符合条件的所有雇员人数（用于分页操作）
	 * @param cloumn
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> listDetails(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception;
}