package org.lyk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.lyk.dao.IActionDAO;
import org.lyk.dao.IDeptDAO;
import org.lyk.dao.IEmpDAO;
import org.lyk.dao.IGroupsDAO;
import org.lyk.dao.ILevelDAO;
import org.lyk.dao.impl.DeptDAOImpl;
import org.lyk.dao.impl.EmpDAOImpl;
import org.lyk.dao.impl.LevelDAOImpl;
import org.lyk.enums.SFLAG;
import org.lyk.service.IEmpService;
import org.lyk.vo.Action;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp;
import org.lyk.vo.Groups;
import org.lyk.vo.Level;
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

	@Resource
	private IDeptDAO deptDAOImpl;

	@Resource
	private ILevelDAO levelDAOImpl;

	@Override
	public boolean login(Emp emp) throws Exception
	{
		if (this.empDAOImpl.findLogin(emp))
		{
			Integer did = emp.getDept().getDid();
			List<Groups> allGroups = this.groupsDAOIMpl.findAllByDept(did);
			emp.getDept().setAllGroups(allGroups);
			for (Groups group : allGroups)
			{
				Integer gid = group.getGid();
				List<Action> allActions = this.actionDAOImpl.findAllByGroups(gid);
				group.setAllActions(allActions);
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean insert(Emp emp) throws Exception
	{
		return this.empDAOImpl.doCreate(emp);
	}

	@Override
	public Map<String, Object> insertPre() throws Exception
	{
		Map<String, Object> result = new HashMap<>();
		List<Dept> allDepts = this.deptDAOImpl.listAllDeptBySflag(SFLAG.NORMAL.getValue());
		List<Level> allLevels = this.levelDAOImpl.findAll();
		result.put("allDepts", allDepts);
		result.put("allLevels", allLevels);
		return result;
	}

}
