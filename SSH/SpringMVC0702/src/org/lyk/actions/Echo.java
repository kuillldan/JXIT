package org.lyk.actions;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lyk.utils.AbstractServlet;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/echo/*")
public class Echo extends AbstractServlet
{
	@Resource
	MessageSource msgSource;

	@RequestMapping("sayHello")
	public ModelAndView sayHello(String name)
	{
		System.out.println(this.msgSource.getMessage("welcome", new Object[]
		{ name }, Locale.getDefault()));
		
		int a = 4/0;
		
		return null;
	}

	@RequestMapping("fileHandler")
	public ModelAndView fileHandler(String name, MultipartFile photo1, MultipartFile photo2,
			HttpServletRequest request)
	{
		System.out.println("name:" + name);

		try
		{
			super.saveFile(photo1, request);
			super.saveFile(photo2, request);
			System.out.println(photo1.getOriginalFilename() + "保存成功");
			System.out.println(photo2.getOriginalFilename() + "保存成功");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String getUploadFolder()
	{
		// TODO Auto-generated method stub
		return "upload/echo";
	}
}
