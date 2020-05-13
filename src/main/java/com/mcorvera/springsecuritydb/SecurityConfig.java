package com.mcorvera.springsecuritydb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mcorvera.springsecuritydb.security.JwtFilter;
import com.mcorvera.springsecuritydb.security.JwtUnauthorizedHandlerEntryPoint;
import com.mcorvera.springsecuritydb.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Autowired
	private JwtFilter jwtfilt;
    
    @Bean
    public JwtFilter getJwtFilter() {
    	return new JwtFilter();
    }
    
    @Autowired
    private JwtUnauthorizedHandlerEntryPoint unauthorizedHandler;
    
    @Bean
    @Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customUserDetailsService).passwordEncoder(bcrypt);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			
			.csrf()
        	.disable()
        	.exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
			.authorizeRequests()
				.antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js").permitAll()
				.antMatchers("/app/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(jwtfilt , UsernamePasswordAuthenticationFilter.class )
				
				;
			
	}

    
}


