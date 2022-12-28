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
		
		String context = request.getContextPath(); // 톰캣에서 생성하는 contextPath , 얘를 가져온다.
		String command = request.getServletPath(); // 경로의 맨 끝, 파일명을 가져온다. 
		String site = null;
		
		System.out.println(context + "," + command);
		
		MemberDAO member = new MemberDAO();
		
		switch (command) {
			case "/home" : site = "index.jsp"; break;
			case "/insert" : site = member.insert(request, response); break;
			case "/list" : site = member.selectAll(request, response); break;
			case "/add" : site = member.nextCustno(request, response); break; // site에 add.jsp가 저장된다.
			case "/modify" : site = member.modify(request, response); break;
			case "/result" : site = member.selectResult(request, response); break;
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
			case "/delete" : int result2 = member.delete(request, response); //DAO메소드에서 delete를 발동시킬거임.
							response.setContentType("text/html; charset=UTF-8");
							out = response.getWriter();
							if(result2 == 1) { 
								out.print("<script>");					// location.href='/HRD_1234';
								out.print("alert('삭제가 완료되었습니다!'); location.href='" + context + "';");
								out.print("</script>");
								out.flush();
							}else {
								out.print("<script>");					
								out.print("alert('회원삭제 실패'); location.href='" + context + "';");
								out.print("</script>");
								out.flush();
							}
							break;
		}
		// 받은 site를 이용하여 포워딩 (페이지 이동)
		getServletContext().getRequestDispatcher("/"+site).forward(request, response); 
	}
	
	
	

}
