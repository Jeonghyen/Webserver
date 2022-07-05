package com.test.toy.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/board/editok.do")
public class EditOk extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//Editok.java
		
		//1. 인코딩
		//2. 데이터 가져오기(subject, content, seq)
		//3. DB > DAO > update
		//4. 결과
		//5. JSP 호출
		
		HttpSession session = req.getSession();
		
	
		
		//1.
		req.setCharacterEncoding("UTF-8");
		
		
		//1.5 새로운 파일 선택
		String path = req.getRealPath("/files");
		//C:\class\server\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ToyProject\files
		int size = 1024 * 1024 * 100;
		
		MultipartRequest multi = null;
		
		try {
			
			multi = new MultipartRequest(
					
						req,
						path,
						size,
						"UTF-8",
						new DefaultFileRenamePolicy()
					
					);
			
			
		} catch (Exception e) {
			System.out.println("AddOk.doPost");
			e.printStackTrace();
		}
		
		System.out.println(path);
		
		
		
		//2.
		String seq = multi.getParameter("seq");
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		
		String isSearch = multi.getParameter("isSearch");
		String column = multi.getParameter("column");
		String word = multi.getParameter("word");
		
		//3.
		BoardDTO dto = new BoardDTO();
		
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setSeq(seq);
		
		BoardDAO dao = new BoardDAO();
		
		
		
		
		//3.5 첨부파일 처리
		//3.5.1 기존 파일 O > 새로운파일 O
		//	- 기존 파일 지우기
		
		//새파일
		String filename = multi.getFilesystemName("attach");
		String orgfilename = multi.getOriginalFileName("attach");
		
		//System.out.println("filename: "+ (filename == null));  //true
		
		//기존파일
		BoardDTO tempdto = dao.get(seq);
		
		//기존 파일이 있는 경우 새로운 파일로 교체
		if(tempdto.getFilename() != null && filename != null) {  //기존 파일이 있고 전달받은 파일이 있을 때
			//기존파일 삭제
			File file = new File(path+ "\\" + tempdto.getFilename());
			file.delete();
			
			dto.setFilename(filename);
			dto.setOrgfilename(orgfilename);
		}else if(filename == null && multi.getParameter("delfile").equals("y")) {
			//기존 파일만 삭제할 경우
			File file = new File(path+ "\\" + tempdto.getFilename());
			file.delete();
			
		}else if(filename == null){  //전달받은 파일이 없을 때
			//기존 파일의 유무와 관계없이 새로운 파일을 추가 안했을 경우
			dto.setFilename(tempdto.getFilename());
			dto.setOrgfilename(tempdto.getOrgfilename());
		} else if(tempdto.getFilename() == null && filename != null) {
			//기존파일은 없고 새로운 파일 등록할 때
			dto.setFilename(filename);
			dto.setOrgfilename(orgfilename);
		} 
		
		
		
		
		//외부URL 접근 막기
		
		int temp = 0;
		
		if(session.getAttribute("auth") == null) {
			temp = 1;
		}else if(session.getAttribute("auth") != null) {
			if(session.getAttribute("auth").equals(dao.get(seq).getId())) {
				temp = 2; //글쓴 본인
			}else {
				
				if(session.getAttribute("auth").toString().equals("admin")) {
					temp = 3; //관리자
				}else {
					temp = 4; //타인
				}
				
			}
		}
		
		int result = 0;
		
		if(temp == 2 || temp == 3 ) {
			result = dao.edit(dto);			
		}
		
		
		//4.
		req.setAttribute("result", result);
		req.setAttribute("seq", seq);
		
		req.setAttribute("isSearch", isSearch);
		req.setAttribute("column", column);
		req.setAttribute("word", word);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/editok.jsp");

		dispatcher.forward(req, resp);

	}
}

