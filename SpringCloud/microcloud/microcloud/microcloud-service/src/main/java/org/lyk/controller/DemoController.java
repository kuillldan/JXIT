package org.lyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo/*")
public class DemoController
{
	@RequestMapping("show/{name}")
//	@ResponseBody
	public String show(@PathVariable String name, ModelMap map)
	{
//		return "hello:" + name;
		map.addAttribute("msg","你好，今天是星期天." + name);
		return "demo/show";
	}
}
