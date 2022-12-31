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
	<%
	String num = request.getParameter("user_num");
	String wnum = request.getParameter("work_num");
	%>

	<div class="workout">
		<form action="update" method="post">
		<input type="hidden" name="user_num" value="${user_num}"> 
		<input type="hidden" name="wk_number" value="${workout.wk_number}">
		<input type="hidden" name="ex_number" value="${workout.ex_number}">
		<input type="hidden" name="user_id" value="${user_id}">
		<input type="hidden" name="user_pwd" value="${user_pwd}">
			<div class="day">
				<table>
					<tr>
						<th>고객 DB 번호 : <%=num%> 
						<input type="hidden" name="user_number" value="<%= num %>">
						</th>
						<th>운동 수정</th>
					</tr>
					<tr>
						<th>운동 부위 <input type="text" name="wk_part" maxlength="20" value="${workout.wk_part}" readonly>
						</th>
					</tr>
					<tr>
						<th>운동명</th>
						<th>무게</th>
						<th>횟수</th>
						<th>세트 수</th>
					</tr>
					<tr>
						<td><input type="text" name="ex_name" maxlength="40" value="${workout.ex_name}"></td>
						<td><input type="text" name="ex_weight" maxlength="20" value="${workout.ex_weight}"></td>
						<td><input type="text" name="ex_reps" maxlength="20" value="${workout.ex_reps}"></td>
						<td><input type="text" name="ex_sets" maxlength="20" value="${workout.ex_sets}"></td>
					</tr>
					<tr>
						<td colspan="1">
							<button type = "submit">수정 완료</button>
							<button onclick="chk_cancel(); return false;">수정 취소</button>
						</td>
					</tr>

				</table>
			</div>
		</form>
		<p>
			가장 지치고 고통스러운 시기가 <br> 가장 성장하기 좋은 시기이다. <br>
			<b> 이상민 </b>
		</p>
		
	</div>
	
			<button onclick="location.href = 'signin?user_id=${user_id}&user_pwd=${user_pwd}'"> 홈으로 </button>

	<script type="text/javascript" src="./script.js"></script>
</body>

</html>