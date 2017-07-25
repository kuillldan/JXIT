package org.lyk.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages/emp/*")
public class EmpAction
{
	@RequestMapping("echo")
	public void echo(String msg)
	{
		System.out.println("ECHO:" + msg);
	}
}