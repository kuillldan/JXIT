package org.lyk.actions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.lyk.services.IDeptService;
import org.lyk.services.impl.DeptServiceImpl;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javassist.expr.NewArray;

@RequestMapping("/dept/*")
@Controller
public class DeptController
{
	@Resource
	private IDeptService deptServiceImpl;
	
	@RequestMapping("list")
	@ResponseBody
	public Object list()
	{
		try
		{
			return this.deptServiceImpl.list();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e;
		}
	}
	
	@ResponseBody
	@RequestMapping("insert")
	public Object insert()
	{
		try
		{
			Dept dept1 = new Dept();
			dept1.setDeptno(41);
			dept1.setDname("软件部");
			dept1.setLoc("重庆");
			
			Dept dept2 = new Dept();
			dept2.setDeptno(42);
			dept2.setDname("软件部");
			dept2.setLoc("重庆");
			
			List<Dept> allDepts = new ArrayList<>();
			allDepts.add(dept1);
			allDepts.add(dept2);
			
			this.deptServiceImpl.doCreate(allDepts);
			System.out.println("插入成功");
			return "插入成功";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("插入失败");
			return "插入失败";
		}
	} 
}
