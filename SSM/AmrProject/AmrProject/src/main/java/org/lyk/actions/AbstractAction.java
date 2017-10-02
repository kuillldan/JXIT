package org.lyk.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lyk.constant.CommonConstant;
import org.lyk.constant.MessageConstant;
import org.lyk.constant.PageConstant;
import org.lyk.helper.SplitHandler;
import org.lyk.utils.StringUtils;
import org.lyk.vo.Action;
import org.lyk.vo.Dept;
import org.lyk.vo.Emp;
import org.lyk.vo.Groups;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AbstractAction
{
	@Resource
	private MessageSource messageSource;

	public String getString(String key, Object... params)
	{
		return this.messageSource.getMessage(key, params, Locale.getDefault());
	}

	public String getMessage(String key, Object... params)
	{
		return this.getString(key, params);
	}

	public String getPage(String key)
	{
		return this.getString(key);
	}

	public String getValidation(String key)
	{
		return this.getString(key);
	}

	protected void setForwardMessageAndUrl(ModelAndView mav, String msg, String url)
	{
		mav.setViewName(this.getPage("forward.jsp"));
		mav.addObject("msg", msg);
		mav.addObject("url", url);
	}

	protected void notAuthorizedThenForwordToErrorPage(ModelAndView mav)
	{
		String msg = this.getMessage(MessageConstant.NOT_AUTHORIZED);
		mav.setViewName(this.getPage(PageConstant.ERROR_JSP));
		mav.addObject(CommonConstant.MSG, msg);
		CommonConstant.LOGGER.error(msg + "(调用者信息:" + getInvokerInfo() + ")");
	}

	protected void setSystemError(ModelAndView mav, String msg_core, Exception e)
	{
		String msg = this.getMessage(MessageConstant.SYSTEM_ERROR, msg_core);
		mav.setViewName(this.getPage(PageConstant.ERROR_JSP));
		mav.addObject(CommonConstant.MSG, msg);
		CommonConstant.LOGGER.error(msg + "(调用者信息:" + getInvokerInfo() + ")");
		CommonConstant.LOGGER.error(e.getMessage() + "(调用者信息:" + getInvokerInfo() + ")", e);
	}

	protected String generatePhotoFileName(MultipartFile pic)
	{
		if (pic == null)
			return CommonConstant.NOPHOTO_JPG;

		if (pic.getSize() <= 0)
			return CommonConstant.NOPHOTO_JPG;

		String photoExt = this.getPhotoExt(pic.getContentType());
		if (StringUtils.isEmpty(photoExt))
			return CommonConstant.NOPHOTO_JPG;

		return UUID.randomUUID().toString() + "." + photoExt;
	}

	private String getPhotoExt(String contentType)
	{
		if ("image/bmp".equals(contentType))
		{
			return "bmp";
		} else if ("image/gif".equals(contentType))
		{
			return "gif";
		} else if ("image/jpeg".equals(contentType))
		{
			return "jpg";
		} else if ("image/png".equals(contentType))
		{
			return "png";
		}

		return null;
	}

	protected boolean savePhoto(MultipartFile photo, String photoFullPath)
	{
		if (photo == null || photoFullPath.endsWith(CommonConstant.NOPHOTO_JPG))
			return false;

		if (photo.getSize() <= 0 || CommonConstant.NOPHOTO_JPG.equalsIgnoreCase(photoFullPath))
			return false;

		InputStream is = null;
		OutputStream os = null;

		try
		{
			File photoFile = new File(photoFullPath);
			if (!photoFile.getParentFile().exists())
			{
				photoFile.getParentFile().mkdirs();
			}

			is = photo.getInputStream();
			os = new FileOutputStream(photoFile);

			byte[] buffer = new byte[CommonConstant.BUFFER_SIZE];
			Integer bytesReaded = -1;
			while ((bytesReaded = is.read(buffer, 0, CommonConstant.BUFFER_SIZE)) > -1)
			{
				os.write(buffer, 0, bytesReaded);
			}
		} catch (Exception e)
		{
			CommonConstant.LOGGER.warn("保存图片失败");
			CommonConstant.LOGGER.warn(e.getMessage(), e);
		} finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					CommonConstant.LOGGER.warn(e.getMessage(), e);
				}
			}

			if (os != null)
			{
				try
				{
					os.close();
				} catch (IOException e)
				{
					CommonConstant.LOGGER.warn(e.getMessage(), e);
				}
			}
		}

		return true;
	}

	protected void handleSplit(SplitHandler splitHandler, HttpServletRequest request, Integer allRecorders, String url,
			List<?> allItems, String columnData)
	{
		request.setAttribute("column", splitHandler.getColumn());
		request.setAttribute("keyWord", splitHandler.getKeyWord());
		request.setAttribute("currentPage", splitHandler.getCurrentPage());
		request.setAttribute("lineSize", splitHandler.getLineSize());
		request.setAttribute("allRecorders", allRecorders);
		request.setAttribute("url", url);
		request.setAttribute("allItems", allItems);
		request.setAttribute("columnData", columnData);
	}

	protected boolean isAuthcated(HttpServletRequest request, Integer... actids)
	{
		if (actids == null || actids.length == 0)
			return false;

		HttpSession session = request.getSession();
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
				for (Integer actid : actids)
				{
					if (action.getActid().equals(actid))
						return true;
				}
			}
		}

		return false;
	}

	private String getInvokerInfo()
	{
		StackTraceElement[] lvStacks = Thread.currentThread().getStackTrace();
		return lvStacks[3].getClassName() + "->" + lvStacks[3].getMethodName() + "->" + lvStacks[3].getLineNumber();
	}
}
