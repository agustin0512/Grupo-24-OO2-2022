package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.service.implementation.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//@Qualifier("userService")
	@Autowired
	private UserService userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/*", "/images/*", "/js/*", "/vendor/bootstrap/css/*", "/vendor/jquery/*", "/vendor/bootstrap/js/*").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").loginProcessingUrl("/loginprocess")
				.usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/loginsuccess").permitAll()
			.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/logout").permitAll();
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.invalidSessionUrl("/login");
	}
}