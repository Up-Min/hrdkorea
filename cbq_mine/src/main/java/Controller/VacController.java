package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.SwitchExpression;

import DAO.VacDAO;


@WebServlet("/")
public class VacController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VacController() {
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
		String link = null;
		
		VacDAO vd = new VacDAO();
		
		switch (command) {
		case "/reserv" : link = "reserv.jsp"; break;
		case "/insert" : int result = vd.insert(request,response); 
						 response.setContentType("text/html; charset=UTF-8");
						 PrintWriter out = response.getWriter();
						 if (result == 1) {
							out.print("<script>");
							out.print("alert ('접종예약정보가 등록 되었습니다!'); location.href = '" + context + "';");
							out.print("</script>");
							out.flush();
						 }else {
							 out.print("<script>");
							 out.print("alert ('접종예약정보 등록 실패!'); location.href = '" + context + "';");
							 out.print("</script>");							 
							 out.flush();
						 }break;
		case "/search" : link = "search.jsp";  break;
		case "/find" : link = vd.find(request,response); break;
		case "/calc" : link = vd.calc(request,response);  break;
		case "/home" : link = "index.jsp"; break;
		}
		
		getServletContext().getRequestDispatcher("/"+link).forward(request, response);
	}

}
