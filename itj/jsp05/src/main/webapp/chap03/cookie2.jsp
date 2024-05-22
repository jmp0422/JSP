<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Cookie[] list = request.getCookies();
    	int sum = 0;
    	for(Cookie c : list){
    	    if(c.getName().equals("apple") || c.getName().equals("ice")){
    	    out.print(c.getName() + " " + c.getValue() + "<br>");
    	    sum = sum + Integer.parseInt(c.getValue());
    	    }
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
사과와 아이스크림의 합은 <%= sum %>
</body>
</html>