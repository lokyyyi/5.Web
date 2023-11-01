<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>WRITE PAGE</h1>

<form action="mycontroller.jsp" method="post">
	<input type="hidden" name="command" value="boardwrite">
	<table border="1">
		<tr>
			<th>WRITER</th>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<th>TITLE</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>CONTENT</th>	
			<td><textarea rows="10" cols="50" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="등록">
				<input type="button" value="취소" onclick="">
			</td>
		</tr>	
	</table>
</form>	
	
</body>
</html>