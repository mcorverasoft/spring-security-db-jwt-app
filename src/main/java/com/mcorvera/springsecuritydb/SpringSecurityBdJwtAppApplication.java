package com.mcorvera.springsecuritydb;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityBdJwtAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBdJwtAppApplication.class, args);
			
	}
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-6:00"));
	}

}
