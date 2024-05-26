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
@WebServlet("/member/login")
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDTO requestMember = new MemberDTO();
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPwd");
		
		
		requestMember.setId(id);
		requestMember.setPw(pw);
		
		System.out.println(requestMember);
		
		String page = "";
		
		try {
			MemberDTO loginMember = memberService.login(requestMember);
			
			if(loginMember != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginMember", loginMember);
				
				// request.getContextPath는 프로젝트 Path만 가져옴 원본 로직 처리 후 원본 경로 이동하기 위함
				response.sendRedirect(request.getContextPath());
			} else {
				page = "/WEB-INF/views/common/failed.jsp";

				request.setAttribute("message", "로그인 실패!");
				
				request.getRequestDispatcher(page).forward(request, response);
			}
			
		} catch (Exception e) {
			page = "/WEB-INF/views/common/failed.jsp";

			request.setAttribute("message", "로그인 실패!");
			
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

}
