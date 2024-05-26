package com.multi.shop.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.multi.shop.board.model.dto.BoardDTO;



public class BoardDAO { 


	public BoardDAO() {
		
	}
	
	public ArrayList<BoardDTO> selectList(SqlSession sqlSession) throws Exception{
		

		return (ArrayList)sqlSession.selectList("boardMapper.selectList");
	}


	public int updateCount(SqlSession sqlSession, int bno) throws Exception{
		
		return sqlSession.update("boardMapper.updateCount", bno);
	}

	
	public BoardDTO selectBoard(SqlSession sqlSession, int bno) throws Exception{
		
		return sqlSession.selectOne("boardMapper.selectBoard", bno);
	}

	public int insertBoard(SqlSession session, BoardDTO newBoard) {
		
		return session.insert("boardMapper.insertBoard", newBoard);
	}
	
	public int updateBoard(SqlSession session, BoardDTO board) {
		
        return session.update("boardMapper.updateBoard", board);
    }

	public int deleteBoard(SqlSession session, int bno) {
		// TODO Auto-generated method stub
		return session.update("boardMapper.deleteBoard", bno);
	}
}
