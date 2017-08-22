package service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import dao.IDeptDAO;
import service.IDeptService;
import vo.Dept;

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
