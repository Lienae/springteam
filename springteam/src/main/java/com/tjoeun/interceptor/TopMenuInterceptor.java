package com.tjoeun.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.dto.BoardInfoDTO;
import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor {

	// Interceptor 에서는 자동주입(Autowired) 이 안 되므로
	// ServletAppContext.java 에서 주입 받아서 TopMenuService 객체를 생성해서
	// TopMenuService 의 생성자를 통해서 TopMenuService 의 객체의 주소를 받도록 설정함
	private TopMenuService topMenuService;
	
	private UserDTO loginUserDTO;
	
	public TopMenuInterceptor(TopMenuService topMenuService, UserDTO loginUserDTO) {
		this.topMenuService = topMenuService;
		this.loginUserDTO = loginUserDTO;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		List<BoardInfoDTO> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserDTO", loginUserDTO);		
		return true;
	}
	
}
