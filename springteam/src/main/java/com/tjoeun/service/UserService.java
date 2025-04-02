package com.tjoeun.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	// Service 에서
	// 사용자가 회원가입하면서 입력한 아이디로 
	// DB 에서 가져온 이름이  조회되는지 비교하기
	public boolean checkUserId(String user_id) {
		String user_name = userDAO.checkUserId(user_id);
		// 사용자가 입력한 아이디로 가입한 회원이 없음
		// else <-- 이미 존재하는 아이디입니다
		if (user_name == null) {
			return true;
		} else {
			return false;
		}	
	}
	
	public void addUserInfo(UserDTO joinUserDTO) {
		userDAO.addUserInfo(joinUserDTO);		
	}
	
	// Service 에서 
	// DB 에서 가져온 로그인한 회원의 정보를,
	// Session Scope 에 (Spring Framework가)미리 생성해 놓은
	// UserDTO loginUserDTO 에 저장함
	public void getLoginUserInfo(UserDTO loginProcUserDTO) {
		UserDTO loginProcUserDTO2 = userDAO.getLoginUserInfo(loginProcUserDTO);
		
		// loginUserDTO <-- Session Scope 에 생성된 UserDTO 
		// DB 로부터 로그인한 회원의 정보를 정상적으로 가져온 경우
		if(loginProcUserDTO2 != null) {
			loginUserDTO.setUser_idx(loginProcUserDTO2.getUser_idx());
			loginUserDTO.setUser_name(loginProcUserDTO2.getUser_name());
			// loginUserDTO.setUser_id(loginProcUserDTO2.getUser_id());
			// loginUserDTO.setUser_pw(loginProcUserDTO2.getUser_pw());
			loginUserDTO.setUserLogin(true);
		}		
		
		return;
	}
	
	// DB 에 저장된 로그인한 회원의 정보를 가져와서 
	// Controller 쪽으로 전달해줌
	public void getModifyUserInfo(UserDTO modifyUserDTO) {
		UserDTO tmp = userDAO.getModifyUserInfo(loginUserDTO.getUser_idx());
		
		modifyUserDTO.setUser_id(tmp.getUser_id());
		modifyUserDTO.setUser_name(tmp.getUser_name());
		modifyUserDTO.setUser_idx(tmp.getUser_idx());
		
		return;
	}
	
	public void modifyUserInfo(UserDTO modifyUserDTO) {
		// 회원의 pw 를 수정하려면 회원의 idx 가 필요함
		// 로그인한 사람이 자신의 pw 를 변경하는 경우이므로
		// 로그인한 사람의 정보가 저장된 session scope 에 있는
		// loginUserDTO 에 있는 idx 를 가져와서 사용하면 됨
		modifyUserDTO.setUser_idx(loginUserDTO.getUser_idx());
				
		// 화면에서 입력한 pw 를 갖고 있는 modifyUserDTO
		userDAO.modifyUserInfo(modifyUserDTO);
	}
	
	
	
}
