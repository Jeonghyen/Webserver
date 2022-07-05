package com.test.toy.etc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.toy.board.BoardDAO;

@WebServlet("/etc/chart.do")
public class Chart extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Chart.java
		
		//1. DB 작업 > DAO위임 > select x 3
		//2. 결과
		//3. JSP호출 + 결과 전달
		
		//1.
		BoardDAO dao = new BoardDAO();
		
		ArrayList<ChartDTO1> list1 = dao.chart1();
		ArrayList<ChartDTO2> list2 = dao.chart2();
		ArrayList<ChartDTO3> list3 = dao.chart3();
		
		req.setAttribute("list1", list1);
		req.setAttribute("list2", list2);
		req.setAttribute("list3", list3);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/etc/chart.jsp");

		dispatcher.forward(req, resp);

	}
}

