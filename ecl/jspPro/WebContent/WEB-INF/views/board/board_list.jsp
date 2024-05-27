<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	<div class="container mt-4">
	<table id="listArea" class="table table-hover table-striped table-bordered">
	        <tr>
				<th scope="col" style="text-align: center; width: 100px;">번호</th>
				<th scope="col" style="text-align: center; width: 200px;">제목</th>
				<th scope="col" style="text-align: center; width: 150px;">작성자</th>
				<th scope="col" style="text-align: center; width: 50px;">조회수</th>
				<th scope="col" style="text-align: center; width: 100px;">작성일</th>
	        </tr>
	    <tbody>
	        <c:if test="${empty list}">
	            <tr>
	                <td colspan="5" class="text-center">조회된 내용이 없습니다.</td>
	            </tr>
	        </c:if>
	
	        <c:forEach var="b" items="${list}"> 
	        <!-- forEach문을 사용해서 list에있는 정보들이 전부들어갈떄까지 반복해서 b에 정보를 담는다 -->
	            <tr>
	                <td class="text-center tdc"><c:out value="${b.no}" /></td>
	                <td class="text-center tdc">
	                    <a href="${pageContext.servletContext.contextPath}/board/selectone?no=${b.no}" class="text-decoration-none">
	                    <!-- 해당개실물을 클릭하면 selectone으로 이동하여 상세정보확인 -->
	                        <c:out value="${b.title}" />
	                    </a>
	                </td>
	                <td class="text-center tdc"><c:out value="${b.writer}" /></td>
	                <td class="text-center tdc"><c:out value="${b.count}" /></td>
	                <td class="text-center tdc"> <c:out value="${b.createDate}" /></td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
	</div>
	<c:if test="${ !empty sessionScope.loginMember }">
	 <div align="center">
        <button class="btns" id="writeBoard" onclick="location.href='${pageContext.servletContext.contextPath}/board/insert'">작성하기</button>
        <!-- 작성하기 버튼 생성 후 버튼클릭시 게시글작성 insert로 넘어감 -->
    </div>
	</c:if>
	<script>
		/* 마우스올리면 색상변하고, 클릭시 상세페이지로 이동하는 스크립트코드 */
	if(document.getElementsByClassName("tdc")) {
	    const $tds = document.getElementsByClassName("tdc");
	    for(let i = 0; i < $tds.length; i++) {
	        $tds[i].onmouseenter = function() {
	            this.parentNode.style.backgroundColor = "skyblue";
	            this.parentNode.style.cursor = "pointer";
	        }
	        
	        $tds[i].onmouseout = function() {
	            this.parentNode.style.backgroundColor = "white";
	        }
	        $tds[i].onclick = function() {
	            var no = this.parentNode.children[0].innerText;

	            // '/selectone' 서블릿으로 리디렉션하면서 쿼리 스트링에 게시물 번호를 포함시킴
	            location.href = '${pageContext.servletContext.contextPath}/board/selectone?no=' + no;
	        }
	    }
	}
		
	</script>
</body>


</html>