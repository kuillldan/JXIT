package org.lyk.actions;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;


@Controller
@RequestMapping("/pages/empServlet/*")
public class EmpServlet
{
	@RequestMapping("insert")
	public ModelAndView insert(org.lyk.vo.Emp emp)
	{
		System.out.println(emp);
		return null;
	}
	
	@InitBinder
	public void dateTranslator(WebDataBinder webDataBinder)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
}
