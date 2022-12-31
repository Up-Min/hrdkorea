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
<link rel="stylesheet" href="./library/fontawesome-free-6.2.0-web/css/all.min.css">
</head>
<style>


.all{
width: 800px; 
position: fixed;
top: 50px;
left: 100px; 
text-align: center; 
color:white; 
border: 3px solid black;
}

li:hover{
cursor: pointer;
}
</style>
<body style="background-color: rgba(40,57,101,.9);">
	<div class="all">
		<div class="title">
			<p>${id}님의 운동기록</p>
		</div>

		<div class="logout-btn">
			<button onclick="location.href='index'">로그아웃</button>
		</div>
		<div class="content">
			<div class="startwk">
				<form action="start" method="post">
					<input type="hidden" name="id" value="${id}"> <input
						type="hidden" name="num" value="${num}"> <input
						type="hidden" name="pwd" value="${pwd}">
					<p>${id}님
					<p>새로운 운동을 기록합니다.</p>
					<button onclick="chk_form(); return false;">운동 기록</button>
				</form>
			</div>
			<div class="listwk">
				<form action="getwk" method="post">
					<input type="hidden" name="id" value="${id}"> <input
						type="hidden" name="num" value="${num}"> <input
						type="hidden" name="pwd" value="${pwd}">
					<p>${id}님
					<p>요일별 운동을 조회합니다.</p>
					<button onclick="chk_form(); return false;">요일별 운동 조회</button>
				</form>
				<ul style = "list-style : none; display: inline-block; padding:0;">
				<li>
					<table>
					<tr>
						<th> 운동일자 </th>
						<th> 운동부위 </th>
						<th> 운동명 </th>
						<th> 무게 </th>
						<th> 세트 당 횟수 </th>
						<th> 세트 수 </th>
					</tr>
					</table>
				
				</li>
				
					<li >
						<form action="tolist" method="post">
							<input type="hidden" name="id" value="${id}"> <input
								type="hidden" name="num" value="${num}"> <input
								type="hidden" name="pwd" value="${pwd}"> <input
								type="hidden" name="list" value="${wklist0}">
							<table>
								<c:forEach items="${wklist0}" var="w" varStatus="status">
									<input type=hidden name="${w.wk_number}" value="${w.wk_number}">
									<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
									<tr
										onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
										<td>${w.wk_date}</td>
										<td>${w.wk_part}</td>
										<td>${w.ex_name}</td>
										<td>${w.ex_weight}</td>
										<td>${w.ex_reps}</td>
										<td>${w.ex_sets}</td>
									</tr>
								</c:forEach>
							</table>
						</form>
					</li>
					<li>
						<form action="tolist" method="post">
							<input type="hidden" name="id" value="${id}"> <input
								type="hidden" name="num" value="${num}"> <input
								type="hidden" name="pwd" value="${pwd}"> <input
								type="hidden" name="list" value="${wklist1}">
							<table>
								<c:forEach items="${wklist1}" var="w" varStatus="status">
									<input type=hidden name="${w.wk_number}" value="${w.wk_number}">
									<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
									<tr
										onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
										<td>${w.wk_date}</td>
										<td>${w.wk_part}</td>
										<td>${w.ex_name}</td>
										<td>${w.ex_weight}</td>
										<td>${w.ex_reps}</td>
										<td>${w.ex_sets}</td>

									</tr>
								</c:forEach>
							</table>
						</form>
					</li>
					<li>
						<form action="tolist" method="post">
							<input type="hidden" name="id" value="${id}"> <input
								type="hidden" name="num" value="${num}"> <input
								type="hidden" name="pwd" value="${pwd}"> <input
								type="hidden" name="list" value="${wklist2}">
							<table>
								<c:forEach items="${wklist2}" var="w" varStatus="status">
									<input type=hidden name="${w.wk_number}" value="${w.wk_number}">
									<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
									<tr
										onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
										<td>${w.wk_date}</td>
										<td>${w.wk_part}</td>
										<td>${w.ex_name}</td>
										<td>${w.ex_weight}</td>
										<td>${w.ex_reps}</td>
										<td>${w.ex_sets}</td>
									</tr>
								</c:forEach>
							</table>
						</form>
					</li>
					<li>
						<form action="tolist" method="post">
							<input type="hidden" name="id" value="${id}"> <input
								type="hidden" name="num" value="${num}"> <input
								type="hidden" name="pwd" value="${pwd}"> <input
								type="hidden" name="list" value="${wklist3}">
							<table>
								<c:forEach items="${wklist3}" var="w" varStatus="status">
									<input type=hidden name="${w.wk_number}" value="${w.wk_number}">
									<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
									<tr
										onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
										<td>${w.wk_date}</td>
										<td>${w.wk_part}</td>
										<td>${w.ex_name}</td>
										<td>${w.ex_weight}</td>
										<td>${w.ex_reps}</td>
										<td>${w.ex_sets}</td>
									</tr>
								</c:forEach>
							</table>
						</form>
					</li>
					<li>
						<form action="tolist" method="post">
							<input type="hidden" name="id" value="${id}"> <input
								type="hidden" name="num" value="${num}"> <input
								type="hidden" name="pwd" value="${pwd}"> <input
								type="hidden" name="list" value="${wklist4}">
							<table>
								<c:forEach items="${wklist4}" var="w" varStatus="status">
									<input type=hidden name="${w.wk_number}" value="${w.wk_number}">
									<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
									<tr
										onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
										<td>${w.wk_date}</td>
										<td>${w.wk_part}</td>
										<td>${w.ex_name}</td>
										<td>${w.ex_weight}</td>
										<td>${w.ex_reps}</td>
										<td>${w.ex_sets}</td>
									</tr>
								</c:forEach>
							</table>
						</form>
					</li>
					<li>
						<form action="tolist" method="post">
							<input type="hidden" name="id" value="${id}"> <input
								type="hidden" name="num" value="${num}"> <input
								type="hidden" name="pwd" value="${pwd}"> <input
								type="hidden" name="list" value="${wklist5}">
							<table>
								<c:forEach items="${wklist5}" var="w" varStatus="status">
									<input type=hidden name="${w.wk_number}" value="${w.wk_number}">
									<!-- jstl쓰면 굳이 request쓸 필요 없다. (Board board : boardList) -->
									<tr
										onclick="location.href = 'tolist?user_id=${id}&user_pwd=${pwd}&user_num=${num}&wk_no=${w.wk_number}'">
										<td>${w.wk_date}</td>
										<td>${w.wk_part}</td>
										<td>${w.ex_name}</td>
										<td>${w.ex_weight}</td>
										<td>${w.ex_reps}</td>
										<td>${w.ex_sets}</td>
									</tr>
								</c:forEach>
							</table>
						</form>
					</li>
				</ul>









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