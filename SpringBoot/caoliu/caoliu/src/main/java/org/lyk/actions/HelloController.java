package org.lyk.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello/*")
public class HelloController extends AbstractController
{
	@RequestMapping("show")
	@ResponseBody
	public String show()
	{
		return "hello, welcome to www.t66y.com";
	}
}
