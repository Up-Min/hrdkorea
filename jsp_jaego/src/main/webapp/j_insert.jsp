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
	<h1> 신규품목 추가 페이지 </h1>
	
	<form name = "formAdd" action="add" method="post">

		<table>
			<tr>
				<td> 상품 이름 </td>
				<td> 상품 수량 </td>
			</tr>
			<tr>
				<td><input type = "text" name = "p_name" placeholder="상품 이름을 입력하세요.">  </td>			
				<td><input type = "text" name = "remain" placeholder="상품 수량을 입력하세요.">  </td>			
			</tr>
		</table>
			<button class="button_insert" onclick = "chk_in(); return false;"> 상품 추가 </button>
		
		</form>

	
</body>
</html>