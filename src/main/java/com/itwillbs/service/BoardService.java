package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardBean;
import com.itwillbs.domain.PageBean;

public interface BoardService {

	int getMaxNum();

	void insertBoard(BoardBean bb);

	List<BoardBean> getBoardList(PageBean pb);

	int getBoardCount();

	BoardBean getBoard(int num);

	void updateReadcount(int num);


}
