package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberBean;
import com.itwillbs.service.MemberService;

@Controller
public class MemberController {
	// 부모인터페이스 멤버변수 선언 => @Inject 자동주입, 자동객체생성
	// memberService 멤버변수 이름 인터페이스 이름 소문자 시작 하게 약속
	@Inject
	private MemberService memberService;
	
//http://localhost:8080/myweb2/member/insert　　/member/insert　가상주소
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
//		/WEB-INF/views/member/insertForm.jsp
		return "member/insertForm";
	}
	
//	　http://localhost:8080/myweb/member/insert　　　/member/insert　가상주소 POST방식
	@RequestMapping(value = "/member/insert", method = RequestMethod.POST)
	public String insertPost(MemberBean mb) {
		System.out.println(mb.getId());
		System.out.println(mb.getPass());
		System.out.println(mb.getName());
		// MemberService 인터페이스 상속 클래스 MemberServiceImpl  @Service
		// MemberDAO 인터페이스 상속 클래스 MemberDAOImpl  @Repository
		memberService.insertMember(mb);
		
//		http://localhost:8080/myweb/login 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/member/login";
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
//		/WEB-INF/views/member/insertForm.jsp
		return "member/loginForm";
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String loginPost(MemberBean mb, HttpSession session, Model model) {
		System.out.println(mb.getId());
		System.out.println(mb.getPass());
		// MemberService 인터페이스 상속 클래스 MemberServiceImpl  @Service
		// MemberDAO 인터페이스 상속 클래스 MemberDAOImpl  @Repository
		MemberBean mb2 = memberService.userCheck(mb);
		if(mb2 != null) {
			session.setAttribute("id", mb.getId());
			return "redirect:/member/main";
		} else {
			model.addAttribute("msg","아이디 또는 비밀번호가 일치하지 않습니다");
			return "member/msg";
		}
//		http://localhost:8080/myweb/login 가상주소 이동
		// response.sendRedirect() 같음
	}
	
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
//		/WEB-INF/views/member/insertForm.jsp
		return "member/main";
	}
	
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
//		/WEB-INF/views/member/insertForm.jsp
		session.invalidate();
		return "redirect:/member/main";
	}
	
	@RequestMapping(value = "/member/info", method = RequestMethod.GET)
	public String info(HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		MemberBean mb = memberService.getMember(id);
		model.addAttribute("mb",mb);
		return "member/info";
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		MemberBean mb = memberService.getMember(id);
		model.addAttribute("mb",mb);
		return "member/updateForm";
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String updatePost(MemberBean mb) {
		// MemberService 인터페이스 상속 클래스 MemberServiceImpl  @Service
		// MemberDAO 인터페이스 상속 클래스 MemberDAOImpl  @Repository
		MemberBean mb2 = memberService.userCheck(mb);
		if(mb2 != null) {
			memberService.updateMember(mb);
		}
		
//		http://localhost:8080/myweb/login 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/member/info";
	}
	
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete() {
		return "member/deleteForm";
	}
	
	@RequestMapping(value = "/member/delete", method = RequestMethod.POST)
	public String deletePost(MemberBean mb, HttpSession session) {
		// MemberService 인터페이스 상속 클래스 MemberServiceImpl  @Service
		// MemberDAO 인터페이스 상속 클래스 MemberDAOImpl  @Repository
		MemberBean mb2 = memberService.userCheck(mb);
		if(mb2 != null) {
			memberService.deleteMember(mb);
			session.invalidate();
		}
		
//		http://localhost:8080/myweb/login 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/member/main";
	}
	
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<MemberBean> mbList = memberService.getMemberList();
		model.addAttribute("mbList",mbList);
		return "member/list";
	}
}
