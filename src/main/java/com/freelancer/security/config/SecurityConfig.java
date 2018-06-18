package com.freelancer.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailService customUserDatailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//PERMITE URLS PARA USUARIO COM PADRAO /USER
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/user").permitAll();
		
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/login").permitAll();
		//BLOQUEIA TODOS AS URLs NÃO MAPEADAS
		http.authorizeRequests().anyRequest().authenticated();
		//MAPEA URLS COMO PADRAO /ADMIN E / PROTECTED PARA SEREM ACESSADAS APENAS COM AUTORIZAÇÃO DE USER OU ADMIN
		http.authorizeRequests().antMatchers("/admim/").hasRole("ADMIN").antMatchers("/protected/").hasRole("USER").and().addFilter(new JWTAuthenticationFilter(authenticationManager())).addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDatailService));
		http.csrf().disable();
	
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//REALIZA A CRIPTOGRAÇÃO DA SENHA DOS USUARIOS PARA AUTENTICAR
		auth.userDetailsService(customUserDatailService).passwordEncoder(new BCryptPasswordEncoder());
//		auth.inMemoryAuthentication()
//		.withUser("admin")
//		.password("admin")
//		.roles("ADMIN");
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
