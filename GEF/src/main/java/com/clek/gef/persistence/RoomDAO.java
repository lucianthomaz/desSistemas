package com.clek.gef.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.clek.gef.model.Room;

public class RoomDAO {
	private Connection conn;
	
	public RoomDAO() throws DBException{
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
	
	public void persist(Room r) throws SQLException{
		openConn();

		String str = "INSERT INTO GEFDATABASE.ROOM (ROOM_NAME, BUILDING, CAPACITY) VALUES (?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(str);
		
		stmt.setString(1, r.getRoomName());
		stmt.setString(2, r.getBuilding());
		stmt.setInt(3, r.getCapacity());
		
		stmt.execute();
		
		closeConn();
	}

	public void persist(List<Room> rs) throws SQLException{
		openConn();

		for (Room r : rs){
			String str = "INSERT INTO GEFDATABASE.ROOM (ROOM_NAME, BUILDING, CAPACITY) VALUES (?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(str);

			stmt.setString(1, r.getRoomName());
			stmt.setString(2, r.getBuilding());
			stmt.setInt(3, r.getCapacity());

			stmt.execute();
		}
		closeConn();
	}
	
	public List<Room> getAllRooms() throws SQLException{
		openConn();
		
		String str = "SELECT * FROM GEFDATABASE.ROOM";
		PreparedStatement stmt = conn.prepareStatement(str);
		ResultSet rs = stmt.executeQuery();
		
		List<Room> lstRoom = new ArrayList<Room>();
		while (rs.next()){
			Room r = new Room();
			r.setBuilding(rs.getString("BUILDING"));
			r.setRoomName(rs.getString("ROOM_NAME"));
			r.setCapacity(rs.getInt("CAPACITY"));
			lstRoom.add(r);
		}
		
		closeConn();
		
		return lstRoom;
	}
	
	protected Room getRoom(int id) throws SQLException{
		openConn();
		
		String str = "SELECT * FROM GEFDATABASE.ROOM WHERE GEFDATABASE.ROOM.ID_ROOM = ?";
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		Room r = null;
		if (rs.next()){
			r = new Room();
			r.setBuilding(rs.getString("BUILDING"));
			r.setRoomName(rs.getString("ROOM_NAME"));
			r.setCapacity(rs.getInt("CAPACITY"));
		}
		closeConn();
		
		return r;
	}
	
	public Room getRoom(String building, String roomName) throws SQLException{
		openConn();
		
		String str = "SELECT * FROM GEFDATABASE.ROOM WHERE GEFDATABASE.ROOM.ROOM_NAME = ? AND GEFDATABASE.ROOM.BUILDING = ?";
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setString(1, roomName);
		stmt.setString(2, building);
		ResultSet rs = stmt.executeQuery();
		
		Room r = null;
		
		if (rs.next()){
			r = new Room();
			r.setBuilding(rs.getString("BUILDING"));
			r.setRoomName(rs.getString("ROOM_NAME"));
			r.setCapacity(rs.getInt("CAPACITY"));
		}
		closeConn();
		
		return r;
	}
	
	protected int getId(Room r) throws SQLException{
		openConn();
		
		String str = "SELECT ID_ROOM FROM GEFDATABASE.ROOM WHERE GEFDATABASE.ROOM.ROOM_NAME = ? AND GEFDATABASE.ROOM.BUILDING = ?";
		PreparedStatement stmt = conn.prepareStatement(str);
		stmt.setString(1, r.getRoomName());
		stmt.setString(2, r.getBuilding());
		ResultSet rs = stmt.executeQuery();
		
		int id = 0;
		
		if (rs.next()){
			id = rs.getInt("ID_ROOM");
		} else {
			id = -1;
		}
		
		closeConn();
		
		return id;
	}
}
