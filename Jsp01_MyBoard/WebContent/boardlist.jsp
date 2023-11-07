<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="java.util.List" %>
<%@ page import="com.mvc.dto.MVCBoardDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<MVCBoardDto> list = (List<MVCBoardDto>)request.getAttribute("allList");
%>
<h1>LIST PAGE</h1>
<table border="1">
	<col width="50px"><col width="100px"><col width="200px"><col width="100px">
	<tr>
		<th>번 호</th>
		<th>이 름</th>
		<th>제 목</th>
		<th>날 짜</th>
	</tr>
	
<% 
	for(int i=0; i<list.size(); i++){
%>
		<tr>
			<td><%=list.get(i).getSeq() %></td>
			<td><%=list.get(i).getWriter() %></td>
			<td><a href="mycontroller.jsp?command=boarddetail&seq=<%=list.get(i).getSeq()%>"><%=list.get(i).getTitle() %></a></td>
			<td><%=list.get(i).getRegdate() %></td>
		</tr>
<%
	}	
%>
	<tr>
		<td colspan="4" align="right">
			<button onclick="location.href='mycontroller.jsp?command=writeform'">글쓰기</button>
		</td>
	</tr>
	
</table>

</body>
</html>