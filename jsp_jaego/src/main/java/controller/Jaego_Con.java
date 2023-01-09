package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

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
		
		switch (command) {
		case "/list": site = list(request); break;
		case "/add" : site = add(request); break;
		case "/getedit" : site = getedit(request); break;
		case "/edit" : site = edit(request); break;
		case "/delete" : site = delete(request); break;
		}
		if(site.startsWith("redirect:/")) {
			String rview = site.substring("redirect:/".length());
			response.sendRedirect(rview);
		}else {
			ctx.getRequestDispatcher("/"+site).forward(request, response);			
		}
	}
	
	public String list (HttpServletRequest request) {
		System.out.println("Con main");
		ArrayList<Jaego_DTO> list = new ArrayList<>();
		
		list = dao.get_list(request);
		request.setAttribute("list", list);
		
		return "index.jsp"; 
	}
	
	public String add (HttpServletRequest request) {
		System.out.println("Con add");
		
		String p_name = request.getParameter("p_name");
		int remain = Integer.parseInt(request.getParameter("remain"));
		int result = 0;
		
		UUID code = UUID.randomUUID();
		String p_code = code.toString();
		
		request.setAttribute("p_code", p_code);
		request.setAttribute("p_name", p_name);
		request.setAttribute("remain", remain);
		
		result = dao.add(request, p_code, p_name, remain);
		System.out.println("DAO add 입력 결과" + result);
		
		return "redirect:/list";
	}

	public String getedit (HttpServletRequest request) {
		System.out.println("Con getedit");
		
		String p_code = request.getParameter("p_code");
		String p_name = request.getParameter("p_name");
		int remain = Integer.parseInt(request.getParameter("remain"));
		
		request.setAttribute("p_code", p_code);
		request.setAttribute("p_name", p_name);
		request.setAttribute("remain", remain);

		return "j_edit.jsp";
	}
	
	public String edit (HttpServletRequest request) {
		System.out.println("Con edit");
		int result = 0;
		
		String p_code = request.getParameter("p_code");
		String p_name = request.getParameter("p_name");
		int remain = Integer.parseInt(request.getParameter("remain"));
		
		
		request.setAttribute("p_code", p_code);
		request.setAttribute("p_name", p_name);
		request.setAttribute("remain", remain);
		
		
		result = dao.edit(request, p_code, p_name, remain);
		System.out.println("DAO edit update 결과 : " + result);
		
		return "redirect:/list";
	}
	
	public String delete (HttpServletRequest request) {
		System.out.println("Con delete");
		int result = 0;
		
		String p_code = request.getParameter("p_code");
		
		request.setAttribute("p_code", p_code);
		
		result = dao.delete(request, p_code);
		System.out.println("DAO delete 결과 : " + result);
		
		return "redirect:/list";
	}
	
}
