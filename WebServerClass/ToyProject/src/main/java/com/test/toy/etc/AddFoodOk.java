package com.test.toy.etc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/etc/addfoodok.do")
public class AddFoodOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//AddFoodOk.java
		//1. 데이터 가져오기
		//2. DB 작업 > DAO 위임 > insert
		//3. 결과
		//4. 피드백
		
		
		//1.
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		String category = req.getParameter("category");
		String lat = req.getParameter("lat");
		String lng = req.getParameter("lng");
		String star = req.getParameter("star");
		
		//2.
		EtcDAO dao = new EtcDAO();
		
		FoodDTO dto = new FoodDTO();
		
		dto.setName(name);
		dto.setCategory(category);
		dto.setLat(lat);
		dto.setLng(lng);
		dto.setStar(star);
		
		//3.
		int result = dao.addFood(dto);
		
		//4.
		if (result == 1) {
			resp.sendRedirect("/toy/etc/food.do");
		} else {
			resp.sendRedirect("/toy/etc/addfood.do");
		}
		
	}

}



















