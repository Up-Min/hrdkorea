package Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.commons.beanutils.BeanUtils;

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

		if(command == "/") {
			command = "/list";
		}
		
		// 경로 라우팅.
		switch (command) {
		case "/list": site = getList(request); break;	
		case "/view": site = getView(request); break;
		case "/write": site = "write.jsp"; break; //글쓰기 화면
		case "/insert" : site = insertBoard(request); break; // insert 기능
								// 글쓰기화면에서 보내준 내용들이 들어가있음. 
		case "/edit" : site = getViewForEdit(request); break; // 수정하는 페이지를 보여준다.
		case "/update" : site = updateBoard(request); break;
		case "/delete" : site = deleteBoard(request); break;
		}
		// redirect, forward?
		
		/* redirect : url의 변화가 있다, 객체 재사용이 안된다.   
		 *			  DB의 변화가 생기는 요청에 사용 (글쓰기, 회원가입 등) insert, update, delete 조심.
		// forward : url의 변화가 없다, 객체 재사용이 가능하다. (request, response)
				    단순 조회 (리스트보기, 검색) 등에 사용.	select등.
		*/
		
		if(site.startsWith("redirect:/")) { // "" 안에 시작하는 문자열을 찾아준다.
			// 결국 리다이렉트를 해주겠다는 말임.
			String rview = site.substring("redirect:/".length());
			//문자열을 자를건데, redirect의 문자 길이만큼 짤라주고, 그 뒤에 오는 것들만 냄긴다.
			//
			System.out.println(rview);
			response.sendRedirect(rview);
		}else{
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
	
	public String getView(HttpServletRequest request) {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		// index에서 받아온 board_no를 사용한다.
		
		try {
			dao.updateViews(board_no); //DAO에 있는 조회수 증가 메소드 사용
			
			Board b = dao.getView(board_no);
			// dao부터 받아온 board객체 
			request.setAttribute("board", b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "게시글을 정상적으로 가져오지 못했습니다!");
			// 'error' 이라는 키로 사용자에게 해당 문자열을 보내줄 것임.
			// 실제로는 어떤 에러인지을 알아야 한다.
		} 
		return "view.jsp";
	} 
	
	public String getViewForEdit(HttpServletRequest request) {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		try {
			Board b = dao.getViewForEdit(board_no); 
			request.setAttribute("board", b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "게시글을 정상적으로 가져오지 못했습니다!");
			// 'error' 이라는 키로 사용자에게 해당 문자열을 보내줄 것임.
			// 실제로는 어떤 에러인지을 알아야 한다.
		} 
		return "edit.jsp";
	}
	
	public String insertBoard(HttpServletRequest request) {
		Board b = new Board();
		try {
			BeanUtils.populate(b, request.getParameterMap()); //일일이 request 없이 beanUtils 사용
			dao.insertBoard(b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글 추가 과정에서 문제 발생");
			try {
				// get방식으로 넘겨줄 때, 한글깨짐을 방지하기 위해 utf-8로 인코딩을 해준다.
				String encodeName = URLEncoder.encode("게시물이 정상적으로 등록되지 않았습니다!", "UTF-8");
				return "redirect:/list?error= " + encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
//			request.setAttribute("error", "게시글을 정상적으로 등록하지 못했습니다!");
			//리다이렉트로 보낼거임.
//			return getList(request); // 추가과정에서 문제 발생시 에러가 리퀘스트에 들어가고, list를 해준다.
			
			// return redirect가 아니고 return getList인 이유?
			// request에 에러 문자가 들어가있기 때문에, 어차피 request로 보낼거니까?
			
		}
		return "redirect:/list";
		/* 리다이렉트에 url이 들어오면 77번줄의 startswith가 돌아감. list로 요청이 된다.
		 * 포워드와의 차이점은, request 객체에 있는 데이터들을 전부 사라지게 하고 넘어간다.
		 * 만약 redirect를 안하면 주소가 forward로 그대로 가게 된다. 
		 * return "list"; 로 할 경우 작성 이후 페이지가 /insert로 가게 되고, insert가 두번 돌아간다.
		 * 즉 insert할 때 데이터를 그대로들고 가므로, 게시글이 두번 작성되는 형태가 된다.
		 * 객체가 리셋이 되지 않는 forward의 특성 때문이다. 
		*/
	}

	public String updateBoard(HttpServletRequest request) {
		Board b = new Board();
		try {
			BeanUtils.populate(b, request.getParameterMap());
			dao.updateBoard(b); // beanUtils 로 받은 값을 넘겨준다.
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글 수정 과정에서 문제 발생");
			try {
				// get방식으로 넘겨줄 때, 한글깨짐을 방지하기 위해 utf-8로 인코딩을 해준다.
				String encodeName = URLEncoder.encode("게시물이 정상적으로 수정되지 않았습니다!", "UTF-8");
				return "redirect:/view?board_no = " + b.getBoard_no() + "&error = " + encodeName;
				// 에러가 나면 일단 뷰 페이지로 가고, 거기서 에러 파라미터를 넣어준다.
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		} 
		return "redirect:/view?board_no=" + b.getBoard_no();
		// 수정한 것을 바로 보여주기 위해 view 페이지로 가게 한다.
	}

	public String deleteBoard(HttpServletRequest request) {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		try {
			dao.deleteBoard(board_no);
		} catch(Exception e){
			e.printStackTrace();
			ctx.log("게시글 삭제 과정에서 문제 발생");
			try {
				// get방식으로 넘겨줄 때, 한글깨짐을 방지하기 위해 utf-8로 인코딩을 해준다.
				String encodeName = URLEncoder.encode("게시물이 정상적으로 삭제되지 않았습니다!", "UTF-8");
				return "redirect:/list?error = " + encodeName;
				// 에러가 나면 일단 뷰 페이지로 가고, 거기서 에러 파라미터를 넣어준다.
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/list";
	}
	
}
