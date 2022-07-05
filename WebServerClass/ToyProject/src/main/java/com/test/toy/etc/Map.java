package com.test.toy.etc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract


@WebServlet("/etc/map.do")
public class Map extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//Map.java
		// - 서블릿 1개 + JSP N개

		//etc/map.do?no=1
		String no = req.getParameter("no");
		
		no = "1";
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/etc/map0" + no + ".jsp");

		dispatcher.forward(req, resp);

	}
}


