package com.tjoeun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.dto.ContentDTO;
import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor {

	private UserDTO loginUserDTO;
	private BoardService boardService;
	
  public CheckWriterInterceptor(UserDTO loginUserDTO, BoardService boardService) {
  	this.loginUserDTO = loginUserDTO;
  	this.boardService = boardService;
  }
	
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  	
  	String strContentIdx = request.getParameter("content_idx");
  	int contentIdx = Integer.parseInt(strContentIdx);
  	ContentDTO currentContentDTO = boardService.getContentInfo(contentIdx);
  	
  	if(currentContentDTO.getContent_writer_idx() != loginUserDTO.getUser_idx()) {
  		String ContextPath = request.getContextPath();
  		response.sendRedirect(ContextPath + "/board/not_writer");
  		return false;
  	}
  	
  	return true;
  }
  
}
