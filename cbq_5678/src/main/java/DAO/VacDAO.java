package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.reserv;
import DTO.calc;

public class VacDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "gisa", "gisa1234");
		return conn;
	}
	
	public int insert(HttpServletRequest request, HttpServletResponse response) {
		String resvno = request.getParameter("resvno");
		String jumin = request.getParameter("jumin");
		String vcode = request.getParameter("vcode");
		String hospcode = request.getParameter("hospcode");
		String resvdate = request.getParameter("resvdate");
		String resvtime = request.getParameter("resvtime");
		int result = 0;
		
		System.out.println("insert");
		
		try {
			conn = getConnection();
			String sql = "insert into tbl_vaccresv_202108 values (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, resvno);
			ps.setString(2, jumin);
			ps.setString(3, vcode);
			ps.setString(4, hospcode);
			ps.setString(5, resvdate);
			ps.setString(6, resvtime);
			result = ps.executeUpdate();
			conn.close();
			ps.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	public String calc(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<calc> list = new ArrayList<calc>();
		
		try {
			conn = getConnection();
			String sql = "select a.hospcode as hospcode, a.hospname as hospname, b.count from tbl_hosp_202108 a, (select count(hospcode) as count, hospcode from tbl_vaccresv_202108 group by hospcode) b where a.hospcode = b.hospcode order by hospname";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				calc cal = new calc();
				cal.setHospcode(rs.getString(1));
				cal.setHospname(rs.getString(2));
				cal.setCount(rs.getString(3));
				list.add(cal);
			}
			request.setAttribute("list", list);
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "calc.jsp";	
	}
	
	
	public String watch (HttpServletRequest request, HttpServletResponse response) {
		String resvno = request.getParameter("resvno");
		System.out.println(resvno);
		String chk = null;
		
		try {
			conn = getConnection();
			String sql = "select b.resvno as resvno, a.pname as pname, b.jumin as jumin, decode(substr(b.jumin,8,1), '1', '??????', '2', '??????') gender ,a.tel as tel, substr(b.resvdate,0,4)||'???'||substr(b.resvdate,5,2)||'???'||substr(b.resvdate,7,2)||'???' as resvdate, substr(b.resvtime,1,2)||':'||substr(b.resvtime,3,2) as resvtime, c.hospname as hospname, c.hosptel as hosptel, c.hospaddr as hospaddr, decode(b.vcode,'V001','A??????','V002','B??????','V003','C??????') vcode from tbl_jumin_201808 a join tbl_vaccresv_202108 b on a.jumin = b.jumin join tbl_hosp_202108 c on b.hospcode = c.hospcode where resvno = '"+resvno+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			reserv res = new reserv();

			if(rs.next()) {
				chk = "fine";
				System.out.println(chk);
				res.setResvno(rs.getString(1));
				res.setPname(rs.getString(2));
				res.setJumin(rs.getString(3));
				res.setGender(rs.getString(4));
				res.setTel(rs.getString(5));
				res.setResvdate(rs.getString(6));
				res.setResvtime(rs.getString(7));
				res.setHospname(rs.getString(8));
				res.setHosptel(rs.getString(9));
				res.setHospaddr(rs.getString(10));
				res.setVcode(rs.getString(11));
			}else {
//				return "wrong.jsp"; // ??? ?????? ???????????? ??????????????? ????????? ???. ?????? ?????? ????????? ???????????? ?????? ????????? ???????????????.
				chk = "wrong";
				System.out.println(chk);
				request.setAttribute("chk", chk);
				conn.close();
				ps.close();
				rs.close();	
			}
			request.setAttribute("chk", chk);
			request.setAttribute("res", res);
			request.setAttribute("resvno", resvno);
			
			conn.close();
			ps.close();
			rs.close();				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "watch.jsp";
	}
	
}
