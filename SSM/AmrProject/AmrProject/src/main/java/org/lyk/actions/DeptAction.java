package org.lyk.actions;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.lyk.service.IDeptService;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/dept/*")
public class DeptAction extends AbstractAction
{
	@Resource
	private IDeptService deptServiceImpl;

	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, 4))
			{
				List<Dept> allDepts = this.deptServiceImpl.list(4);
				request.setAttribute(CommonConstant.ALLITEMS, allDepts);
				mav.setViewName(super.getPage("dept.list.jsp"));
			} else
			{
				String msg = super.getMessage("not.authorized");
				mav.setViewName("error.jsp");
				CommonConstant.LOGGER.info(msg);
			}
		} catch (Exception e)
		{
			String msg = "查询部门数据发生系统异常";
			super.setSystemError(mav, msg, e);
			CommonConstant.LOGGER.info(msg);
			CommonConstant.LOGGER.error(e.getMessage(), e);
		}
		return mav;
	}
}
