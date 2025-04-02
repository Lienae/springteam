package com.tjoeun.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.dto.UserDTO;
import com.tjoeun.mapper.UserMapper;

@Repository
public class UserDAO {
	
	@Autowired
	private UserMapper userMapper;
	
	public String checkUserId(String user_id) {
		String user_name = userMapper.checkUserId(user_id);
		return user_name;
	}
	
	public void addUserInfo(UserDTO joinUserDTO) {
		userMapper.addUserInfo(joinUserDTO);		
	}
	
	public UserDTO getLoginUserInfo(UserDTO loginProcUserDTO) {
		UserDTO loginProcUserDTO2 = userMapper.getLoginUserInfo(loginProcUserDTO);
		return loginProcUserDTO2;
	}
	
	public UserDTO getModifyUserInfo(int user_idx) {
		UserDTO modifyUserDTO = userMapper.getModifyUserInfo(user_idx);
		return modifyUserDTO;
	}
	
	public void modifyUserInfo(UserDTO modifyUserDTO) {
		userMapper.modifyUserInfo(modifyUserDTO);
	}
}
