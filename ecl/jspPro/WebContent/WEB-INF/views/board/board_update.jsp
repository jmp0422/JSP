<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<style>
    th { width: 200px; }
</style>
</head>
<body>
    <jsp:include page="../common/menubar.jsp" />
    
    <div class="outer outer-board-update" style="display: flex; justify-content: center;">
        <form action="${pageContext.servletContext.contextPath}/board/update" method="post">
            <br>
            <input type="hidden" name="no" value="${b.no}">
            <table id="listArea" class="table table-hover table-striped table-bordered">
                <tr>
                    <th>분야</th>
                    <td style="text-align: left;">
                        <select name="category">
                            <option value="10" ${b.category == 10 ? 'selected' : ''}>공통</option>
                            <option value="20" ${b.category == 20 ? 'selected' : ''}>운동</option>
                            <option value="30" ${b.category == 30 ? 'selected' : ''}>요리</option>
                            <option value="70" ${b.category == 70 ? 'selected' : ''}>기타</option>
                        </select>
                    </td>
                </tr>
                <tbody>
                    <tr>
                        <th>제목</th>
                        <td colspan="3"><input type="text" name="title" value="${b.title}" style="width:500px;"></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td colspan="3">
                            <textarea name="content" cols="60" rows="15" style="resize:none;width:500px;">${b.content}</textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <div align="center">
                <button class="btns" type="reset">취소하기</button>
                <button class="btns" type="submit">수정하기</button>
            </div>
        </form>
        <br><br>
    </div>
</body>
</html>