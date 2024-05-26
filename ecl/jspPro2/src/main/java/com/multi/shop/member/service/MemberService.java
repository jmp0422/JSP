package com.multi.shop.member.service;

import java.util.ArrayList;

import com.multi.shop.member.model.dto.MemberDTO;

public interface MemberService {
	
	
	int insertMember(MemberDTO requestMember) throws Exception ;

	MemberDTO login(MemberDTO requestMember) throws Exception ;

}

