package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tjoeun.dto.BoardInfoDTO;

public interface TopMenuMapper {
	
	@Select("select * from board_info_table order by board_info_idx")
	List<BoardInfoDTO> getTopMenuList();

}
