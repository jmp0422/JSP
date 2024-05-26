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
	}
	
	@Override
	public int insertMember(MemberDTO newMember) throws Exception {
		// TODO Auto-generated method stub
		// 세션 연결
		// insertMember 호출
		// 결과 값에 따라 커밋/롤백
		// 결과 값 리턴
		SqlSession sqlSession = getSqlSession();
		
		int result = memberDAO.insertMember(sqlSession, newMember);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
        sqlSession.close();
		
		return result;
	}

	@Override
	public MemberDTO loginCheck(MemberDTO member) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = getSqlSession();
		MemberDTO loginMember = null;
		/* 로그인 요청한 원문 비밀번호화 저장되어있는 암호화된 비밀번호가 일치하는지 확인한다. 
		 * //matches(평문, 암호화) -> true, false 반환
		 * */
		
		String encPwd = memberDAO.selectEncryptedPwd(sqlSession, member);
		System.out.println("encPwd : " + encPwd);
		System.out.println("getPw : " + member.getPw());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(passwordEncoder.matches(member.getPw(), encPwd)) {
			loginMember = memberDAO.selectLoginMember(sqlSession, member);
		}
		sqlSession.close();
		return loginMember;
	}

}
