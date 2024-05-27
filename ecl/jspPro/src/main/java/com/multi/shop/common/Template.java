package com.multi.shop.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	public static SqlSession getSqlSession() {
		// MyBatis를 사용하여 데이터베이스와 상호작용하기 위한 SQL 세션을 가져오는 메소드
		SqlSession sqlSession = null;
		
		try {
			// mybatis-config.xml 파일을 읽어들이기 위해 Resources.getResourceAsStream() 메소드를 사용하여 InputStream을 생성
			InputStream stream = Resources.getResourceAsStream("/mybatis-config.xml");
			
			// SqlSessionFactoryBuilder를 사용하여 InputStream으로부터 SqlSessionFactory를 빌드하고, 그것을 통해 SqlSession을 열어서 가져옴
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqlSession;
	}
}
