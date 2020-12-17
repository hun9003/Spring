package com.itwillbs.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardBean;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.itwillbs.mapper.BoardMapper";

	@Override
	public Integer getMaxNum() {
		System.out.println("BoardDAOImpl - getBoardNum()");
		
		return sqlSession.selectOne(namespace+".getBoardNum");
	}

	@Override
	public void insertBoard(BoardBean bb) {
		System.out.println("BoardDAOImpl - insertBoard()");
		
		sqlSession.insert(namespace+".insertBoard",bb);
	}

}
