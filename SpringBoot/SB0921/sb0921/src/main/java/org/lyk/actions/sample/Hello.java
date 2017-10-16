package org.lyk.actions.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello
{
	@RequestMapping("/home")
	@ResponseBody
	public String home()
	{// spring-boot:run
		return "Hello World????";
	}
}
