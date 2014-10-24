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
					if (!r.isFreeTime(ct)){
						alloc = false;
					}
				}
				if(alloc){
					r.addClass(st);
				}
			}
		}
		
	}
	
	private void reset(){
		BD bd = BD.getInstance();
		for (Room r : bd.listRooms){
			r.reset();
		}
	}
}
