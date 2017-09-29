package org.lyk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lyk.dao.IEmpDAO;
import org.lyk.dao.ILevelDAO;
import org.lyk.dao.impl.EmpDAOImpl;
import org.lyk.dao.impl.LevelDAOImpl;
import org.lyk.service.IAdminService;
import org.lyk.vo.Emp;
import org.lyk.vo.Level;
import org.springframework.stereotype.Service;

@Service()
public class AdminServiceImpl implements IAdminService
{
	@Resource
	private IEmpDAO empDAOImpl;
	
	@Resource
	private ILevelDAO levelDAOImpl;
	
	@Override
	public boolean add(Emp admin) throws Exception
	{
		return this.empDAOImpl.doCreate(admin);
	}

	@Override
	public List<Level> addPre() throws Exception
	{
		return this.levelDAOImpl.findAll();
	}

}
