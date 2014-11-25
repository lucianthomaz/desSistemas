package com.clek.gef.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.clek.gef.model.Accesses;
import com.clek.gef.model.Room;

public class AccessesDAO {
private Connection conn;
	
	public AccessesDAO() throws DBException{
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
	
	public void newAccess() throws SQLException{
		openConn();

		String str = "INSERT INTO GEFDATABASE.ACCESS (ACCESS_DATE) VALUES (?)";
		PreparedStatement stmt = conn.prepareStatement(str);
		
		java.util.Date d = new java.util.Date();
		
		
		stmt.setDate(1, new java.sql.Date(d.getTime()));
		
		stmt.execute();
		
		closeConn();
	}
	
	public Accesses getAccesses() throws SQLException{
		openConn();
		
		Accesses a = new Accesses();
		
		String str = "SELECT COUNT(*) FROM GEFDATABASE.ACCESS WHERE GEFDATABASE.ACCESS.ACCESS_DATE >= (?)";
		PreparedStatement stmt = conn.prepareStatement(str);
		
		java.util.Date d = new java.util.Date();
		
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		
		stmt.setDate(1, new java.sql.Date(d.getTime()));
		
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()){
			a.dayAccesses = rs.getInt(1);
		}
		
		///////
		
		str = "SELECT COUNT(*) FROM GEFDATABASE.ACCESS WHERE GEFDATABASE.ACCESS.ACCESS_DATE >= (?)";
		stmt = conn.prepareStatement(str);
		
		d = new java.util.Date();
		
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		d.setDate(1);
		
		stmt.setDate(1, new java.sql.Date(d.getTime()));
		
		rs = stmt.executeQuery();
		
		if (rs.next()){
			a.monthAccesses = rs.getInt(1);
		}
		
		///////
		
		str = "SELECT COUNT(*) FROM GEFDATABASE.ACCESS WHERE GEFDATABASE.ACCESS.ACCESS_DATE >= (?)";
		stmt = conn.prepareStatement(str);
		
		d = new java.util.Date();
		
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		d.setDate(1);
		d.setMonth(0);
		
		stmt.setDate(1, new java.sql.Date(d.getTime()));
		
		rs = stmt.executeQuery();
		
		if (rs.next()){
			a.yearAccesses = rs.getInt(1);
		}
		
		closeConn();
		
		return a;
	}
}
