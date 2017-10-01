package org.lyk.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
 
import org.lyk.dao.IGroupsDAO; 
import org.lyk.service.IGroupsService;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Action;
import org.lyk.vo.Emp;
import org.lyk.vo.Groups;

public class GroupsService implements IGroupsService
{
	@Resource
	private IGroupsDAO groupsDAOImpl;

	@Override
	public List<Groups> findAllByDept(Integer did) throws Exception
	{
		return this.groupsDAOImpl.findAllByDept(did);
	}

}
