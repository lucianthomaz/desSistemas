package com.clek.gef.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.clek.gef.model.Course;

public class CourseDAO {
private Connection conn;
	
	public CourseDAO() throws DBException{
		try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            throw new DBException("Erro ao se conectar com o Banco de Dados, não foi encontrado o Driver.");
        }
	}
	
	private void openConn() throws SQLException{
		
		conn = DriverManager.getConnection(ConnectionString.connStr, "admin", "admin");
	}
	
	private void closeConn() throws SQLException{
		conn.commit();
		conn.close();
	}
	
	public void persist(Course c) throws SQLException{
		openConn();

		String str = "INSERT INTO GEFDATABASE.COURSE (CODE_COURSE, CREDIT, NAME, MODULE) VALUES (?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(str);
		
		stmt.setString(1, c.getCode());
		stmt.setInt(2, c.getCredit());
		stmt.setString(3, c.getName());
		stmt.setInt(4, c.getModule());
		
		stmt.execute();
		
		closeConn();
	}

	public void persist(List<Course> cs) throws SQLException{
		openConn();

		for (Course c : cs){
			String str = "INSERT INTO GEFDATABASE.COURSE (CODE_COURSE, CREDIT, NAME, MODULE) VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(str);
			
			stmt.setString(1, c.getCode());
			stmt.setInt(2, c.getCredit());
			stmt.setString(3, c.getName());
			stmt.setInt(4, c.getModule());
			
			stmt.execute();
		}
		closeConn();
	}
	
	public List<Course> getAllCourses() throws SQLException{
		openConn();
		
		String str = "SELECT * FROM GEFDATABASE.COURSE";
		PreparedStatement stmt = conn.prepareStatement(str);
		ResultSet rs = stmt.executeQuery();
		
		List<Course> lstCourse = new ArrayList<Course>();
		while (rs.next()){
			Course c = new Course();
			c.setCode(rs.getString("CODE_COURSE"));
			c.setCredit(rs.getInt("CREDIT"));
			c.setModule(rs.getInt("MODULE"));
			c.setName(rs.getString("NAME"));
			lstCourse.add(c);
		}
		
		closeConn();
		
		return lstCourse;
	}
	
	protected Course getCourse(int id) throws SQLException{
		openConn();
		
		String str = "SELECT * FROM GEFDATABASE.COURSE WHERE GEFDATABASE.COURSE.ID_COURSE = ?";
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		Course c = null;
		if (rs.next()){
			c = new Course();
			c.setCode(rs.getString("CODE_COURSE"));
			c.setCredit(rs.getInt("CREDIT"));
			c.setModule(rs.getInt("MODULE"));
			c.setName(rs.getString("NAME"));
		}
		closeConn();
		
		return c;
	}
	
	public Course getCourse(String code) throws SQLException{
		openConn();
		
		String str = "SELECT * FROM GEFDATABASE.COURSE WHERE GEFDATABASE.COURSE.CODE_COURSE = ?";
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setString(1, code);
		ResultSet rs = stmt.executeQuery();
		
		Course c = null;
		if (rs.next()){
			c = new Course();
			c.setCode(rs.getString("CODE_COURSE"));
			c.setCredit(rs.getInt("CREDIT"));
			c.setModule(rs.getInt("MODULE"));
			c.setName(rs.getString("NAME"));
		}
		closeConn();
		
		return c;
	}
	
	protected int getId(Course c) throws SQLException{
		openConn();
		
		String str = "SELECT ID_COURSE FROM GEFDATABASE.COURSE WHERE GEFDATABASE.COURSE.CODE_COURS = ?";
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setString(1, c.getCode());
		ResultSet rs = stmt.executeQuery();
		
		int id = 0;
		
		if (rs.next()){
			id = rs.getInt("ID_COURSE");
		} else {
			id = -1;
		}
		
		closeConn();
		
		return id;
	}
}
