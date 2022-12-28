<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
<%@page import="DTO.Rank"%>
    
    <%
    request.setCharacterEncoding("UTF-8");
    ArrayList<Rank> list = new ArrayList<Rank>();
    list = (ArrayList<Rank>)request.getAttribute("list");
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
	
	<section>
	
	<div class = "title">후보자 등수</div>
	
	<div class = "wrap"> 
	<table style = "width : 500px">
		<tr>
		<th> 후보번호 </th>
		<th> 성명 </th>
		<th> 총투표건수 </th>
		</tr>
		
		<%for(Rank r : list) { %>		
		<tr>
		<td> <%= r.getM_no() %></td>
		<td> <%= r.getM_name() %></td>
		<td> <%= r.getCount() %></td>
		</tr>
	
		<%} %>

	</table>	
	</div>
	</section>	
	
	
	<%@ include file =  "Footer.jsp" %>

</body>
</html>