package com.test.toy.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/info.do")
public class Info extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Info.java
		//1. 아이디
		//2. DB > select
		//3. 결과
		//4. JSP
		
		HttpSession session = req.getSession();
		
		//1.
		String id = (String)session.getAttribute("auth");
		
		//2.
		MemberDao dao = new MemberDao();
		
		//3.
		MemberDto dto = dao.get(id);
		
		//4.
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/info.jsp");

		dispatcher.forward(req, resp);

	}
}

