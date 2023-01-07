<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./script.js"></script>
</head>
<style>
.warn_button{
background-color: red;
color: black;
font-size: 18px;
margin-left: 5px;
}

</style>
<body>
	<h1> 상품 재고 확인 & 관리 페이지 </h1>

	<table>
		<tr>
			<th> 상품 코드 </th>
			<th> 상품 이름 </th>
			<th> 상품 수량 </th>
			<th> 최근 수정일 </th>
		</tr>
		
		<c:forEach items = "${list}" var = "l">
			<tr>
				<td> ${l.p_code} </td>
				<td> ${l.p_name} </td>
				<td> ${l.remain} </td>
				<td> ${l.edit_date} </td>
				<td><button type = "button" onclick = "location.href = 'getedit?p_code=${l.p_code}&p_name=${l.p_name}&remain=${l.remain}&edit_date=${l.edit_date}'; return false;"> 재고 수정 </button> </td>
				<td><button class = "warn_button" type = "button" onclick = "chk_delete(); location.href = 'delete?p_code=${l.p_code}'; return false;"> 품목 삭제 (주의!) </button> </td>				
			</tr>
		</c:forEach>
		
		<tr>
			<td><button type="button" onclick = "location.href = 'j_insert.jsp'"> 신규품목 추가 </button></td>
		</tr>
	
	</table>
</body>
</html>