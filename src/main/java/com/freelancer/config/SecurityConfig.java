package com.freelancer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailService customUserDatailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//PERMITE URLS PARA USUARIO COM PADRAO /USER
		http.authorizeRequests().antMatchers("*/user").permitAll();
		//BLOQUEIA TODOS AS URLs NÃO MAPEADAS
		http.authorizeRequests().anyRequest().authenticated();
		//MAPEA URLS COMO PADRAO /ADMIN E / PROTECTED PARA SEREM ACESSADAS APENAS COM AUTORIZAÇÃO DE USER OU ADMIN
		http.authorizeRequests().antMatchers("*/admim/*").hasRole("ADMIN").antMatchers("*/protected/*").hasRole("USER").and().httpBasic().and().csrf().disable();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//REALIZA A CRIPTOGRAÇÃO DA SENHA DOS USUARIOS PARA AUTENTICAR
		auth.userDetailsService(customUserDatailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
//	@Autowired
//	public void configureGolbal(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth
//		.inMemoryAuthentication()
//			.passwordEncoder(NoOpPasswordEncoder.getInstance())
//			.withUser("admin").password("admin").roles("ADMIN");
//	}
}
