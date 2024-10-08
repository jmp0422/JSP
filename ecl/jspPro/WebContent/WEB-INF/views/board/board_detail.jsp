<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
		<div class="container my-4">
    	<table class="table table-striped table-bordered">
				<tbody>
                    <tr>
                        <th scope="row" width="100">글번호</th>
                        <td><strong>${ b.no }</strong></td>
                    </tr>
                    <tr>
                        <th scope="row">제목</th>
                        <td><strong>${ b.title }</strong></td>
                    </tr>
                    <tr>
                        <th scope="row">작성자</th>
                        <td><strong>${ b.writer }</strong></td>
                    </tr>
                    <tr>
                        <th scope="row">조회수</th>
                        <td><strong>${ b.count }</strong></td>
                    </tr>
                    <tr>
                        <th scope="row">작성일</th>
                        <td><strong>${ b.createDate }</strong></td>
                    </tr>
                    <tr>
                        <th scope="row">내용</th>
                        <td  class="scrollable-cell" height=300px><strong>${ b.content }</strong></td>
                    </tr>
                </tbody>
            </table>
        </div>

    <div class="text-center mt-4">
    <button class="btn btn-primary" onclick="location.href='${pageContext.servletContext.contextPath}/board/list'">목록으로</button>
    <c:if test="${ !empty sessionScope.loginMember && sessionScope.loginMember.id == b.writer}">
    	<button class="btn btn-secondary" onclick="location.href='${pageContext.servletContext.contextPath}/board/update?no=${b.no}'">수정하기</button>
    	<form id="deleteForm" action="${pageContext.servletContext.contextPath}/board/delete" method="post">
    		<input type="hidden" name="no" value="${b.no}">
		</form>

		<button class="btn btn-secondary" onclick="submitDeleteForm()">삭제하기</button>

		<script>
		/* 삭제클릭시 실행되는 매서드 */
		    function submitDeleteForm() {
		        if (confirm("정말로 삭제하시겠습니까?")) {
		            document.getElementById("deleteForm").submit();
		        }
		    }
		</script>
    </c:if>
    </div>
    
    
    
  
</body>
</html>