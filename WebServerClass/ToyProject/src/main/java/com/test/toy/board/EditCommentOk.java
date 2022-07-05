package com.test.toy.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/editcommentok.do")
public class EditCommentOk extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//EditCommentOk.java
		
		
		//1.데이터 수신
		
		req.setCharacterEncoding("UTF-8");
		
		String content = req.getParameter("content");
		String seq = req.getParameter("seq");
		
		String pseq = req.getParameter("pseq");
		String isSearch = req.getParameter("isSearch");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		
		//2. DAO
		
		CommentDTO dto = new CommentDTO();
		
		dto.setSeq(seq);
		dto.setContent(content);
		
		BoardDAO dao = new BoardDAO();
		
		int result = dao.editComment(dto);
		
		if(result == 1) {
			//댓글 쓴 글로 돌아가기
			resp.sendRedirect(String.format("/toy/board/view.do?seq=%s&isSearch=%s&column=%s&word=%s",pseq, isSearch, column, URLEncoder.encode(word, "UTF-8")));
		}else {
			//실패
			PrintWriter writer = resp.getWriter();
			
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<script>");
			writer.println("alert('failed');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
			
			writer.close();
		}
		
		
		
//		
//		<input type="hidden" name="pseq" value="${dto.seq }"  />
//		<input type="hidden" name="isSearch" value="${isSearch }"  />
//		<input type="hidden" name="column" value="${column }"  />
//		<input type="hidden" name="word" value="${word }"  />
//		<input type="hidden" name="seq"  />
		
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/name.jsp");
//
//		dispatcher.forward(req, resp);

	}
}

