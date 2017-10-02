package org.lyk.actions;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
				String msg = "权限不够";
				mav.addObject("msg", msg);
				CommonConstant.LOGGER.info(msg);
				mav.setViewName(super.getPage(PageConstant.ERROR_JSP));
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, super.getMessage(MessageConstant.SYSTEM_ERROR, "增加雇员"), e);
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
					super.setForwardMessageAndUrl(mav, "增加管理员成功", "/pages/admin/addPre.action");
					CommonConstant.LOGGER.info("增加管理员成功");
				} else
				{
					super.setForwardMessageAndUrl(mav, "增加管理员失败", "/pages/admin/addPre.action");
				}
			} else
			{
				String msg = "权限不够";
				mav.addObject("msg", msg);
				CommonConstant.LOGGER.info(msg);
				mav.setViewName(super.getPage(PageConstant.ERROR_JSP));
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "增加管理员失败", e);
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
			String msg = "查询管理员信息发生系统异常";
			super.setSystemError(mav, msg, e);
		}
		return mav;
	}
}
