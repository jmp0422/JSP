package com.multi.shop.product.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.multi.shop.product.model.dto.AttachmentDTO;
import com.multi.shop.product.model.dto.CompanyDTO;
import com.multi.shop.product.model.dto.ProductDTO;



public class ProductDAO {

	public ArrayList<ProductDTO> selectList(SqlSession sqlSession) throws Exception{
		
		return (ArrayList) sqlSession.selectList("productMapper.selectList");
	}

	public ArrayList<CompanyDTO> selectCompanyList(SqlSession sqlSession) {
		
		return (ArrayList) sqlSession.selectList("productMapper.selectCompanyList");
		
	}

	public int insertProduct(SqlSession session, ProductDTO newProduct) {
		
		return session.insert("productMapper.insertProduct", newProduct);
		
	}

	public int insertAttachment(SqlSession session, AttachmentDTO attachmentDTO) {
  
		return session.insert("productMapper.insertAttachment", attachmentDTO);
		
	}

}
