package com.clek.gef.model;
import java.util.HashSet;

public class StudentsClass {
	
	private Course course;
	
	private String code;
	private HashSet<ClassTime> classTime;
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
	

	
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
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
