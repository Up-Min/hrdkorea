<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "java.util.*" %>
 <%@ page import = "DTO.find" %>
 
    <%
   	String result = (String)request.getAttribute("result");
    String resvno = (String)request.getAttribute("resvno");
    find f = new find();
    f = (find)request.getAttribute("find");
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
		<%if(result.equals("true")){ %>
		<div class = "head"> <h2> 예약번호 <%= resvno %> 님의 접종예약조회 </h2> </div>		
		<section>
			<div class = "wrap">
				<table>
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
						<td> <%=f.getPname() %>  </td>
						<td> <%=f.getJumin() %>  </td>
						<td> <%=f.getGender() %>  </td>
						<td> <%=f.getTel() %>  </td>
						<td> <%=f.getResvdate() %>  </td>
						<td> <%=f.getResvtime() %>  </td>
						<td> <%=f.getHospname() %>  </td>
						<td> <%=f.getHosptel() %>  </td>
						<td> <%=f.getHospaddr() %>  </td>
						<td> <%=f.getVcode() %>  </td>
					</tr>
					<tr>
						<td colspan="1">
						<button class = "btn" type = "reset" onclick="location = 'search'" > 돌아가기 </button>
						</td>
					</tr>
				</table>
			</div>
		</section>		
		<%}else{ %>
		<div class = "head"> <h1> 접종정보가 존재하지 않습니다!!! </h1> </div>	
		<br>	
		<button class = "btn" type = "reset" onclick="location = 'search'" > 돌아가기 </button>
		<%} %>
		<%@ include file = "footer.jsp" %>
</body>
</html>