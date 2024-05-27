package com.multi.shop.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/EncodingFilter") //인코딩처리
public class EncodingFilter extends HttpFilter implements Filter {
	private String encodingType;
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public EncodingFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 요청이 POST인 경우에만 인코딩 타입을 변경합니다.
		HttpServletRequest hrequest = (HttpServletRequest) request;
		if("POST".equals(hrequest.getMethod())) {
			request.setCharacterEncoding(encodingType);
			System.out.println("변경된 인코딩 타입 : " + request.getCharacterEncoding());
		}

		// 필터 체인을 통해 요청을 전달합니다.
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	//필터의 초기화 메소드
	public void init(FilterConfig fConfig) throws ServletException {
		// 초기화 파라미터로부터 인코딩 타입을 가져와서 저장합니다.
		encodingType = fConfig.getInitParameter("encoding-type");
	}

}
