package com.multi.shop.board.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import static com.multi.shop.common.Template.getSqlSession;

import com.multi.shop.board.model.dao.BoardDAO;
import com.multi.shop.board.model.dto.BoardDTO;

public class BoardServiceImpl implements BoardService {
	private BoardDAO boardDao = new BoardDAO();

	@Override
	public ArrayList<BoardDTO> selectList() throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession sqlSession = getSqlSession();
		
		ArrayList<BoardDTO> list = boardDao.selectList(sqlSession);
		sqlSession.close();
		
		
		return list;
	}

	@Override
	public BoardDTO selectBoard(int bno) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession sqlSession = getSqlSession();
		
		boardDao.updateCount(sqlSession, bno);
	    sqlSession.commit();
		
        BoardDTO board = boardDao.selectBoard(sqlSession, bno);
        sqlSession.close();
        
        return board;
	}

	@Override
	public int insertBoard(BoardDTO newBoard) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = getSqlSession();
		
		int result = boardDao.insertBoard(sqlSession, newBoard);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
        sqlSession.close();
		
		return result;
	}
	
	@Override
    public int updateBoard(BoardDTO board) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = getSqlSession();
				
		int result = boardDao.updateBoard(sqlSession, board);
				
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
				
		sqlSession.close();
				
		return result;
    }

	@Override
	public int deleteBoard(int bno) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = getSqlSession();
		
		int result = boardDao.deleteBoard(sqlSession, bno);
				
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
				
		sqlSession.close();
				
		return result;
	}

}
