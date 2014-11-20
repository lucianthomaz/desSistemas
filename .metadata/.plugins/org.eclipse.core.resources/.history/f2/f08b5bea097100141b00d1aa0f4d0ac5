package com.clek.gef.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.HashSet;

import com.clek.gef.model.ClassTime;
import com.clek.gef.model.DayOfWeek;
import com.clek.gef.model.StudentsClass;
import com.clek.gef.model.Time;

public class ClassTimeDAO {
private Connection conn;
	
	public ClassTimeDAO() throws DBException{
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
	
	public void cleanTable() throws SQLException{
		String str = "DROP TABLE GEFDATABASE.CLASS_TIME";
		openConn();
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.execute();
		closeConn();
	}
	
	public void recreateTable() throws SQLException{
		String str = "CREATE TABLE GEFDATABASE.Class_Time( ID_CLASS_TIME INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
				"ID_STUDENTS_CLASS INTEGER NOT NULL,"+
				"ID_ROOM INTEGER,"+
				"DAY_OF_WEEK VARCHAR(10) NOT NULL,"+
				"CLASS_TIME VARCHAR(1) NOT NULL,"+
				"PRIMARY KEY (ID_CLASS_TIME),"+
				"FOREIGN KEY(ID_STUDENTS_CLASS) REFERENCES GEFDATABASE.Students_Class,"+
				"CONSTRAINT UK_CLASS_TIME UNIQUE(ID_STUDENTS_CLASS, DAY_OF_WEEK, CLASS_TIME));";
		openConn();
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.execute();
		closeConn();
	}
	
	public void persist(ClassTime ct, StudentsClass sc) throws SQLException, DBException{
		int idR = 0;
		if (ct.gRoom() != null){
			RoomDAO rd = new RoomDAO();
			idR = rd.getId(ct.gRoom());
		}
		StudentsClassDAO scd = new StudentsClassDAO();
		int idSc = scd.getId(sc);
		
		openConn();
		
		String str = "INSERT INTO GEFDATABASE.CLASS_TIME (ID_STUDENTS_CLASS, ID_ROOM, DAY_OF_WEEK, CLASS_TIME) VALUES (?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(str);
		
		stmt.setInt(1, idSc);
		stmt.setInt(2, idR == 0 ? null : idR);
		stmt.setString(3, ct.getDay().name());
		stmt.setString(4, ct.getTime().name());
		stmt.execute();
		
		closeConn();
	}

	public void allocRoom(ClassTime ct, StudentsClass sc) throws DBException, SQLException{
		RoomDAO rd = new RoomDAO();
		int idR = rd.getId(ct.gRoom());
		StudentsClassDAO scd = new StudentsClassDAO();
		int idSc = scd.getId(sc);
		String str = "UPDATE GEFDATABASE.CLASS_TIME SET ID_ROOM = ? WHERE ID_STUDENTS_CLASS = ? AND DAY_OF_WEEK = ? AND CLASS_TIME = ?";
		
		openConn();
		
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setInt(1, idR);
		stmt.setInt(2, idSc);
		stmt.setString(3, ct.getDay().name());
		stmt.setString(4, ct.getTime().name());
		stmt.execute();
		
		closeConn();
		
	}
	
	public void persist(Collection<ClassTime> lct, StudentsClass sc) throws SQLException, DBException{
		openConn();

		StudentsClassDAO scd = new StudentsClassDAO();
		int idSc = scd.getId(sc);
		
		for (ClassTime ct : lct){
			int idR = 0;
			if (ct.gRoom() != null){
				RoomDAO rd = new RoomDAO();
				idR = rd.getId(ct.gRoom());
			}
			
			
			String str = "INSERT INTO GEFDATABASE.CLASS_TIME (ID_STUDENTS_CLASS, ID_ROOM, DAY_OF_WEEK, CLASS_TIME) VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(str);
			
			stmt.setInt(1, idSc);
			stmt.setInt(2, idR == 0 ? null : idR);
			stmt.setString(3, ct.getDay().name());
			stmt.setString(4, ct.getTime().name());
			stmt.execute();
		}
		closeConn();
	}
	
	public HashSet<ClassTime> getAllClassTime(StudentsClass sc) throws SQLException, DBException{
		StudentsClassDAO scd = new StudentsClassDAO();
		int idSc = scd.getId(sc);
		
		openConn();
		
		String str = "SELECT * FROM GEFDATABASE.CLASS_TIME WHERE GEFDATABASE.ID_STUDENTS_CLASS = ?";
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setInt(1, idSc);
		ResultSet rs = stmt.executeQuery();

		
		HashSet<ClassTime> lstClassTime = new HashSet<ClassTime>();
		while (rs.next()){
			ClassTime ct = new ClassTime();
			
			int indR = rs.getInt("ID_ROOM");
			if (indR != 0){
				RoomDAO rd = new RoomDAO();
				ct.sRoom(rd.getRoom(indR));
			}
			
			ct.setDay(DayOfWeek.valueOf(rs.getString("DAY_OF_WEEK")));
			ct.setTime(Time.valueOf(rs.getString("CLASS_TIME")));
			
			lstClassTime.add(ct);
		}
		
		closeConn();
		return lstClassTime;
	}
	/*
	protected StudentsClass getStudentsClass(int id) throws SQLException, DBException{
		openConn();
		
		String str = "SELECT * FROM GEFDATABASE.STUDENTS_CLASS WHERE GEFDATABASE.STUDENTS_CLASS.ID_STUDENTS_CLASS = ?";
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		CourseDAO cd = new CourseDAO();
		StudentsClass sc = null;
		if (rs.next()){
			sc = new StudentsClass();
			sc.setCode(rs.getString("NUMBER_STUDENTS_CLASS"));
			sc.sCourse(cd.getCourse(rs.getInt("ID_COURSE")));
			//TODO pegar horario das turmas
		}
		closeConn();
		
		return sc;
	}
	
	public StudentsClass getStudentsClass(String code, Course c) throws SQLException, DBException{
		openConn();
		
		String str = "SELECT * FROM GEFDATABASE.STUDENTS_CLASS WHERE GEFDATABASE.STUDENTS_CLASS.NUMBER_STUDENTS_CLASS = ? AND GEFDATABASE.STUDENTS_CLASS.ID_COURSE = ?";
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setString(1, code);
		CourseDAO cd = new CourseDAO();
		int id = cd.getId(c);
		ResultSet rs = stmt.executeQuery();
		
		StudentsClass sc = null;
		if (rs.next()){
			sc = new StudentsClass();
			sc.setCode(rs.getString("NUMBER_STUDENTS_CLASS"));
			sc.sCourse(c);
			//TODO pegar horario das turmas
		}
		closeConn();
		
		return sc;
	}
	
	protected int getId(StudentsClass sc) throws SQLException, DBException{
		openConn();
		
		String str = "SELECT * FROM GEFDATABASE.STUDENTS_CLASS WHERE GEFDATABASE.STUDENTS_CLASS.NUMBER_STUDENTS_CLASS = ? AND GEFDATABASE.STUDENTS_CLASS.ID_COURSE = ?";
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setString(1, sc.getCode());
		CourseDAO cd = new CourseDAO();
		int idC = cd.getId(sc.gCourse());
		stmt.setInt(2, idC);
		ResultSet rs = stmt.executeQuery();
		
		int id = 0;
		
		if (rs.next()){
			id = rs.getInt("ID_COURSE");
		} else {
			id = -1;
		}
		
		return id;
	}*/
}
