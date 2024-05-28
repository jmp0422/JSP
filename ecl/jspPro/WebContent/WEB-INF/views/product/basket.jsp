<%@page import="com.multi.shop.member.model.dto.MemberDTO"%>
<%@page import="com.multi.shop.product.model.dto.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
  
<%--     <%
    //기존의 장바구니 리스트가 있었는지 없었는지 체크해보자.
    //ArrayList<ProductDTO> list
    //{dto, dto, dto}
    ArrayList<ProductDTO> list = null;
	String s = ""; //장바구니에 아무것도 없을때 찍어줄 메세지${ sessionScope.loginMember
    if(session.getAttribute("loginMember") != null){
	    if(session.getAttribute("basket") == null){
	    	//장바구니에 넣은적이 없다.(0개)
	    	list = new ArrayList<ProductDTO>();// 처음 장바구니를 사용할 경우 새 리스트를 생성
	    }else{ //2번째 이후부터 실행
	    	//장바구니에 넣은적이 있다.(1개 ==> 2개)
	    	list = (ArrayList<ProductDTO>)session.getAttribute("basket");//{dto}
	    }
	
	    session.setAttribute("basket", list); //{dto} ==> {dto, dto} // 세션에 장바구니 리스트를 저장
	    //어떤것이 들어가도 괜찮을 때 Object으로 바꾸어서 넣어줌.
    }else{ //null이면(넘어오는 값이 없으면)
		if (session.getAttribute("basket") == null) {
			s = "장바구니에 아무것도 들어있지 않음.";
		} else {
			list = (ArrayList<ProductDTO>) session.getAttribute("basket");
		}
	}

    %> --%>
    
    
    <c:choose>
    <c:when test="${not empty sessionScope.loginMember}">
		
        <c:set var="list" value="${sessionScope.basket}" scope="page" />
        <c:set var="sessionScope.basket" value="${list}" />
        
    </c:when>
    <c:otherwise>
        <!-- 로그인하지 않은 사용자의 경우 -->
        <c:if test="${empty sessionScope.basket}">
            <!-- 세션의 basket이 비어있을 때 메세지 설정 -->
            <c:set var="s" value="장바구니에 아무것도 들어있지 않음." scope="page" />
        </c:if>
        
        <!-- 세션의 basket에 무언가 있을 경우 list 변수에 할당 -->
        <c:set var="list" value="${sessionScope.basket}" scope="page" />
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
    <jsp:include page="../common/menubar.jsp" />

   <%-- <div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
            <div class="text-center">
            <jsp:include page="../common/top2.jsp" />
        </div>
        <div class="mt-4">
            <br>
            <% if (session.getAttribute("loginMember") != null) { %>
                <%= ((MemberDTO)session.getAttribute("loginMember")).getName() %>님 장바구니 정보입니다.
                <hr>
            <% } %>
            <form action="processOrder" method="post">
                <table class="table table-hover">
                   <thead class="thead-light">
                    <tr>
                        <th></th>
                        <th>아이디</th>
                        <th>물건이름</th>
                        <th>가격</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (list != null ) {
                        for (ProductDTO dto : list) { %>
                            <tr>
                                <td><input type="checkbox" name="selectedItems" value="<%= dto.getId() %>" /></td>
                                <td><%= dto.getId() %></td>
                                <td><%= dto.getName() %></td>
                                <td><%= dto.getPrice() %></td>
                            </tr>
                        <% }
                    } else { %>
                        <tr>
                            <td colspan="4" style="text-align: center;">장바구니에 아무것도 들어있지 않음.</td>
                        </tr>
                    <% } %>
                     </tbody>
                </table>
                <% if (list != null && !list.isEmpty()) { %>
                    <input type="submit" value="선택한 항목 주문하기" class="btn btn-primary" />
                <% } %>
            </form>
        </div>
    </div> --%>
    
    
	<div class="container mt-5">
	    <div class="row">
	        <div class="col-md-12">
	            <div class="text-center">
	                <jsp:include page="../common/top2.jsp" />
	            </div>
	            <div class="mt-4">
	                <br>
	                <c:if test="${not empty sessionScope.loginMember}">
	                    ${sessionScope.loginMember.name}님 장바구니 정보입니다.
	                    <hr>
	                </c:if>
	                <form action="processOrder" method="post">
	                    <table class="table table-hover">
	                        <thead class="thead-light">
	                            <tr>
	                                <th></th>
	                                <th>아이디</th>
	                                <th>물건이름</th>
	                                <th>가격</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <c:choose>
	                                <c:when test="${not empty list}">
	                                    <c:forEach var="dto" items="${list}">
	                                        <tr>
	                                            <td><input type="checkbox" name="selectedItems" value="${dto.id}" /></td>
	                                            <td>${dto.id}</td>
	                                            <td>${dto.name}</td>
	                                            <td>${dto.price}</td>
	                                        </tr>
	                                    </c:forEach>
	                                </c:when>
	                                <c:otherwise>
	                                    <tr>
	                                        <td colspan="4" style="text-align: center;">장바구니에 아무것도 들어있지 않음.</td>
	                                    </tr>
	                                </c:otherwise>
	                            </c:choose>
	                        </tbody>
	                    </table>
	                    <c:if test="${not empty list and fn:length(list) > 0}">
	                        <input type="submit" value="선택한 항목 주문하기" class="btn btn-primary" />
	                    </c:if>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>
</body>
</html>


<!-- --------------------
    String[] selectedItems = request.getParameterValues("selectedItems");
    ArrayList<ProductDTO> orderItems = new ArrayList<>();
    
    if (selectedItems != null) {
        for (String itemId : selectedItems) {
            // itemId로 제품 정보 조회 후 orderItems에 추가
            ProductDTO product = getProductById(Integer.parseInt(itemId));
            orderItems.add(product);
        }
        // 주문 로직 수행
        // 예: orderProcessing(orderItems);
    }
    // 주문 성공 페이지 또는 확인 페이지로 리디렉션
    response.sendRedirect("orderSuccess.jsp");

 -->