package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VoteDAO;

@WebServlet("/")
public class VoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VoteController() {
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
		
		System.out.println(context + "  " + command);
		
		VoteDAO vd = new VoteDAO();
		
		switch (command) {
			case "/home" : site = "index.jsp"; break;
			case "/list" : site = vd.list(request, response); break;
			case "/vote" : site = "vote.jsp"; break;
			case "/insert" : int result1 = vd.insert(request, response); 
							response.setContentType("text/html; charset=UTF-8");
							PrintWriter out = response.getWriter();
							if(result1 == 1) {
								out.print("<script>");					// location.href='/HRD_5678';
								out.print("alert('투표하기 정보가 정상적으로 등록되었습니다!'); location.href='" + context + "';");
								out.print("</script>");
								out.flush();
							}else {
								out.print("<script>");					// location.href='/HRD_5678';
								out.print("alert('투표실패!'); location.href='" + context + "';");
								out.print("</script>");
								out.flush();								
							}
							break;
			case "/result" : site = vd.result(request, response); break;
			case "/rank" : site = vd.Rank(request, response); break;
		}
		
		getServletContext().getRequestDispatcher("/" + site).forward(request, response);
		
	}
}
