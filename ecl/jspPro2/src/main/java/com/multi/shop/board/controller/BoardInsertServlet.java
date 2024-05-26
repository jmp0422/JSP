package com.multi.shop.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multi.shop.board.model.dto.BoardDTO;
import com.multi.shop.board.service.BoardService;
import com.multi.shop.board.service.BoardServiceImpl;
import com.multi.shop.member.model.dto.MemberDTO;
import com.mysql.cj.Session;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/board/insert")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
       

    public BoardInsertServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "";
		path = "/WEB-INF/views/board/insertform.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "";
		BoardDTO newBoard = new BoardDTO();
		HttpSession session = request.getSession();
		
		int category = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		
		System.out.println("작성자는  "+member.getName());
		
		newBoard.setCategoryCode(category);
		newBoard.setTitle(title);
		newBoard.setContent(content);
		newBoard.setWriter(member.getName());
		
		System.out.println("BoardInsert : " + newBoard);
		
		try {
			int result = boardService.insertBoard(newBoard);
			
			if (result > 0) {

				page = "/WEB-INF/views/common/success.jsp";

				request.setAttribute("successCode", "insertBoard");

			} else {

				page = "/WEB-INF/views/common/failed.jsp";

				request.setAttribute("message", "게시글 생성 실패!");
			}
		} catch (Exception e) {
			page = "/WEB-INF/views/common/failed.jsp";

			request.setAttribute("message", "게시글 생성 실패!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);

	}

}
