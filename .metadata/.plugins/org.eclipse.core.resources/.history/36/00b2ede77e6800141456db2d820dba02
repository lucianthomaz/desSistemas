package com.clek.gef.model;
import java.util.HashSet;

public class StudentsClass {
	
	private Course course;
	private Room room;
	
	private String code;
	private HashSet<ClassTime> classTime;
	private String building;
	private String roomName;
	private String courseCode;
	private String courseName;
	
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public StudentsClass(){
		this.classTime = new HashSet<ClassTime>();
	}
	
	public StudentsClass (String code, Course disc){
		this.course = disc;
		this.courseCode = disc.getCode();
		this.courseName = disc.getName();
		this.code = code;
		this.classTime = new HashSet<ClassTime>();
	}
	
	public StudentsClass (String code){
		this.code = code;
		this.classTime = new HashSet<ClassTime>();
	}
	
	public String getRoomName(){
		return this.roomName;
	}
	
	public void setRoomName(String roomName){
		this.roomName = roomName;
	}
	
	public String getBuilding(){
		return this.building;
	}
	
	public void setBuilding(String building){
		this.building = building;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public Room gRoom(){
		return this.room;
	}
	
	public void sRoom(Room room){
		this.roomName = room.getRoomName();
		this.building = room.getBuilding();
		this.room = room;
	}
	
	public Course gCourse(){
		return this.course;
	}
	
	public void sCourse(Course course){
		this.course = course;
	}
	
	public boolean addClassTime(ClassTime time){
		return this.classTime.add(time);
	}
	
	public void setClassTime(HashSet<ClassTime> classTime){
		this.classTime = classTime;
	}
	
	public HashSet<ClassTime> getClassTime(){
		return this.classTime;
	}
	
	
}
