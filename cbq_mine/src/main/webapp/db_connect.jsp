<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3> DB 연동 샘플 </h3>
	<hr>
	<%
	Class.forName("oracle:jdbc:OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "gisa", "gisa1234");
	
	%>
	

</body>
</html>