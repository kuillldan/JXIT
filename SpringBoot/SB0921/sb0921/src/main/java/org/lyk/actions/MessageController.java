package org.lyk.actions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message/*")
public class MessageController
{
	@RequestMapping("show")
	public String show(String mid, Model model)
	{
		model.addAttribute("url", "www.t66y.com");
		model.addAttribute("mid", mid);
		model.addAttribute("msg", "今天是周末");
		return "message/message_show";
	}
	
	@ResponseBody
	@RequestMapping("getInfo")
	public String getInfo()
	{
		return "这个好像不管用";
	}
}
