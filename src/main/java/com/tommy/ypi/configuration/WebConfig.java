package com.tommy.ypi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
							.allowedOrigins("*")
							.allowedMethods("*")  // default : GET, HEAD, POST
							.allowCredentials(false)
							.maxAge(3600);
	}
}
