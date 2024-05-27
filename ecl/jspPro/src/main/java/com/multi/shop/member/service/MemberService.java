package com.multi.shop.member.service;

import com.multi.shop.member.model.dto.MemberDTO;
//회원서비스를 정의하는 인터페이스
public interface MemberService {
	//신규회원 등록 메소드 선언
	int insertMember(MemberDTO newMember) throws Exception ;
	// 회원로그인을 확인하는 메소드 선언
	MemberDTO loginCheck(MemberDTO member) throws Exception ;
	
}
