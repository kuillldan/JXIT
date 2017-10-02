package org.lyk.actions;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.lyk.constant.*;
import org.lyk.enums.ActionIDEnum;
import org.lyk.service.IGroupsService;
import org.lyk.vo.*;
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
			if(super.isAuthcated(request, ActionIDEnum.GROUPS_LIST.getValue()))
			{
				List<Groups> allGroups = this.groupsServiceImpl.findAllByDept(did);
				request.setAttribute(CommonConstant.ALLITEMS, allGroups);
				mav.setViewName(super.getPage(PageConstant.GROUPS_LIST_JSP));
			}
			else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
			}
		}
		catch(Exception e)
		{
			super.setSystemError(mav, "查询部门权限组信息", e);
		}
		return mav;
	}
}
