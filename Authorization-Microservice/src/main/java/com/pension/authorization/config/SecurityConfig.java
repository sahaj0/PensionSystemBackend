package com.pension.authorization.config;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pension.authorization.filter.JwtAuthorizationFilter;
import com.pension.authorization.service.MyUserService;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableWebSecurity
@ComponentScan("com.*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private JwtAuthorizationFilter jwtAuthorizationFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(myUserService).passwordEncoder(passwordEncoder());
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable().cors().disable().authorizeRequests().antMatchers(Paths.EXCLUDED_PATHS)
		.permitAll().anyRequest().authenticated()
		.and().exceptionHandling().and().sessionManagement();
		httpSecurity.headers().frameOptions().disable();
		httpSecurity.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
}
