package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.domain.MemberBean;

@Service
public class MemberServiceImpl implements MemberService{

	// MemberDAO 인터페이스 멤버변수 선언 @Inject 주입 객체생성
	// 의존관계주입 객체생성 
	@Inject
	private MemberDAO memberDAO;
	
	@Override
	public void insertMember(MemberBean mb) {
		System.out.println("MemberServiceImpl insertMember()");
		//메서드 호출
		memberDAO.insertMember(mb);
	}

	@Override
	public MemberBean userCheck(MemberBean mb) {
		System.out.println("MemberServiceImpl userCheck()");
		
		return memberDAO.userCheck(mb);
	}

	@Override
	public MemberBean getMember(String id) {
		System.out.println("MemberServiceImpl getMember()");
		
		return memberDAO.getMember(id);
	}

	@Override
	public void updateMember(MemberBean mb) {
		System.out.println("MemberServiceImpl getMember()");
		
		memberDAO.updateMember(mb);
	}

	@Override
	public void deleteMember(MemberBean mb) {
		System.out.println("MemberServiceImpl deleteMember()");
		
		memberDAO.deleteMember(mb);
	}

	@Override
	public List<MemberBean> getMemberList() {
		System.out.println("MemberServiceImpl getMemberList()");
		
		return memberDAO.getMemberList();
	}

}
