package org.lyk.service.impl;

import javax.annotation.Resource;

import org.lyk.dao.IDeptDAO;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
 


@Service 
public class DeptServiceImpl implements IDeptService
{
	@Resource(name="deptDAOImpl")
	private IDeptDAO deptDAOImpl;
	@Override
	public boolean insert(Dept dept)
	{ 
		return this.deptDAOImpl.doCreate(dept);
	}
}
