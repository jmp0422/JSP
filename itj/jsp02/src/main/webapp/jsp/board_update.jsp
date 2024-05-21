<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.multi.jsp.board.BbsDAO"%>



<!--
jsp에서 해야하는 작업 3가지
1) form안에 입력한 데이터 받아서 저장해두자. ==> java
2) 받은 데이터를 DAO에게 주자.  ==> java
3) 결과를 html로 만들자. ==> html
         -->

    <%
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    int no = Integer.parseInt(request.getParameter("no"));

    BbsDAO dao = new BbsDAO();

    int result = dao.update(title,content,no);

    String resultText = "게시글수정 실패함";

    if(result == 1){
        resultText = "게시글수정 성공함";
    }

    %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%= resultText %>
    <h5>당신이 입력한 게시글정보를 확인해주세요.</h5>
    <hr color="green">
    수정된 제목은 <%= title %> <br>
    수정된 내용은 <%= content %> <br>
    게시글번호는  <%= no %> <br>



</body>
</html>