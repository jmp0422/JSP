package com.multi.shop.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multi.shop.board.service.BoardService;
import com.multi.shop.board.service.BoardServiceImpl;
import com.multi.shop.member.model.dto.MemberDTO;


@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
       

    public BoardDeleteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "";
		
		int bNum = Integer.parseInt(request.getParameter("no")); // get으로 쿼리스트링을 통해 받아옴
		
		int result = 0;
		
		try {
			result = boardService.boardDelete(bNum);
			
			if (result > 0) {
				page = "/WEB-INF/views/common/success.jsp";
				
				request.setAttribute("successCode", "deleteBoard");
			} else {
				page = "/WEB-INF/views/common/failed.jsp";

				request.setAttribute("message", "게시글 삭제 실패!");
			}
			
		} catch (Exception e) {
			page = "/WEB-INF/views/common/failed.jsp";
			
			request.setAttribute("message", "게시글 삭제 실패!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
