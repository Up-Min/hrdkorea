package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;

public class MemberDAO {
	Connection conn = null; //db연결 담당
	PreparedStatement ps = null; //쿼리 연결 담당
	ResultSet rs = null; //결과 담아오는 그거
	
	// DB 연결 메소드
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");//오라클 드라이버 로드
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "gisa", "gisa1234");
//		db연결담당에 연결을 해주는데, 오라클의 정보와 접속할 정보, 아이디, 비밀번호를 입력한다.
		return con; // 연결정보를 리턴해준다. 
	} 
	
	//insert (회원등록 그 자체.)
	// request 는 view단에서 요청한 값이 들어가있다. 그래서 받아온 데이터들을 파라미터로 하나하나 빼올 수 있어짐.
	public String insert (HttpServletRequest request, HttpServletResponse response) {
		int custo = Integer.parseInt(request.getParameter("custno"));
		String custname = request.getParameter("custname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city");
		
//		회원등록을 하기 위해 먼저 넣을 회원 정보를 선언해줘야한다. 이때, DTO의 역할을 하는 Member을 참고하여,
//		Member에서 선언했던 아이들을 getter을 통해 받아온다.
		
		
		int result = 0; // ps.executeUpdate -> 입력, 수정, 삭제 결과값을 반영해주기 위해 사용하는데,
		// 얘를 받아줄 int형 변수를 선언한다.
		
		
		try {
			conn = getConnection(); 
			//db 연결. 리소스는 열면 무조건 닫아야 하기 때문에 try, catch 사용.
			String sql = "insert into member_tbl_02 values (?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?)";
			//db의 쿼리문을 입력한다. 이때 특정값을 지정해줄 수 없기 때문에 쿼리문에는 ?로 넣어주고, 선언된 테이블 컬럼 순서대로
			// 쿼리문을 작성해준다.
			ps = conn.prepareStatement(sql);
			// 연결된 DB에 대해 작성한 쿼리문 (String sql)을 보내주는 prepareStatement를 넣어준다.
			ps.setInt(1, custo);
			ps.setString(2, custname);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setString(5, joindate);
			ps.setString(6, grade);
			ps.setString(7, city);
			// 선언해준 회원정보들을 넣어준다. 각 set에 들어가는 ?의 순서대로
			// DTO에서 가져온 getParameter변수들을 알맞게 넣어준다.
			
			// insert, update, delete 성공한 갯수를 반환함. 
			result = ps.executeUpdate();
			// executeUpdate를 통해 앞서 선언했던 insert를 반영해준다. 
			System.out.println(result);
			
			conn.close();
			ps.close();
			//사용했던 리소스들을 닫아준다. 
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "add";
		// 작업이 마치고 나도 add.jsp 페이지에 머물 수 있도록 add를 리턴해준다.
	}
	
	// 회원번호 자동 증가
	public String nextCustno(HttpServletRequest request, HttpServletResponse response) {
		try{
			conn = getConnection();
//			DB연결
			String sql = "select max(custno)+1 custno from member_tbl_02";
//			자동으로 증가시킬 회원번호의 마지막 번호 (제일 큰 번호)에 +1 을 시켜줌.
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int custno = 0;
//			증가시킨 회원번호를 받을 수 있는 int형 변수 선언
			
			if(rs.next()) custno = rs.getInt(1);
//			rs.next, 즉 sql단에서 다음 행에 값이 있을 경우,
//			첫번째로 선언된 변수 (회원번호)를 받아온다, 그리고 그 받아온 것을 custno에 저장한다.
			
			request.setAttribute("custno", custno);
//			sql로부터 받아온 회원번호 custno를 request에 저장해준다.
			
			conn.close();
			ps.close();
			rs.close();
			// 리소스들을 닫아준다.
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "add.jsp";
// 회원번호 자동 증가는 add페이지에서 운용되므로, add페이지에 계속 남아있을 수 있도록 add.jsp를 리턴해준다.
	}
	
	//회원목록 조회 (전부 가져오기)
	public String selectAll(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Member> list = new ArrayList<Member>();
//		각 회원의 모든 정보를 담을 수 있는 arraylist를 선언해준다.
		try {		
			conn = getConnection();
			//DB연결
String sql = "select custno, custname, phone, address, TO_CHAR(joindate, 'YYYY-MM-DD') joindate,"; 
sql += " DECODE(grade, 'A', 'VIP', 'B', '일반', 'C', '직원') grade, city from member_tbl_02 order by custno";
//쿼리문 입력, 이때 date나 grade와 같이 특정 셋업이 필요한 아이들도 DB에서 가져오는 동안에 처리될 수 있도록
//쿼리문 안에 설정을 해준다.

// grade세부 설정과 같이 어지간한 건 전부 DB로 해결할 수 있어야한다. - decode.
			
			ps = conn.prepareStatement(sql);
	// 쿼리문 입력
			rs = ps.executeQuery(); 
	//select한 쿼리문을 적용시킨다.
			
			while(rs.next()) {
				Member member = new Member();
				member.setCustno(rs.getInt(1));
				member.setCustname(rs.getString(2));
				member.setPhone(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setJoindate(rs.getString(5));
				member.setGrade(rs.getString(6));
				member.setCity(rs.getString(7));	
				list.add(member);
//	select를 통해 가져오게 될 값들을 member 객체에 넣어주고, 이를 114번줄에서 선언한 arraylist에 넣어준다.
	
			}
			request.setAttribute("list", list);
			// "list"라는 이름으로 list를 request에 넣어준다.
			conn.close();
			ps.close();
			rs.close();
			// 사용했던 리소스들을 닫아준다.
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return "list.jsp";
		// 회원 목록을 뜨게하는 list.jsp를 리턴시킨다.
	}
	
	//회원정보 수정위한 원본 가져오기
	public String modify(HttpServletRequest request, HttpServletResponse response) {
//		수정할 때는 내가 이전에 입력했던 정보가 보이는 상태에서 수정을 한다.
//		즉, 데이터를 먼저 가져와야 한다.
//		컨트롤러에 리퀘스트에 있는 list에서 가져온 custno. 그 정보를 사용할 수 있다. 
	try {
		conn = getConnection();
		int custno = Integer.parseInt(request.getParameter("custno"));
		// 현재 custno를 가져와서 int형 변수로 저장시켜 준다.
		
		String sql = "select custname, phone, address, TO_CHAR(joindate, 'YYYY-MM-DD') joindate, grade, city ";
		sql += "from member_tbl_02 where custno=" + custno;
		// 회원정보 수정 페이지에 필요한 컬럼들을 쿼리문을 통해 가져올 것이다. 이때, 내가 수정하려는 custno의 값을 쿼리문에 넣을 수 있도록
		// 이전에 선언한 custno를 넣어준다.
		
		
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery(); //DB에서 가져온 데이터 넣어줌.
		
		Member member = new Member();
		
		// 하나만 가져올거니까 굳이 리스트화 안하고 바로 하나만 사용할거다.
		if(rs.next()) {
			member.setCustno(custno);
			member.setCustname(rs.getString(1));
			member.setPhone(rs.getString(2));
			member.setAddress(rs.getString(3));
			member.setJoindate(rs.getString(4));
			member.setGrade(rs.getString(5));
			member.setCity(rs.getString(6));
		}
		
		request.setAttribute("member", member);
		// 가져온 member객체를 member이라는 이름을 붙여서 저장해준다.
		request.setAttribute("custno", custno);
		// 동일하게, 수정하려했던 고객번호, custno도 저장해준다.
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "modify.jsp";
	
	}
	
	// 이제, 업데이트.
	public int update (HttpServletRequest request, HttpServletResponse response) { 
		int custo = Integer.parseInt(request.getParameter("custno"));
		String custname = request.getParameter("custname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city");
		int result = 0;
		
		try {
			conn = getConnection(); 
			//db 연결. 리소스는 열면 무조건 닫아야 하기 때문에 try, catch 사용.
			String sql = "update member_tbl_02 set";
					sql += " custname = ?, ";
					sql += " phone = ?, ";
					sql += " address = ?, ";
					sql += " joindate = to_date(?,'yyyy-mm-dd'), ";
					sql += " grade = ?, ";
					sql += " city = ? ";
					sql += " where custno = ?";
			
					ps = conn.prepareStatement(sql);
					ps.setString(1, custname);
					ps.setString(2, phone);
					ps.setString(3, address);
					ps.setString(4, joindate);
					ps.setString(5, grade);
					ps.setString(6, city);
					ps.setInt(7, custo);
					
			// insert, update, delete 성공한 갯수를 반환함. 
			result = ps.executeUpdate();
			// 우리는 업데이트 하나만 할거니까 리턴값이 1이 나올거임. 그 1을 써먹는다.
			// 회원정보 수정 완료여부 표시를 위해 1을 써먹을거다.
			
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
}
