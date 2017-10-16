package org.lyk.actions;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class StartSpringBoot
{
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(StartSpringBoot.class, args);
	}
}
