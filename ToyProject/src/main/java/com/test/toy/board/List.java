package com.test.toy.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/list.do")
public class List extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doTemp(req, resp);
	}
	
	
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		doTemp(req, resp);
//	}
	
	

	private void doTemp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//List.java
		//1. DB 작업 > DAO 위임 > select
		//2. 결과
		//3. JSP 호출하기 + 결과 전달하기
		
		
		
		
		//그냥 목록? vs 검색 결과?
		//- list.do
		//- list.do + (column + word)
		
		req.setCharacterEncoding("UTF-8");
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String isSearch = "n";
		
//		if ((column == null || word == null)
//				|| (column == "" || word == "")) {
//			isSearch = "n";
//		} else {
//			isSearch = "y";
//		}
		
		if((column != null && word != null)
				&& (column != "" && word != "")
				) {
			
			//System.out.println("word");
			isSearch = "y";
			
		} else {
			isSearch = "n";
		}
		
		
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		map.put("column", column);
		map.put("word", word);
		map.put("isSearch", isSearch);
		
		System.out.println(column);
		System.out.println(map.get("column"));
		
		//해시태그 클릭
		String tag = req.getParameter("tag");
		map.put("tag", tag);
		

		//페이징
		int nowPage = 0;  //현재 페이지 번호
		int begin = 0;   
		int end = 0;
		int pageSize = 10;   //한 페이지 당 출력 게시물 수
		
		int totalPage = 0;
		int totalCount = 0;
		
		
		
		//list.do > list.do?page=1
		//list.do?page=1
		
		
		String page = req.getParameter("page");
		
		//넘어온 페이지가 없으면 1페이지로 이동
		if(page == null || page == "") nowPage = 1;
		else nowPage = Integer.parseInt(page);
		
		//page=1 -> 1 and 10 
		//page=2 -> 11 and 20 
		begin = ((nowPage -1) * pageSize) + 1;
		end = begin + pageSize -1;
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
				
		
		HttpSession session = req.getSession();
		
		//1. + 2.
		BoardDAO dao = new BoardDAO();
		
		ArrayList<BoardDTO> list = dao.list(map);
		
		//2.5
		//- 출력 데이터의 가공 업무
		
		Calendar now = Calendar.getInstance();
		String strNow = String.format("%tF", now);// "2022-06-29"
		
		for (BoardDTO dto : list) {
			
			//시분초 자르기
			if (dto.getRegdate().startsWith(strNow)) {
				//오늘
				dto.setRegdate(dto.getRegdate().substring(11));
			} else {
				//어제 이전
				dto.setRegdate(dto.getRegdate().substring(0, 10));
			}
			
			
			//제목이 길면 자르기
			if (dto.getSubject().length() > 25) {
				dto.setSubject(dto.getSubject().substring(0, 25) + "..");
			}
			
			//태그 비활성화
			dto.setSubject(dto.getSubject().replace("<", "&lt;").replace(">", "&gt;"));
			
		}
		
		//2.6 총 페이지 수 구하기
		//- 총 게시물 수 > 267
		totalCount = dao.getTotalCount(map);
		//- 총 페이지 수 > 267 / 10 = 26.7 > 무조건 반올림
		totalPage = (int)Math.ceil((double)totalCount / pageSize);

		String pagebar = "";
		int blockSize = 10; //한번에 보여질 페이지 개수
		int n = 0;			//페이지 번호
		int loop = 0;		//루프
		
		pagebar = "";
		
		
		loop = 1;
		n = 1;
		
//		for(n=1; n<=totalPage; n++) {
//			//현재 페이지 표시 + 링크 제거
//			if(n == nowPage) {
//				pagebar += String.format("<a href='#!' style='color: cornflowerblue;'>%d</a>", n, n);
//				
//			}else {
//				pagebar += String.format("<a href='/toy/board/list.do?page=%d'>%d</a>", n, n);
//				
//			}
//			
//		}
		
		

		
		pagebar +="<ul class=\"pagination\">";
		
		
		loop = 1;
		n = ((nowPage-1)/blockSize)*blockSize + 1;
		System.out.println(n);
		//이전 10페이지
		
		//첫페이지 일 때
//		if(n ==1) {
//			pagebar += "";
//			
//		}else {
//			
//			pagebar += String.format("<a href='/toy/board/list.do?page=%d'>[이전%d페이지]</a>", n-1, blockSize);
//		}
		
		if(n ==1) {
			pagebar += String.format("<li class=\"page-item\">\r\n"
					+ "					      <a class=\"page-link\" href=\"#!\" aria-label=\"Previous\">\r\n"
					+ "					        <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "					      </a>\r\n"
					+ "					    </li>");
			
		}else {
			pagebar += String.format("<li class=\"page-item\">\r\n"
					+ "					      <a class=\"page-link\" href=\"/toy/board/list.do?page=%d\" aria-label=\"Previous\">\r\n"
					+ "					        <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "					      </a>\r\n"
					+ "					    </li>", n-1);
			
		}
		
		
//		while(!(loop > blockSize || n > totalPage)) {
//			
//			//현재 페이지 표시 + 링크 제거
//			if(n == nowPage) {
//				pagebar += String.format("<a href='#!' style='color: cornflowerblue;'>%d</a>", n, n);
//				
//			}else {
//				pagebar += String.format("<a href='/toy/board/list.do?page=%d'>%d</a>", n, n);
//				
//			}
//			
//			loop++;
//			n++;
//			
//		}
		while(!(loop > blockSize || n > totalPage)) {
			
			//현재 페이지 표시 + 링크 제거
			if(n == nowPage) {
				pagebar += String.format(" <li class=\"page-item active\"><a class=\"page-link\" href=\"#!\">%d</a></li>", n, n);
				
			}else {
				pagebar += String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/toy/board/list.do?page=%d\">%d</a></li>", n, n);
				
			}
			
			loop++;
			n++;
			
		}
		
		//다음 10페이지
		
//		if(n > totalPage) {
//			//클릭 못하게하기
//			//pagebar += String.format("<a href='#!'>[다음%d페이지]</a>",blockSize);
//			//없애기
//			pagebar += "";
//			
//		}else {
//			
//			pagebar += String.format("<a href='/toy/board/list.do?page=%d'>[다음%d페이지]</a>", n, blockSize);
//			
//		}
		
		if(n > totalPage) {
			//클릭 못하게하기
			//pagebar += String.format("<a href='#!'>[다음%d페이지]</a>",blockSize);
			//없애기
			
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "					      <a class=\"page-link\" href=\"#!\" aria-label=\"Next\">\r\n"
					+ "					        <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "					      </a>\r\n"
					+ "					    </li>\r\n"
					+ "					 ");
			
			
		}else {
			
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "					      <a class=\"page-link\" href=\"/toy/board/list.do?page=%d\" aria-label=\"Next\">\r\n"
					+ "					        <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "					      </a>\r\n"
					+ "					    </li>\r\n"
					+ "					 ", n);
			
		}
		
		pagebar += " </ul>";
		
		
		
		
		
		
		
		//2.7 새로고침 조회수 증가 방지
		session.setAttribute("read", "n");
		
		
		
		//3.
		req.setAttribute("list", list);
		
		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("pagebar", pagebar);
		
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);
	}

}



















