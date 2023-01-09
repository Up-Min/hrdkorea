package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import dto.Jaego_DTO;

public class Jaego_DAO {
	
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	
	public Connection open() {
		Connection conn = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "test", "test1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public ArrayList<Jaego_DTO> get_list(HttpServletRequest request) {
		System.out.println("DAO get_list");
		Connection conn = open();

		String sql = "select * from product";
		ArrayList<Jaego_DTO> l = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Jaego_DTO j = new Jaego_DTO();
				j.setP_code(rs.getString(1));
				j.setP_name(rs.getString(2));
				j.setRemain(rs.getInt(3));
				j.setEdit_date(rs.getString(4));
				l.add(j);
			}
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	public int add(HttpServletRequest request, String p_code, String p_name, int remain) {
		System.out.println("DAO add");
		int result = 0;
		String sql = "insert into product values (?,?,?,to_char(sysdate+9/24, 'YYYY-MM-DD'))";
		
		Connection conn = open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_code);
			pstmt.setString(2, p_name);
			pstmt.setInt(3, remain);
			result = pstmt.executeUpdate();
			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	public int edit (HttpServletRequest request, String p_code, String p_name, int remain) {
		System.out.println("DAO edit");
		int result = 0;
		String sql = "update product set p_name = ?, remain = ?, edit_date = to_char(sysdate+9/24, 'YYYY-MM-DD') where p_code = ?";
		
		Connection conn = open();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_name);
			pstmt.setInt(2, remain);
			pstmt.setString(3, p_code);
			result = pstmt.executeUpdate();
			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int delete (HttpServletRequest request, String p_code) {
		int result = 0;
		
		String sql = "delete from product where p_code = ?";
		
		Connection conn = open();
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p_code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
	}
}
