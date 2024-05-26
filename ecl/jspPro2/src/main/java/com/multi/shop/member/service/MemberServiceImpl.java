package com.multi.shop.member.service;

import static com.multi.shop.common.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.multi.shop.member.model.dao.MemberDAO;
import com.multi.shop.member.model.dto.MemberDTO;

public class MemberServiceImpl implements MemberService {
	private final MemberDAO memberDAO;
	
	
	public MemberServiceImpl() {
		memberDAO = new MemberDAO();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insertMember(MemberDTO requestMember) throws Exception {
		int result = 0;
		
		SqlSession sqlSession = getSqlSession();
		
		System.out.println(requestMember);
		
		result = memberDAO.insertMember(sqlSession, requestMember);
		
		if (result > 0) {
			System.out.println("성공");
			sqlSession.commit();
		} else {
			System.out.println("실패");
			sqlSession.rollback();
		}
			
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public MemberDTO login(MemberDTO requestMember) throws Exception {
		SqlSession session = getSqlSession();
		
		MemberDTO loginMember = null;
		/* 로그인 요청한 원문 비밀번호화 저장되어있는 암호화된 비밀번호가 일치하는지 확인한다. 
		 * //matches(평문, 암호화) -> true, false 반환
		 * */
		
		String encPwd = memberDAO.selectEncryptedPwd(session, requestMember);
		System.out.println("encPwd : " + encPwd);
		System.out.println("getPwd : " + requestMember.getPw());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(passwordEncoder.matches(requestMember.getPw(), encPwd)) {
			loginMember = memberDAO.selectLoginMember(session, requestMember);
		}
		session.close();
		
		return loginMember;
	}

}
