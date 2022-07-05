package com.test.toy.ajax;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax/ex02data2.do")
public class Ex02Data2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AjaxDAO dao = new AjaxDAO();
		
		int cnt = dao.getCount();
		
		System.out.println(cnt);
		
		req.setAttribute("cnt", cnt);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ajax/ex02data2.jsp");

		dispatcher.forward(req, resp);

	}
}

