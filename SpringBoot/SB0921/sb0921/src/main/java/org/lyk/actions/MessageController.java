package org.lyk.actions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message/*")
public class MessageController
{
	@RequestMapping("show")
	public String show(String mid, Model model)
	{
		model.addAttribute("url", "www.t66y.com");
		model.addAttribute("mid", mid);
		return "message/message_show";
	}
}
