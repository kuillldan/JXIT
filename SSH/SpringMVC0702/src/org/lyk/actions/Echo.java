package org.lyk.actions;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
 


@Controller
@RequestMapping("/pages/echo/*")
public class Echo
{
	@Resource
	MessageSource msgSource; 
	
	@RequestMapping("sayHello")
	public ModelAndView sayHello(String name)
	{
		System.out.println(this.msgSource.getMessage("welcome", new Object[]{name}, Locale.getDefault()));
		return null;
	}
	
	@RequestMapping("fileHandler")
	public ModelAndView fileHandler(String name, MultipartFile photo)
	{
		System.out.println("name:" + name);
		System.out.println("文件内容:" + photo.getContentType());
		System.out.println("文件名称:" + photo.getOriginalFilename());
		System.out.println("文件大小:" + photo.getSize());
		System.out.println("是否为空:" + photo.isEmpty());
		return null;
	}
}
