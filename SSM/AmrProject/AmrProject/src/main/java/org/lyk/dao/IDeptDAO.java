package org.lyk.dao;

import java.util.List;

import org.lyk.vo.Dept;

public interface IDeptDAO extends IDAO<Integer, Dept>
{
	public List<Dept> listAllDeptBySflag(Integer sflag) throws Exception;
}
