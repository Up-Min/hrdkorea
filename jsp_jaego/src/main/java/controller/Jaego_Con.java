package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Jaego_DAO;
import dto.Jaego_DTO;

@WebServlet("/")
public class Jaego_Con extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Jaego_DAO dao;
	private ServletContext ctx;
	
    public Jaego_Con() {super();}
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dao = new Jaego_DAO();
		ctx = getServletContext();
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
		

		System.out.println(command);
		switch (command) {
		case "/": site = main(request); break;
		
		}
		
	}
	
	public String main (HttpServletRequest request) {
		System.out.println("Con main");
		ArrayList<Jaego_DTO> list = new ArrayList<>();
		list = dao.get_list(request);
		request.setAttribute("list", list);
		return "index.jsp"; 
	}
	

}
