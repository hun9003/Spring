package com.itwillbs.service;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardBean;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;

	@Override
	public int getMaxNum() {
		System.out.println("BoardServiceImpl - getMaxNum()");
		
		return boardDAO.getMaxNum();
	}

	@Override
	public void insertBoard(BoardBean bb) {
		System.out.println("BoardServiceImpl - getBoardNum()");
		if(boardDAO.getMaxNum()==null) {
			bb.setNum(1);
			bb.setRe_ref(1);
		}
		bb.setNum(boardDAO.getMaxNum()+1);
		bb.setRe_ref(boardDAO.getMaxNum()+1);
		bb.setDate(new Timestamp(System.currentTimeMillis()));
		bb.setReadcount(0);
		bb.setRe_seq(0);
		bb.setRe_lev(0);
		boardDAO.insertBoard(bb);
	}
}
