package org.lyk.actions;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lyk.constant.CommonConstant;
import org.lyk.constant.MessageConstant;
import org.lyk.constant.PageConstant;
import org.lyk.enums.ActionIDEnum;
import org.lyk.helper.SplitHandler;
import org.lyk.enums.AFLAG;
import org.lyk.service.IEmpService;
import org.lyk.utils.MD5Code;
import org.lyk.utils.StringUtils;
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
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, ActionIDEnum.EMP_ADD.getValue()))
			{
				HttpSession session = request.getSession();
				Emp empInSession = (Emp) session.getAttribute(CommonConstant.EMP);

				emp.setPhoto(super.generatePhotoFileName(pic));
				emp.setHeid(empInSession.getEid());
				emp.setPassword(new MD5Code().getMD5ofStr(emp.getPassword()));
				emp.setAflag(AFLAG.NORMAL.getValue());

				if (this.empServiceImpl.insert(emp))
				{
					String photoFullPath = this.getPhotoFullPath(emp);
					if (super.savePhoto(pic, photoFullPath))
					{
						CommonConstant.LOGGER.debug("图片保存成功:" + photoFullPath);
					} else
					{
						CommonConstant.LOGGER.debug("图片保存失败:" + photoFullPath);
					}

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

	private String getPhotoFullPath(Emp emp)
	{
		String parentFolder = super.getString("EMP.PHOTO.SAVE.PATH");
		if (!parentFolder.endsWith("/"))
			parentFolder += "/";
		String photoFullPath = parentFolder + emp.getPhoto();

		return photoFullPath;
	}

	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, ActionIDEnum.EMP_LIST.getValue()))
			{
				SplitHandler splitHandler = new SplitHandler(request);
				Map<String, Object> empInfos = this.empServiceImpl.listAllEmp(splitHandler.getColumn(),
						splitHandler.getKeyWord(), splitHandler.getCurrentPage(), splitHandler.getLineSize());

				Integer allRecorders = (Integer) empInfos.get("allRecorders");
				List<Emp> allItems = (List<Emp>) empInfos.get("allItems");
				super.handleSplit(splitHandler, request, allRecorders, super.getPage(PageConstant.EMP_LIST_ACTION),
						allItems, "姓名:name|电话:phone");
				mav.setViewName(super.getPage(PageConstant.EMP_LIST_JSP));
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "查询雇员列表", e);
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
		CommonConstant.LOGGER.debug("级别lid:" + lid);
		CommonConstant.LOGGER.debug("薪水:" + salary);
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

	@RequestMapping("editPre")
	public ModelAndView editPre(HttpServletRequest request, Integer eid, Integer cp)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, ActionIDEnum.EMP_EDIT_PRE.getValue()))
			{
				Map<String, Object> result = this.empServiceImpl.editPre(eid);
				Emp emp = (Emp) result.get("emp");
				List<Dept> allDepts = (List<Dept>) result.get("allDepts");
				List<Level> allLevels = (List<Level>) result.get("allLevels");
				mav.addObject("vo", emp);
				mav.addObject("allDepts", allDepts);
				mav.addObject("allLevels", allLevels);
				mav.setViewName(super.getPage(PageConstant.EMP_EDIT_JSP) + "?currentPage=" + cp);
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "加载员工信息", e);
		}
		return mav;
	}

	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest request, Emp emp, Integer cp, MultipartFile pic)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if (super.isAuthcated(request, ActionIDEnum.EMP_EDIT.getValue()))
			{
				if (!StringUtils.isEmpty(emp.getPassword()))
				{
					emp.setPassword(new MD5Code().getMD5ofStr(emp.getPassword()));
				} else
				{
					emp.setPassword(null);
				}

				if (CommonConstant.NOPHOTO_JPG.equalsIgnoreCase(emp.getPhoto()))
				{// 原来没有照片

					if (pic != null && pic.getSize() > 0)
					{// 现在有照片上传

						// 设置新照片
						emp.setPhoto(super.generatePhotoFileName(pic));
						String photoFullPath = this.getPhotoFullPath(emp);
						super.savePhoto(pic, photoFullPath);
					} else
					{
						// 现在没有照片上传
						// 什么也不做
					}
				} else
				{// 原来有照片
					if (pic != null && pic.getSize() > 0)
					{// 现在有照片上传

						// 删除旧照片
						String photoFullPath = this.getPhotoFullPath(emp);
						File photoFile = new File(photoFullPath);
						if (photoFile.exists())
						{
							photoFile.delete();
							CommonConstant.LOGGER.debug("雇员照片删除成功");
						}

						// 设置新照片
						emp.setPhoto(super.generatePhotoFileName(pic));
						photoFullPath = this.getPhotoFullPath(emp);
						super.savePhoto(pic, photoFullPath);
					} else
					{
						// 现在没有照片上传
						// 什么也不做 保持原来的照片
					}
				}

				String url = super.getPage(PageConstant.EMP_LIST_ACTION) + "?currentPage=" + cp;
				if (this.empServiceImpl.edit(emp))
				{
					super.setForwardMessageAndUrl(mav, "编辑员工信息成功", url);
				}
				else
				{
					super.setForwardMessageAndUrl(mav, "编辑员工信息失败", url);
				}
			} else
			{
				super.notAuthorizedThenForwordToErrorPage(mav);
			}
		} catch (Exception e)
		{
			super.setSystemError(mav, "编辑员工信息", e);
		}
		return mav;
	}
}
