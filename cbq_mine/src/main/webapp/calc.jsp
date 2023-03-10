<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import = "java.util.*" %>
    <%@ page import="DTO.calc" %>
    
    <%
    request.setCharacterEncoding("utf-8");
	ArrayList<calc> list = new ArrayList<calc>();
	list = (ArrayList<calc>)request.getAttribute("list");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href = "style.css">
<script type="text/javascript" src = "script.js"></script>
</head>
<body>
		<%@ include file = "headmenu.jsp" %>
		<div class = "head"> <h2> 병원별 접종건수 통계 </h2> </div>		
		<section>
			<div class = "wrap">
				<table>
					<tr>
						<th> 병원코드 </th>
						<th> 병원명 </th>
						<th> 접종건수 </th>
					</tr>
					<% 
					int result = 0;
					for (calc c : list) {%>
					<tr>
						<td> <%=c.getHospcode() %>  </td>
						<td> <%=c.getHospname() %>  </td>
						<td> <%=c.getCount() %>  </td>
						<% result += Integer.parseInt(c.getCount()); %>
					</tr>
					<%} %>

					<tr>
						<td> </td>
						<th> 총 누계 </th>
						<th> <%= result %>  </th>
					</tr>
					<tr>
						<td colspan="1">
						<button class = "btn" type = "reset" onclick="location = 'search'" > 돌아가기 </button>
						</td>
					</tr>
				</table>
			</div>
		</section>	

		<%@ include file = "footer.jsp" %>
</body>
</html>