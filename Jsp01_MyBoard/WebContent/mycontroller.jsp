
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.mvc.dao.MVCBoardDao" %>
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
	String command = request.getParameter("command");
	System.out.println("[command: ]" + command+"]");
	
	MVCBoardDao dao = new MVCBoardDao();
	
	if(command.equals("boardlist")){
		//1. 필요 데이터 준비
		List<MVCBoardDto> list = dao.selectAll();	
		
		//2. 페이지 전환
		/*
			페이지 이동 방법
			1. foward	: request,response 객체가 유지된다,
						  이동된 url이 화면에 안보인다.
						  1) <jsp:forward>
						  2) pageContext.forward()
						  3) requestDispatcher - servlet
			2. redirect : 새로 페이지를 요청한 것과 같은 방식으로 페이지 이동.
						  request, response 객체가 유지되지 않는다.
		*/
		request.setAttribute("allList", list);
		pageContext.forward("boardlist.jsp");
		
	}else if(command.equals("writeform")){
		response.sendRedirect("boardwrite.jsp");
		
	}else if(command.equals("boardwrite")){
		//comand가 boardwrite일때
		//함께 넘어오는 데이터(write,title,content 값)를 받아 db에 저장
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		MVCBoardDto dto = new MVCBoardDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		
		int res = dao.insert(dto);
		
		if(res>0){
%>			
			<script type="text/javascript">
				alert("글작성 성공!");
				location.href="mycontroller.jsp?command=boardlist";
			</script>
<%		
		}else{
%>
			<script type="text/javascript">
				alert("글작성 실패!");
				location.href="mycontroller.jsp?command=writeform";
			</script>
<%
		}
	}
%>
<%-- <h1><%=command %></h1> --%>
</body>
</html>