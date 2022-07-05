package com.test.toy.ajax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.toy.DBUtil;

public class AjaxDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public AjaxDAO() {
		conn = DBUtil.open();
	}

	public ArrayList<ResearchDTO> listResearch() {

		try {
			
			String sql = "select * from tblResearch where seq = 1";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<ResearchDTO> list = new ArrayList<ResearchDTO>();
			
			while(rs.next()) {
				
				ResearchDTO dto = new ResearchDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setQuestion(rs.getString("question"));
				dto.setItem1(rs.getString("item1"));
				dto.setItem2(rs.getString("item2"));
				dto.setItem3(rs.getString("item3"));
				dto.setItem4(rs.getString("item4"));
				dto.setCnt1(rs.getString("cnt1"));
				dto.setCnt2(rs.getString("cnt2"));
				dto.setCnt3(rs.getString("cnt3"));
				dto.setCnt4(rs.getString("cnt4"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AjaxDAO.listResearch");
			e.printStackTrace();
		}
		
		return null;
	}



	public int getCount() {
		
		
		try {
			
			String sql = "select count(*) as cnt from tblBoard";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println("AjaxDAO.getCount");
			e.printStackTrace();
		}
		
		return 0;
	}
}


















