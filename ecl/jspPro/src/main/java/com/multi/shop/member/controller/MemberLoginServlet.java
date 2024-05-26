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
		// TODO Auto-generated method stub
		String id = request.getParameter("userId");
        String pw = request.getParameter("userPwd");
        
        MemberDTO member = new MemberDTO();
        member.setId(id);
        member.setPw(pw);
        
        System.out.println("memberController requestMember : " + member);
        
        String page = "";
        
        try {
        	
			MemberDTO loginMember = memberService.loginCheck(member);
			
			if (loginMember != null) {
				
				HttpSession session = request.getSession();
				
				session.setAttribute("loginMember", loginMember);
				
				response.sendRedirect(request.getContextPath());
				
			} else {
				page = "/WEB-INF/views/common/failed.jsp";

				request.setAttribute("message", "로그인 실패!");
				
				request.getRequestDispatcher(page).forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			page = "/WEB-INF/views/common/failed.jsp";

			request.setAttribute("message", "로그인 실패!");
			request.getRequestDispatcher(page).forward(request, response);
		}
        
	}

}
