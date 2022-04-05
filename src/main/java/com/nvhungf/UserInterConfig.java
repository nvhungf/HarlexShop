package com.nvhungf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nvhungf.interceptor.AdminLoggerInterceptor;
import com.nvhungf.interceptor.AdminSecurityInterceptor;
import com.nvhungf.interceptor.UserLoggerInterceptor;
import com.nvhungf.interceptor.UserSecurityInterceptor;




@Configuration
public class UserInterConfig implements WebMvcConfigurer{

	@Autowired
	UserLoggerInterceptor interceptor;
	
	@Autowired
	UserSecurityInterceptor auth;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/assets/**");
		registry.addInterceptor(auth)
			.addPathPatterns("")
			.excludePathPatterns("/user/login");
		
	}
}