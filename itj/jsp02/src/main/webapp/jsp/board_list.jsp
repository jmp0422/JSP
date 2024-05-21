<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.multi.jsp.board.BbsDAO"%>
<%@page import="com.multi.jsp.board.BbsDTO"%>
<%@page import="java.util.ArrayList"%>




    <%
    BbsDAO dao = new BbsDAO();
    BbsDTO dto = new BbsDTO();

    ArrayList<BbsDTO> result = dao.list();

    %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
    body{
       background: lime;
    }
    *{
       font-size: 20px;
       text-align: center;
    }
    .left {
       /* 여러개를 선택해서 지정하고 싶을 때 클래스를 사용. 클래스 중 left에 해당하는 것만 스타일을 지정해라. */
       /* 클래스 선택은 .클래스명 */
       width: 100px;
       background: blue;
       color: white;
    }
    .data {
       width: 100px;
       background: white;
       color: blue;
    }
    #bottom {
       background: red;
       color: yellow;
    }
    input{
       background: greenyellow;
    }
</style>
<body bgcolor="skyblue">
<!-- 반복문(for)문 필요! -->
<table border="1" align="center">
<tr>
    <td class="left">게시글번호</td>
    <td class="left">제목</td>
    <td class="left">내용</td>
    <td class="left">작성자</td>
    <td>상세정보</td>
</tr>
<% //스크립트릿
    for(BbsDTO bag: result){ //{bag, bag, bag, ...}
%>
    <tr>
       <td class="data"><%= bag.getNo() %></td>
       <td class="data"><%= bag.getTitle() %></td>
       <td class="data"><%= bag.getContent() %></td>
       <td class="data"><%= bag.getWriter()%></td>
       <td>
          <a  href="board_one.jsp?no=<%= bag.getNo() %>">
             <button style="background: gold">상세정보보기</button>
          </a>
       </td>
    </tr>
<% } //for %>
</table>
</body>
</html>