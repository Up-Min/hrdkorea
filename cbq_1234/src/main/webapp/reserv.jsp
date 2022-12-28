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
	
	<form name = "reserv" action="insert">
	<input type = "hidden" id = "gubun" value = "insert">	
	<div class = "wrap">
	 <table style = "width : 700px">
	 	<tr>
	 		<th> 접종예약번호 </th>
	 		<td class = "val"><input type = "text" name = "resvno"> 예)20210001 </td>
	 	</tr>
	 	<tr>
	 		<th> 주민번호 </th>
	 		<td class = "val"><input type = "text" name = "jumin"> 예)710101-1000001 </td>
	 	</tr>
	 	<tr>
	 		<th> 백신코드 </th>
	 		<td class = "val"><input type = "text" name = "vcode"> 예)V001~V003 </td>
	 	</tr>
	 	<tr>
	 		<th> 병원코드 </th>
	 		<td class = "val"><input type = "text" name = "hospcode"> 예)H001 </td>
	 	</tr>
	 	<tr>
	 		<th> 예약일자 </th>
	 		<td class = "val"><input type = "text" name = "resvdate"> 예)20211231 </td>
	 	</tr>
	 	<tr>
	 		<th> 예약시간 </th>
	 		<td class = "val"><input type = "text" name = "resvtime"> 예)1230 </td>
	 	</tr>
	 	<tr>
	 		<td colspan="1">
	 			<button class="btn" type="submit" onclick ="fn_submit(); return false;" > 등 록 </button>
	 			<button class="btn" type="reset" name="reset" onclick ="fn_reset(); location = 'reserv'" > 다시쓰기 </button>
	 		</td>
	 	</tr>
	 </table>
	</div>
	</form>
	</section>
	<%@ include file = "Footer.jsp" %>
</body>
</html>