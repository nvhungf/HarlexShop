package com.nvhungf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nvhungf.interceptor.AdminLoggerInterceptor;
import com.nvhungf.interceptor.AdminSecurityInterceptor;




@Configuration
public class InterConfig implements WebMvcConfigurer{

	@Autowired
	AdminLoggerInterceptor interceptor;
	
	@Autowired
	AdminSecurityInterceptor auth;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/assets/**");
		registry.addInterceptor(auth)
			.addPathPatterns("/admin/**")
			.excludePathPatterns("/dashboard/login");
		
	}
}