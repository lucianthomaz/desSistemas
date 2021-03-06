package com.clek.gef.logic;

import java.sql.SQLException;
import java.util.List;

import com.clek.gef.model.Room;
import com.clek.gef.persistence.DBException;
import com.clek.gef.persistence.PersistenceFacade;

public class RoomController {
	private static RoomController instance;
	private PersistenceFacade persistence;
	private RoomController() throws DBException{
		persistence = PersistenceFacade.getInstance();
	}
	
	public static synchronized RoomController getInstance() throws DBException{
		if (instance == null){
			instance = new RoomController();
		}
		return instance;
	}
	
	public void addRoom(Room r) throws SQLException{
		persistence.persistRoom(r);
	}
	
	public void addRoom(List<Room> lstr) throws SQLException{
		for (Room r : lstr){
			this.addRoom(r);
		}
	}

	public List<Room> getAllRooms() throws SQLException {
		return persistence.getAllRooms();
	}
	
	public Room getRoom(String building, String roomName) throws SQLException{
		return persistence.getRoom(building, roomName);
	}
}
