<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<form action="delete" method="post">		
		<input type="hidden" name="work_num" value="${work_num}">
		<input type="hidden" name="user_id" value="${user_id}">
		<input type="hidden" name="user_pwd" value="${user_pwd}">
		<p>운동 리스트 페이지</p>
		<p>${user_id}님의 운동기록</p>

		<table>
			<tr>
				<th>날짜</th>
				<th>부위</th>
				<th>운동</th>
				<th>무게</th>
				<th>횟수</th>
				<th>세트 수</th>
			</tr>
			<c:forEach items="${list}" var="w" varStatus="status">
				<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
				<tr>
					<td>${w.wk_date}</td>
					<td>${w.wk_part}</td>
					<td>${w.ex_name}</td>
					<td>${w.ex_weight}</td>
					<td>${w.ex_reps}</td>
					<td>${w.ex_sets}</td>
					<td
						onclick="location.href = 'edit?user_id=${user_id}&user_pwd=${user_pwd}&user_num=${user_num}&wk_no=${w.wk_number}&ex_num=${w.ex_number}'">
						기록 수정</td>
				</tr>

			</c:forEach>
		</table>

		<p>
			행복은 성취의 기쁨과 창조적 노력이 주는 <br> 쾌감 속에 있다. <br> <b> 프랭클린
				루즈벨트 </b>
		</p>

		
		<button type="submit">운동 기록 전체 삭제</button>
	</form>
		<button onclick="location.href = 'signin?user_id=${user_id}&user_pwd=${user_pwd}'"> 홈으로</button>
	<script type="text/javascript" src="./script.js"></script>
</body>
<%-- 	<table>
		<c:forEach items="${wklist0}" var="w" varStatus="status">
			<input type=hidden value="${w.wk_number}">
			<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
			<tr onclick = "location.href = 'wk_list.jsp?user_num=${num}&list_no=${w.wk_number}'">
				<td>${w.wk_date}</td>
				<td>${w.wk_number}</td>
				<td>${w.ex_number}</td>
				<td>${w.wk_part}</td>
				<td>${w.ex_name}</td>
				<td>${w.ex_weight}</td>
				<td>${w.ex_reps}</td>
				<td>${w.ex_sets}</td>	
			</tr>
		</c:forEach>
	</table>

	<hr>
	<table>
		<c:forEach items="${wklist1}" var="w" varStatus="status">
			<input type=hidden value="${w.wk_number}">
			<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
			<tr>
				<td>${w.wk_date}</td>
				<td>${w.wk_number}</td>
				<td>${w.ex_number}</td>
				<td>${w.wk_part}</td>
				<td>${w.ex_name}</td>
				<td>${w.ex_weight}</td>
				<td>${w.ex_reps}</td>
				<td>${w.ex_sets}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<table>
		<c:forEach items="${wklist2}" var="w" varStatus="status">
			<input type=hidden value="${w.wk_number}">
			<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
			<tr>
				<td>${w.wk_date}</td>
				<td>${w.wk_number}</td>
				<td>${w.ex_number}</td>				
				<td>${w.wk_part}</td>
				<td>${w.ex_name}</td>
				<td>${w.ex_weight}</td>
				<td>${w.ex_reps}</td>
				<td>${w.ex_sets}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<table>
		<c:forEach items="${wklist3}" var="w" varStatus="status">
			<input type=hidden value="${w.wk_number}">
			<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
			<tr>
				<td>${w.wk_date}</td>
				<td>${w.wk_number}</td>
				<td>${w.ex_number}</td>				
				<td>${w.wk_part}</td>
				<td>${w.ex_name}</td>
				<td>${w.ex_weight}</td>
				<td>${w.ex_reps}</td>
				<td>${w.ex_sets}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<table>
		<c:forEach items="${wklist4}" var="w" varStatus="status">
			<input type=hidden value="${w.wk_number}">
			<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
			<tr>
				<td>${w.wk_date}</td>
				<td>${w.wk_number}</td>
				<td>${w.ex_number}</td>				
				<td>${w.wk_part}</td>
				<td>${w.ex_name}</td>
				<td>${w.ex_weight}</td>
				<td>${w.ex_reps}</td>
				<td>${w.ex_sets}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<table>
		<c:forEach items="${wklist5}" var="w" varStatus="status">
			<input type=hidden value="${w.wk_number}">
			<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
			<tr>
				<td>${w.wk_date}</td>
				<td>${w.wk_number}</td>
				<td>${w.ex_number}</td>				
				<td>${w.wk_part}</td>
				<td>${w.ex_name}</td>
				<td>${w.ex_weight}</td>
				<td>${w.ex_reps}</td>
				<td>${w.ex_sets}</td>
			</tr>
		</c:forEach>
	</table> --%>

</html>