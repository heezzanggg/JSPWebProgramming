package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	//데이터베이스 연동을 위한 DAO클래스,
	//DB 연결/종료,CRUD지원을 위한 메서드로 구성
	
	Connection conn = null;
	PreparedStatement pstmt;
	
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String JDBC_URL = "jdbc:mysql://localhost:3306/jwbook?serverTimezone=UTC";
		final String id = "root";
		final String pw = "1234";
	
	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,id,pw);
			System.out.print("연결오픈!!!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			pstmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Student s) {
		System.out.println("인서트!!!");
		open();
		String sql = "INSERT INTO student(username, univ, birth, email) values(?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,s.getUsername());
			pstmt.setString(2,s.getUniv());
			pstmt.setDate(3, s.getBirth());
			pstmt.setString(4,s.getEmail());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public List<Student> getAll(){
		System.out.println("getAll!!!");

		open();
		
		List<Student> students = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from student");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setUniv(rs.getString("univ"));
				s.setBirth(rs.getDate("birth"));
				s.setEmail(rs.getString("email"));
				
				students.add(s);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return students; //리턴 널 조심하자잉!!!! 암것도 안나온다잉!!!
	}

}
