<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div class="wrap">
		<p>일일 운동 목록 페이지</p>
		<p>${id}님의운동기록</p>
		<p>${id}님의회원번호는${num}입니다.</p>
	</div>
	<form action="start" method="post">
		<input type="hidden" name="id" value="${id}"> <input
			type="hidden" name="num" value="${num}">
		<p>회원아이디 ${id} 님.
		<p>회원번호 ${num} 운동을 시작합니다.</p>
		<button onclick="chk_form(); return false;">버튼 테스트</button>
	</form>

	<form action="tolist" method="post">
		<input type="hidden" name="id" value="${id}"> <input
			type="hidden" name="num" value="${num}">
		<p>회원아이디 ${id} 님.
		<p>전체 운동을 조회합니다.</p>
		<button onclick="chk_form(); return false;">버튼 테스트</button>
	</form>

	<form action="getwk" method="post">
		<input type="hidden" name="id" value="${id}"> <input
			type="hidden" name="num" value="${num}">
		<p>회원아이디 ${id} 님.
		<p>특정 운동을 조회합니다.</p>
		<button onclick="chk_form(); return false;">버튼 테스트</button>
	</form>

	<table>
		<c:forEach items="${wklist0}" var="w" varStatus="status">
			<input type=hidden value="${w.wk_number}">
			<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
			<tr onclick = "location.href = 'list'">
				<td>${w.wk_date}</td>
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
				<td>${w.wk_part}</td>
				<td>${w.ex_name}</td>
				<td>${w.ex_weight}</td>
				<td>${w.ex_reps}</td>
				<td>${w.ex_sets}</td>
			</tr>
		</c:forEach>
	</table>

	<script type="text/javascript" src="./script.js"></script>
</body>
</html>