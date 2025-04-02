package com.tjoeun.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dto.ContentDTO;
import com.tjoeun.dto.PageDTO;
import com.tjoeun.dto.UserDTO;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Value("${page.listcount}")
	private int page_listcount;
	
	@Value("${page.pagenationcount}")
	private int page_pagenationcount;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	
	
	private String saveUploadFile(MultipartFile upload_file) {
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try {
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file_name;
	}

	public void addContentInfo(ContentDTO writeContentDTO) {
		
		MultipartFile upload_file = writeContentDTO.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			System.out.println("파일이름 : " + file_name);
			
			// file 을 첨부하지 않으면 
			// content_file 이라는 멤버변수에는 null 값이 할당 됨
			writeContentDTO.setContent_file(file_name);
		}
		
		/*
		System.out.println(writeContentDTO.getContent_subject());
		System.out.println(writeContentDTO.getContent_text());
		System.out.println(writeContentDTO.getUpload_file());
		System.out.println(writeContentDTO.getUpload_file().getSize());
		*/
		
		writeContentDTO.setContent_writer_idx(loginUserDTO.getUser_idx());		
		
		boardDAO.addContentInfo(writeContentDTO);
		
	}
	
	public String getBoardInfoName(int board_info_idx) {
		String board_info_name = boardDAO.getBoardInfoName(board_info_idx);
		return board_info_name;
	}
	
	public List<ContentDTO> getContentList(int board_info_idx, int page){
		
		/*
		 	0(page) ->  0(start)
		 	1(page) -> 10(start)
		 	2(page) -> 20(start) 
		 	한 page 당 게시글의 개수 : page_listcount
		 */
		int start = (page - 1) * this.page_listcount;
		RowBounds rowBounds = new RowBounds(start, page_listcount);
		
		List<ContentDTO> contentDTOList = boardDAO.getContentList(board_info_idx, rowBounds);
		
		return contentDTOList;
	}
	
	public ContentDTO getContentInfo(int content_idx) {
		ContentDTO contentDTO = boardDAO.getContentInfo(content_idx);
		return contentDTO;
	}
	
	public void modifyContentInfo(ContentDTO modifyContentDTO) {
		
		MultipartFile upload_file = modifyContentDTO.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			System.out.println("파일이름 : " + file_name);
			
			// file 을 첨부하지 않으면 
			// content_file 이라는 멤버변수에는 null 값이 할당 됨
			// 이와 관련해서 오류 발생을 방지해 주는 것이
			// Mapper 의 query 문 중 #{content_file, jdbcType=VARCHAR} 부분
			modifyContentDTO.setContent_file(file_name);
		}

		boardDAO.modifyContentInfo(modifyContentDTO);
	}
	
	public void deleteContentInfo(int content_idx) {
		boardDAO.deleteContentInfo(content_idx);
	}
	
	// contentCount   : 게시판 당 전체 게시글 개수
	// currentPage     : 현재 페이지 번호
	// page_listcount : 페이지 당 게시글 개수
	// page_pagenationcount  : 페이지 버튼의 개수  - 화면 아랫쪽에 나오는 페이지 이동 버튼 개수
	public PageDTO getContentCount(int content_board_idx, int currentPage) {
		int contentCount = boardDAO.getContentCount(content_board_idx);
		
		PageDTO pageDTO = new PageDTO(contentCount, currentPage, page_listcount, page_pagenationcount);
		return pageDTO;
	}

	
}
