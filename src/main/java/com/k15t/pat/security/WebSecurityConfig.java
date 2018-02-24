package com.k15t.pat.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug=false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/","/rest/*","/public/**","/h2/**", "/registration.html", "/about",  "/api-docs", "/v2/api-docs*")
				.permitAll().anyRequest().authenticated(); // 7
		http.headers()
			.frameOptions().sameOrigin()
			.httpStrictTransportSecurity().disable();
				
		http.csrf().disable();

	}
	

}