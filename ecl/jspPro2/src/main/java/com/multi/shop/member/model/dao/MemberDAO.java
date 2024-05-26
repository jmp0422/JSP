package com.multi.shop.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.multi.shop.member.model.dto.MemberDTO;

public class MemberDAO {
	public MemberDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public int insertMember(SqlSession session, MemberDTO dto) {
		
		return session.insert("memberMapper.insertMember", dto);
	}

	public String selectEncryptedPwd(SqlSession session, MemberDTO requestMember) {
		
		return session.selectOne("memberMapper.selectEncryptedPwd", requestMember);
		
	}

	public MemberDTO selectLoginMember(SqlSession session, MemberDTO requestMember) {
		
		return session.selectOne("memberMapper.selectLoginMember", requestMember);
	}

}
