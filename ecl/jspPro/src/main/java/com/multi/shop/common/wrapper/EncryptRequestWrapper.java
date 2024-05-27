package com.multi.shop.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptRequestWrapper extends HttpServletRequestWrapper {
	
	/* 부모쪽에 기본생성자가 존재하지 않기 때문에 request를 전달해주는 생성자가 필요하다. */
	public EncryptRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	/**
	 * HTTP 요청의 파라미터 값을 가져오는 메소드를 오버라이딩하여, 비밀번호 파라미터의 값을 암호화함
	 * @param key 가져올 파라미터의 이름
	 * @return 파라미터의 값 (암호화된 경우 암호화된 값, 그렇지 않은 경우 원본 값)
	 */
	@Override
	public String getParameter(String key) {
		System.out.println("getParameter : "+ key);
		String value = "";
		if("pw".equals(key)) { //getparameter로 가져온 정보들중에서 pw만 암호화처리
		// 스프링 시큐리티중 BCrypt 암호화를 제공해주는 라이브러리를 추가한 후 암호화 처리
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			value = passwordEncoder.encode(super.getParameter(key)); //encode 메소드는 비밀번호에 솔트를 자동으로 추가하고, 결과적으로 생성된 해시 값을 반환
			System.out.println(value);
		} else {
			value = super.getParameter(key); // 파라미터로 가져온 나머지정보들은 그대로반환
		}
		
		return value;
	}
	

}
