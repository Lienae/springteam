package com.tjoeun.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.UserService;
import com.tjoeun.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	@GetMapping("/login")
	public String login(@ModelAttribute("loginProcUserDTO") UserDTO userDTO,
											@RequestParam(value="fail", defaultValue="false") boolean fail, Model model) {
		model.addAttribute("fail", fail);
		return "user/login";
	}
	
	@PostMapping("/loginProcedure")
	public String login(@Valid @ModelAttribute("loginProcUserDTO") UserDTO loginProcUserDTO,
			                BindingResult result ) {
		
		// 로그인 실패 - 유효성 검사 통과 못함
		if(result.hasErrors()) {
			return "user/login";
		}
		
		// 로그인 성공 - 유효성 검사 통과함
		
		// DB 에서 가져온 로그인한 회원의 정보를,
		// Session Scope 에 (Spring Framework가)미리 생성해 놓은
		// UserDTO loginUserDTO 에 저장함
		userService.getLoginUserInfo(loginProcUserDTO);
		
		// Service 에서 loginUserDTO.setUserLogin(true); 에 의해
		// 로그인에 성공하면 	private boolean userLogin 가 true 가 됨
		// if(loginUserDTO.isUserLogin()) {
		if(loginUserDTO.isUserLogin() == true) {
			return "user/login_success";
		}else {
			return "user/login_failure";
		}
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserDTO") UserDTO joinUserDTO) {
		return "user/join";
	}
	
	@PostMapping("/join_procedure")
	public String join_procedure(@Valid @ModelAttribute("joinUserDTO") UserDTO joinUserDTO,
															 BindingResult result) {
		// 유효성 검사 통과 못함
		if(result.hasErrors()) {
			return "user/join";
			
		}
		userService.addUserInfo(joinUserDTO);
		
		// 유효성 검사 통과함
		return "user/join_success";
	}
	
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserDTO") UserDTO modifyUserDTO) {
		userService.getModifyUserInfo(modifyUserDTO);		
		return "user/modify";
	}
	
	@PostMapping("/modifyProcedure")
	public String modifyProcedure(@Valid @ModelAttribute("modifyUserDTO") UserDTO modifyUserDTO,
			                          BindingResult result) {
		if(result.hasErrors()) {
			return "user/modify";
		}	
		
		userService.modifyUserInfo(modifyUserDTO);
		
		return "user/modify_success";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		loginUserDTO.setUserLogin(false);
		
		// 로그아웃할 때 session 을 invalidate() 해서
		// Session Scope 에 있는 회원 정보 삭제하기
		session.invalidate();
		
		// 로그아웃 한 후 Session 에 있는 loginUserDTO 에 저장된
		// 회원 정보 확인하기
		System.out.println(loginUserDTO);
		
		return "user/logout";
	}
	
	@GetMapping("/cannot_login")
	public String cannot_login() {
		// user 폴더의 cannot_login.jsp 로 이동함
		return "user/cannot_login";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
	
}
