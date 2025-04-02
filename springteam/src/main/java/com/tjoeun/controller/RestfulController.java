package com.tjoeun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.service.UserService;

@RestController
public class RestfulController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/checkUserId/{user_id}")
	public String checkUserId(@PathVariable String user_id) {
		
		boolean chk = userService.checkUserId(user_id);
		
		return chk + "";
		
	}
	
}
