package org.lyk.service;

import java.util.Map;

import org.lyk.vo.Emp;

public interface IEmpService
{
	public boolean login(Emp emp) throws Exception;
	public boolean insert(Emp emp)throws Exception;
	public Map<String, Object> insertPre()throws Exception;
}
