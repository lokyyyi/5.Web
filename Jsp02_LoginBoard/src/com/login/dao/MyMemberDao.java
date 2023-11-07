package com.login.dao;

import com.login.dto.MyMemberDto;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyMemberDao {
	
	//로그인
	public MyMemberDto login(String id, String pw) {
		//준비
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyMemberDto res = new MyMemberDto();
		
		String sql = " SELECT * FROM MYMEMBER WHERE MYID=? AND MYPW=? AND MYENABLED=?";
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1,id);
			pstm.setString(2, pw);
			pstm.setString(3, "Y");
			System.out.println("03.query 준비: " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			while(rs.next()) {
				res.setMyno(rs.getInt(1));
				res.setMyid(rs.getString(2));
				res.setMypw(rs.getString(3));
				res.setMyname(rs.getString(4));
				res.setMyaddr(rs.getString(5));
				res.setMyphone(rs.getString(6));
				res.setMyemail(rs.getString(7));
				res.setMyenabled(rs.getString(8));
				res.setMyrole(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con);
			close(pstm);
			close(rs);
			System.out.println("05.db 종료\n");
		}
		return res;
	}
	
	//회원 전체 조회
	public List<MyMemberDto> selectAll(){
		//준비
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<MyMemberDto> res = new ArrayList<MyMemberDto>();
		
		String sql = " SELECT * FROM MYMEMBER ORDER BY MYNO DESC ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03. query 준비 " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				MyMemberDto tmp = new MyMemberDto();
					tmp.setMyno(rs.getInt(1));
					tmp.setMyid(rs.getString(2));
					tmp.setMypw(rs.getString(3));
					tmp.setMyname(rs.getString(4));
					tmp.setMyaddr(rs.getString(5));
					tmp.setMyphone(rs.getString(6));
					tmp.setMyemail(rs.getString(7));
					tmp.setMyenabled(rs.getString(8));
					tmp.setMyrole(rs.getString(9));
					
					res.add(tmp);
			}
		} catch (Exception e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(con);
			close(rs);
			close(pstm);
		}
		return res;
	}
	
	//id 중복 확인
	public String idChk(String id) {
		//준비
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String res = null;
		
		String sql = " SELECT * FROM MYMEMBER WHERE MYID=? ";
	
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			System.out.println("03.query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			while(rs.next()) {
				res = rs.getString(2);
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(con);
			close(pstm);
			close(rs);
			System.out.println("05. db종료\n");
		}
		
		return res;
	}
	
	//회원가입
	public int insertUser(MyMemberDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " INSERT INTO MYMEMBER VALUES(NULL,?, ?, ?, ?, ?, ?, 'Y','USER')";
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, dto.getMyid());
			pstm.setString(2, dto.getMypw());
			pstm.setString(3, dto.getMyname());
			pstm.setString(4, dto.getMyaddr());
			pstm.setString(5, dto.getMyphone());
			pstm.setString(6, dto.getMyemail());
			System.out.println("03. query 준비: " + sql);
			res = pstm.executeUpdate();
			System.out.println("쿼리 실행 및 리턴");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(con);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		return res;
	}
	
	//회원탈퇴
	public int deleteuser(int myno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		String sql = " UPDATE MYMEMBER SET MYENABLED = 'N' WHERE MYNO = ? ";
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("03. query 준비: " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. 쿼리 실행 및 리턴");			
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(con);
			close(pstm);
			System.err.println("05. db 종료\n");
		}
		return res;
	}
	
	
}
