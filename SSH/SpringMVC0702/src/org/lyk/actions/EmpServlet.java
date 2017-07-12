package org.lyk.actions;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.lyk.utils.AbstractServlet;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;


@Controller
@RequestMapping("/pages/empServlet/*")
public class EmpServlet extends AbstractServlet
{
	@RequestMapping("insert")
	public ModelAndView insert(org.lyk.vo.Emp emp)
	{
		System.out.println(emp);
		return null;
	}

	@Override
	protected String getUploadFolder()
	{
		// TODO Auto-generated method stub
		return null;
	} 
}
