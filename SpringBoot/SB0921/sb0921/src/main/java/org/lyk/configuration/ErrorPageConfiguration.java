package org.lyk.configuration;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfiguration
{
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer()
	{
		return new EmbeddedServletContainerCustomizer()
		{
			
			@Override
			public void customize(ConfigurableEmbeddedServletContainer arg0)
			{
				ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error-400.html");
				ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-404.html");
				ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-500.html");
				arg0.addErrorPages(errorPage400,errorPage404,errorPage500);
			}
		};
	}
	
}
