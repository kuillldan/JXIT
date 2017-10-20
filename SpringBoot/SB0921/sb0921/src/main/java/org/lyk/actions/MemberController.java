package org.lyk.actions;

import java.lang.reflect.Member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Object add(Member vo)
	{
		return vo;
	}
}
