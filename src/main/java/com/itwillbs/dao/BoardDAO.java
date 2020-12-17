package com.itwillbs.dao;

import com.itwillbs.domain.BoardBean;

public interface BoardDAO {

	Integer getMaxNum();

	void insertBoard(BoardBean bb);

}
