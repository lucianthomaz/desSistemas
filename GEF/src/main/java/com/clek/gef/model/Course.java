package com.clek.gef.model;



public class Course {
	private String code;
	private int credit;
	private String name;
	private int module;
	
	public Course(){
		
	}
	
	public Course(String code, int credit, String name, int module){
		this.code = code;
		this.credit = credit;
		this.name = name;
		this.module = module;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public int getCredit(){
		return this.credit;
	}
	
	public void setCredit(int credit){
		this.credit = credit;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getModule(){
		return this.module;
	}
	
	public void setModule(int module){
		this.module = module;
	}
}

