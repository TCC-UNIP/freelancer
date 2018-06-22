package com.freelancer.security.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class CORSFilter extends GenericFilterBean implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Acess-Control-Allow-Origin", "*");
		httpResponse.setHeader("Acess-Control-Allow-Methods", "*");
		//httpResponse.setHeader("Acess-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
		
		httpResponse.setHeader("Acess-Control-Allow-Headers", "*");
		//httpResponse.setHeader("Acess-Control-Allow-Headers", "Origin, X-Requested-With, Cotent-Type, Accept, X-Auth-Token,X-Csrf-Token,Authorization");
	
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpResponse.setHeader("Access-Control-Max-Age", "3600");
		
		System.out.println("CORS CONFIGURATION DONE");
		
		chain.doFilter(request, response);
	}

}