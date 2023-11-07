package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MVCBoardDto;
import static common.JDBCTemplate.*;

public class MVCBoardDao {
	
	//전체조회
	public List<MVCBoardDto> selectAll(){
		//준비
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<MVCBoardDto> res = new ArrayList<>();
		String sql = "SELECT * FROM MVCBOARD ORDER BY SEQ DESC";
		
		try {
			stmt = con.createStatement();
			System.out.println("03.query 준비: " + sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04.query 실행 및 리턴");
			
			while(rs.next()) {
				MVCBoardDto tmp = new MVCBoardDto();
				tmp.setSeq(rs.getInt(1));
				tmp.setWriter(rs.getString(2));
				tmp.setTitle(rs.getString(3));
				tmp.setContent(rs.getString(4));
				tmp.setRegdate(rs.getDate(5));
				
				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
			System.out.println("05.db 종료\n");
		}
		return res;
	}
	
	//상세조회
	public MVCBoardDto selectOne(int seq) {
		//준비
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MVCBoardDto res = new MVCBoardDto();
		
		String sql = " SELECT * FROM MVCBOARD WHERE SEQ=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03.query 준비 " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			while(rs.next()) {
				res.setSeq(rs.getInt("SEQ"));
				res.setWriter(rs.getString(2));
				res.setTitle(rs.getString("TITLE"));
				res.setContent(rs.getString(3));
				res.setRegdate(rs.getDate(5));
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db종료\n");
		}
		return res;
	}
	
	//추가
	public int insert(MVCBoardDto dto) {
		//준비
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " INSERT INTO MVCBOARD VALUES(NULL, ?, ?, ?, NOW())";
		
		//try-catch == alt shift z 
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("03.query 준비: " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(con);
			close(pstm);
			System.out.println("05.db 종료 \n");
		}
		return res;
	}
	//수정
	public int update(MVCBoardDto dto) {
		//준비
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE MVCBOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("03.query 준비: "+ sql);
			
			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05.db 종료 \n");
		}
		return res;
	}
	
	//삭제
	public int delete(int seq) {
		//준비
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " DELETE FROM MVCBOARD WHERE SEQ=? ";
	
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, seq);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(con);
			close(pstm);
			System.out.println("05. db종료\n");
		}
		return res;
	}
}
