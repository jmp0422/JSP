package com.multi.shop.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.shop.member.model.dto.MemberDTO;
import com.multi.shop.member.service.MemberService;
import com.multi.shop.member.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberRegistServlet
 */
@WebServlet("/member/insert")
public class MemberRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService = new MemberServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/insert.jsp";

		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//받아오기
		request.setCharacterEncoding("UTF-8");
		MemberDTO requestMember = new MemberDTO();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		//dto에담기
		
		requestMember.setId(id);
		requestMember.setPw(pw);
		requestMember.setName(name);
		requestMember.setTel(tel);
		
		//출력하기
		System.out.println("memberController requestMember : " + requestMember);
		String page = "";
		try {
			int result = memberService.insertMember(requestMember);
			
			System.out.println("memberController result : " + result);

			

			if (result > 0) {

				page = "/WEB-INF/views/common/success.jsp";

				request.setAttribute("successCode", "insertMember");

			} else {

				page = "/WEB-INF/views/common/failed.jsp";

				request.setAttribute("message", "회원 가입 실패!");
			}
		} catch (Exception e) {
			page = "/WEB-INF/views/common/failed.jsp";

			request.setAttribute("message", "회원 가입 실패!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}
}
