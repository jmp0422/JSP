package com.multi.shop.member.service;

import static com.multi.shop.common.Template.getSqlSession;
//스태틱으로 세션연결해야함

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

		// 세션 연결
		SqlSession sqlSession = getSqlSession();
		// insertMember 호출
		int result = memberDAO.insertMember(sqlSession, newMember); //dao를 통해 새회원 정보를 데이터베이스에 삽입
		// 결과 값에 따라 커밋/롤백
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
        sqlSession.close();
        // 결과 값 리턴
		return result;
	}

	@Override
	public MemberDTO loginCheck(MemberDTO member) throws Exception {

		SqlSession sqlSession = getSqlSession();
		MemberDTO loginMember = null; //로그인한 회원의 정보를 담을 객체생성
		/* 로그인 요청한 원문 비밀번호화 저장되어있는 암호화된 비밀번호가 일치하는지 확인한다. 
		 * //matches(평문, 암호화) -> true, false 반환
		 * */
		
		//DAO를 통해 데이터베이스에서 암호화된 비밀번호를 가져옴
		String encPwd = memberDAO.selectEncryptedPwd(sqlSession, member);
		System.out.println("encPwd : " + encPwd);
		System.out.println("getPw : " + member.getPw());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//가져온 암호화된 비밀번호와 사용자가 입력한 비밀번호를 비교하여 일치하는지 확인
		if(passwordEncoder.matches(member.getPw(), encPwd)) {
			loginMember = memberDAO.selectLoginMember(sqlSession, member); //일치하면 DAO를 통해 회원정보를 가져옴
		}
		sqlSession.close(); //세션닫기
		return loginMember; //로그인한 회원의 정보반환
	}

}
