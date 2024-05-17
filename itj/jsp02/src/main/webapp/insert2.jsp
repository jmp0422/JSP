<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <%

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");

    %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    당신의 회원가입 정보입니다.
    <hr>

    당신의 id는 <%= id %><br>
    당신의 pw는 <%= pw %><br>
    당신의 name는 <%= name %><br>
    당신의 tel는 <%= tel %><br>






</body>
</html>