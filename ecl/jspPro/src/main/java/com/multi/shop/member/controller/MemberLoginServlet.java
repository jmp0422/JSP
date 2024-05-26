package com.multi.shop.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multi.shop.member.service.MemberService;
import com.multi.shop.member.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberService memberService = new MemberServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("userId");
		String memberPwd = request.getParameter("userPwd");
		
		System.out.println("memberId 는 " + memberId);
		System.out.println("memberPwd 는 " + memberPwd);
		
		try {
			MemberDto loginMember = memberService.loginCheck(requestMember);
			
			if(loginMember != null) {
				
				HttpSession session = request.getSession();
				session.setAttribute("loginMember", loginMember);
				response.sendRedirect(request.getContextPath());
				
				
			}else {
				page = "/WEB-INF/vies/common/failed.jsp";
				
				request.setAttribute("message", "로그인실패!";)
			}
		} catch (Exception e) {
			page = "/WEB-INF/vies/common/failed.jsp";
			
			request.setAttribute("message", "로그인실패!";)
		}
	}

}
