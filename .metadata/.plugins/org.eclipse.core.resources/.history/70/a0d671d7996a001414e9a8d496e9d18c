package com.clek.gef.logic;

import java.sql.SQLException;
import java.util.HashSet;

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
	
	public void distrubute() throws DBException, SQLException{
		BD bd = BD.getInstance();
		if (bd.listStudentsClass.isEmpty() || bd.listRooms.isEmpty()){
			return;
		}
		
		for (StudentsClass st : bd.listStudentsClass){
			HashSet<ClassTime> lstCt = st.getClassTime();
			
			for (Room r : bd.listRooms){
				if (st.gCourse().getModule() > r.getCapacity()){
					continue;
				}
				for (ClassTime ct : lstCt){
					if (!getOcuppedTimes(r).contains(ct)){
						ct.sRoom(r);
						PersistenceFacade.getInstance().allocRoom(ct, st);
					}
				}
			}
		}
		
	}
	
	private HashSet<ClassTime> getOcuppedTimes(Room r){
		BD bd = BD.getInstance();
		
		HashSet<ClassTime> times = new HashSet<ClassTime>();
		
		for (StudentsClass sc : bd.listStudentsClass){
			for (ClassTime ct : sc.getClassTime()){
				if (ct.getBuilding().equals(r.getBuilding()) && ct.getRoomName().equals(r.getRoomName())){
					times.add(ct);
				}
			}
		}
		
		return times;
	}
}
