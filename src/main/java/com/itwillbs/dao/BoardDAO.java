package com.itwillbs.dao;

import java.util.List;

import com.itwillbs.domain.BoardBean;
import com.itwillbs.domain.PageBean;

public interface BoardDAO {

	Integer getMaxNum();

	void insertBoard(BoardBean bb);

	List<BoardBean> getBoardList(PageBean pb);
	
	Integer getBoardCount();

	BoardBean getBoard(int num);

	void updateReadcount(int num);
}
