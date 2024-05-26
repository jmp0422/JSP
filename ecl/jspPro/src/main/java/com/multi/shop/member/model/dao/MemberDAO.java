package com.multi.shop.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.multi.shop.member.model.dto.MemberDTO;

public class MemberDAO {
	// memberMapper, id = "insertMember"
	public int insertMember(SqlSession session, MemberDTO newMember) throws Exception{
			
		return session.insert("memberMapper.insertMember", newMember);
	}
	
	public String selectEncryptedPwd(SqlSession sqlSession, MemberDTO member) {
		// TODO Auto-generated method stub	
		
		return sqlSession.selectOne("memberMapper.selectEncryptedPwd", member);
	}
	
	public MemberDTO selectLoginMember(SqlSession sqlSession, MemberDTO member) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectOne("memberMapper.selectLoginMember", member);
	}


}