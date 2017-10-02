package org.lyk.actions;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lyk.constant.CommonConstant;
import org.lyk.constant.MessageConstant;
import org.lyk.constant.PageConstant;
import org.lyk.enums.ActionIDEnum;
import org.lyk.service.IDeptService;
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
			if (super.isAuthcated(request, ActionIDEnum.DEPT_LIST.getValue()))
			{
				List<Dept> allDepts = this.deptServiceImpl.list();
				request.setAttribute(CommonConstant.ALLITEMS, allDepts);
				mav.setViewName(super.getPage("dept.list.jsp"));
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "查询部门数据", e);
		}
		return mav;
	}

	@RequestMapping("update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, Dept dept)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, ActionIDEnum.DEPT_EDIT.getValue()))
			{
				response.getWriter().print(this.deptServiceImpl.updateTitleByDid(dept));
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "修改部门数据", e);
			try
			{
				response.getWriter().print(false);
			} catch (IOException e1)
			{
				CommonConstant.LOGGER.error(e1.getMessage(), e1);
			}
		}
		return null;
	}
}
