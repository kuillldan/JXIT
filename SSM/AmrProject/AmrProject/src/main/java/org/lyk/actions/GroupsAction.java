package org.lyk.actions;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
 
import org.lyk.service.IGroupsService;
import org.lyk.utils.CommonConstant;
import org.lyk.vo.Action;
import org.lyk.vo.Groups;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/groups/*")
public class GroupsAction extends AbstractAction
{
	@Resource
	private IGroupsService groupsServiceImpl;
	
	@RequestMapping("list")
	public ModelAndView listByDept(HttpServletRequest request,Integer did)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if(super.isAuthcated(request, 6))
			{
				List<Groups> allGroups = this.groupsServiceImpl.findAllByDept(did);
				request.setAttribute(CommonConstant.ALLITEMS, allGroups);
				mav.setViewName(super.getPage(CommonConstant.GROUPS_LIST_JSP));
			}
			else
			{
				String msg = super.getMessage(CommonConstant.NOT_AUTHORIZED);
				super.setForwardMessageAndUrl(mav, msg, super.getPage(CommonConstant.DEPT_LIST_ACTION));
				CommonConstant.LOGGER.info(msg);
			}
		}
		catch(Exception e)
		{
			String msg = "查询部门权限组信息发生系统异常";
			super.setSystemError(mav, msg, e);
			CommonConstant.LOGGER.error(msg);
			CommonConstant.LOGGER.error(e.getMessage(),e);
		}
		return mav;
	}
}
