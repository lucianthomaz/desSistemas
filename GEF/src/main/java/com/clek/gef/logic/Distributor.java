package com.clek.gef.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.clek.gef.model.*;
import com.clek.gef.persistence.*;

public class Distributor {
	public static Distributor instance;
	
	private Distributor(){
		
	}
	
	public static synchronized Distributor getInstance(){
		if (instance == null){
			instance = new Distributor();
		}
		return instance;
	}
	
	public void distrubute(Bulk bulk) throws DBException, SQLException{
		if (!bulk.getLstStudentsClass().isEmpty() && !bulk.getLstRoom().isEmpty()){
			for (StudentsClass st : bulk.getLstStudentsClass()){
				ArrayList<ClassTime> lstCt = st.getClassTime();

				for (Room r : bulk.getLstRoom()){
					if (st.gCourse().getModule() > r.getCapacity()){
						continue;
					}
					for (ClassTime ct : lstCt){
						if (!getOcuppedTimes(bulk, r).contains(ct)){
							ct.sRoom(r);
							PersistenceFacade.getInstance().allocRoom(ct, st);
						}
					}
				}
			}
		}
	}
	
	public List<Room> getFreeRoom(Bulk b, ClassTime ct){
		List<Room> freeRooms = new ArrayList<Room>();
		
		for (Room r : b.getLstRoom()){
			HashSet<ClassTime> hashCt = this.getOcuppedTimes(b, r);
			if (!hashCt.contains(ct)){
				freeRooms.add(r);
			}
		}
		
		return freeRooms;
	}
	
	private HashSet<ClassTime> getOcuppedTimes(Bulk b, Room r){
		HashSet<ClassTime> times = new HashSet<ClassTime>();
		
		for (StudentsClass sc : b.getLstStudentsClass()){
			for (ClassTime ct : sc.getClassTime()){
				if (ct.getBuilding() == null || ct.getRoomName() == null || ct.gRoom() == null){
					continue;
				}
				if (ct.getBuilding().equals(r.getBuilding()) && ct.getRoomName().equals(r.getRoomName())){
					times.add(ct);
				}
			}
		}
		
		return times;
	}
}
