<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="java.util.List" %>
<%@ page import="com.login.dto.MyMemberDto" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MyMemberDto login = (MyMemberDto)session.getAttribute("dto"); 
	if(login == null){
		pageContext.forward("index.jsp");
	}
	List<MyMemberDto> list = (List<MyMemberDto>)request.getAttribute("list");
%>
<div>
	<span><%=login.getMyid() %> 님 환영합니다.(등급: <%=login.getMyrole() %>)</span>	
</div>
<h1>회원 정보 조회</h1>

<table border="1">
	<col width="50"><col width="100"><col width="100"><col width="100"><col width="500"><col width="150"><col width="300"><col width="100"><col width="50">
	<tr>
		<th>번호</th><th>아이디</th><th>비밀번호</th><th>이름</th><th>주소</th><th>전화번호</th><th>이메일</th><th>가입여부</th><th>등급</th>
	</tr>
<%

	for(MyMemberDto dto : list){
%>
	<tr>
		<td><%=dto.getMyno() %></td>
		<td><%=dto.getMyid() %></td>
		<td><%=dto.getMypw() %></td>
		<td><%=dto.getMyname() %></td>
		<td><%=dto.getMyaddr() %></td>
		<td><%=dto.getMyphone() %></td>
		<td><%=dto.getMyemail() %></td>
		<td><%=dto.getMyenabled().equals("Y")?"가입":"탈퇴" %></td>
		<td><%=dto.getMyrole() %></td>
	</tr>
<%		
	}
%>
	<tr>
		<td colspan="9">
			<button onclick="location.href='adminmain.jsp'">메인</button>
		</td>
	</tr>
</table>
















</body>
</html>