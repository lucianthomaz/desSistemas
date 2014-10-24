package com.clek.gef.persistence;

import java.util.ArrayList;

import com.clek.gef.model.*;

public class BD {
	public ArrayList<Room> listRooms;
	public ArrayList<StudentsClass> listStudentsClass;
	public ArrayList<Course> listCourse;
	
	private static BD instance;
	
	private BD(){
		listRooms = new ArrayList<Room>();
		listStudentsClass = new ArrayList<StudentsClass>();
		listCourse = new ArrayList<Course>();
	}
	
	public static synchronized BD getInstance(){
		if (instance == null){
			instance = new BD();
		}
		return instance;
	}
	
	public static synchronized void setInstance(BD inst){
		instance = inst;
	}
}
