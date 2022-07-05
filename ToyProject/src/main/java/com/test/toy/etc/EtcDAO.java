package com.test.toy.etc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.toy.DBUtil;

public class EtcDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public EtcDAO() {
		conn = DBUtil.open();
	}

	public ArrayList<CategoryDTO> listCategory() {
		
		try {
			
			String sql = "select seq, name from tblCategory order by name asc";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<CategoryDTO> list = new ArrayList<CategoryDTO>();
			
			while (rs.next()) {
				
				CategoryDTO dto = new CategoryDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("EtcDAO.listCategory");
			e.printStackTrace();
		}
		
		return null;
	}

	public int addFood(FoodDTO dto) {
		
		try {
			
			String sql = "insert into tblFood (seq, name, category, lat, lng, star) values (seqFood.nextVal, ?, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getCategory());
			pstat.setString(3, dto.getLat());
			pstat.setString(4, dto.getLng());
			pstat.setString(5, dto.getStar());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("EtcDAO.addFood");
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<FoodDTO> listFood() {
		
		try {
			
			String sql = "select tblFood.*, (select marker from tblCategory where seq = tblFood.category) as marker, (select icon from tblCategory where seq = tblFood.category) as icon from tblFood order by name asc";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<FoodDTO> list = new ArrayList<FoodDTO>();
			
			while (rs.next()) {
				
				FoodDTO dto = new FoodDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setCategory(rs.getString("category"));
				dto.setLat(rs.getString("lat"));
				dto.setLng(rs.getString("lng"));
				dto.setStar(rs.getString("star"));
				
				dto.setMarker(rs.getString("marker"));
				dto.setIcon(rs.getString("icon"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("EtcDAO.listFood");
			e.printStackTrace();
		}
		
		return null;
	}
	
}



















