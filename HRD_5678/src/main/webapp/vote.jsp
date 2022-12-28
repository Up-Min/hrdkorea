<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.*"%>
<%@page import="DTO.Member"%>

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
		
	<section>
	<div class = "title">투표하기</div>
	<form name = "vote" action = "insert">
	<input type = "hidden" id = "GUBUN" value = "insert">
	<div class = "wrap"> 	
		<table>
		<tr>
			<th> 주민번호 </th>
			<td class = "val"><input type = "text" name="v_jumin"> 예:8906153115472</td>
		</tr>
		<tr>
			<th> 성명 </th>
			<td class = "val"><input type = "text" name="v_name"> </td>
		</tr>
		<tr>
			<th> 투표번호 </th>
			<td class = "val">
			<select name="m_no"> 
			<option value = "1"> [1] 김후보 </option>
			<option value = "2"> [2] 이후보 </option>
			<option value = "3"> [3] 박후보 </option>
			<option value = "4"> [4] 조후보 </option>
			<option value = "5"> [5] 최후보 </option>
			</select>
			</td>
		</tr>
		<tr>
			<th> 투표시간 </th>
			<td class = "val"><input type = "text" name="v_time"> </td>
		</tr>
		<tr>
			<th> 투표장소 </th>
			<td class = "val"><input type = "text" name="v_area"> </td>
		</tr>
		<tr>
			<th> 유권자확인 </th>
			<td class = "val"><input type = "radio" name="v_confirm" value="Y"> 확인 <input type = "radio" name="v_confirm" value="N"> 미확인 </td>
		</tr>
		<tr class = "last">
			<td colspan="2">
			<button class = "btn" type="submit" onclick = "fn_submit(); return false;"> 투표하기 </button>
			<button class = "btn" type="reset"  name = "reset" onclick="fn_reset(); location='vote'" > 다시하기 </button>
			</td>
		</tr>
		</table>
	</div>
	</form>	
	</section>
	
	<%@ include file =  "Footer.jsp" %>
</body>
</html>