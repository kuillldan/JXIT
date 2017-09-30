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
}
