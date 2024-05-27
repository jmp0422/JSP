package com.multi.shop.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.multi.shop.member.model.dto.MemberDTO;

public class MemberDAO {
	// 신규회원 등록 메소드
	public int insertMember(SqlSession session, MemberDTO newMember) throws Exception{
			
		return session.insert("memberMapper.insertMember", newMember); //mapper에있는 insertMember 쿼리를 실행하여 신규회원을 데이터베이스에 삽입, 그후 반환
	}
	// 암호화된 비밀번호를 데이터베이스에서 조회하는 메소드
	public String selectEncryptedPwd(SqlSession sqlSession, MemberDTO member) {
		// TODO Auto-generated method stub	
		
		return sqlSession.selectOne("memberMapper.selectEncryptedPwd", member); //mapper에있는 selectEncryptedPwd 쿼리를 실행하여 회원의 암호화된 비밀번호를 조회 후 반환
	}
	// 로그인한 회원 정보를 데이터베이스에서 조회하는 메소드
	public MemberDTO selectLoginMember(SqlSession sqlSession, MemberDTO member) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectOne("memberMapper.selectLoginMember", member); //mapper에있는selectLoginMember 쿼리를 실행하여 회원의 정보를 조회 후 반환
	}


}