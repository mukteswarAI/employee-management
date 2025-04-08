package org.ems.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final SessionValidationInterceptor sessionValidationInterceptor;

	public WebMvcConfig(SessionValidationInterceptor sessionValidationInterceptor) {
		this.sessionValidationInterceptor = sessionValidationInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionValidationInterceptor).addPathPatterns("/admin/**", "/hr/**", "/employee/**")
				.excludePathPatterns("/login", "/logout", "/error");
	}

}
