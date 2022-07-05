package com.test.toy.ajax;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax/ex02.do")
public class Ex02 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 데이터 가져오기
		//2. JSP에 전달
		
		String cnt = req.getParameter("cnt");
		
		req.setAttribute("cnt", cnt);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ajax/ex02.jsp");

		dispatcher.forward(req, resp);

	}
}

