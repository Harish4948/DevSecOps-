package com.devsecops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NumericApplication {
	public static void main(String[] args) {
		SpringApplication.run(NumericApplication.class, args);
	}

	@Bean
	public void contentTypeOptionsHeaderFilter() {
		FilterRegistrationBean<ContentTypeOptionsHeaderFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new ContentTypeOptionsHeaderFilter());
		registrationBean.addUrlPatterns("/*");
	}
	
