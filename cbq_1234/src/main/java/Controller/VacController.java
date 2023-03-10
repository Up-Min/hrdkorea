package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VacDAO;


@WebServlet("/")
public class VacController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public VacController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UFT-8");
		doIt(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UFT-8");
		doIt(request, response);
	}
	
	protected void doIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;
		
		VacDAO vc = new VacDAO();
		
		switch(command) {
		case "/home" : site="index.jsp"; break;
		case "/reserv" : site="reserv.jsp"; break;
		case "/case" : site="reserv.jsp"; break;
		case "/calc" : site="reserv.jsp"; break;		
		case "/insert" : int result = vc.insert(request,response); 
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
				if(result == 1) {
					out.print("<script>");
					out.print("alert('접종예약정보가 등록 되었습니다!')");
					out.print("<script>");
				}else {
					out.print("<script>");
					out.print("alert('등록 실패!')");
					out.print("<script>");					
				}break;
		
		}
		getServletContext().getRequestDispatcher("/"+site).forward(request, response);
	}

}
