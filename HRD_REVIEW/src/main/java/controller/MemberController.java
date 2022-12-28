package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

/**
 * Servlet implementation class MemberController
 */

// 경로맵핑 새로운거 배움 -> 디폴트 서블릿 맵핑
// 웹 서블릿에 이름을 지정하지 않고, 어떤 url로 가도 일단 이쪽으로 오게 하는 것.
// 대신 web.xml에 대한 추가 설정이 필요하다. 
// @WebServlet("/") 
// @WebServlet("/MemberController") 뭘 클릭해도 이ㄷ쪽으로 오게 해서, 여기서 다른 곳으로 갈 수 있게 지정해주는것.

//예를들어,nav 메뉴에서 회원등록을 눌렀을 때, 일단 href가 add이기 때문에 일단 여기로 온다.
//그리고 do pro가 작동하게 된다.


@WebServlet("/")
// 디폴트 서블릿 맵핑. 웹 서블릿에 이름을 지정하지 않고, 어떤 url에서도 일단 컨트롤러로 오게 하는 장치가 된다.
// 컨트롤러에서 모든 페이지를 처리해주기 위해 서블릿으로 가져온다.

public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberController() {
        super();
    }

// get, post 방식 상관없이 dopro에서 작동하도록 처리함.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		dopro(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		dopro(request, response);
	}
	
	
	protected void dopro (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//path를 가져온다.
		
		String context = request.getContextPath(); 
		// 톰캣에서 생성하는 contextPath , 얘를 가져온다.
		String command = request.getServletPath(); 
		// 경로의 맨 끝, 파일명을 가져온다. URL에 마지막 '/'이후 오는 단어를 command에 저장시켜준다.
		
		String site = null;
		// url뒤에 붙일 단어에 대해 선언을 해준다. 이 site는 향후 MemberController/' ' <- 여기에 오는 단어에 해당된다.
		
		System.out.println(context + "," + command);
		
		
		MemberDAO member = new MemberDAO();
		// SQL서버와 연동하는 DAO역할을 하는 MemberDAO를 선언한다. 
		
		
		switch (command) { // 경로의 맨 끝, 즉 어떤 값을 받는지에 따라 site를 재지정 해줄 것이다.
			case "/home" : site = "index.jsp"; break; //홈페이지는 index.jsp로 지정한다.
			case "/insert" : site = member.insert(request, response); break;
			case "/list" : site = member.selectAll(request, response); break;
			case "/add" : site = member.nextCustno(request, response); break; // site에 add.jsp가 저장된다.
			case "/modify" : site = member.modify(request, response); break;
			case "/update" : int result1 = member.update(request, response);
							response.setContentType("text/html; charset=UTF-8");
							PrintWriter out = response.getWriter();
							if(result1 == 1) { //업데이트 성공
								out.print("<script>");					// location.href='/HRD_1234';
								out.print("alert('회원수정이 완료 되었습니다!'); location.href='" + context + "';");
								out.print("</script>");
								out.flush();
							}else {
								out.print("<script>");					
								out.print("alert('수정실패'); location.href='" + context + "';");
								out.print("</script>");
								out.flush();
							}
							break;
		}
		// 받은 site를 이용하여 포워딩 (페이지 이동)
		getServletContext().getRequestDispatcher("/"+site).forward(request, response); 
	}
	
	
	

}
