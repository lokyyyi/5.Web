<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	JSTL : JSP Standard Tag Library
	종류 : core(c) : 변수, 제어문, 페이지 처리...
		 format(fmt) : 숫자, 날짜, 시간 포맷 처리...
		 function(fn) : collection, String 등을 처리
		 database(sql) : db 관련 처리
		 xml(x) : xml 문서 처리

 -->
 <h1>JSTL PAGE</h1>
 <table border="1">
 	<tr>
 		<th>이름</th>
 		<th>국어</th>
 		<th>영어</th>
 		<th>수학</th>
 	</tr>
 	<c:forEach var="score" items="${list }">
 		<tr>
 			<td>
 				<c:if test="${score.name eq '이름1' }">
 					${score.name }님!
 				</c:if>
 				<c:choose>
 					<c:when test="${score.name == '이름2' }">
 						<c:out value="${score.name }님@@"></c:out>
 					</c:when>
 					<c:when test="${score.name eq '이름3' }">
 						<c:out value="홍길동"></c:out>
 					</c:when>
 					<c:otherwise>
 						<c:out value="누구...?"></c:out>
 					</c:otherwise>
 				</c:choose>
 			</td>
 			<td>${score.kor }</td>
 			<td>${score.eng }</td>
 			<td>${score.sum }</td>
 		</tr>
 	</c:forEach>
 </table>
 
 	<c:forEach var="i" begin="1" end="10" step="1">
 		${i }
 	</c:forEach>
 
 	<c:set var="no" value="100" scope="request"></c:set>
 	
 	<c:out value="${no }"></c:out>
 	<h2>${no }</h2>
 
</body>
</html>