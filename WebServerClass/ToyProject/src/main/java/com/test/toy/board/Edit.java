package com.test.toy.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//Edit.java
		//1. 데이터 가져오기
		//2. DB > DAO> select
		//3. 결과
		//4. JSP + 결과
		
		
		//1.
		String seq = req.getParameter("seq");
		String isSearch = req.getParameter("isSearch");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		
		//2.
		BoardDAO dao = new BoardDAO();
		
		BoardDTO dto = dao.get(seq);
		
		//3 + 4
		req.setAttribute("dto", dto);
		req.setAttribute("isSearch", isSearch);
		req.setAttribute("column", column);
		req.setAttribute("word", word);
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/edit.jsp");

		dispatcher.forward(req, resp);

	}
}
