package com.clek.gef.persistence;

import java.sql.SQLException;
import java.util.List;

import com.clek.gef.model.Room;

public class PersistenceFacade {
	private static PersistenceFacade instance;
	
	public static synchronized PersistenceFacade getInstance() throws DBException{
		if (instance == null){
			instance = new PersistenceFacade();
		}
		return instance;
	}
	
	private RoomDAO roomDao;
	
	private PersistenceFacade() throws DBException{
		roomDao = new RoomDAO();
	}
	
	public void persistRoom(Room r) throws SQLException{
		roomDao.persist(r);
	}
	
	public void persistRoom(List<Room> rs) throws SQLException{
		roomDao.persist(rs);
	}
	
	public List<Room> getAllRooms() throws SQLException{
		return roomDao.getAllRooms();
	}
}
