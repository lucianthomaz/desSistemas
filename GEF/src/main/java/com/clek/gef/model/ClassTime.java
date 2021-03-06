package com.clek.gef.model;

public class ClassTime implements Comparable<ClassTime>{
	private DayOfWeek day;
	private Time time;

	private String building;
	private String roomName;

	private Room room;
	
	public ClassTime(DayOfWeek day, Time time){
		this.setTime(time);
		this.setDay(day);
	}
	
	public ClassTime(){
		
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
	
	public int hashCode(){
		return (int)(Math.pow(day.ordinal()*7, 2) + time.ordinal());
	}
	
	public boolean equals(Object obj){
		if (obj instanceof ClassTime){
			ClassTime time = (ClassTime) obj;
			if (time.getDay() == this.day && time.getTime() == this.time){
				return true;
			}
		}
		return false;
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
	
	public Room gRoom(){
		return this.room;
	}
	
	public void sRoom(Room room){
		this.roomName = room.getRoomName();
		this.building = room.getBuilding();
		this.room = room;
	}

	public int compareTo(ClassTime o) {
		if (this.day.ordinal() > o.day.ordinal()){
			return 1;
		}
		if (this.day.ordinal() < o.day.ordinal()){
			return -1;
		}
		return 0;
	}
}