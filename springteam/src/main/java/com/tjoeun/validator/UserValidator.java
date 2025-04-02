package com.tjoeun.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tjoeun.dto.UserDTO;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDTO userDTO = (UserDTO)target;
		
		String beanName = errors.getObjectName();
		System.out.println("beanName : " + beanName);
		
		if(beanName.equals("joinUserDTO") || beanName.equals("modifyUserDTO")) {
			if(userDTO.getUser_pw().equals(userDTO.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
		}
		
		if(beanName.equals("joinUserDTO")){
			// 중복확인 버튼 누르지 않고 회원가입 버튼을 눌렀을 때
			if(userDTO.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DidntCheckUserId");	
			}
		}
		
	}

	
	
}
