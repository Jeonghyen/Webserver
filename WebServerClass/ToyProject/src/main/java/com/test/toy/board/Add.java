package com.test.toy.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/add.do")
public class Add extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//새글 쓰기 > add.do > null
		// 답변 쓰기 > add.do?reply = 1 > 1
		//  add.do?reply = > ""
		
		//add.do > null
		//add.do?reply =  > ""
		//add.do?reply = 1 > 1
		
		
		String reply = req.getParameter("reply");
		String thread = req.getParameter("thread");
		String depth = req.getParameter("depth");
		
		//해시태그 목록 가져오기(자동완성)
		BoardDAO dao = new BoardDAO();
		
		ArrayList<String> taglist = dao.taglist();
		
		System.out.println("taglist: " + taglist);
		
		req.setAttribute("reply", reply);
		req.setAttribute("thread", thread);
		req.setAttribute("depth", depth);
		
		req.setAttribute("taglist", taglist);
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/add.jsp");

		dispatcher.forward(req, resp);

	}
}

