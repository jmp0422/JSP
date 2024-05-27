package com.multi.shop.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multi.shop.member.model.dto.MemberDTO;
import com.multi.shop.member.service.MemberService;
import com.multi.shop.member.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login") //로그인서블릿
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //클라이언트의 post요청을 받아 로그인 처리를 수행
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라메터방식으로 id,pw 가져오기
		String id = request.getParameter("userId");
        String pw = request.getParameter("userPwd");
        
        MemberDTO member = new MemberDTO(); // memberdto 에 set하기
        member.setId(id);
        member.setPw(pw);
        
        System.out.println("memberController requestMember : " + member); //받아온 회원정보입력
        
        String page = "";
        
        try {
        	
			MemberDTO loginMember = memberService.loginCheck(member); //로그인체크매서드 실행
			
			if (loginMember != null) { 
				//로그인성공시 세션을 생성하고 로그인한 회원정보를 세션에 저장
				HttpSession session = request.getSession();
				
				session.setAttribute("loginMember", loginMember);
				
				response.sendRedirect(request.getContextPath());
				
			} else { //로그인실패시
				page = "/WEB-INF/views/common/failed.jsp";

				request.setAttribute("message", "로그인 실패!"); //실패메세지 출력
				
				request.getRequestDispatcher(page).forward(request, response);  //page에설정된 실패페이지로 이동
			}
			
		} catch (Exception e) {
			// 예외발생시 실패메세지 출력 및 실패페이지 포워드
			e.printStackTrace();
			page = "/WEB-INF/views/common/failed.jsp";

			request.setAttribute("message", "로그인 실패!");
			request.getRequestDispatcher(page).forward(request, response);
		}
        
	}

}
