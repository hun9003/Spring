package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardBean;
import com.itwillbs.domain.PageBean;

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
		System.out.println("BoardServiceImpl - insertBoard()");
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

	@Override
	public List<BoardBean> getBoardList(PageBean pb) {
		System.out.println("BoardServiceImpl - getBoardList()");
		pb.setCurrentPage(Integer.parseInt(pb.getPageNum()));
		
		int startRow = (pb.getCurrentPage()-1)*pb.getPageSize()+1-1;
		
		
		pb.setStartRow(startRow);
		return boardDAO.getBoardList(pb);
	}

	@Override
	public int getBoardCount() {
		int count = 0;
		if(boardDAO.getBoardCount() != null) {
			count = boardDAO.getBoardCount();
		}
		return count;
	}

	@Override
	public BoardBean getBoard(int num) {
		return boardDAO.getBoard(num);
	}

	@Override
	public void updateReadcount(int num) {
		boardDAO.updateReadcount(num);
		
	}
}
