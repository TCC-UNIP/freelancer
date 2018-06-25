package com.freelancer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.freelancer.security.config.CORSFilter;

@SpringBootApplication
public class FreelancerApplication {
	//INICIA A APLICAÇÃO
	public static void main(String[] args) {
		SpringApplication.run(FreelancerApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<CORSFilter> corsFilterRegistration() {
		FilterRegistrationBean<CORSFilter> registrationBean = new FilterRegistrationBean<CORSFilter>(new CORSFilter());
		registrationBean.setName("CORS Filter");
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
