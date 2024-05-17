<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.multi.jsp.member.MemberDAO"%>



<!--
jsp에서 해야하는 작업 3가지
1) form안에 입력한 데이터 받아서 저장해두자. ==> java
2) 받은 데이터를 DAO에게 주자.  ==> java
3) 결과를 html로 만들자. ==> html
         -->

    <%
    String id = request.getParameter("id");
    String tel = request.getParameter("tel");

    MemberDAO dao = new MemberDAO();

    int result = dao.update(id,tel);

    String resultText = "회원수정 실패함";

    if(result == 1){
        resultText = "회원수정 성공함";
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
    <h5>당신이 입력한 회원정보를 확인해주세요.</h5>
    <hr color="green">
    가입한 아이디는 <%= id %> <br> <!-- id변수에 있는 것 보여줘(프린트) -->
    수정한 전화번호는 <%= tel %> <br>



</body>
</html>