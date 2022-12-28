<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div class = "head"><h2> 백신접종예약 </h2></div>
	
	<form name = "reserv" action="watch">
	<input type = "hidden" id = "gubun" value = "watch">	
	<div class = "wrap">
		<table class = "look">
			<tr>
				<th> 예약번호를 입력 하시오. </th>
				<td><input type = "text" name = "resvno">   </td>
			</tr>
			<tr>
				<td colspan = "2">
					<button class = "btn" type="submit" onclick= "fn_seek(); return false;"> 예약조회 </button>
					<button class = "btn" type="reset" onclick= "location.href='index.jsp'; return false;"> 홈으로 </button>
				</td>
			</tr>
		</table>
	</div>
	</form>
	</section>
	
<%@ include file =  "Footer.jsp" %>

</body>
</html>