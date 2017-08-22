package org.lyk.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/emp/*")
public class EmpServlet
{
	@RequestMapping
	public ModelAndView echo(String msg)
	{
		ModelAndView mav = new ModelAndView("/pages/emp/show.jsp");
		System.out.println("*********MSG:" + msg);
		mav.addObject("msg", msg);
		return mav;
	}
}
