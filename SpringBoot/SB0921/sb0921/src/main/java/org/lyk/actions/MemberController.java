package org.lyk.actions;



import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.lyk.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.joran.conditional.ElseAction;

@Controller
public class MemberController extends AbstractController
{
	@RequestMapping("/addPre")
	public String addPre()
	{
		return "member_add";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(Member vo,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			System.out.println("member数据接收发生异常");
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for(ObjectError oe : allErrors)
			{
				System.out.println(oe.getCode() + ":" + oe.getDefaultMessage());
			}
			
			return allErrors;
		}
		return vo;
	}
	
	@RequestMapping("/showMemberList")
	public String showMemberList(Model model)
	{
		Member member1 = new Member();
		member1.setAge(30);
		member1.setBirthday(new Date(System.currentTimeMillis()));
		member1.setMid("21591923");
		member1.setSalary(8888.2);
		
		Member member2 = new Member();
		member2.setAge(32);
		member2.setBirthday(new Date(System.currentTimeMillis()));
		member2.setMid("6748368");
		member2.setSalary(8576.2);
		
		List<Member> allItems = new ArrayList<>();
		allItems.add(member1);
		allItems.add(member2);
		model.addAttribute("allItems", allItems);
		
		return "show_member";
	}
	
	@RequestMapping("/showMemberMap")
	public String showMemberMap(Model model)
	{
		Member member1 = new Member();
		member1.setAge(30);
		member1.setBirthday(new Date(System.currentTimeMillis()));
		member1.setMid("21591923");
		member1.setSalary(8888.2);
		
		Member member2 = new Member();
		member2.setAge(32);
		member2.setBirthday(new Date(System.currentTimeMillis()));
		member2.setMid("6748368");
		member2.setSalary(8576.2);
		
		Map<String,Member> allItems = new HashMap<>();
		allItems.put("member1", member1);
		allItems.put("member2", member2);
		model.addAttribute("allItems", allItems);
		
		return "show_member";
	}
	
	@RequestMapping("/showMember")
	public String showMember(Model model)
	{
		Member member = new Member();
		member.setAge(30);
		member.setBirthday(new Date(System.currentTimeMillis()));
		member.setMid("21591923");
		member.setSalary(8888.2);
		model.addAttribute("member", member);
		return "show_member";
	}
	
	@RequestMapping("/showInner")
	public String showInner(HttpServletRequest request)
	{
		request.setAttribute("requestMessage", "hello request");
		request.getSession().setAttribute("sessionMessage", "hello session");
		request.getServletContext().setAttribute("applicationMessage", "hello application");
		
		return "show_inner";
	}
}
