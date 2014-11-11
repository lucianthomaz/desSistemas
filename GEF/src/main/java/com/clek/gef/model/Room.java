package com.clek.gef.model;



public class Room {
	private int capacity;
	private String roomName;
	private String building;
	
	public Room (){
		
	}
	
	public Room (int capacity, String name, String building){
		this.capacity = capacity;
		this.roomName = name;
		this.building = building;
	}
	
	public String getBuilding(){
		return building;
	}
	
	public void setBuilding(String building){
		this.building = building;
	}
	
	public String getRoomName(){
		return roomName;
	}
	
	public void setRoomName(String roomName){
		this.roomName = roomName;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
}
