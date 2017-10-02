package org.lyk.actions;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.lyk.constant.CommonConstant;
import org.lyk.constant.MessageConstant;
import org.lyk.constant.PageConstant;
import org.lyk.enums.ActionIDEnum;
import org.lyk.helper.SplitHandler;
import org.lyk.service.IAdminService;
import org.lyk.service.impl.AdminServiceImpl;
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
			if (this.isAuthcated(request, ActionIDEnum.ADMIN_ADD_PRE.getValue()))
			{
				mav.setViewName(super.getPage(PageConstant.ADMIN_ADD_JSP));
				List<Level> allLevels = this.adminServiceImpl.addPre();
				mav.addObject("allLevels", allLevels);
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "预加载管理员信息", e);
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
			if (this.isAuthcated(request, ActionIDEnum.ADMIN_ADD.getValue()))
			{
				emp.setPhoto(super.generatePhotoFileName(pic));
				emp.setHeid(empInSession.getEid());
				emp.setPassword(new MD5Code().getMD5ofStr(emp.getPassword()));
				Dept dept = new Dept();
				dept.setDid(1);
				emp.setDept(dept);
				emp.setAflag(2);

				CommonConstant.LOGGER.debug("eid:" + emp.getEid());
				CommonConstant.LOGGER.debug("ename:" + emp.getName());

				if (this.adminServiceImpl.add(emp))
				{
					String parentFolder = super.getString("EMP.PHOTO.SAVE.PATH");
					if (!parentFolder.endsWith("/"))
						parentFolder += "/";
					String photoFullPath = parentFolder + emp.getPhoto();
					File photoFile = new File(photoFullPath);
					if (!photoFile.getParentFile().exists())
						photoFile.getParentFile().mkdirs();

					if (super.savePhoto(pic, photoFullPath))
					{
						CommonConstant.LOGGER.debug("图片保存成功:" + photoFullPath);
					} else
					{
						CommonConstant.LOGGER.debug("图片保存失败:" + photoFullPath);
					}
					super.setForwardMessageAndUrl(mav, "增加管理员成功", PageConstant.ADMIN_ADD_PRE_ACTION);
					CommonConstant.LOGGER.info("增加管理员成功");
				} else
				{
					super.setForwardMessageAndUrl(mav, "增加管理员失败", PageConstant.ADMIN_ADD_PRE_ACTION);
				}
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "增加管理员", e);
		}
		return mav;
	}

	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			SplitHandler splitHandler = new SplitHandler(request);
			Map<String, Object> adminInfos = this.adminServiceImpl.getAllAdmin(splitHandler.getColumn(),
					splitHandler.getKeyWord(), splitHandler.getCurrentPage(), splitHandler.getLineSize());

			Integer allRecorders = (Integer) adminInfos.get("allRecorders");
			List<Emp> allItems = (List<Emp>) adminInfos.get("allItems");
			super.handleSplit(splitHandler, request, allRecorders, "pages/admin/list.action", allItems,
					"姓名:name|电话:phone");
			mav.setViewName("/pages/admin/admin_list.jsp");
		} catch (Exception e)
		{
			super.setSystemError(mav, "查询管理员信息", e);
		}
		return mav;
	}

	@RequestMapping("checkEid")
	public ModelAndView checkEid(HttpServletRequest request, HttpServletResponse response, Integer eid)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, ActionIDEnum.ADMIN_ADD_PRE.getValue(), ActionIDEnum.ADMIN_ADD.getValue()))
			{
				response.getWriter().print(this.adminServiceImpl.checkEid(eid));
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
			if (super.isAuthcated(request, ActionIDEnum.ADMIN_ADD_PRE.getValue(), ActionIDEnum.ADMIN_ADD.getValue()))
			{
				response.getWriter().print(this.adminServiceImpl.checkSalary(salary, lid));
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
