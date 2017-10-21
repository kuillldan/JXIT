package org.lyk;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class StartSpringBoot extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		// TODO Auto-generated method stub
		return builder.sources(StartSpringBoot.class);
	}

	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(StartSpringBoot.class, args);
	}
}
