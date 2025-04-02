package com.tjoeun.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.dto.BoardInfoDTO;
import com.tjoeun.mapper.TopMenuMapper;

@Repository
public class TopMenuDAO {

	// IoC 	
	@Autowired
	private TopMenuMapper topMenuMapper;
	
	public List<BoardInfoDTO> getTopMenuList() {
		List<BoardInfoDTO> topMenuList = topMenuMapper.getTopMenuList();
		return topMenuList;
	}
	
}
