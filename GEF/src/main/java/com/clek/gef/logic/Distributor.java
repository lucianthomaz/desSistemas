package com.clek.gef.logic;

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
	
	public void distrubute(){
		BD bd = BD.getInstance();
		if (bd.listStudentsClass.isEmpty() || bd.listRooms.isEmpty()){
			return;
		}
		
		for (StudentsClass st : bd.listStudentsClass){
			for (Room r : bd.listRooms){
				HashSet<ClassTime> lstCt = st.getClassTime();
				boolean alloc = true;
				for (ClassTime ct : lstCt){
					if (getOcuppedTimes(r).contains(ct)){
						alloc = false;
					}
				}
				if(alloc){
					st.setRoomName(r.getRoomName());
					st.setBuilding(r.getBuilding());
					st.sRoom(r);
				}
			}
		}
		
	}
	
	private HashSet<ClassTime> getOcuppedTimes(Room r){
		BD bd = BD.getInstance();
		
		HashSet<ClassTime> times = new HashSet<ClassTime>();
		
		for (StudentsClass sc : bd.listStudentsClass){
			if (sc.getRoomName().equals(r.getRoomName()) && sc.getBuilding().equals(r.getBuilding())){
				times.addAll(sc.getClassTime());
			}
		}
		
		return times;
	}
}
