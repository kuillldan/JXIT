package org.lyk.actions;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lyk.constant.CommonConstant;
import org.lyk.constant.MessageConstant;
import org.lyk.constant.PageConstant;
import org.lyk.enums.ActionIDEnum;
import org.lyk.service.IEmpService;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp;
import org.lyk.vo.Level;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/emp/*")
public class EmpAction extends AbstractAction
{
	@Resource
	private IEmpService empServiceImpl;

	@RequestMapping("addPre")
	public ModelAndView addPre(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, ActionIDEnum.EMP_ADD_PRE.getValue()))
			{
				Map<String, Object> result = this.empServiceImpl.insertPre();
				List<Dept> allDepts = (List<Dept>) result.get("allDepts");
				List<Level> allLevels = (List<Level>) result.get("allLevels");
				mav.addObject("allDepts", allDepts);
				mav.addObject("allLevels", allLevels);
				mav.setViewName(super.getPage(PageConstant.EMP_ADD_JSP));
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "预加载雇员信息", e);
		}
		return mav;
	}

	@RequestMapping("add")
	public ModelAndView add(HttpServletRequest request, Emp emp, MultipartFile pic)
	{
		CommonConstant.LOGGER.debug("****************EmpAction.add执行");
		CommonConstant.LOGGER.debug(emp.toString());
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, ActionIDEnum.EMP_ADD.getValue()))
			{
				if (this.empServiceImpl.insert(emp))
				{
					super.setForwardMessageAndUrl(mav, "增加雇员成功", super.getPage(PageConstant.EMP_ADD_PRE_ACTION));
				} else
				{
					super.setForwardMessageAndUrl(mav, "增加雇员失败", super.getPage(PageConstant.EMP_ADD_PRE_ACTION));
				}
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "增加雇员信息", e);
		}
		return mav;
	}

	@RequestMapping("checkEid")
	public ModelAndView checkEid(HttpServletRequest request, HttpServletResponse response, Integer eid)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, ActionIDEnum.EMP_ADD_PRE.getValue(), ActionIDEnum.EMP_ADD.getValue()))
			{
				response.getWriter().print(this.empServiceImpl.checkEid(eid));
				return null;
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
				return mav;
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "检查员工重复性", e);
			return mav;
		}
	}

	@RequestMapping("checkSalary")
	public ModelAndView checkSalary(HttpServletRequest request, HttpServletResponse response, Integer lid,
			Double salary)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, ActionIDEnum.EMP_ADD_PRE.getValue(), ActionIDEnum.EMP_ADD.getValue()))
			{
				response.getWriter().print(this.empServiceImpl.checkSalary(salary, lid));
				return null;
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
				return mav;
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "检查工资水平", e);
			return mav;
		}
	}
}
