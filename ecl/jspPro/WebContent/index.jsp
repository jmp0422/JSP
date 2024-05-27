<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="/WEB-INF/views/main/main.jsp"/>
	<!-- main.jsp로 포워딩시킴(redirect는 클라이언트측에 이동을 하달하지만, forwarding은 서버안에서 자체적으로 보내기때문에 클라이언트가 알수가없음) -->
</body>
</html>