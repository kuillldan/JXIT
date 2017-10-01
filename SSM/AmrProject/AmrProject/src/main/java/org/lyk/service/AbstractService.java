package org.lyk.service;

import javax.annotation.Resource;

import org.lyk.dao.IDeptDAO;
import org.lyk.dao.IGroupsDAO;
import org.lyk.service.impl.DeptServiceImpl;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Emp;

public abstract class AbstractService
{
	@Resource
	private IDeptDAO deptDAOImpl;
	
	@Resource
	private IGroupsDAO groupsDAO;
	
	public boolean isAuthorized(Integer actid,Emp emp)
	{
		try
		{
			Integer did = emp.getDept().getDid();
			
			return false;
		}
		catch(Exception e)
		{
			CommonConstant.LOGGER.error("检查用户权限发生异常");
			return false;
		}
	}
}
