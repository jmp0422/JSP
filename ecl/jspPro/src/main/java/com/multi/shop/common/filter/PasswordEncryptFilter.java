package com.multi.shop.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.multi.shop.common.wrapper.EncryptRequestWrapper;


/* member 서비스인 경우에만 암호화 처리 할 수 있도록 한다. */
@WebFilter("/member/*") // "/member/*" 패턴에 대한 요청에 이 필터를 적용합니다.
public class PasswordEncryptFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		/* 로그인을 하는 경우 자동 암호화된 값으로 비교하게 되면 비교할 수 없다.
		 * 로그인 요청은 암호화하는 필터에서 암호화 처리 되지 않도록 해준다.
		 * */
		HttpServletRequest hrequest = (HttpServletRequest) request;
		
		String uri = hrequest.getRequestURI(); //현재요청을 uri로 가져와서 찍어보기
		System.out.println("uri : " + uri);
		
		String intent = uri.substring(uri.lastIndexOf("/")); // URI에서 마지막 "/" 이후의 문자열을 추출
		System.out.println("intent : " + intent);
		
		/* 로그인 요청이 아닌 경우에만 암호화를 한다.  */
		if(!"/login".equals(intent)) {
			
			// 암호화된 요청을 위한 래퍼 객체를 생성하여 필터 체인에 전달합니다.
			EncryptRequestWrapper wrapper = new EncryptRequestWrapper(hrequest);
			chain.doFilter(wrapper, response);// 다음 필터로 요청을 전달합니다.
		} else {
			chain.doFilter(request, response);// 로그인 요청인 경우에는 암호화를 수행하지 않고 필터 체인을 그대로 진행합니다.
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
