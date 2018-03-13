//package com.jsm.security;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import com.jsm.config.SwaggerConfig;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableAutoConfiguration
//
//@Import(SwaggerConfig.class)
//public class SpringBasicSecurity  extends WebSecurityConfigurerAdapter{
//	private static final String[] AUTH_WHITELIST = {
//
//            // -- swagger ui
//            "/swagger-resources/**",
//            "/swagger-ui.html",
//            "/v2/api-docs",
//            "/webjars/**"
//    };
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()		
//		.antMatchers(AUTH_WHITELIST).permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().and().csrf().disable();
//		
//	
//		
//		
//	}
//	
//	
//	
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//	 	UserDetails user = User.withDefaultPasswordEncoder()
//				.username("moises")
//				.password("1982")
//				.roles("USER")
//				.build();
//	 	
//	 	return new InMemoryUserDetailsManager(user);
//	}
//}
