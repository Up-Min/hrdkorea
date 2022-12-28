package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import DAO.wk_DAO;
import DTO.logininfo;

/**
 * Servlet implementation class wk_Controller
 */
@WebServlet("/")
public class wk_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private wk_DAO dao;
    private ServletContext ctx;
	
	
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new wk_DAO();
		ctx = getServletContext();
	}

	public wk_Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doIt(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doIt(request, response);
	}

	protected void doIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;
		
		
		switch (command) {
		case "/index" : site = "index.jsp"; break;
		case "/signup": site = signUp(request); break;
		case "/signin": site = signIn(request, response); break;
		case "/main" : site = "main.jsp"; break;
		case "/start" : site = "wk_insert.jsp"; break;
		case "/insert" : site = insertWorkout(request); break;
//		case "/getWorkout" : site = getWorkoutnum(request); break;
//		case "/insertEx" : site = insertExercise(request); break;
		}
		
		if(site.startsWith("redirect:/")) { // "" 안에 시작하는 문자열을 찾아준다.
			String rview = site.substring("redirect:/".length());
			System.out.println(rview);
			response.sendRedirect(rview);
		}else{
			ctx.getRequestDispatcher("/"+site).forward(request, response);
		}
	}
	
	public String signUp(HttpServletRequest request) {
		logininfo l = new logininfo();
		try {
			BeanUtils.populate(l, request.getParameterMap());
			dao.signUp(l);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("index");
		return "redirect:/index";
	}
	
	
	public String signIn(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("user_id");
			String num = request.getParameter("user_number");
			logininfo l = dao.signIn(request);
			
			if(id.equals(l.getUser_id()))  {
				request.setAttribute("id", l.getUser_id());
				request.setAttribute("num",l.getUser_number());
				return "main";
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('로그인 정보가 정확하지 않습니다.'); location.href = 'index.jsp';");
				out.print("</script>");
				out.flush();
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("con_signin");
		return "main";
	}
	
	public String insertWorkout (HttpServletRequest request) {
		try {
			String[] s_temp1 = request.getParameterValues("ex_name");
			String[] s_temp2 = request.getParameterValues("ex_weight");
			String[] s_temp3 = request.getParameterValues("ex_reps");
			String[] s_temp4 = request.getParameterValues("ex_sets");
			System.out.println(Arrays.toString(s_temp1));
			System.out.println(Arrays.toString(s_temp2));
			System.out.println(Arrays.toString(s_temp3));
			System.out.println(Arrays.toString(s_temp4));
			
			
			dao.insertWorkout(request);
			int workoutnum = dao.getWorkoutnum(request);
			request.setAttribute("workoutnum", workoutnum);
			for(int i=0; i<s_temp1.length; i++) {
				dao.insertExercise(request, s_temp1[i], s_temp2[i], s_temp3[i], s_temp4[i] );
			}

			
//			request.setAttribute("ex_name_list", s_temp1);
//			request.setAttribute("ex_weight_list", s_temp2);
//			request.setAttribute("ex_reps_list", s_temp3);
//			request.setAttribute("ex_sets_list", s_temp4);
//			for(String ex_name : s_temp1) {
//				dao.insertExercise_name(request, ex_name);
//			}
//			for(String ex_weight : s_temp2) {
//				dao.insertExercise_name(request, ex_weight);
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "main";
	}
	
//	public String getWorkoutnum(HttpServletRequest request) {
//		String[] s_temp1 = request.getParameterValues("ex_name");
//		String[] s_temp2 = request.getParameterValues("ex_weight");
//		String[] s_temp3 = request.getParameterValues("ex_reps");
//		String[] s_temp4 = request.getParameterValues("ex_sets");
//		request.setAttribute("ex_name_list", s_temp1);
//		request.setAttribute("ex_weight_list", s_temp2);
//		request.setAttribute("ex_reps_list", s_temp3);
//		request.setAttribute("ex_sets_list", s_temp4);
//		try {
//			dao.getWorkoutnum(request);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
//		return "insertEx";
//	}
	
//	public String insertExercise(HttpServletRequest request) {
//		try {
//			String[] s_temp1 = request.getParameterValues("ex_name");
//			String[] s_temp2 = request.getParameterValues("ex_weight");
//			String[] s_temp3 = request.getParameterValues("ex_reps");
//			String[] s_temp4 = request.getParameterValues("ex_sets");
//			String workoutnum = request.getParameter("workoutnum");
//			System.out.println(workoutnum);
//			request.setAttribute("workoutnum", workoutnum);
//			request.setAttribute("ex_name_list", s_temp1);
//			request.setAttribute("ex_weight_list", s_temp2);
//			request.setAttribute("ex_reps_list", s_temp3);
//			request.setAttribute("ex_sets_list", s_temp4);
//			dao.insertExercise(request);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return "main";
//	}
	
	
}
