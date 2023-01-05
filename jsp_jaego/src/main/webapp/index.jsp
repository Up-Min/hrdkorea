<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th> 상품코드 </th>
			<th> 상품이름 </th>
			<th> 상품명 </th>
			<th> 최근 수정일 </th>
		</tr>
		
		<c:forEach items = "${list}" var = "l">
			<tr>
				<td> ${l.p_code} </td>
				<td> ${l.p_name} </td>
				<td> ${l.remain} </td>
				<td> ${l.edit_date} </td>
			</tr>
		</c:forEach>
	
	
	</table>
</body>
</html>