package com.el.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.el.score.Score;

@WebServlet("/controller.do")
public class MyController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		if(command.equals("elpage")) {
			response.sendRedirect("basic.jsp");
		}else if(command.equals("eltest")) {
			Score sc = new Score("주현록",100,90,55);
			
			request.setAttribute("score", sc);
			RequestDispatcher dis = request.getRequestDispatcher("eltest.jsp");
			dis.forward(request, response);
		}else if(command.equals("jstlpage")) {
			List<Score> res = new ArrayList<Score>();
			for(int i=1; i<6; i++) {
				Score sc = new Score("이름"+i,70+i,80+i,90+i);
				res.add(sc);
			}
			request.setAttribute("list", res);
			
			RequestDispatcher dis = request.getRequestDispatcher("jstlpage.jsp");
			dis.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
