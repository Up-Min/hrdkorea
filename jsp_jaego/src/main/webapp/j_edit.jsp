<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./script.js"></script>
</head>
<body>
	<h1> 품목 수정 페이지 </h1>
	
	<form name = "formEdit" action="edit" method="post">
			<input type = "hidden" name = "p_code" value = "${p_code}"> 
		<table>
			<tr>
				<td> 수정 상품 이름 </td>
				<td> 수정 상품 수량 </td>
			</tr>
			<tr>
				<td><input type = "text" name = "p_name" value="${p_name}">  </td>			
				<td><input type = "text" name = "remain" value="${remain}">  </td>			
			</tr>
		</table>
			<button class="button_insert" onclick = "chk_ed(); return false;"> 상품 수정 </button>
		
		</form>


</body>
</html>