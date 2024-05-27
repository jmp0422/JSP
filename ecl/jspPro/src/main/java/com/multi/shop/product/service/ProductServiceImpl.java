package com.multi.shop.product.service;
import static com.multi.shop.common.Template.getSqlSession;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.multi.shop.product.model.dao.ProductDAO;
import com.multi.shop.product.model.dto.AttachmentDTO;
import com.multi.shop.product.model.dto.CompanyDTO;
import com.multi.shop.product.model.dto.ProductDTO;

public class ProductServiceImpl implements ProductService {
	private ProductDAO productDao = new ProductDAO();
	
	@Override
	public ArrayList<ProductDTO> selectList() throws Exception{
		SqlSession sqlsession = getSqlSession();
		ArrayList<ProductDTO>list = productDao.selectList(sqlsession);
		
		sqlsession.close();
		return list;
	}

	@Override
	public ArrayList<CompanyDTO> selectCompanyList() throws Exception {
		
		SqlSession sqlsession = getSqlSession();
		ArrayList<CompanyDTO>list = productDao.selectCompanyList(sqlsession);
		
		sqlsession.close();
		return list;
	}
	
	@Override
	public int insertProduct(ProductDTO newProduct) throws Exception {
		SqlSession session = getSqlSession();
		int result = 0;
		int productResult = productDao.insertProduct(session, newProduct);
		
		List<AttachmentDTO> fileList = newProduct.getAttachments();
	
		int productId = newProduct.getId();
		
		int attachmentResult = 0;
		for(int i = 0; i < fileList.size(); i++) {
			fileList.get(i).setRefProductNo(productId);
			attachmentResult += productDao.insertAttachment(session, fileList.get(i));
		}
		
		/* 모든 결과가 성공이면 트랜젝션을 완료한다. */
		if(productResult > 0 && attachmentResult == fileList.size()) {
			session.commit();
			result = 1;
		} else {
			session.rollback();
		}
		session.close();
		
		
		return result;
		
	}
	
	

}
