package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Board;

public class BoardDAO {

	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	// 리소스 방식이라는 것을 써먹어보자.

	public Connection open() { // DB와 연결 수행
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "test", "test1234");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn; // DB연결 정보 리턴.
	}

	// 리소스 열고 닫는 방식을 편하게 해보자.
	public ArrayList<Board> getList() throws Exception {
		Connection conn = open(); //
		ArrayList<Board> boardlist = new ArrayList<>(); // board 객체를 저장할 arraylist

		String sql = "select board_no, title, user_id, to_char(reg_date, 'yyyy.mm.dd') regdate, views from board";
		PreparedStatement pstmt = conn.prepareStatement(sql); // 쿼리문 등록 -> 컴파일 해주는 perparedStatement
		ResultSet rs = pstmt.executeQuery(); // 결과값을 받아주는 rs에 pstmt를 실행한 결과(excuteQuery)를 넣어준다.

		// 예전에 리소스를 닫을 때는 close메소드를 만들어 주던지, pstmt.close, rs.close 등등을 이용해서 리소스를 닫아줬었다.
		try {
			while (rs.next()) {
				Board b = new Board();
				b.setBoard_no(rs.getInt(1));
				b.setTitle(rs.getString(2));
				b.setUser_id(rs.getString(3));
				b.setReg_date(rs.getString(4));
				b.setViews(rs.getInt(5));
				boardlist.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
			conn.close();
			pstmt.close();
			rs.close();
			return boardlist;

		// 리소스 자동 닫기를 이용해보자.(try-with-resource 구문)
//		try (conn; pstmt; rs) {
//
//		}
	}

}
