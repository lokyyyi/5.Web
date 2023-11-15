package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MyMVCBiz;
import com.mvc.biz.MyMVCBizImpl;
import com.mvc.dto.MyMVCDto;

@WebServlet("/MyMVCServlet")
public class MyMVCServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[command : " + command + "]");
		
		MyMVCBiz biz = new MyMVCBizImpl();
		
		if(command.equals("list")) {
			//데이터 준비
			List<MyMVCDto> list = biz.selectAll();
			request.setAttribute("list", list);
			
			RequestDispatcher dis = request.getRequestDispatcher("boardlist.jsp");
			dis.forward(request, response);
			
		}else if(command.equals("writeform")) {
			response.sendRedirect("boardwrite.jsp");
			
		}else if(command.equals("boardwrite")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MyMVCDto dto = new MyMVCDto(0, writer, title, content, null);
			
			boolean res = biz.insert(dto);
			
			PrintWriter out = response.getWriter();
			String str = "";
			if(res) {
				str = "<script type='text/javascript'>"+
					  "alert('글 작성 성공');"+
					  "location.href='controller.do?command=list';"+
					  "</script>";
				out.print(str);
			}else {
				str = "<script type='text/javascript'>"+
						  "alert('글 작성 실패');"+
						  "location.href='controller.do?command=writeform';"+
						  "</script>";
					out.print(str);
			}
		}else if(command.equals("detail")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
		
			MyMVCDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			
			RequestDispatcher dis = request.getRequestDispatcher("boarddetail.jsp");
			dis.forward(request, response);
		}else if(command.equals("updateform")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MyMVCDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			RequestDispatcher dis = request.getRequestDispatcher("boardupdate.jsp");
			dis.forward(request, response);
			
		}else if(command.equals("boardupdate")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyMVCDto dto = new MyMVCDto(seq, null, title, content, null);
			
			boolean res = biz.update(dto);
			
			PrintWriter out = response.getWriter();
			String str = "";
			if(res) {
				str = "<script type='text/javascript'>"+
						  "alert('글 수정 성공');"+
						  "location.href='controller.do?command=detail&seq="+seq+"';"+
						  "</script>";
					out.print(str);
			}else {
				str = "<script type='text/javascript'>"+
						  "alert('글 수정 실패');"+
						  "location.href='controller.do?command=updateform&seq="+seq+"';"+
						  "</script>";
					out.print(str);
			}
		}else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			boolean res = biz.delete(seq);
			
			if(res) {
				request.setAttribute("msg", "글 삭제 성공");
				request.setAttribute("url", "controller.do?command=list");
			}else {
				request.setAttribute("msg", "글 삭제 실패");
				request.setAttribute("url", "controller.do?command=detail&seq="+seq);
			}
			RequestDispatcher dis = request.getRequestDispatcher("res.jsp");
			dis.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
