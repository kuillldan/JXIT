package org.lyk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lyk.dao.IActionDAO;
import org.lyk.dao.IEmpDAO;
import org.lyk.dao.IGroupsDAO;
import org.lyk.dao.impl.EmpDAOImpl;
import org.lyk.service.IEmpService;
import org.lyk.vo.Action;
import org.lyk.vo.Emp;
import org.lyk.vo.Groups;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements IEmpService
{
	@Resource
	private IEmpDAO empDAOImpl;
	
	@Resource
	private IGroupsDAO groupsDAOIMpl;
	
	@Resource
	private IActionDAO actionDAOImpl;

	@Override
	public boolean login(Emp emp) throws Exception
	{
		if(this.empDAOImpl.findLogin(emp))
		{
			Integer did = emp.getDept().getDid();
			List<Groups> allGroups = this.groupsDAOIMpl.findAllByDept(did);
			emp.getDept().setAllGroups(allGroups);
			for(Groups group : allGroups)
			{
				Integer gid = group.getGid();
				List<Action> allActions = this.actionDAOImpl.findAllByGroups(gid);
				group.setAllActions(allActions);
			}
			
			return true;
		}
		
		return false;
	}

}
