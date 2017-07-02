package org.lyk.actions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lyk.service.IMessageService;
import org.lyk.service.impl.MessageServiceImpl;
import org.lyk.vo.Message;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Controller
@RequestMapping("/pages/back/message/*")
public class MessageAction
{
	@Resource
	private IMessageService messageServiceImpl;
	
	@Resource
	private ResourceBundleMessageSource messageResource;
	
	//@RequestMapping(value="message_insert",method=RequestMethod.POST)
	@RequestMapping("insert")
	public ModelAndView insert(Message msg,HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		
		ServletContext servletContext = request.getServletContext();
		HttpSession session = request.getSession();
		System.out.println("真实路径:" + servletContext.getRealPath("/"));
		System.out.println("SESSION ID:" + session.getId());
		 
		
		
		this.messageServiceImpl.insert(msg);
		
		
		ModelAndView modelAndView = new ModelAndView("/common/forward");
		modelAndView.addObject("msg", "Message添加成功").addObject("url","/index.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="message_update",method=RequestMethod.POST)
	public ModelAndView update(Message msg)throws Exception
	{
		this.messageServiceImpl.update(msg);
		ModelAndView modelAndView = new ModelAndView("/pages/common/forward.jsp");
		modelAndView.addObject("msg", "Message更新成功").addObject("url","/index.jsp");
		return modelAndView;
	}
	
	@RequestMapping("message_list")
	public ModelAndView list(
			@RequestParam(value="col",defaultValue="title") String column,
			@RequestParam(value="kw",defaultValue="")String keyWord,
			@RequestParam(value="cp",defaultValue="1")Integer currentPage,
			@RequestParam(value="ls",defaultValue="10")Integer lineSize) throws Exception
	{
		Map<String, Object> result = this.messageServiceImpl.list(column, keyWord, currentPage, lineSize);
		List<Message> allMessages = (List<Message>)result.get("allMessages");
		Integer allMessagesCount = (Integer)result.get("allMessagesCount");
		System.out.println("总录条数:" + allMessagesCount);
		for(Message msg : allMessages)
		{
			System.out.println(msg);
		}
		
		
		ModelAndView modelAndView = new ModelAndView("/pages/common/forward.jsp");
		modelAndView.addObject("msg", "Message更新成功").addObject("url","/index.jsp");
		return modelAndView;
	}
	
	@InitBinder
	public void bindDate(WebDataBinder webDataBinder)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	
	@RequestMapping("inner")
	public ModelAndView inner(HttpServletRequest request,HttpServletResponse response)
	{
		ServletContext servletContext = request.getServletContext();
		HttpSession session = request.getSession();
		System.out.println("真实路径:" + servletContext.getRealPath("/"));
		System.out.println("SESSION ID:" + session.getId());
		try
		{
			
			response.getWriter().println("<h1>Hello SHIT</h1>");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("insertPre")
	public ModelAndView insertPre()
	{
		
		ModelAndView modelAndView = new ModelAndView("message/insert");
		return modelAndView;
	}
	
	@RequestMapping("sayHello")
	public ModelAndView sayHello(HttpServletResponse response)
	{
		String message_cn = this.messageResource.getMessage("welcome", new Object[]{"你好"}, Locale.getDefault());
		Locale en_US_locale = new Locale("en","US");
		String message_en = this.messageResource.getMessage("welcome", new Object[]{"Hello"}, en_US_locale);
		try
		{
			response.setCharacterEncoding("UTF8");
			response.getWriter().println("<h1>"+message_cn+"</h1>");
			response.getWriter().println("<h1>"+message_en+"</h1>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
