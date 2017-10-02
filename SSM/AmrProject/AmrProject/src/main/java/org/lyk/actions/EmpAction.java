package org.lyk.actions;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.lyk.constant.CommonConstant;
import org.lyk.constant.MessageConstant;
import org.lyk.constant.PageConstant;
import org.lyk.enums.ActionIDEnum;
import org.lyk.service.IEmpService;
import org.lyk.vo.Dept;
import org.lyk.vo.Level;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
				String msg = super.getMessage(MessageConstant.NOT_AUTHORIZED);
				super.forwardToErrorPage(mav, msg);
				CommonConstant.LOGGER.info(msg);
			}
		} catch (Exception e)
		{
			String msg = super.getMessage(MessageConstant.SYSTEM_ERROR, "预加载雇员信息");
			super.setSystemError(mav, msg, e);
			CommonConstant.LOGGER.error(msg);
			CommonConstant.LOGGER.error(e.getMessage(), e);
		}
		return mav;
	}
}
