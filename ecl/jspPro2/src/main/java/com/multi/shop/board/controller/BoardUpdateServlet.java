package com.multi.shop.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.shop.board.model.dto.BoardDTO;
import com.multi.shop.board.service.BoardService;
import com.multi.shop.board.service.BoardServiceImpl;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "";
		
		int bno = Integer.parseInt(request.getParameter("no"));
		
		
		try {
			BoardDTO dto = boardService.selectBoard(bno);

			request.setAttribute("b",  dto);
			System.out.println(dto);
			
			path = "/WEB-INF/views/board/updateform.jsp";


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("msg", "게시물 목록 조회 실패!");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDTO updBoard = new BoardDTO();
		int result = 0;
		String page = "";
		
		int bNum  = Integer.parseInt(request.getParameter("no"));
		int category = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		updBoard.setNo(bNum);
		updBoard.setCategoryCode(category);
		updBoard.setTitle(title);
		updBoard.setContent(content);
		
		try {
			result = boardService.updateBoard(updBoard);
			
			if (result > 0) {

				page = "/WEB-INF/views/common/success.jsp";

				request.setAttribute("successCode", "updateBoard");

			} else {

				page = "/WEB-INF/views/common/failed.jsp";

				request.setAttribute("message", "게시글 수정 실패!");
			}
		} catch (Exception e) {
			page = "/WEB-INF/views/common/failed.jsp";

			request.setAttribute("message", "게시글 수정 실패!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);

	}
}
