package org.lyk.actions;

import javax.annotation.Resource;

import org.lyk.service.IAdminService;
import org.lyk.service.impl.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/admin/*")
public class AdminAction extends AbstractAction
{
	@Resource
	private IAdminService adminServiceImpl;

	@RequestMapping("addPre")
	public ModelAndView addPre()
	{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(super.getPage("admin.add.jsp"));
		return mav;
	}

	@RequestMapping("add")
	public ModelAndView add()
	{
		return null;
	}
}
