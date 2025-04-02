package com.tjoeun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.tjoeun.dto.UserDTO;

// Spring MVC project 에서 사용하는 Bean 들을 등록함
@Configuration
public class RootAppContext {
	
	@Bean("loginUserDTO")
	@SessionScope
	public UserDTO loginUserDTO() {
		return new UserDTO();
	}
	
}








