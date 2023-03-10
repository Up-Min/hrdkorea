<%@page
	import="org.apache.commons.collections.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="java.util.*"%>
<%@ page import="DTO.*"%>

<%
List<workout> list1 = (List)request.getAttribute("wklist0");
List<workout> list2 = (List)request.getAttribute("wklist1");
List<workout> list3 = (List)request.getAttribute("wklist2");
List<workout> list4 = (List)request.getAttribute("wklist3");
List<workout> list5 = (List)request.getAttribute("wklist4");
List<workout> list6 = (List)request.getAttribute("wklist5");

%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
<link rel="stylesheet"
	href="./library/fontawesome-free-6.2.0-web/css/all.min.css">
</head>
<style>
.top {
	text-align: center;
}

.all {
	width: 800px;
	text-align: center;
	color: white;
	border: 3px solid black;
	margin: 0 auto;
	margin-top: 50px;
}

.title {
	margin: 3px;
	font-size: 20px;
}

td {
	padding: 5px;
	font-size: 15px;
	text-align: center;
	margin-left: 3px;
}

hr {
	border: 0px;
}

ul{
list-style: none; 
display: flex; 
padding: 0;
flex-wrap: wrap;
justify-content: center;
}

li{
margin-left : 7px;
margin-right : 7px;
width: 47%; 
margin-bottom: 10px;
color: black;
background-color: wheat;

/*height: 200px;

display: block; */
} 


.wlist{
text-align: center;
margin: 0 auto;
margin-left: 5px;
}

.work:hover {
	cursor: pointer;
}

button:hover {
	cursor: pointer;
}

.bla {
	font-size: 12px;
}

.wktable{
width: 90%
}
</style>
<body style="background-color: rgba(40, 57, 101, .9);">

	<div class="top">




		<div class="all">
			<div class="title">
				<br> <span>${id}님의 운동기록</span> <br>
			</div>
			<hr>
			<div class="content">
				<div class="startwk">
					<form action="start" method="post">
						<input type="hidden" name="id" value="${id}"> <input
							type="hidden" name="num" value="${num}"> <input
							type="hidden" name="pwd" value="${pwd}">
						<p>새로운 운동을 기록합니다.</p>
						<button onclick="chk_form(); return false;">
							<img alt="운동기록" src="./img/workout.png" width="50px">
						</button>
					</form>
				</div>
				<br>
				<hr>
				<div class="listwk">
					<form action="getwk" method="post">
						<input type="hidden" name="id" value="${id}"> <input
							type="hidden" name="num" value="${num}"> <input
							type="hidden" name="pwd" value="${pwd}">
						<p>요일별 운동을 조회합니다.</p>
						<button onclick="chk_form(); return false;">
							<img alt="운동조회" src="./img/list.png" width="50px">
						</button>
					</form>
					<br>
					<ul>

						<li>
							<form action="tolist" method="post">
								<div class="wlist list0">
									<input type="hidden" name="id" value="${id}"> <input
										type="hidden" name="num" value="${num}"> <input
										type="hidden" name="pwd" value="${pwd}"> <input
										type="hidden" name="list" value="${wklist0}">
									<table class = "wktable">
										<c:forEach items="${wklist0}" var="w" varStatus="status">
											<input type=hidden name="${w.wk_number}"
												value="${w.wk_number}">
											<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
											<tr class = "work"
												onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
												<td>${w.wk_date}</td>
												<td>${w.ex_name}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</form>
						</li>
						<li>
							<form action="tolist" method="post">
								<div class="wlist list1">
									<input type="hidden" name="id" value="${id}"> <input
										type="hidden" name="num" value="${num}"> <input
										type="hidden" name="pwd" value="${pwd}"> <input
										type="hidden" name="list" value="${wklist1}">
									<table class = "wktable">
										<c:forEach items="${wklist1}" var="w" varStatus="status">
											<input type=hidden name="${w.wk_number}"
												value="${w.wk_number}">
											<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
											<tr class = "work"
												onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
												<td>${w.wk_date}</td>
												<td>${w.ex_name}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</form>
						</li>
						<li>
							<form action="tolist" method="post">
								<div class="wlist list2">
									<input type="hidden" name="id" value="${id}"> <input
										type="hidden" name="num" value="${num}"> <input
										type="hidden" name="pwd" value="${pwd}"> <input
										type="hidden" name="list" value="${wklist2}">
									<table class = "wktable">
										<c:forEach items="${wklist2}" var="w" varStatus="status">
											<input type=hidden name="${w.wk_number}"
												value="${w.wk_number}">
											<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
											<tr class = "work"
												onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
												<td>${w.wk_date}</td>
												<td>${w.ex_name}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</form>
						</li>
						<li>
							<form action="tolist" method="post">
								<div class="wlist list3">
									<input type="hidden" name="id" value="${id}"> <input
										type="hidden" name="num" value="${num}"> <input
										type="hidden" name="pwd" value="${pwd}"> <input
										type="hidden" name="list" value="${wklist3}">
									<table class = "wktable">
										<c:forEach items="${wklist3}" var="w" varStatus="status">
											<input type=hidden name="${w.wk_number}"
												value="${w.wk_number}">
											<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
											<tr class = "work"
												onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
												<td>${w.wk_date}</td>
												<td>${w.ex_name}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</form>
						</li>
						<li>
							<form action="tolist" method="post">
								<div class="wlist list4">
									<input type="hidden" name="id" value="${id}"> <input
										type="hidden" name="num" value="${num}"> <input
										type="hidden" name="pwd" value="${pwd}"> <input
										type="hidden" name="list" value="${wklist4}">
									<table class = "wktable">
										<c:forEach items="${wklist4}" var="w" varStatus="status">
											<input type=hidden name="${w.wk_number}"
												value="${w.wk_number}">
											<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
											<tr class = "work"
												onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
												<td>${w.wk_date}</td>
												<td>${w.ex_name}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</form>
						</li>
						<li>
							<form action="tolist" method="post">
								<div class="wlist list5">
									<input type="hidden" name="id" value="${id}"> <input
										type="hidden" name="num" value="${num}"> <input
										type="hidden" name="pwd" value="${pwd}"> <input
										type="hidden" name="list" value="${wklist5}">
									<table class = "wktable">
										<c:forEach items="${wklist5}" var="w" varStatus="status">
											<input type=hidden name="${w.wk_number}"
												value="${w.wk_number}">
											<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
											<tr
												onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
												<td>${w.wk_date}</td>
												<td>${w.ex_name}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</form>
						</li>
					</ul>
					<p class="bla">운동 목록을 눌러 상세페이지로 이동합니다.</p>
					<br>
					<p>계정에서 로그아웃 합니다.</p>
					<div class="logout-btn">
						<button onclick="logout(); return false;">
							<img alt="로그아웃" src="./img/logout.png" width="45px">
						</button>
					</div>
					<br>


				</div>
			</div>
		</div>
	</div>

	<%-- 	<form action="tolist" method="post">
		
		
		
		
		
		<input type="hidden" name="id" value="${id}"> <input
			type="hidden" name="num" value="${num}">
		<p>회원아이디 ${id} 님.
		<p>전체 운동을 조회합니다.</p>
		<button onclick="chk_form(); return false;">버튼 테스트</button>
	</form> --%>



	<%-- 	<table>
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


 --%>
	<script type="text/javascript" src="./script.js"></script>
</body>
</html>