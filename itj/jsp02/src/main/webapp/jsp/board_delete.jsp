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
    int no = Integer.parseInt(request.getParameter("no"));


    BbsDAO dao = new BbsDAO();



    int result = dao.delete(no);

    String resultText = "게시글삭제 실패함";

    if(result == 1){
        resultText = "게시글삭제 성공함";
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
    <h5>당신이 삭제할 게시글 정보를 확인해주세요.</h5>
    <hr color="green">
    당신이 삭제를 원하는 번호는 <%= no %> <br> <!-- id변수에 있는 것 보여줘(프린트) -->



</body>
</html>