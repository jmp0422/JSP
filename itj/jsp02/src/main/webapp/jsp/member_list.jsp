<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.multi.jsp.member.MemberDAO"%>
<%@page import="com.multi.jsp.member.MemberDTO"%>
<%@page import="java.util.ArrayList"%>


<!--
jsp에서 해야하는 작업 3가지
1) form안에 입력한 데이터 받아서 저장해두자. ==> java
2) 받은 데이터를 DAO에게 주자.  ==> java
3) 결과를 html로 만들자. ==> html
         -->

    <%
    MemberDAO dao = new MemberDAO();
    MemberDTO dto = new MemberDTO();

    ArrayList<MemberDTO> result = dao.list();

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
    <td class="left">아이디</td>
    <td class="left">패스워드</td>
    <td class="left">이름</td>
    <td class="left">전화번호</td>
    <td>상세정보</td>
</tr>
<% //스크립트릿
    for(MemberDTO bag: result){ //{bag, bag, bag, ...}
%>
    <tr>
       <td class="data"><%= bag.getId() %></td>
       <td class="data"><%= bag.getPw() %></td>
       <td class="data"><%= bag.getName() %></td>
       <td class="data"><%= bag.getTel()%></td>
       <td>
          <a  href="member_one.jsp?id=<%= bag.getId() %>">
             <button style="background: gold">상세정보보기</button>
          </a>
       </td>
    </tr>
<% } //for %>
</table>
</body>
</html>