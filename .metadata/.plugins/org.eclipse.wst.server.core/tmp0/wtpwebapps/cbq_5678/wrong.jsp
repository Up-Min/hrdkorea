<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href = "style.css">
<script type="text/javascript" src = "script.js"></script>
</head>
<body>
	
	<%@ include file = "Header.jsp" %>
	
	<section class = "main2">
	
 	<h1> 접종 예약정보가 존재하지 않습니다!</h1>	
 
 	<button class = "btn" type = "reset" name = "reset" onclick="location.href='look.jsp'; return false;"> 돌아가기 </button>
 
	 </section>
	
	<%@ include file = "Footer.jsp" %>
	
</body>
</html>