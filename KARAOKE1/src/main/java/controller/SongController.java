package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import DAO.*;
import DTO.*;


@WebServlet("/")
public class SongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SongDAO song;
    private ServletContext ctx;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		song = new SongDAO();
		ctx = getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String view = null;
		System.out.println(context + ", " + command);
		

		switch(command) {
		case "/home":
			view = "index.jsp";
			break;
		case "/search":
			view = "search.jsp";
			break;
		case "/searchsong":
			view = searchSong(request);
			break;
			
		case "/list":
			view = allList(request, response);
			break;
		
		case "/add":
			view = "add.jsp";
			break;
		
		case "/insert":
			view = insertSong(request,response);
			break;
			
		case "/select":
			view = select(request,response);
			break;
			
		case "/reply":
			view = reply(request);
			break;
			
		case "/delete":
			view = deleteReply(request);
			break;
			
		case "/edit":
			view = songForEdit(request);
			break;
			
		case "/update":
			view = updateSong(request);
			break;
		
		case "/deleteSong":
			view = deleteSong(request);
			break;
			
}
		
		if(view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			System.out.println(rview);
			response.sendRedirect(rview);
		} else {
		ctx.getRequestDispatcher("/"+ view).forward(request, response);
		
		}
	}
	
	public String searchSong(HttpServletRequest request) {
		List<Result> list;
		try {
			list = song.getList(request);
			request.setAttribute("songList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("?????? ?????????????????? ?????? ??????");
			request.setAttribute("erorr", "?????? ????????? ??????????????? ?????? ???????????????.");
		}
		return "searchResult.jsp";
	}
	
	public String allList(HttpServletRequest request, HttpServletResponse response) {
		List<Result> alllist;
		try {
			alllist = song.getAllList(request,response);
			request.setAttribute("alllist", alllist);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("?????? ????????? ?????? ???????????? ?????? ??????");
			request.setAttribute("error", "?????? ???????????? ??????????????? ???????????? ???????????????.");
		}
		return "songlist.jsp";
		
	
	}
	
	public String insertSong(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("CON insertsong");
		Result s = new Result();
		int result = 0;
		try {
			BeanUtils.populate(s, request.getParameterMap());
			result = song.insertSong(s);
			System.out.println("DAO insertsong ?????? : "+result);
			response.setContentType("text/html; charset=UTF-8");
			if(result == 0 ) {
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('????????? ??????????????? ???????????? ???????????????! ??????????????? ??????????????????.'); location.href='list'");
				out.print("</script>");
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("?????? ?????? ???????????? ????????? ?????????????????????.");
			try {
				 String encodeName = URLEncoder.encode("????????? ??????????????? ???????????? ???????????????.", "UTF-8");
				 return "redirect:/list?error="+encodeName;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/list";
	}
	
	public String select(HttpServletRequest request, HttpServletResponse response) {
		int songno = Integer.parseInt(request.getParameter("songno"));
		try {
			Result s = song.select(songno);
			String result = song.select_Error(songno);
			System.out.println(result);
			request.setAttribute("song", s);
			request.setAttribute("error", result);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result.equals("no")) {
				out.print("<script>");   				
				out.print("alert('?????? ???????????????!'); location.href='index.jsp';");
				out.print("</script>");
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("??????????????? ???????????? ???????????? ?????? ??????");
			request.setAttribute("error", "????????? ??????????????? ???????????? ???????????????.");
		}
		List<Reply> replylist;
		try {
			replylist = song.getreplyList(request);
			request.setAttribute("replylist", replylist);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("?????? ???????????? ?????? ??????");
			request.setAttribute("error", "?????? ???????????? ??????????????? ???????????? ???????????????.");
		}
		
		return "select.jsp";
	}
	
	public String reply(HttpServletRequest request) {
		Reply r = new Reply();
		int songno = 0;
		try {
			BeanUtils.populate(r, request.getParameterMap());
			songno = song.reply(r);
		} catch (Exception e) {
			e.printStackTrace();

		}
		List<Reply> replylist;
		try {
			replylist = song.getreplyList(request);
			request.setAttribute("replylist", replylist);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("?????? ???????????? ?????? ??????");
			request.setAttribute("error", "?????? ???????????? ??????????????? ???????????? ???????????????.");
		}
		return "redirect:/select?songno="+songno;
	}

	public String deleteReply(HttpServletRequest request) {
		int commentno = Integer.parseInt(request.getParameter("commentno"));
		int songno = Integer.parseInt(request.getParameter("songno"));
		
		try {
			song.deleteReply(commentno);
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		return "redirect:/select?songno="+songno;
	}
	
	public String songForEdit(HttpServletRequest request) {
		int songno = Integer.parseInt(request.getParameter("songno"));
		try {
			Result s = song.getSongForEdit(songno);
			request.setAttribute("song", s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "edit.jsp";
	}
	
	public String updateSong(HttpServletRequest request) {
		Result s = new Result();
		try {
			BeanUtils.populate(s, request.getParameterMap());
			song.updateSong(s);
			System.out.println("song.updateSong(??????)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/list";
	}
	
	public String deleteSong(HttpServletRequest request) {
		int songno = Integer.parseInt(request.getParameter("songno"));
		try {
			song.deleteSong(songno);
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		return "redirect:/list";
	}
}


