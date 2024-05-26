package com.multi.shop.member.model.dao;

import org.apache.ibatis.session.SqlSession;
import com.multi.shop.member.model.dto.MemberDTO;

public class MemberDAO {
	public int insertMember(SqlSession session, MemberDTo requestMember) {
		
		return session.insert("memberMapper.insertMember", requestMember);
		
	}
}
