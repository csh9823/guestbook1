package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.Vo.GuestbookVo;

public class GuestbookDao {

	// 필드
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	// 생성자

	// 메소드 gs

	// 메소드 일반

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	//리스트
	public List<GuestbookVo> getList() {
		List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();
		
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열
			String query ="";
			query +=" select  	 no,";
			query +=" 		 	 name,";
			query +=" 		     password,";
			query +=" 			 content,";
			query +=" 			 to_char(ReG_DATE, 'yyyy-mm-dd hh:mi:ss ') reg_date";
			query +=" 			 from guestbook";
			query +=" 			 order by reg_date desc";
			
			//쿼리
			pstmt = conn.prepareStatement(query);
			
			//바인딩(X)
			
			//실행
			rs = pstmt.executeQuery();
			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				
				GuestbookVo guestbookVo = new GuestbookVo(no,name,password,content,regDate);
				guestbookList.add(guestbookVo);
			}
		}catch (SQLException e) {
			 System.out.println("error:" + e);
			}
		close();
		return guestbookList;
	}
	
	//추가
	public int GuestbookInsert(GuestbookVo guestbookvo) {		
		int count = 0;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " insert into guestbook ";
			query += " values(seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";
			// System.out.println(query);

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, guestbookvo.getName()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, guestbookvo.getPassword()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setString(3, guestbookvo.getContent()); // ?(물음표) 중 3번째, 순서중요
			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			// System.out.println("[" + count + "건 추가되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}
	
	//삭제
	public int GuestbookDelete(int no , String password) {
		int count = 0;
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " delete from Guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";
			
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, no);// ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, password);
			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			// System.out.println(count + "건 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}
}
