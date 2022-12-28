<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@page import = "java.util.*" %>
    <%@page import = "DTO.calc" %>
    
     <%
    request.setCharacterEncoding("UTF-8");
    ArrayList<calc> list = new ArrayList<calc>();
	list = (ArrayList<calc>)request.getAttribute("list");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href = "style.css">
</head>
<body>
	<%@ include file = "Header.jsp" %>
	
	<section class = "main2">
 	<h1> 병원별 접종건수 통계 </h1>	
	<div class = "wrap">
 	<table>
 		<tr>
 			<th> 병원코드 </th>
 			<th> 병원명 </th>
 			<th> 접종건수 </th>
 		</tr>
 		<% int result = 0;
 		for (calc c : list){ 

 		%> 
 		<tr>
 			<td> <%= c.getHospcode() %> </td> 
 			<td> <%= c.getHospname() %> </td> 
 			<td> <%= c.getCount() %> </td> 
 			<% result += Integer.parseInt(c.getCount()); %>
 		</tr>
 		<% } %>
 		<tr>
 			<td></td>
 			<td> 총 누계 </td>
 			<td> <%= result %> </td>
 		</tr>
 	</table> 
	</div>
 
 	<button class = "btn" type = "reset" name = "reset" onclick="location.href='look.jsp'; return false;"> 돌아가기 </button>
 
	 </section>
	
	<%@ include file = "Footer.jsp" %>
</body>
</html>