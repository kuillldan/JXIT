package org.lyk.dao;

import java.util.List;

import org.lyk.vo.Dept;

public interface IDeptDAO
{
	public boolean insert(Dept dept) throws Exception;
	public List<Dept> findAll() throws Exception;
}
