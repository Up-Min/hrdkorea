<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html style="background-color: #667643;">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<style>
	
body{
width: 800px;
height : 500px;
margin: 0 auto;
margin-top: 50px;
background-color: transparent;
}

.workout{
	width: 700px;
	height : 400px;
	text-align: center;
	color: black;
	border: 3px solid black;
	margin: 0 auto;
	margin-top: 50px;
	font-size: 18px;
	background-color: #D9D9D9;
}

.title{
	font-size: 25px;
	line-height: 30px;
	padding: 5px;
	margin-top: 15px;
	margin-bottom: 15px;
}

table{
	margin-top:10px;
	text-align: center;
	margin: 0 auto;
}

tr{
margin-top: 5px;
	padding: 5px;
}


td{
	padding: 5px;
}

.good{
	font-size: 20px;
	line-height: 25px;
}

p{
	margin-top: 10px;
}

.button{
	margin: 0 auto;
	text-align: center;
}

button:hover{
 cursor: pointer;
}
</style>

<body>
	<%
	String num = request.getParameter("user_num");
	String wnum = request.getParameter("work_num");
	%>

	<div class="workout">
		<form name="editForm" action="update" method="post">
		<input type="hidden" name="user_num" value="${user_num}"> 
		<input type="hidden" name="wk_number" value="${workout.wk_number}">
		<input type="hidden" name="ex_number" value="${workout.ex_number}">
		<input type="hidden" name="user_id" value="${user_id}">
		<input type="hidden" name="user_pwd" value="${user_pwd}">
			<div class="day">
			<p class="title"> 운동 수정 페이지 </p>
				<table>
					<tr>
						<th> </th>
						<th>운동 부위 </th> 
						<th><input type="text" name="wk_part" maxlength="20" value="${workout.wk_part}" readonly> </th>
						<th></th>
					</tr>
					<tr>
						<th> <br> </th>
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
						<td></td>
						<td>
							<button onclick="update(); return false;"><img alt="check" src="./img/check.png" width="45px"></button>
							<p> 수정 완료 </p>
							</td>
							<td>
							<button onclick="chk_cancel(${user_id}, ${user_pwd}); return false;"> <img alt="나가기" src="./img/cancel.png" width="45px"> </button>
							<p>수정 취소</p>
						</td>
						<td></td>
					</tr>

				</table>
			</div>
		</form>
		<br>
		<p class="good">
			가장 지치고 고통스러운 시기가 <br> 가장 성장하기 좋은 시기이다 <br>
			<b> -이상민- </b>
		</p>
		
	</div>
	<div class="button">
			<button onclick="location.href = 'signin?user_id=${user_id}&user_pwd=${user_pwd}'"> <img alt="홈으로" src="./img/home.png" width="50px"> </button>	
	</div>
	<script type="text/javascript" src="./script.js"></script>
</body>

</html>