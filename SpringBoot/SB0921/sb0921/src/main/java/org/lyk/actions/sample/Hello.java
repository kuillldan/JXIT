package org.lyk.actions.sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello
{
	@RequestMapping("/home")
	public String home(HttpServletRequest request,HttpServletResponse response)
	{ 
		//System.out.println(request.getServletContext().getRealPath("/"));
		return request.getServletContext().getRealPath("/");
	}
}
