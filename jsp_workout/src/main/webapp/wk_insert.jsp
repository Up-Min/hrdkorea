<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="./script.js"></script>
</head>
<style>
.top {
 text-align: center;
}
.workout {
	width: 700px;
	height : 550px;
	text-align: center;
	color: black;
	border: 5px solid white;
	margin: 0 auto;
	margin-top: 20px;
	font-size: 18px;
	background-color: #D9D9D9;
}

.title{
	margin: 3px;
	font-size: 20px;
}


.day{
	margin: 0 auto;
	text-align: center;
	padding: 5px;
	display: grid;
}

.good{
	font-size: 20px;
	line-height: 25px;
}

.content{
width : 900px;
margin: 0 auto;
margin-top: 50px;
}

.btn_tohome{
 text-align: center;
}

.tohome{
	margin: 0 auto;
}

.last_btn{
 margin: 0 auto;
 text-align: center;
}

table:hover{
	cursor: auto;
}

hr{
 border : 0px;
}

td{
	padding: 5px;
}

img:hover {
	cursor: pointer;
}

p{
margin-top: 5px;
}

.head{
padding: 7px;
font-size: 25px;
line-height: 30px;
}

</style>
<body style = " background-color: #5E0707;">
	<%
	String num = request.getParameter("num");
	%>
	
	<div class = "content"> 
	
	
	<div class="workout">
	<p class = "head"> 운동 기록 페이지 </p>
		<form name="insertForm" action="insert">
		<input type="hidden" name="user_id" value="${id}"> 
		<input type="hidden" name="user_num" value="<%=num%>">
		<input type="hidden" name="user_pwd" value="${pwd}">
			<div class="day">
				<table>
					<tr>
						<th> </th>
						<th> <br>  </th>
					</tr>
					<tr class = "part">
						<th> </th>
						<th>운동 부위 </th>
						<th> 
						<input type="text" name="wk_part" maxlength="20">
						</th>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<th>운동명</th>
						<th>무게</th>
						<th>횟수</th>
						<th>세트 수</th>
					</tr>
					<tr>
						<td><input type="text" name="ex_name" maxlength="40"></td>
						<td><input type="text" name="ex_weight" maxlength="20"></td>
						<td><input type="text" name="ex_reps" maxlength="20"></td>
						<td><input type="text" name="ex_sets" maxlength="20"></td>
					</tr>
					<tr>
						<td><input type="text" name="ex_name" maxlength="40"></td>
						<td><input type="text" name="ex_weight" maxlength="20"></td>
						<td><input type="text"  name="ex_reps" maxlength="20"></td>
						<td><input type="text"  name="ex_sets" maxlength="20"></td>
					</tr>
					<tr>
						<td><input type="text" name="ex_name" maxlength="40"></td>
						<td><input type="text" name="ex_weight" maxlength="20"></td>
						<td><input type="text" name="ex_reps" maxlength="20"></td>
						<td><input type="text" name="ex_sets" maxlength="20"></td>
					</tr>
					<tr>
						<td><input type="text" name="ex_name" maxlength="40"></td>
						<td><input type="text" name="ex_weight" maxlength="20"></td>
						<td><input type="text" name="ex_reps" maxlength="20"></td>
						<td><input type="text" name="ex_sets" maxlength="20"></td>
					</tr>
					<tr>
						<td><input type="text" name="ex_name" maxlength="40"></td>
						<td><input type="text" name="ex_weight" maxlength="20"></td>
						<td><input type="text" name="ex_reps" maxlength="20"></td>
						<td><input type="text" name="ex_sets" maxlength="20"></td>
					</tr>
					<tr>
						<td><input type="text" name="ex_name" maxlength="40"></td>
						<td><input type="text" name="ex_weight" maxlength="20"></td>
						<td><input type="text" name="ex_reps" maxlength="20"></td>
						<td><input type="text" name="ex_sets" maxlength="20"></td>
					</tr>
					<tr class = "last_btn">
						<td></td>
						<td>
							<br>
							<button type = "submit" onclick="insert(); return false;"> <img alt="성공" src="./img/achievement.png" width="50px"></button>
							<br>
							<p> 운동기록 </p>
						</td>
						<td>
						<br>
<%-- 						<button onclick="location.href = 'signin?user_id=${id}&user_pwd=${pwd}'; return false;"><img alt="나가기" src="./img/exit1.png" width="45px"></button> --%>
						<button onclick="chk_cancel(${id},${pwd}); return false;"><img alt="나가기" src="./img/exit1.png" width="45px"></button>
						<br>
						<p> 운동취소 </p>
						</td>
						<td></td>		
					</tr>
				</table>
			</div>
		</form>
		<div class = "good">
		<p>
			사람이 자신의 몸이 가질 수 있는 마음과 강함을 알지 못하고 <br> 늙어버리는 것은 안타까운 일이다 <br>
			<br>
			<b> -소크라테스- </b>
		</p>
		</div>	
	</div>
	
	<div class="btn_tohome">
	<button class="tohome" onclick="location.href = 'signin?user_id=${id}&user_pwd=${pwd}'"> <img alt="홈으로" src="./img/home.png" width="50px"> </button>	
	</div>
	</div>
<!-- <script>
	function chkInsert() {
		var w = document.wk_insert();
		alert("운동 기록이 완료 되었습니다!");
		w.submit();
	}
	</script> -->
</body>
</html>