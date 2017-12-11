package org.lyk;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo/*")
public class DemoController
{
//	@ResponseBody
	@RequestMapping("show/{name}")
	public String show(@PathVariable String name, ModelMap map)
	{
		System.out.println("传入参数:" + name);
		map.addAttribute("msg", "hello jsp");
		return "demo/show";
	}
}
