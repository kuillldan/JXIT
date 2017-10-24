package org.lyk.actions;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.lyk.services.IMessageService;
import org.lyk.services.impl.MessageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Hello/*")
public class Hello extends AbstractController
{
	@Resource
	private DataSource dataSource;
	
	@Resource
	private IMessageService messageServiceImpl;
	
	@RequestMapping("welcome")
	public String welcome(String name)
	{// spring-boot:run
		return super.getMessage("welcom.msg", name);
	}

	@RequestMapping("showUrl")
	public String showUrl()
	{
		return super.getMessage("save.path");
	}
	
	@RequestMapping("showMsg")
	public String showMsg()
	{
		return this.messageServiceImpl.getInto();
	}
	
	@RequestMapping("testDataSource")
	@ResponseBody
	public String testDataSource()
	{
		try
		{
			System.out.println(this.dataSource.getConnection());
			return "测试成功";
		}
		catch(Exception e)
		{
			return "测试失败";
		}
	}
	
}
