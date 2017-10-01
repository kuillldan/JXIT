package org.lyk.actions;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.lyk.service.IActionService;
import org.lyk.service.impl.ActionServiceImpl;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Action;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/action/*")
public class ActionAction extends AbstractAction
{
	@Resource
	private IActionService actionServiceImpl;

	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request, Integer gid)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, 6))
			{
				List<Action> allActions = this.actionServiceImpl.listAllActionsByGroupsId(gid);
				request.setAttribute(CommonConstant.ALLITEMS, allActions);
				mav.setViewName(super.getPage(CommonConstant.ACTION_LIST_JSP));
			} else
			{
				String msg = super.getMessage(CommonConstant.NOT_AUTHORIZED);
				CommonConstant.LOGGER.info(msg);
				super.setForwardMessageAndUrl(mav, msg, CommonConstant.ERROR_JSP);
			}
		} catch (Exception e)
		{
			String msg = super.getMessage(CommonConstant.SYSTEM_ERROR_WHEN_SEARCHING, "权限");
			super.setSystemError(mav, msg, e);
			CommonConstant.LOGGER.error(msg);
			CommonConstant.LOGGER.error(e.getMessage(), e);
		}
		return mav;
	}
}
