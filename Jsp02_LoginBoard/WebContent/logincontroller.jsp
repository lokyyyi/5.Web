<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="java.util.List" %>
<%@ page import="com.login.dao.MyMemberDao" %>
<%@ page import="com.login.dto.MyMemberDto" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String command = request.getParameter("command");
	System.out.println("[command:" + command+ "]");
	
	MyMemberDao dao = new MyMemberDao();
	
	if(command.equals("login")){
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MyMemberDto dto = dao.login(id,pw);
		
		if(dto.getMyid() != null){
			//session scope 세션영역
			session.setAttribute("dto", dto);
			
/*
	page 영역
	하나의 jsp페이지 내에서만 객체를 공유하는 영역.
	pageContext객체.
	
	request 영역
	요청을 받아서 응답하기 까지 객체가 유효한 영역
	request 객체
	
	session 영역
	하나의 브라우저 당 1개의 session 객체를 공유하는 영역.
	session 객체
	
	application 영역
	같은 어플리케이션 내에서 요청되는 페이지들은 간츤 객체를 공유하는 영억.
	하나의 어플리케이션 당 1개의 application 객체를 생성.
	application 객체.
*/
		//session에 담긴 객체가 살아있는 시간.
		//setMaxInactiveInterval(int second)
		session.setMaxInactiveInterval(60*60);
		
			if(dto.getMyrole().equals("ADMIN")){
				response.sendRedirect("adminmain.jsp");
			}else if(dto.getMyrole().equals("USER")){
				response.sendRedirect("usermain.jsp");
			}
		}else{
%>
	<script type="text/javascript">
		alert("login 실패");
		location.href="index.jsp"
	</script>
<%
		}
	}else if(command.equals("userlistall")){
		//1. 페이지 준비
		List<MyMemberDto> list = dao.selectAll();
		
		//2. 페이지 전환
		request.setAttribute("list", list);
		pageContext.forward("userlistall.jsp");
		//pageContext : 현재 jsp페이지의 context를 나타내고
		//jsp 페이지와 관련된 다른 내장 객체를 얻거나, 요청(request)과 응답(response)의 제어권을 다른 페이지로 넘겨주는데 사용
	
	}else if(command.equals("logout")){
		//session 정보 삭제
		session.invalidate();
		response.sendRedirect("index.jsp");
		
	}else if(command.equals("registform")){
		response.sendRedirect("registform.jsp");
		
	}else if(command.equals("idchk")){
		String myid = request.getParameter("id");
		String res = dao.idChk(myid);
		
		boolean idnotused = true;
		if(res != null){
			idnotused = false;
		}
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
		
	}else if(command.equals("insertuser")){
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		
		MyMemberDto dto = new MyMemberDto();
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		
		int res = dao.insertUser(dto);
		
		if(res>0){
%>
		<script type="text/javascript">
			alert("회원가입성공");
			location.href = "index.jsp";			
		</script>
<%
		}else{
%>
		<script type="text/javascript">
			alert("회원가입 실패");
			location.href = "logincontroller.jsp?command=registform";
		</script>
<%
		}
	}else if(command.equals("deleteuser")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		int res = dao.deleteuser(myno);
		
		if(res>0){
%>
		<script type="text/javascript">
			alert("탈퇴 성공");
			location.href='logincontroller.jsp?command=logout';
		</script>
<%			
		}else{
%>
		<script type="text/javascript">
			alert("탈퇴 실패");
			location.href='usermain.jsp';
		</script>
<%			
		}
	}
%>
</body>
</html>