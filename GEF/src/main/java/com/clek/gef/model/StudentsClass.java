package com.clek.gef.model;
import java.util.HashSet;

public class StudentsClass {
	
	private Course course;
	private String code;
	private HashSet<ClassTime> classTime;
	
	public StudentsClass(){
		
	}
	
	public StudentsClass (String code, Course disc){
		this.course = disc;
		this.code = code;
		this.classTime = new HashSet<ClassTime>();
	}
	
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public Course getCourse(){
		return this.course;
	}
	
	public void setCourse(Course course){
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
