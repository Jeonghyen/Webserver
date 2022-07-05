package com.test.toy.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

import com.test.toy.DBUtil;

public class Dummy {

	
	public static void main(String[] args) {
		
		//게시글 더미 생성
		String sql = "insert into tblBoard (seq, subject, content, id, regdate, readcount) values (seqBoard.nextVal, ?, ?, ?, default, default)";
		
		//시드 데이터
		String[] subject = {"오늘은","내 생일인데","케이크도","먹고","밥도","맛있는 거","먹고","정말","행복했어","내년","생일도","이렇게","행복하게","보내자"};
		
		String content = "내용입니다.";
		
		String[] id = {"hong", "admin", "hello"};
		
		
		Connection conn;
		PreparedStatement pstat;
		
		try {
			
			conn = DBUtil.open();
			
			pstat = conn.prepareStatement(sql);
			
			Random rnd = new Random();
			
			for ( int i=0; i<256; i++) {
				pstat.setString(1, subject[rnd.nextInt(subject.length)] + " "+subject[rnd.nextInt(subject.length)] + " "+subject[rnd.nextInt(subject.length)] + " " );
				
				pstat.setString(2, content);
				pstat.setString(3, id[rnd.nextInt(id.length)]);
				
				pstat.executeQuery();
				
				System.out.println(i);
				
				
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Dummy.main");
			e.printStackTrace();
		}
		
	}
	
}
