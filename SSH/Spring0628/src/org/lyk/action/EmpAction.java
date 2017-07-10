package org.lyk.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/pages/emp/*")
public class EmpAction
{
	public ModelAndView echo(String msg)
	{
		ModelAndView mav = new ModelAndView("/pages/emp/show.jsp");
		
		mav.addObject("msg", msg.toUpperCase());
		return mav;
	}
}
