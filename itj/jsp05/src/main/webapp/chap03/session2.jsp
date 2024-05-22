<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	int tennis2 = (int)session.getAttribute("tennis");
    	int swim2 = (int)session.getAttribute("swim");
    	int result = tennis2 + swim2;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
테니스와 수영 둘다 수강하면 합계는 <%= result %>
</body>
</html>