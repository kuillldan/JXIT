package org.lyk.dao;

import org.lyk.vo.Emp;

public interface IEmpDAO extends IDAO<Integer,Emp>
{
	public boolean findLogin(Emp emp);
}
