package com.tjoeun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.dto.UserDTO;

public class CheckLoginInterceptor implements HandlerInterceptor{

	private UserDTO loginUserDTO;
	
	public CheckLoginInterceptor(UserDTO loginUserDTO) {
		this.loginUserDTO = loginUserDTO;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// 로그아웃 상태에서는 접근하지 못하게 하기 
		if(loginUserDTO.isUserLogin() == false) {
			// user 폴더의 cannot_login.jsp 로 이동함
			String contextPath = request.getContextPath();
			
			// UserController 에서 url pattern 이 
			// "/user/cannot_login" 인 메소드로 이동함
			response.sendRedirect(contextPath + "/user/cannot_login");
			
			
			return false;
		}
		
		return true;
	}
	
}
