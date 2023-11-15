<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="com.el.score.Score"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Score sc = (Score)request.getAttribute("score");
	
	%>
	<table border="1">
		<tr>
			<th colspan="2"><%=sc.getName() %> 님의 점수는...</th>
		</tr>
		<tr>
			<th>국어</th>
			<td>${score.kor }</td>
		</tr>
		<tr>
			<th>영어</th>
			<td>${score.eng }</td>
		</tr>
		<tr>
			<th>수학</th>
			<td>${score.sum }</td>
		</tr>
		<tr>
			<th>평균</th>
			<td>${(score.kor+score.eng+score.sum)/3 }</td>		
		</tr>
	</table>
</body>
</html>