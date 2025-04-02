package com.tjoeun.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.dto.ContentDTO;
import com.tjoeun.dto.PageDTO;
import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
										 @RequestParam(value="page", defaultValue="1") int page, 
										 Model model) {
		
		String board_info_name = boardService.getBoardInfoName(board_info_idx);
		List<ContentDTO> contentDTOList = boardService.getContentList(board_info_idx, page);
		
		PageDTO pageDTO = boardService.getContentCount(board_info_idx, page);
		
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("board_info_name", board_info_name);
		model.addAttribute("contentDTOList",contentDTOList);
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("page", page);
		
		return "board/main";
	}

	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx") int board_info_idx,
										 @RequestParam("content_idx") int content_idx,
										 @RequestParam("page") int page,
										 Model model) {

		ContentDTO readContentDTO = boardService.getContentInfo(content_idx);
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("readContentDTO", readContentDTO);
		model.addAttribute("loginUserDTO", loginUserDTO);
		model.addAttribute("page", page);
		
		return "board/read";
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentDTO") ContentDTO writeContentDTO,
											@RequestParam("board_info_idx") int board_info_idx) {
		writeContentDTO.setContent_board_idx(board_info_idx);
		return "board/write";
	}
	
	@PostMapping("/writeProcedure")
	public String writeProcedure(@Valid @ModelAttribute("writeContentDTO") ContentDTO writeContentDTO,
															 BindingResult result) {
		
		if(result.hasErrors()) {
			return "board/write";
		}		
		
		boardService.addContentInfo(writeContentDTO);	
		
		return "board/writeSuccess";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx,
											 @RequestParam("content_idx") int content_idx,
											 @RequestParam("page") int page,
											 @ModelAttribute("modifyContentDTO") ContentDTO modifyContentDTO, 
											 Model model) {
		
		ContentDTO tmpContentDTO = boardService.getContentInfo(content_idx);
		
		modifyContentDTO.setContent_writer_name(tmpContentDTO.getContent_writer_name());
		modifyContentDTO.setContent_date(tmpContentDTO.getContent_date());
		modifyContentDTO.setContent_subject(tmpContentDTO.getContent_subject());
		modifyContentDTO.setContent_text(tmpContentDTO.getContent_text());
		modifyContentDTO.setContent_file(tmpContentDTO.getContent_file());
		modifyContentDTO.setContent_writer_idx(tmpContentDTO.getContent_writer_idx());
		modifyContentDTO.setContent_board_idx(board_info_idx);
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("page", page);
		return "board/modify";
	}
	
	@PostMapping("/modifyProcedure")
	public String modifyProcedure(@Valid @ModelAttribute("modifyContentDTO") ContentDTO modifyContentDTO,
															  BindingResult result, Model model,
															  @RequestParam("page") int page ) {
		
		model.addAttribute("page", page);
		
		if(result.hasErrors()) {
			
			return "board/modify";
		}
		
		boardService.modifyContentInfo(modifyContentDTO);
		
		
		return "board/modifySuccess";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("board_info_idx") int board_info_idx,
                			 @RequestParam("content_idx") int content_idx,
                			 Model model) {
		
		boardService.deleteContentInfo(content_idx);
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		return "board/delete";
	}
	
	@GetMapping("/not_writer")
	public String notWriter() {
		return "board/not_writer";
	}
	
}
