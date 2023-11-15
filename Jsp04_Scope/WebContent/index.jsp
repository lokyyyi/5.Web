<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	// SCOPE
	// page, request, session, application
	/* 
		page 영역
		하나의 jsp 페이지 내에서만 객체를 공유하는 영역
		jsp파일에는 pageContext가 내장, 이 객체는 page 영역에서만 유효.
		
		request 영역
		요청을 받아서 응답하기까지 객체가 유효한 영역.
		servlet에서 forward를 사용하면 request객체가 공유
		
		session 영역
		하나의 브라우저 당 1개의 session 객체가 생성되고,
		같은 브라우저 내에서 요청되는 모든 페이지들은 같은 session객체를 공유.
		request.getSession() 호출하여 세션영역의 객체 얻을 수 있다.
		
		application 영역
		하나의 어플리케이션 당 1개의 application 객체가 생성.
		같은 어플리케이션 내에서 요청되는 페이지들은 같은 application 객체를 공유.
		어플리케이션이 종료되면 반환.
		request.getServletContext() 호출하여 어플리케이션 영역의 객체 얻을 수 있다.
		
	*/
	
	pageContext.setAttribute("pageId", "myPageIdVal");
	request.setAttribute("reqId", "myRequestIdVal");
	session.setAttribute("sessionId", "mySessionIdVal");
	application.setAttribute("appId", "myApplicationIdVal");

%>
</head>
<body>
	<h1>index</h1>
	
	
	
	page id : <%=pageContext.getAttribute("pageId") %><br>
	reqId : <%=request.getAttribute("reqId") %><br>
	sessionId : <%=session.getAttribute("sessionId") %><br>
	applicationId : <%=application.getAttribute("appId") %>
	
	<br>
	<a href="result.jsp">이동(result.jsp)</a><br>
	<a href="myservlet.do">이동(controller)</a>
	
	
</body>
</html>