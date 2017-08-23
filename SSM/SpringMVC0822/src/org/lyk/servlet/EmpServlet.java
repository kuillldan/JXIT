package org.lyk.servlet;

import java.util.Locale;

import javax.annotation.Resource;

import org.lyk.vo.Dept;
import org.lyk.vo.Emp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/emp/*")
public class EmpServlet
{
	private static final Logger logger = LoggerFactory.getLogger(EmpServlet.class);
	
	@Resource(name="ms")
	private MessageSource messageResource;
	
	@RequestMapping("echo")
	public ModelAndView echo(String name)
	{
		String path = this.messageResource.getMessage("emp.show", null, Locale.getDefault());
		String msg = this.messageResource.getMessage("welcome", new Object[]{name}, Locale.getDefault());
		
		ModelAndView mav = new ModelAndView(path);
		mav.addObject("msg", msg);
		return mav;
	}
	
	@RequestMapping("saveFile")
	public ModelAndView saveFile(MultipartFile photo,String desc)
	{
		
		ModelAndView mav = new ModelAndView(this.messageResource.getMessage("emp.show", null, Locale.getDefault()));
		if(photo.isEmpty())
		{
			logger.info("未检测到文件上传");
		}
		else
		{
			logger.info("文件名:" + photo.getOriginalFilename());
			logger.info("文件类型:" + photo.getContentType());
			logger.info("文件大小:" + photo.getSize());
		}
		logger.info("desc:" + desc);
		mav.addObject("desc", desc);
		return mav;
	}
}
