package com.saurav.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MyConfiguration {
	
	@Bean
	public SecurityFilterChain securityAutho(HttpSecurity http) throws Exception {
		
				http.authorizeHttpRequests(
				auth -> auth.antMatchers("/saurav/customer/login","/saurav/admin/register","/saurav/customer/register","/saurav/admin/login").permitAll()
				.antMatchers("/saurav/admin/**").hasAuthority("ADMIN")
				.antMatchers("/saurav/customer/**").hasAuthority("USER")
				).csrf().disable().httpBasic();
		
		
		return http.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncode() {	
		return NoOpPasswordEncoder.getInstance();   
	}

}
