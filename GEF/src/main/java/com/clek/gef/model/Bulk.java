package com.clek.gef.model;

import java.util.ArrayList;
import java.util.List;

public class Bulk {
	List<Room> lstRoom;
	List<StudentsClass> lstStudentsClass;
	List<Course> lstCourse;
	
	public Bulk(){
		lstRoom = new ArrayList<Room>();
		lstStudentsClass = new ArrayList<StudentsClass>();
		lstCourse = new ArrayList<Course>();
	}

	public List<Room> getLstRoom() {
		return lstRoom;
	}

	public void setLstRoom(List<Room> lstRoom) {
		this.lstRoom = lstRoom;
	}

	public List<StudentsClass> getLstStudentsClass() {
		return lstStudentsClass;
	}

	public void setLstStudentsClass(List<StudentsClass> lstStudentsClass) {
		this.lstStudentsClass = lstStudentsClass;
	}

	public List<Course> getLstCourse() {
		return lstCourse;
	}

	public void setLstCourse(List<Course> lstCourse) {
		this.lstCourse = lstCourse;
	}
	
}
