package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardBean;
import com.itwillbs.domain.PageBean;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.itwillbs.mapper.BoardMapper";

	@Override
	public Integer getMaxNum() {
		System.out.println("BoardDAOImpl - getBoardNum()");
		
		return sqlSession.selectOne(namespace+".getMaxNum");
	}

	@Override
	public void insertBoard(BoardBean bb) {
		System.out.println("BoardDAOImpl - insertBoard()");
		
		sqlSession.insert(namespace+".insertBoard",bb);
	}

	@Override
	public List<BoardBean> getBoardList(PageBean pb) {
		System.out.println("BoardDAOImpl - getBoardList()");
		
		return sqlSession.selectList(namespace+".getBoardList",pb);
	}

	@Override
	public Integer getBoardCount() {
		System.out.println("BoardDAOImpl - getBoardCount()");
		return sqlSession.selectOne(namespace+".getBoardCount");
	}

	@Override
	public BoardBean getBoard(int num) {
		System.out.println("BoardDAOImpl - getBoard()");
		return sqlSession.selectOne(namespace+".getBoard",num);
	}

	@Override
	public void updateReadcount(int num) {
		System.out.println("BoardDAOImpl - updateReadcount()");
		sqlSession.update(namespace+".updateReadcount",num);
	}

}
