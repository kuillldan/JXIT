package org.lyk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lyk.dao.IActionDAO;
import org.lyk.dao.impl.ActionDAOImpl;
import org.lyk.service.IActionService;
import org.lyk.vo.Action;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements IActionService
{
	@Resource
	private IActionDAO actionDAOImpl;
	
	@Override
	public List<Action> listAllActionsByGroupsId(Integer gid) throws Exception
	{
		return this.actionDAOImpl.findAllByGroups(gid);
	}

}
