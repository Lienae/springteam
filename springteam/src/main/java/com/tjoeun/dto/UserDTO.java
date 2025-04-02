package com.tjoeun.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
	private int user_idx;
	
	@Size(min=2, max=4)
	@Pattern(regexp="[가-힣]*")
	private String user_name;
	
	@Size(min=4, max=20)
	@Pattern(regexp="[A-Za-z0-9]*")
	private String user_id;
	
	@Size(min=4, max=20)
	@Pattern(regexp="[A-Za-z0-9]*")
	private String user_pw;
	
	@Size(min=4, max=20)
	@Pattern(regexp="[A-Za-z0-9]*")
	private String user_pw2;
	
	// 회원 가입 여부 저장
	private boolean userIdExist = false;
	
	// 로그인 상태 정보 저장
	private boolean userLogin = false;
	
}
