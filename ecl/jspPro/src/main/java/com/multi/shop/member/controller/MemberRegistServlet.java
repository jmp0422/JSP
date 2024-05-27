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
@WebServlet("/member/insert") //회원가입 기능을 처리하는 서블릿
public class MemberRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl(); // memberservice 인터페이스를 구현한 memberserviceimpl 객체를 생성하여 사용
       
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
    
    //회원가입처리(get요청처리)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = "/WEB-INF/views/member/insert.jsp"; // 회원가입페이지의 경로설정

		request.getRequestDispatcher(path).forward(request, response); //설정된경로로 포워드하여 페이지 표시
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//회원 가입 처리(post요청처리)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청으로부터 회원정보를 파라미터로 받아옴
		String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        
        // 회원정보를 dto를 생성하여 set하기
        MemberDTO member = new MemberDTO();
        member.setId(id);
        member.setPw(pw);
        member.setName(name);
        member.setTel(tel);

        System.out.println("memberController requestMember : " + member); //회원정보가 잘들어갔는지 확인차 입력
		
        //회원가입결과에 따라 페이지이동
		String page = "";
		try {
			int result = memberService.insertMember(member); //회원가입처리 메소드 호출
			
			System.out.println("memberController result : " + result);	 //회원가입결과 출력

			if (result > 0) {
				//회원가입성공시 성공페이지로 이동
				page = "/WEB-INF/views/common/success.jsp";

				request.setAttribute("successCode", "insertMember"); //success.jsp에있는 successcode매서드의 케이스중 insertMember의 케이스를 실행하기위한 설정

			} else {
				//회원가입실패시 실패페이지로 이동
				page = "/WEB-INF/views/common/failed.jsp";

				request.setAttribute("message", "회원 가입 실패!");
			}
		} catch (Exception e) {
			//회원가입 과정 중 예외 발생 시 실패페이지로 이동
			page = "/WEB-INF/views/common/failed.jsp";

			request.setAttribute("message", "회원 가입 실패!"); 
		}
		
		request.getRequestDispatcher(page).forward(request, response); //설정된페이지로 포워드하여 결과표시
	}

}
