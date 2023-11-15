<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function test() {
		let url = '${url}';
		location.href=url;
		
	}
</script>
</head>
<body>
<h1>${msg }</h1>
<button onclick="test();">확인</button>

</body>
</html>