package com.tjoeun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	// @Resource(name="loginUserDTO")
	// private UserDTO loginUserDTO;
	
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		System.out.println("안녕하세요 !~~ ");
		// System.out.println(loginUserDTO);
		
		System.out.println(request.getServletContext().getRealPath("/"));
		return "redirect:/main";
	}
}
