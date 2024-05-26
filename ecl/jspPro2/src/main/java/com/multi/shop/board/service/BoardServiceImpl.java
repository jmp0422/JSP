package com.multi.shop.board.service;

import java.util.ArrayList;
import static com.multi.shop.common.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;

import com.multi.shop.board.model.dao.BoardDAO;
import com.multi.shop.board.model.dto.BoardDTO;
import com.multi.shop.common.Template;

public class BoardServiceImpl implements BoardService{
	private BoardDAO boardDao = new BoardDAO();
	
	@Override
	public ArrayList<BoardDTO> selectList() throws Exception {
		
		SqlSession sqlSession = getSqlSession();
		
		ArrayList<BoardDTO> list = boardDao.selectList(sqlSession);
		sqlSession.close();
		return list;
		// TODO Auto-generated method stub
	}

	@Override
	public BoardDTO selectBoard(int bno) throws Exception {
		SqlSession sqlSession = getSqlSession();
		BoardDTO one = boardDao.selectBoard(sqlSession, bno);
		sqlSession.close();
		return one;
		// TODO Auto-generated method stub
	}
	
	@Override
	public int updateCount(int bno) throws Exception {
		int result = 0;
		
		SqlSession sqlSession = getSqlSession();
		
		System.out.println(bno);
		
		result = boardDao.updateCount(sqlSession, bno);
		
		sqlSession.commit();
		
		sqlSession.close();
		return result;
		// TODO Auto-generated method stub
	}

	@Override
	public int insertBoard(BoardDTO newBoard) throws Exception {
		int result = 0;
		SqlSession sqlSession = getSqlSession();
		
		System.out.println(newBoard);
		
		result = boardDao.insertBoard(sqlSession, newBoard);
		
		if (result > 0) {
			System.out.println("성공");
			sqlSession.commit();
		} else {
			System.out.println("실패");
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	}

	@Override
	public int boardDelete(int bno) throws Exception {
		int result = 0;
		SqlSession sqlSession = getSqlSession();
		
		result = boardDao.deleteBoard(sqlSession, bno);
		
		if (result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		return result;
	}

	@Override
	public int updateBoard(BoardDTO updBoard) {
		int result = 0;
		SqlSession sqlSession = getSqlSession();
		
		result = boardDao.updateBoard(sqlSession, updBoard);
		
		if (result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		return result;
	}
	
}
