package com.te.lms.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.te.lms.security.filter.SecurityFilter;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;
	private final SecurityFilter securityFilter;

	private final String EMPLOYEE = "EMPLOYEE";
	private final String ADMIN = "ADMIN";
	private final String MENTOR = "MENTOR";
	
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/api/v1/private/admin").hasRole(ADMIN);
		http.authorizeRequests().antMatchers("/api/v1/private/mentor").hasAnyRole(MENTOR,ADMIN);
		http.authorizeRequests().antMatchers("/api/v1/private/employee").hasAnyRole(EMPLOYEE,MENTOR,ADMIN);
		http.authorizeRequests().antMatchers("/api/v1/public/**").permitAll().anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
