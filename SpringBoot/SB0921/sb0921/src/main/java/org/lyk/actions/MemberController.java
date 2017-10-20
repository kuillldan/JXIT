package org.lyk.actions;



import java.util.List;

import org.lyk.vo.Member;
import org.springframework.stereotype.Controller;
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
}
