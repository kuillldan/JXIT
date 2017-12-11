package org.lyk.controller;

import java.util.UUID;

import org.lyk.dao.MemberDAO;
import org.lyk.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/member/*")
public class MemberController
{
	@Autowired
	private MemberDAO memberDAO;
	
	@RequestMapping("demo")
	public Object demo()
	{
		return "hello JPA";
	}
	
	
	@RequestMapping("add")
	public Object add(Member member)
	{
		member.setSysId(UUID.randomUUID().toString());
		System.out.println("保存之前:" + member);
		Member memberSaved = this.memberDAO.save(member);
		return true;
	}
	
	@RequestMapping("findById")
	public Object findById(String sysId)
	{
		try
		{
			Member member = this.memberDAO.getOne(sysId); 
			
			if(member == null)
				return "data was not found";
			else
				return JSON.toJSONString(member);
		}
		catch(Exception e)
		{
			System.out.println("******ops 发生了异常");
			e.printStackTrace();
			return "exception";
		}
	}
	
	@RequestMapping("list")
	public Object list(String name)
	{
		return JSON.toJSONString(this.memberDAO.findByNameLike("%"+name+"%"));
	} 
	
	@RequestMapping("count")
	public String count()
	{
		return JSON.toJSONString(this.memberDAO.count());
	}
	
	@RequestMapping("delete")
	public Object delete(String sysId)
	{
		try
		{
			this.memberDAO.delete(sysId);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();;
			return false;
		}
	}
	
	@RequestMapping("exist")
	public Object exist(String sysId)
	{
		try
		{
			return this.memberDAO.exists(sysId);
		}
		catch(Exception e)
		{
			return "exception";
		}
	}
}
