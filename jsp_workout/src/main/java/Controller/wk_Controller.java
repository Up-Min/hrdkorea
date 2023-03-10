package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

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
import DTO.workout;

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
		case "/index" : site = "index.jsp"; break; // 로그인 페이지
		case "/signup": site = signUp(request); break; // 회원가입
		case "/signin": site = signIn(request, response); break; // 로그인
		case "/getwk" : site = getWkNumber(request); break; // 회원별 운동번호 + 운동번호별 운동
		case "/main" : site = "main.jsp"; break; // 메인페이지 (회원 운동 목록)
		
		case "/start" : site = start(request); break; // 신규 운동 작성 페이지
		case "/insert" : site = insertWorkout(request); break; // 운동 입력
		case "/tolist" : site = list(request); break;
		case "/wklist" : site = wklist(request); break;
		case "/list" : site = "wk_list.jsp"; break;
		case "/edit" : site = getForEdit(request); break;
		case "/update" : site = update(request,response); break;
		
		case "/delete" : site = delete(request); break;
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
		System.out.println("Cont SignUp");
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
		System.out.println("Cont signIn");
		try {
			String id = request.getParameter("user_id");
			String pwd = request.getParameter("user_pwd");
			String num = request.getParameter("user_number");
			logininfo l = dao.signIn(request);
			
			if(id.equals(l.getUser_id()))  {
				request.setAttribute("id", l.getUser_id());
				request.setAttribute("num",l.getUser_number());
				request.setAttribute("pwd", l.getUser_pwd());
				System.out.println("정상 로그인");
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
		System.out.println("로그인 에러");
		return "index";
	}
	
	public String getWkNumber(HttpServletRequest request) { //사용자 운동번호 + 운동번호에 따른 목록 
		System.out.println("Cont getWknumber");
		ArrayList<Integer> Wklist;
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		String pwd = request.getParameter("pwd");
		request.setAttribute("pwd", pwd);
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		try {
			Wklist = dao.getWkNumber(request);
			
			LinkedHashSet<Integer> hashwk = new LinkedHashSet<>();
			for(int i : Wklist) {
				hashwk.add(i);
			}
			
			ArrayList<Integer> wknum = new ArrayList<Integer>(hashwk);
			
			for(int i=0; i<wknum.size(); i++) {
				List<workout> list = dao.insertWkNumber(request, wknum.get(i));
				request.setAttribute("wklist"+i+"", list);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "main";
//		return "list";
	}
	
	public String list(HttpServletRequest request) { //사용자 전체 운동 목록
		System.out.println("Cont list");	
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		int work_num = Integer.parseInt(request.getParameter("wk_no"));
		String id = request.getParameter("user_id");
		String pwd = request.getParameter("user_pwd");
		
		List<workout> list;	
		try {
			list = dao.list(user_num, work_num);
			request.setAttribute("list", list);
			request.setAttribute("user_num", user_num);
			request.setAttribute("work_num", work_num);
			request.setAttribute("user_id", id);
			request.setAttribute("user_pwd", pwd);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return "list";
	}
	
	public String wklist (HttpServletRequest request) {
		System.out.println("Cont wklist");
	
		request.setAttribute("wklist0", request.getParameter("list"));
		request.setAttribute("wklist1", request.getParameter("wklist1"));
		request.setAttribute("wklist2", request.getParameter("wklist2"));
		request.setAttribute("wklist3", request.getParameter("wklist3"));
		request.setAttribute("wklist4", request.getParameter("wklist4"));
		request.setAttribute("wklist5", request.getParameter("wklist5"));
		
		String id = request.getParameter("id");
		String num = request.getParameter("num");
		request.setAttribute("id", id);
		request.setAttribute("num", num);
		return "list";
	}

	public String getForEdit (HttpServletRequest request) {
		System.out.println("Cont getForEdit");
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		int work_num = Integer.parseInt(request.getParameter("wk_no"));
		int ex_num = Integer.parseInt(request.getParameter("ex_num"));
		String id = request.getParameter("user_id");
		String pwd = request.getParameter("user_pwd");
		request.setAttribute("user_id", id);
		request.setAttribute("user_pwd", pwd);
		
		try {
			workout w = dao.getForEdit(user_num, work_num, ex_num);
			request.setAttribute("workout", w);
			request.setAttribute("user_num", user_num);
			request.setAttribute("work_num", work_num);
			request.setAttribute("ex_num", ex_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wk_edit.jsp";
	}
	
	public String update (HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Cont update");
		workout w = new workout();
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		int work_num = Integer.parseInt(request.getParameter("wk_number"));
		int ex_num = Integer.parseInt(request.getParameter("ex_number"));
		request.setAttribute("user_num", user_num);
		request.setAttribute("work_num", work_num);
		request.setAttribute("ex_num", ex_num);
		String id = request.getParameter("user_id");
		String pwd = request.getParameter("user_pwd");
		request.setAttribute("user_id", id);
		request.setAttribute("user_pwd", pwd);
		int result = 0;
		try {
			BeanUtils.populate(w, request.getParameterMap());
			result = dao.updateEx(w);
			System.out.println("CONT-DAO 업데이트 결과 : "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tolist?user_num="+user_num+"&wk_no="+work_num+"";
	}
	
	public String delete (HttpServletRequest request) {
		System.out.println("Cont delete");
		int work_num = Integer.parseInt(request.getParameter("work_num"));
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		try {
		dao.delete(work_num);
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return "signin?user_id="+user_id+"&user_pwd="+user_pwd;
	}
	
	public String start(HttpServletRequest request) {
		System.out.println("Cont start");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		request.setAttribute("id", id);
		request.setAttribute("pwd", pwd);
		
		
		return "wk_insert.jsp";
	}
	
	public String insertWorkout (HttpServletRequest request) { // 운동 기입
		System.out.println("Cont insertWorkout");
		String id = request.getParameter("user_id");
		String pwd = request.getParameter("user_pwd");
		String num = request.getParameter("user_num");
		
		try {
			System.out.println("insert num : " + num);
			
			request.setAttribute("user_id", id);
			request.setAttribute("user_pwd", pwd);
			request.setAttribute("user_num", num);
			String[] s_temp1 = request.getParameterValues("ex_name");
			String[] s_temp2 = request.getParameterValues("ex_weight");
			String[] s_temp3 = request.getParameterValues("ex_reps");
			String[] s_temp4 = request.getParameterValues("ex_sets");
			String[] Ns_temp1 = new String[0];
			String[] Ns_temp2 = new String[0];
			String[] Ns_temp3 = new String[0];
			String[] Ns_temp4 = new String[0];

			for(int i=0; i<s_temp1.length; i++) {
			List<String> list = new ArrayList<>(Arrays.asList(Ns_temp1));
				if(s_temp1[i] != "") {
					list.add(s_temp1[i]);
				}
			Ns_temp1 = list.toArray(new String[0]);
			}
			for(int i=0; i<s_temp2.length; i++) {
				List<String> list = new ArrayList<>(Arrays.asList(Ns_temp2));
				if(s_temp2[i] != "") {
					list.add(s_temp2[i]);
				}
				Ns_temp2 = list.toArray(new String[0]);
			}
			for(int i=0; i<s_temp3.length; i++) {
				List<String> list = new ArrayList<>(Arrays.asList(Ns_temp3));
				if(s_temp3[i] != "") {
					list.add(s_temp3[i]);
				}
				Ns_temp3 = list.toArray(new String[0]);
			}
			for(int i=0; i<s_temp4.length; i++) {
				List<String> list = new ArrayList<>(Arrays.asList(Ns_temp4));
				if(s_temp4[i] != "") {
					list.add(s_temp4[i]);
				}
				Ns_temp4 = list.toArray(new String[0]);
			}

			dao.insertWorkout(request);
			int workoutnum = dao.getWorkoutnum(request);
			request.setAttribute("workoutnum", workoutnum);
			for(int i=0; i<Ns_temp1.length; i++) {
				dao.insertExercise(request, Ns_temp1[i], Ns_temp2[i], Ns_temp3[i], Ns_temp4[i] );
			}
			

			
//			for(int i=0; i<s_temp1.length; i++) {
//			List<String> list = new ArrayList<>(Arrays.asList(s_temp1));
//				if(s_temp1[i].isEmpty()) {
//					list.remove(i);					
//				}
//			s_temp1 = list.toArray(new String[0]);
//			}
//			System.out.println(Arrays.toString(s_temp1));
			
			
//			List<String> list = new ArrayList(Arrays.asList(s_temp1));
//			list.removeAll(Collections.singletonList(null)); 
//			System.out.println(list);
//			
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
		return "signin?user_id="+id+"&user_pwd="+pwd+"";
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
