package com.itwillbs.controller;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardBean;
import com.itwillbs.domain.PageBean;
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
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(Model model,HttpServletRequest request) {
		
		PageBean pb = new PageBean();
		pb.setPageSize(10);
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pb.setPageNum("1");
		} else {
			pb.setPageNum(pageNum);
		}
		
		List<BoardBean> boardList = boardService.getBoardList(pb);
		
		pb.setCount(boardService.getBoardCount());
		
		model.addAttribute("boardList",boardList);
		model.addAttribute("pb",pb);
		return "board/list";
	}
	
	@RequestMapping(value = "/board/content", method = RequestMethod.GET)
	public String content(Model model, HttpServletRequest request) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardBean bb = boardService.getBoard(num);
		boardService.updateReadcount(num);
		model.addAttribute("bb",bb);
		
		return "board/content";
	}
}
