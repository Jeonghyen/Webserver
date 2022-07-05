package com.test.toy.etc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/etc/addfood.do")
public class AddFood extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. DB > select
		//2. 결과
		//3. JSP호출
		
		//1.
		EtcDAO dao = new EtcDAO();
		
		ArrayList<CategoryDTO> list = dao.listCategory();
		
		
		//3. 
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/etc/addfood.jsp");

		dispatcher.forward(req, resp);

	}
}

