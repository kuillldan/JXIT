package org.lyk.service.impl;

import javax.annotation.Resource;

import org.lyk.dao.IEmpDAO;
import org.lyk.dao.impl.EmpDAOImpl;
import org.lyk.service.IEmpService;
import org.lyk.vo.Emp;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements IEmpService
{
	@Resource
	private IEmpDAO empDAOImpl;
	
	@Override
	public boolean login(Emp emp) throws Exception
	{
		return this.empDAOImpl.findLogin(emp);
	}
	
}
