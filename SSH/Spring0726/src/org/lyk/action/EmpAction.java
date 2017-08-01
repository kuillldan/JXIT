package org.lyk.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.lyk.vo.Emp;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pages/emp/*")
public class EmpAction extends AbstractAction
{
	@RequestMapping("echo")
	public void echo(String info, MultipartFile file1, MultipartFile file2) throws IOException
	{
		System.out.println("=======================");
		System.out.println(info);
		System.out.println(file1.getOriginalFilename());
		System.out.println(file2.getOriginalFilename());
		System.out.println(super.getMessage("welcome", new Object[]
		{ "sheldon" }));
		System.out.println("=======================");
	}
}
