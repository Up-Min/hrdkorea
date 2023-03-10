package DAO;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DTO.find;
import DTO.calc;

public class VacDAO {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "gisa", "gisa1234");
		return con;
	}
	
	public int insert (HttpServletRequest request, HttpServletResponse response) {
		String resvno = request.getParameter("resvno");
		String jumin = request.getParameter("jumin");
		String vcode = request.getParameter("vcode");
		String hospcode = request.getParameter("hospcode");
		String resvdate = request.getParameter("resvdate");
		String resvtime = request.getParameter("resvtime");
		int result = 0;
		try {
			System.out.println(con);
			con = getConnection();
			String sql = "insert into tbl_vaccresv_202108 values (?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, resvno);
			ps.setString(2, jumin);
			ps.setString(3, vcode);
			ps.setString(4, hospcode);
			ps.setString(5, resvdate);
			ps.setString(6, resvtime);
			result = ps.executeUpdate();
			System.out.println(result);
			con.close();
			ps.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;	
	}
		
	public String find(HttpServletRequest request, HttpServletResponse response) {
		String resvno = request.getParameter("resvno");
		String result;
		try {
			System.out.println(con);
			con = getConnection();
			String sql = "select b.resvno as resvno, a.pname as pname, b.jumin as jumin, decode(substr(b.jumin,8,1), '1', '남자', '2', '여자') gender ,a.tel as tel, substr(b.resvdate,0,4)||'년'||substr(b.resvdate,5,2)||'월'||substr(b.resvdate,7,2)||'일' as resvdate, substr(b.resvtime,1,2)||':'||substr(b.resvtime,3,2) as resvtime, c.hospname as hospname, c.hosptel as hosptel, c.hospaddr as hospaddr, decode(b.vcode,'V001','A백신','V002','B백신','V003','C백신') vcode from tbl_jumin_201808 a join tbl_vaccresv_202108 b on a.jumin = b.jumin join tbl_hosp_202108 c on b.hospcode = c.hospcode where resvno = '"+resvno+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			find f = new find();
			
			if(rs.next()) {
				result = "true";
				f.setResvno(rs.getString(1));
				f.setPname(rs.getString(2));
				f.setJumin(rs.getString(3));
				f.setGender(rs.getString(4));
				f.setTel(rs.getString(5));
				f.setResvdate(rs.getString(6));
				f.setResvtime(rs.getString(7));
				f.setHospname(rs.getString(8));
				f.setHosptel(rs.getString(9));
				f.setHospaddr(rs.getString(10));
				f.setVcode(rs.getString(11));
			}else{
				result = "false";
			}
			System.out.println(result);
			request.setAttribute("result", result);
			request.setAttribute("resvno", resvno);
			request.setAttribute("find", f);
			
			con.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "find.jsp";
	}

	public String calc(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<calc> list = new ArrayList<calc>();
		
		try {
			System.out.println(con);
			con = getConnection();
			String sql = "select a.hospcode, a.hospname, b.count from tbl_hosp_202108 a, (select count(hospcode) as count, hospcode from tbl_vaccresv_202108 group by hospcode) b where a.hospcode = b.hospcode order by hospname";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				calc c = new calc();
				c.setHospcode(rs.getString(1));
				c.setHospname(rs.getString(2));
				c.setCount(rs.getString(3));
				list.add(c);
			}
			request.setAttribute("list", list);
			
			con.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "calc.jsp";
	}
	
	
	
	
	
	
	
	
	
	
}
