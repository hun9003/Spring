package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberBean;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.itwillbs.mapper.MemberMapper";
	
	@Override
	public void insertMember(MemberBean mb) {
		System.out.println("MemberDAOImpl insertMember()");
//		sqlSession.insert(statement,값);
//		sqlSession.update(statement,값);
//		sqlSession.delete(statement,값);
//		sqlSession.selectList(statement,값);
//		sqlSession.selectOne(statement,값);
		
		sqlSession.insert(namespace+".insertMember",mb);
	}

	@Override
	public MemberBean userCheck(MemberBean mb) {
		System.out.println("MemberDAOImpl userCheck()");
		return sqlSession.selectOne(namespace+".userCheck",mb);
	
	}

	@Override
	public MemberBean getMember(String id) {
		System.out.println("MemberDAOImpl getMember()");
		return sqlSession.selectOne(namespace+".getMember",id);
	}

	@Override
	public void updateMember(MemberBean mb) {
		System.out.println("MemberDAOImpl updateMember()");
		sqlSession.update(namespace+".updateMember",mb);
	}

	@Override
	public void deleteMember(MemberBean mb) {
		System.out.println("MemberDAOImpl deleteMember()");
		sqlSession.delete(namespace+".deleteMember",mb);
	}

	@Override
	public List<MemberBean> getMemberList() {
		System.out.println("MemberDAOImpl getMemberList()");
		return sqlSession.selectList(namespace+".getMemberList");
	}

}
