package com.multi.shop.member.service;

import com.multi.shop.member.model.dto.MemberDTO;

public interface MemberService {

	int insertMember(MemberDTO newMember) throws Exception ;

	MemberDTO loginCheck(MemberDTO member) throws Exception ;
	
}
