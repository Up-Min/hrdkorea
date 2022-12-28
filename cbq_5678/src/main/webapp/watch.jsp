<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "java.util.*" %>
    <%@page import = "DTO.reserv" %>
    
    <%
    request.setCharacterEncoding("UTF-8");
	reserv r = new reserv();
	r = (reserv)request.getAttribute("res");
	String resvno = (String)request.getAttribute("resvno");
	String chk = (String)request.getAttribute("chk");
	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href = "style.css">
<script type="text/javascript" src = "script.js"></script>
</head>
<body>
	<%@ include file = "Header.jsp" %>
	
	<% if(chk.equals("fine")){ %>
		<section>
		<div class = "head"><h2> 예약번호 :  <%=resvno%>님의 접종예약조회  </h2></div>
		<div class = "wrap"> 
		<table style = "width : 900px">
		<tr>
		<th> 이름 </th>
		<th> 주민번호 </th>
		<th> 성별 </th>
		<th> 전화번호 </th>
		<th> 예약일자 </th>
		<th> 예약시간 </th>
		<th> 병원명 </th>
		<th> 대표전화 </th>
		<th> 병원주소 </th>
		<th> 백신종류 </th>
		</tr>
		
		<tr>
		<td><%=r.getPname() %></td>
		<td><%=r.getJumin() %></td>
		<td><%=r.getGender()%></td>
		<td><%=r.getTel()   %></td>
		<td><%=r.getResvdate()%></td>
		<td><%=r.getResvtime() %></td>
		<td><%=r.getHospname()%></td>
		<td><%=r.getHosptel()%></td>
		<td><%=r.getHospaddr()%></td>
		<td><%=r.getVcode()%></td>
		</tr>
		</table>
		
		<br>
		<br>
		</div>
		<button class = "btn" type="reset" onclick = "location.href = 'look.jsp'; return false;">돌아가기</button>
	</section>
	<%} else {%>
	<section class = "main2">
 	<h1> 접종 예약정보가 존재하지 않습니다! 이 버전은 수정된 버전입니다!</h1>	
 	<button class = "btn" type = "reset" name = "reset" onclick="location.href='look.jsp'; return false;"> 돌아가기 </button>
	 </section>
	<%} %>
	<%@ include file =  "Footer.jsp" %>

</body>
</html>