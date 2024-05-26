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

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/board/insert")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = "/WEB-INF/views/board/insertform.jsp";

		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = ((MemberDTO) request.getSession().getAttribute("loginMember")).getId();
		
		BoardDTO board = new BoardDTO();
		
		board.setCategoryCode(Integer.parseInt(category));
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		System.out.println("doPost. boardDTO : " + board);
		
		int result;
		String path = "";
		
		try {
			result = boardService.insertBoard(board);
			if (result > 0) {

				path = "/WEB-INF/views/common/success.jsp";

				request.setAttribute("successCode", "insertBoard");

			} else {

				path = "/WEB-INF/views/common/failed.jsp";

				request.setAttribute("message", "게시글 작성 실패!");
			}
		} catch (Exception e) {
			path = "/WEB-INF/views/common/failed.jsp";

			request.setAttribute("message", "게시글 작성 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

}
