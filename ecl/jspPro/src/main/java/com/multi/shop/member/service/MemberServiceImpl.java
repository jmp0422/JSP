package com.multi.shop.member.service;

import static com.multi.shop.common.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;

import com.multi.shop.member.model.dao.MemberDAO;
import com.multi.shop.member.model.dto.MemberDTO;

public class MemberServiceImpl implements MemberService {
	
	private final MemberDAO memberDAO;
	
	public MemberServiceImpl() {
		memberDAO = new MemberDAO();
	}
	
	@Override
	public int insertMember(MemberDTO requestMember) throws Exception{
		// 세션연결
		SqlSession session = getSqlSession();
		
		int result = memberDAO.insertMember(session, requestMember);
		if(result > 0 ) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return result;
		
		// insertMember호출
		// 결과값에 따라 커밋 ,롤백
		
	}
}
