<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.*" %>
	<%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<!-- add.jsp 에 있는거 복사 -->

<%@ include file="topmenu.jsp"%>
	<section>
		<div class="title">홈쇼핑 회원 정보 수정</div>
		<form name="frm" action="action.jsp">
			<input type="hidden" name="gubun" value="modify">
							<!-- 어차피 action에서 전부 처리할거기 때문에, 구분이 필요함. 그래서 modify! -->
			<div class="wrapper">
				<table>
						<!-- jsp코드가 여기 들어갈거임 -->
						<%
						try{
						Class.forName("oracle.jdbc.OracleDriver");
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "gisa", "gisa1234");
						Statement stmt = con.createStatement(); //DB 커넥션 연결
						
						String custno = request.getParameter("custno"); //url에 있는 키를 받아옴.
						//modify.jsp?custno=100002 -> 100002를 가져옴.
						
						String sql = "SELECT custname, phone, address, TO_CHAR(joindate,'YYYY-MM-DD') joindate, grade, city FROM member_tbl_02 WHERE custno =" + custno;
						ResultSet rs = stmt.executeQuery(sql); //쿼리 실행
						
						if(rs.next()){
						%>
						<tr>
						<th>회원번호(자동발생)</th>
						<td>									<!-- String custno 넣어줌.  -->
						<input type = "text" name="custno" value="<%= custno %>" readonly>
						</td>
						</tr>
						<tr>
						<th>회원성명</th>
						<td><input type="text" name="custname" value="<%=rs.getString(1)%>"></td>
					</tr>
					<tr>
						<th>회원전화</th>
						<td><input type="text" name="phone" value="<%=rs.getString(2)%>"></td>
					</tr>
					<tr>
						<th>회원주소</th>
						<td><input type="text" name="address" value="<%=rs.getString(3)%>"></td>
					</tr>
					<tr>
						<th>가입일자</th>
						<td><input type="text" name="joindate" value="<%=rs.getString(4)%>"></td>
					</tr>
					<tr>
						<th>고객등급[A:VIP,B:일반,C:직원]</th>
						<td><input type="text" name="grade" value="<%=rs.getString(5)%>"></td>
					</tr>
					<tr>
						<th>도시코드</th>
						<td><input type="text" name="city" value="<%=rs.getString(6)%>"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit" onclick="fn_submit(); return false;"> 수정 </button>
							<button class="btn" type="button" onclick="location='list.jsp'">조회</button>
						</td>
					</tr>
						<%} 
						con.close();
						stmt.close();
						}catch(Exception e){
							e.printStackTrace();
						}
						
						%>
				</table>
			</div>
		</form>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>