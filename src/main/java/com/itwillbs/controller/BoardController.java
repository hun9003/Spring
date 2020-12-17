package com.itwillbs.controller;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardBean;
import com.itwillbs.service.BoardService;

@Controller
public class BoardController {
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write() {
		return "board/writeForm";
	}
	
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String writePost(BoardBean bb) {
		
		boardService.insertBoard(bb);
		
		return "redirect:/board/list";
	}
}
