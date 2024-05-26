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

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardServiceImpl();

    public BoardDeleteServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");

        String noStr = request.getParameter("no");

        if (noStr == null || noStr.isEmpty()) {
            request.setAttribute("message", "게시글 번호가 유효하지 않습니다.");
            request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
            return;
        }

        try {
            int no = Integer.parseInt(noStr);
            BoardDTO board = boardService.selectBoard(no);

            if (board == null) {
                request.setAttribute("message", "해당 게시글을 찾을 수 없습니다.");
                request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
                return;
            }

            if (!board.getWriter().equals(loginMember.getId())) {
                request.setAttribute("message", "삭제 권한이 없습니다.");
                request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
                return;
            }

            int result = boardService.deleteBoard(no);

            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/board/list");
            } else {
                request.setAttribute("message", "게시글 삭제 실패!");
                request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "게시글 삭제 중 오류가 발생했습니다.");
            request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
        }
    }
}
