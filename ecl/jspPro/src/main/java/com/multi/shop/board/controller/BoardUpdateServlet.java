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
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the board number from the request parameter
        String noStr = request.getParameter("no");
        int no = Integer.parseInt(noStr);

        String path = "";
        try {
            // Get the board information based on the board number
            BoardDTO board = boardService.selectBoard(no);
            request.setAttribute("b", board);
            path = "/WEB-INF/views/board/board_update.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            path = "/WEB-INF/views/common/errorPage.jsp";
            request.setAttribute("msg", "게시물 수정 페이지 조회 실패!");
        }

        request.getRequestDispatcher(path).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the updated board information from the request parameters
        String noStr = request.getParameter("no");
        int no = Integer.parseInt(noStr);
        String category = request.getParameter("category");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // Get the writer information from the session
        HttpSession session = request.getSession();
        MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
        String writer = loginMember.getId();

        // Create a BoardDTO object with the updated information
        BoardDTO board = new BoardDTO();
        board.setNo(no);
        board.setCategoryCode(Integer.parseInt(category));
        board.setTitle(title);
        board.setContent(content);
        board.setWriter(writer);

        System.out.println("update. doPost. boardDTO : " + board);

        int result;
        String path = "";

        try {
            // Update the board information
            result = boardService.updateBoard(board);
            if (result > 0) {
                path = "/WEB-INF/views/common/success.jsp";
                request.setAttribute("successCode", "updateBoard");
            } else {
                path = "/WEB-INF/views/common/failed.jsp";
                request.setAttribute("message", "게시글 수정 실패!");
            }
        } catch (Exception e) {
            path = "/WEB-INF/views/common/failed.jsp";
            request.setAttribute("message", "게시글 수정 실패!");
        }

        request.getRequestDispatcher(path).forward(request, response);
    }
}
