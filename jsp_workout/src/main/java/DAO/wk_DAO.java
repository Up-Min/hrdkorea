package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import DTO.logininfo;
import DTO.workout;

public class wk_DAO {

	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	public Connection open() { // DB와 연결 수행
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "test", "test1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn; 
	}
	
	public void signUp(logininfo l) throws Exception {
		System.out.println("DAO signup");
		Connection conn = open();
		String sql = "insert into logininfo values (login_seq.nextval, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try {
			pstmt.setString(1, l.getUser_id());
			pstmt.setString(2, l.getUser_pwd());
			pstmt.setString(3, l.getUser_email());
			pstmt.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		conn.close();
		pstmt.close();
	}
	
	public logininfo signIn(HttpServletRequest request) throws Exception{
		System.out.println("DAO signIN");
		Connection conn = open();
		logininfo l = new logininfo();
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		String sql ="select user_id, user_pwd, user_number from logininfo where user_id = ? and user_pwd = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pwd); 			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				l.setUser_id(rs.getString(1));
				l.setUser_pwd(rs.getString(2));
				l.setUser_number(rs.getInt(3));
			}
		conn.close();
		pstmt.close();
		return l;
	}
	
	public ArrayList<workout> list (int user_num, int work_num) throws SQLException {
		System.out.println("DAO List");
		ArrayList<workout> workoutlist = new ArrayList<>();
		
		Connection conn = open();
		String sql = "select a.wk_date, a.wk_part, b.ex_name, b.ex_weight, b.ex_reps, b.ex_sets, b.wk_number, b.ex_number from (select * from workout where user_number = ?) a join exercise b on a.wk_number = b.wk_number where b.wk_number = ? order by a.wk_date, b.ex_number";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, user_num);
		pstmt.setInt(2, work_num);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			workout w = new workout();
			w.setWk_date(rs.getString(1));
			w.setWk_part(rs.getString(2));
			w.setEx_name(rs.getString(3));
			w.setEx_weight(rs.getInt(4));
			w.setEx_reps(rs.getInt(5));
			w.setEx_sets(rs.getInt(6));
			w.setWk_number(rs.getInt(7));
			w.setEx_number(rs.getInt(8));
			workoutlist.add(w);
		}
		conn.close();
		pstmt.close();
		return workoutlist;	
	}
	
	public ArrayList<Integer> getWkNumber (HttpServletRequest request) throws SQLException {
		System.out.println("DAO getWkNumber");
		ArrayList<Integer> WkNumList = new ArrayList<>();
		int num = Integer.parseInt(request.getParameter("num"));
		Connection conn = open();
		String sql = "select b.wk_number from (select * from workout where user_number = ? ) a join exercise b on a.wk_number = b.wk_number order by a.wk_part";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			WkNumList.add(rs.getInt(1));
		}
		conn.close();
		pstmt.close();
		return WkNumList;
	}
	
	public ArrayList <workout> insertWkNumber(HttpServletRequest request,int num) throws SQLException {
		System.out.println("DAO insertWkNumber");
		int usnum = Integer.parseInt(request.getParameter("num"));
//		System.out.println(usnum);

		Connection conn = open();
		String sql = "select a.wk_date, a.wk_part, b.ex_name, b.ex_weight, b.ex_reps, b.ex_sets, b.wk_number, b.ex_number from (select * from workout where user_number = ?) a join exercise b on a.wk_number = b.wk_number where b.wk_number = ? order by a.wk_date, b.ex_number";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ArrayList <workout> list = new ArrayList<>();
		
			pstmt.setInt(1, usnum);
			pstmt.setInt(2, num);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				workout w = new workout();
				w.setWk_date(rs.getString(1));
				w.setWk_part(rs.getString(2));
				w.setEx_name(rs.getString(3));
				w.setEx_weight(rs.getInt(4));
				w.setEx_reps(rs.getInt(5));
				w.setEx_sets(rs.getInt(6));
				w.setWk_number(rs.getInt(7));
				w.setEx_number(rs.getInt(8));
				list.add(w);	
		}
		conn.close();
		pstmt.close();
		return list;
		
	}
	
	
	public void insertWorkout(HttpServletRequest request) throws SQLException {
		System.out.println("DAO insertWorkout");
		Connection conn = open();
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		String wk_part = request.getParameter("wk_part");
		System.out.println("user_num : " + user_num);
		System.out.println("part : " + wk_part);
		
		String sql = "insert into workout values(wk_seq.nextval, to_char(sysdate + 9/24, 'YYYY-MM-DD HH24:MI'), ?, "+user_num+")";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try {
			pstmt.setString(1, wk_part);
			pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
		conn.close();
		pstmt.close();
	}
	
	public int getWorkoutnum(HttpServletRequest request) throws SQLException {
		System.out.println("DAO getWorkoutnum");
		Connection conn = open();
		int workoutnum = 0;
		String sql =  "select max(wk_number) from workout";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			workoutnum = rs.getInt(1);	
		}	
		System.out.println("change : " + workoutnum);
		request.setAttribute("workoutnum", workoutnum);
		conn.close();
		pstmt.close();
		return workoutnum;
	}
	
	public workout getForEdit(int user_num, int work_num, int ex_num) throws Exception{
		System.out.println("DAO getForEdit");
		Connection conn = open();
		workout w = new workout();
		
		String sql = "select a.wk_date, a.wk_part, b.ex_name, b.ex_weight, b.ex_reps, b.ex_sets, b.wk_number, b.ex_number from (select * from workout where user_number = ?) a join exercise b on a.wk_number = b.wk_number where b.wk_number = ? and b.ex_number = ? order by a.wk_date, b.ex_number";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, user_num);
		pstmt.setInt(2, work_num);
		pstmt.setInt(3, ex_num);
		ResultSet rs = pstmt.executeQuery();
		
		try {
			while(rs.next()) {
				w.setWk_date(rs.getString(1));
				w.setWk_part(rs.getString(2));
				w.setEx_name(rs.getString(3));
				w.setEx_weight(rs.getInt(4));
				w.setEx_reps(rs.getInt(5));
				w.setEx_sets(rs.getInt(6));
				w.setWk_number(rs.getInt(7));
				w.setEx_number(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn.close();
		pstmt.close();
		rs.close();
		return w;
	}
	
	public int updateEx(workout w) throws SQLException {
		System.out.println("DAO updateEx");
		Connection conn = open();
		String sql ="update exercise set ex_name = ?, ex_weight = ?, ex_reps = ?, ex_sets = ? where wk_number = ? and ex_number = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int result = 0;
		try {
			pstmt.setString(1, w.getEx_name());
			pstmt.setInt(2, w.getEx_weight());
			pstmt.setInt(3, w.getEx_reps());
			pstmt.setInt(4, w.getEx_sets());
			pstmt.setInt(5, w.getWk_number());
			pstmt.setInt(6, w.getEx_number());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		conn.close();
		pstmt.close();
		return result;
	}
	
	public void delete(int work_num) throws SQLException {
		System.out.println("DAO.delete");
		Connection conn = open();
		String sql = "delete from exercise where wk_number = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try {
			pstmt.setInt(1, work_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn.close();
		pstmt.close();
	}
	
	
	public void insertExercise(HttpServletRequest request, String ex_name, String ex_weight, String ex_reps, String ex_sets) throws SQLException {
		System.out.println("DAO insertExercise");
//		String[] s_temp1 = request.getParameterValues("ex_name");
//		String[] s_temp2 = request.getParameterValues("ex_weight");
//		String[] s_temp3 = request.getParameterValues("ex_reps");
//		String[] s_temp4 = request.getParameterValues("ex_sets");
//		System.out.println(Arrays.toString(s_temp1));
//		System.out.println(Arrays.toString(s_temp2));
//		System.out.println(Arrays.toString(s_temp3));
//		System.out.println(Arrays.toString(s_temp4));
		int workoutnum = (Integer)request.getAttribute("workoutnum");
//		System.out.println("inner"+test);
		int ex_weight1 = Integer.parseInt(ex_weight);
		int ex_reps1 = Integer.parseInt(ex_reps);
		int ex_sets1 = Integer.parseInt(ex_sets);
		
		
		Connection conn = open();
		
//		int ex_number = Integer.parseInt(test);
		
		String sql = "insert into exercise values (ex_seq.nextval, ?, ?, ?, ?, ? )";
//		String sql = "insert into exercise(ex_number, wk_number, ex_name) values (ex_seq.nextval, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try {
			pstmt.setInt(1, workoutnum);
			pstmt.setString(2, ex_name);
			pstmt.setInt(3, ex_weight1);
			pstmt.setInt(4, ex_reps1);
			pstmt.setInt(5, ex_sets1);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn.close();
		pstmt.close();
		
		// form에서 동일한 이름의 input이 한꺼번에 들어올 때, 이것들을 어떻게 하면 이쁘게 넣어줄 수 있을까?
		// 얘네들은 한번에 들어오나? 그러면 이름값을 지정을 해줘야하나?
		// 
		
	}
	
	
	
}
