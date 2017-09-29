package org.lyk.actions;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lyk.service.IAdminService;
import org.lyk.service.impl.AdminServiceImpl;
import org.lyk.utils.CommonConstant;
import org.lyk.utils.MD5Code;
import org.lyk.utils.StringUtils;
import org.lyk.vo.Action;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp;
import org.lyk.vo.Groups;
import org.lyk.vo.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/admin/*")
public class AdminAction extends AbstractAction
{
	@Resource
	private IAdminService adminServiceImpl;

	@RequestMapping("addPre")
	public ModelAndView addPre(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (this.isAuthcated(request.getSession(), 1))
			{
				mav.setViewName(super.getPage(CommonConstant.ADMIN_ADD_JSP));
				List<Level> allLevels = this.adminServiceImpl.addPre();
				mav.addObject("allLevels", allLevels);
			} else
			{
				String msg = "权限不够";
				mav.addObject("msg", msg);
				CommonConstant.LOGGER.info(msg);
				mav.setViewName(super.getPage(CommonConstant.ERROR_JSP));
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, super.getMessage("unknown.error", "增加雇员"), e);
		}
		return mav;
	}

	@RequestMapping("add")
	public ModelAndView add(Emp emp, HttpServletRequest request, MultipartFile pic)
	{
		HttpSession session = request.getSession();
		Emp empInSession = (Emp) session.getAttribute(CommonConstant.EMP);
		ModelAndView mav = new ModelAndView();
		try
		{
			if (this.isAuthcated(session, 2))
			{
				emp.setPhoto(super.generatePhotoFileName(pic));
				emp.setHeid(empInSession.getEid());
				emp.setPassword(new MD5Code().getMD5ofStr(emp.getPassword()));

				CommonConstant.LOGGER.debug("eid:" + emp.getEid());
				CommonConstant.LOGGER.debug("ename:" + emp.getName());

				if (this.adminServiceImpl.add(emp))
				{
					String photoFullPath = request.getServletContext().getRealPath("/upload/emp/") + emp.getPhoto();
					super.savePhoto(pic, photoFullPath);
					super.setForwardMessageAndUrl(mav, "增加管理员成功", "/pages/admin/addPre.action");
				} else
				{
					super.setForwardMessageAndUrl(mav, "增加管理员失败", "/pages/admin/addPre.action");
				}
			} else
			{
				String msg = "权限不够";
				mav.addObject("msg", msg);
				CommonConstant.LOGGER.info(msg);
				mav.setViewName(super.getPage(CommonConstant.ERROR_JSP));
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "增加管理员失败", e);
		}
		return mav;
	}

	private boolean isAuthcated(HttpSession session, Integer actid)
	{
		Emp emp = (Emp) session.getAttribute(CommonConstant.EMP);
		if (emp == null)
			return false;

		Dept dept = emp.getDept();
		if (dept == null)
			return false;
		List<Groups> allGroups = dept.getAllGroups();
		if (allGroups == null || allGroups.size() == 0)
			return false;

		for (Groups groups : allGroups)
		{
			List<Action> allActions = groups.getAllActions();
			if (allActions == null || allActions.size() == 0)
				continue;
			for (Action action : allActions)
			{
				if (action.getActid().equals(actid))
					return true;
			}
		}

		return false;
	}
}
