package org.lyk.actions;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.lyk.constant.CommonConstant;
import org.lyk.constant.MessageConstant;
import org.lyk.constant.PageConstant;
import org.lyk.enums.ActionIDEnum;
import org.lyk.service.IActionService;
import org.lyk.service.impl.ActionServiceImpl;
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
			if (super.isAuthcated(request, ActionIDEnum.GROUPS_LIST.getValue()))
			{
				List<Action> allActions = this.actionServiceImpl.listAllActionsByGroupsId(gid);
				request.setAttribute(CommonConstant.ALLITEMS, allActions);
				mav.setViewName(super.getPage(PageConstant.ACTION_LIST_JSP));
			} else
			{
				String msg = super.getMessage(MessageConstant.NOT_AUTHORIZED);
				CommonConstant.LOGGER.info(msg);
				super.setForwardMessageAndUrl(mav, msg, PageConstant.ERROR_JSP);
			}
		} catch (Exception e)
		{
			String msg = super.getMessage(MessageConstant.SYSTEM_ERROR, "权限");
			super.setSystemError(mav, msg, e);
			CommonConstant.LOGGER.error(msg);
			CommonConstant.LOGGER.error(e.getMessage(), e);
		}
		return mav;
	}
}
