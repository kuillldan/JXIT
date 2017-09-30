package org.lyk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public Map<String, Object> getAllAdmin(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception
	{
		Map<String, Object> map = new HashMap<>();
		List<Emp> allItems = this.empDAOImpl.findAllAdmin(column, keyWord, currentPage, lineSize);
		Integer allRecorders = this.empDAOImpl.findAllAdminCount(column, keyWord);
		map.put("allItems", allItems);
		map.put("allRecorders", allRecorders);
		return map;
	}

}
