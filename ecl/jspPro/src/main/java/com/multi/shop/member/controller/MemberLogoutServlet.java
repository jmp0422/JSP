package com.multi.shop.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet("/member/logout") //로그아웃 서블릿
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션무효화 : 현재세션의 모든속성르 제거하고 세션을 무효화한다 - > 로그아웃
		request.getSession().invalidate();
		//홈페이지로 이동하도록 요청(redirect)
		response.sendRedirect(request.getContextPath());
	}

}
