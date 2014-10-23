package com.clek.gef.model;

public class ClassTime{
	private DayOfWeek day;
	private Time time;
	
	public ClassTime(DayOfWeek day, Time time){
		this.setTime(time);
		this.setDay(day);
	}
	
	public DayOfWeek getDay(){
		return day;
	}
	
	public Time getTime(){
		return time;
	}
	
	public void setDay(DayOfWeek day){
		this.day = day;
	}
	
	public void setTime(Time time){
		this.time = time;
	}
}