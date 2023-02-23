package com.springboot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build();
//        return new InMemoryUserDetailsManager(user);
//    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
	  throws Exception {
	    auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery("select username,CONCAT('{noop}',password),enabled from users" 
	    		  + " where username=?")
	      .authoritiesByUsernameQuery("select username , role from authority" 
	    		  + " where username=?");
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
      throws Exception {
        httpSecurity.authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .permitAll()
        .and()
        .logout()
        .permitAll();
      return httpSecurity.build();
    }
		
}
