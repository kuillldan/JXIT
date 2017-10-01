package org.lyk.dao.impl;

import java.util.List;

import org.lyk.dao.AbstractDAO;
import org.lyk.dao.IActionDAO;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Action;
import org.springframework.stereotype.Component;

@Component
public class ActionDAOImpl extends AbstractDAO implements IActionDAO
{
	private static final String MAPPING_PREFIX = CommonConstant.MAPPING_PREFIX + "ActionNS.";
	
	
	@Override
	public List<Action> findAllByGroups(Integer gid) throws Exception
	{
		return 
	}

}
