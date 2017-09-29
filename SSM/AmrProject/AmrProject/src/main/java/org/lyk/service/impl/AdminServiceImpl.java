package org.lyk.service.impl;

import javax.annotation.Resource;

import org.lyk.dao.IEmpDAO;
import org.lyk.dao.impl.EmpDAOImpl;
import org.lyk.service.IAdminService;
import org.lyk.vo.Emp;
import org.springframework.stereotype.Service;

@Service()
public class AdminServiceImpl implements IAdminService
{
	@Resource
	private IEmpDAO empDAOImpl;
	
	@Override
	public boolean add(Emp admin) throws Exception
	{
		return this.empDAOImpl.doCreate(admin);
	}

}
