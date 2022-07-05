package com.test.toy.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.toy.DBUtil;

public class MemberDao {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public MemberDao() {
		conn = DBUtil.open();
	}

	//RegisterOK > dto > insert
	public int add(MemberDto dto) {
		
		try {
			
			String sql = "insert into tblUser(id, pw, name, lv, pic, regdate)"
					+ "    values(?, ?, ?, '1', ?, default)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getPic());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDao.add");
			e.printStackTrace();
		}
		
		return 0;
	}

	public MemberDto login(MemberDto dto) {
		
		try {
			
			String sql = "select * from tblUser where id=? and pw=? and active='y'";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());

			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				//로그인 성공
				dto.setName(rs.getString("name"));
				dto.setLv(rs.getString("lv"));
				dto.setPic(rs.getString("pic"));
				dto.setRegdate(rs.getString("regdate"));
				
				
				
				return dto;
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("MemberDao.login");
			e.printStackTrace();
		}
		
		
		return null;
	}

	public MemberDto get(String id) {
		
try {
			
			String sql = "select * from tblUser where id=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, id);
			

			rs = pstat.executeQuery();
			
			MemberDto dto = new MemberDto();
			
			if(rs.next()) {
				
				//로그인 성공
				dto.setId(id);
				dto.setName(rs.getString("name"));
				dto.setLv(rs.getString("lv"));
				dto.setPic(rs.getString("pic"));
				dto.setRegdate(rs.getString("regdate"));
				
				
				
				return dto;
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("MemberDao.login");
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	//Unregister > id > 회원 탈퇴
	public int unregister(String id) {
		
		try {
			
			String sql ="update tblUser set active='n', pw='not used', name='not used', lv=0, pic='not used', regdate = sysdate where id=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, id);
			
			return pstat.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println("MemberDao.unregister");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
}
