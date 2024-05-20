<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%

        String id = (String)request.getAttribute("id");
        String pw = (String)request.getAttribute("pw");

        String imagePath = request.getContextPath() + "/resources/img/";
        String yesImagePath = imagePath + "ok.JPG";

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h3>아이디 : <%= id %></h3>
	<h3>비번 : <%= pw %></h3>
	<img src= <%=  yesImagePath%> >
</body>
</html>