package com.tjoeun.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ContentDTO {	
	private int content_idx;
	
	@NotBlank
	private String content_subject;
	
	@NotBlank
	private String content_text;
	
	// browser 가 보내는 파일 데이터를 담는 변수
	private MultipartFile upload_file;
	
	// database 에 저장된 file 이름을 담을 변수
	private String content_file;
	
	private int content_writer_idx;
	private int content_board_idx;
	private String content_date;
	
	// join한 다른 테이블의 값을 저장하기 위함
	private String content_writer_name;
}
