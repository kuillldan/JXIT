package cn.mldn.microboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.lyk.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/*")
public class MemberController
{
	@RequestMapping("getMember")
	public String getMember(ModelMap model) throws ParseException
	{
		Member vo = new Member();
		vo.setMid("2159");
		vo.setName("远奎");
		vo.setGender("男");
		vo.setAge(41);
		vo.setPhoneNumber("132123288473");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		vo.setHireDate(sdf.parse("1987-10-18"));
		
		model.addAttribute("member",vo);
		
		return "member/showMember";
	}
	
	@RequestMapping("showAll")
	public String showAll(ModelMap model) throws ParseException
	{
		Map<String,Member> allMembers = new HashMap<>();
		
		
		for(int i = 0; i < 10; i++)
		{ 
			Member vo1 = new Member();
			vo1.setMid("编号00" + i);
			vo1.setName("远奎");
			vo1.setGender("男");
			vo1.setAge(41 + i);
			vo1.setPhoneNumber("132123288473");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			vo1.setHireDate(sdf.parse("1987-10-18"));
			
			allMembers.put("0" + i,vo1);
		}
		
		model.addAttribute("allMembers", allMembers);
		
		
		
		return "member/showMember";
	}
}
