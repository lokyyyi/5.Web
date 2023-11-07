package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCTemplate {
	//1.연결
	public static Connection getConnection() {
		
		//드라이버 등록
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("01.드라이버 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01.드라이버 연결 실패");
			e.printStackTrace();
		}		
		String url = "jdbc:mysql://localhost:3306/multi";
		String id = "root";
		String pw = "1234";
		
		Connection con = null;
		
		//db연결
		try {
			con = DriverManager.getConnection(url,id,pw);
			//autocommit 해제
			con.setAutoCommit(false);
			System.out.println("02.계정 연결");
		} catch (SQLException e) {
			System.out.println("02.계정 연결 실패");
			e.printStackTrace();
		}
	return con;
	}
	
	//2.해제
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("[error]Connection close 에러");
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			System.out.println("[error]Statement close 에러");
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println("[error]ResultSet close 에러");
			e.printStackTrace();
		}
	}
	
	//3.저장
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch(SQLException e	) {
			System.out.println("[error] commit 에러");
			e.printStackTrace();
		}
	}
	//4.취소
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			System.out.println("[error] rollback 에러");
			e.printStackTrace();
		}
	}
}
