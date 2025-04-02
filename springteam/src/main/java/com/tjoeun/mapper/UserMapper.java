package com.tjoeun.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tjoeun.dto.UserDTO;

public interface UserMapper {

	// 회원 가입 여부 조회하기
	@Select("select user_name from user_table where user_id = #{user_id}")
	String checkUserId(String user_id);
	
	// 회원 가입하기
	@Insert("insert into user_table values(user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	void addUserInfo(UserDTO joinUserDTO);
	
	// 로그인 성공했을 때, 회원 정보 가져오기
	//  ㄴ 가져와서, Spring Framework 가  Session Scope 에
	//     서버 실행할 때 자동으로 생성해 놓은 UserDTO 에
	//     로그인한 회원의 정보를 저장하기 위함
	@Select("select * from user_table where user_id = #{user_id} and user_pw = #{user_pw}")
	UserDTO getLoginUserInfo(UserDTO loginProcUserDTO);
	
	@Select("select * from user_table where user_idx = #{user_idx}")
	UserDTO getModifyUserInfo(int user_idx);
	
	@Update("update user_table " +
					"set user_pw = #{user_pw} " +
					"where user_idx = #{user_idx}")
	void modifyUserInfo(UserDTO modifyUserDTO);
	
	
}
