package com.clek.gef.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.clek.gef.model.Room;

public class RoomDAO {
	private Connection conn;
	
	private void openConn() throws SQLException{
		conn = DriverManager.getConnection("jdbc:derby:MyDB;create=true");
	}
	
	private void closeConn() throws SQLException{
		conn.commit();
		conn.close();
	}
	public void persist(Room r) throws SQLException{
		openConn();

		String str = "INSERT INTO GEFDATABASE.ROOM VALUES (?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(str);
		
		stmt.setString(1, r.getRoomName());
		stmt.setString(2, r.getBuilding());
		stmt.setInt(3, r.getCapacity());
		
		stmt.execute();
		
		closeConn();
	}
}
