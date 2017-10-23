package org.lyk.actions;



import java.sql.Date;
import java.util.List;

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
