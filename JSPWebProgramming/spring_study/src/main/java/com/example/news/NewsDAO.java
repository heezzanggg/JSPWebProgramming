package com.example.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NewsDAO {
	
	PreparedStatement pstmt;
	
	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String JDBC_URL = "jdbc:mysql://localhost:3306/jwbook?serverTimezone=UTC";
	final String id = "root";
	final String pw = "1234";
	
	//DB연결 가져오는 메소드
	public Connection open() {
		
		Connection  conn = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,id,pw);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//뉴스추가 메서드
	public void addNews(News n) throws SQLException {
		Connection conn = open();
		
		String sql = "insert into news(title,img,content) values(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt);
		try(conn;pstmt){
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getContent());
			pstmt.executeUpdate();
		}
		
	}
	
	//뉴스기사 목록 전체를 가져오는 메소드
	public List<News> getAll() throws Exception{
		System.out.println("getAll 메소드");
		Connection conn = open();
		List<News> newsList = new ArrayList<>();
		
		String sql = "select aid,title,DATE_FORMAT(date,'%Y-%m-%d %h:%i:%s') as cdate from news";
		//PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') : H2
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try (conn;pstmt;rs){
			while(rs.next()) {
				News n = new News();
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("cdate"));
				
				newsList.add(n);
			}
			return newsList;
		}
		
	}
	
	//뉴스 한개를 클릭했을 때 세부내용 보여주는 메소드
	public News getNews(int aid) throws SQLException {
		
		Connection conn = open();
		News n = new News();
		String sql = "select aid,title,img,DATE_FORMAT(date,'%Y-%m-%d %h:%i:%s') as cdate, content from news where aid=?";
		//PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') : H2
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		try(conn;pstmt;rs){
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setImg(rs.getString("img"));
			n.setDate(rs.getString("cdate"));
			n.setContent(rs.getString("content"));
			
			pstmt.executeQuery();
			return n;
		}
	}
	
	//뉴스삭제 메서드
	public void delNews(int aid) throws SQLException {
		Connection conn = open();

		String sql = "delete from news where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try(conn;pstmt){
			pstmt.setInt(1, aid);
			//삭제된 뉴스기사 없을 경우
			if(pstmt.executeUpdate() ==0 ) {
				throw new SQLException("DB에러");
			}
		}
	}
	
		

}
