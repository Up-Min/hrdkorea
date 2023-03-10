package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.Board;

@WebServlet("/")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO dao;
	private ServletContext ctx;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 맨 처음 서블릿 BoardController 선언할 때 톰캣이 생성해주는데, 그 이후 init를 호출해준다.
		// 딱 한번만 선언되기 때문에 객체를 공유할 수 있다.
		dao = new BoardDAO();
		ctx = getServletContext(); // 웹 프로젝트당 생성되며, 어플리케이션의 자원관리를 수행하는 servletContext를 가져와준다.
		//

	}
	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// url경로의 길을 찾아주는 라우팅을 이용할거임.
		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;

		// 경로 라우팅.
		switch (command) {
		case "/list": site = getList(request); break;			
		}
		// redirect, forward?
		// redirect : 
		// forward : 
		if(site.startsWith("redirect:/")) {
			
		}else {
			ctx.getRequestDispatcher("/"+site).forward(request, response);
		}
		
	}
	
	public String getList(HttpServletRequest request) {
		List <Board> list;
		
		try {
			list = dao.getList(); // dao.getList 에서 throw한 exception이 여기까지 넘어옴.
			// 결국 누군가는 exception을 처리해줘야함.
			
			request.setAttribute("boardList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시판 목록 생성 과정에서 문제 발생");
			// 로그, 기록. 에러가 발생하면 찍어낼 수 있도록 이야기 함.
			// 얘좀 중요하다.
			request.setAttribute("error", "게시판 목록이 정상적으로 처리되지 않았습니다!");
			// 'error' 이라는 키로 사용자에게 해당 문자열을 보내줄 것임.
		} 
		return "index.jsp";
	}
}
