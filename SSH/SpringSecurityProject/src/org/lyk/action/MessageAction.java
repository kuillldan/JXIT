package org.lyk.action;


import java.util.ArrayList;
import java.util.List;

import org.lyk.vo.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/pages/back/message/*")
public class MessageAction
{
	@RequestMapping("insertPre")
	public ModelAndView insertPre()
	{
		ModelAndView mav = new ModelAndView("back/message/insert");
		return mav;
	}
	
	@RequestMapping("insert")
	public ModelAndView insert(Message msg)
	{
		System.out.println("【增加数据】" + msg);
		ModelAndView mav = new ModelAndView("common/forward");
		mav.addObject("msg","信息添加成功").addObject("url", "/pages/back/message/insertpre.action");
		return mav;
	}
	
	@RequestMapping("list")
	public ModelAndView list()
	{
		List<Message> allMessages = new ArrayList<>();
		for(int i = 1; i <= 10; i++)
		{
			Message vo = new Message();
			vo.setMid(i);
			vo.setTitle("lyk-" + i);
			vo.setContent("cl.l5j.biz");
			allMessages.add(vo);
		}
		
		ModelAndView mav = new ModelAndView("back/message/list");
		mav.addObject("allMessages",allMessages);
		return mav;
	}
	
	@RequestMapping("delete")
	public ModelAndView delete(Integer mid)
	{
		System.out.println("【删除数据】数据编号:" + mid);
		
		ModelAndView mav = new ModelAndView("common/forward");
		mav.addObject("msg", "信息删除成功");
		mav.addObject("url","/pages/back/message/list.action");
		return mav;
	}
}
