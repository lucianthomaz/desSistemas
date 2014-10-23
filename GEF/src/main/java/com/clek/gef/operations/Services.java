package com.clek.gef.operations;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.*;

import com.clek.gef.model.*;
import com.clek.gef.persistence.BD;

@Path("/")
public class Services {
	@GET
	@Path("rooms")
	@Produces("application/json")
	public Collection<Room> distributedRooms(){
		BD bd = BD.getInstance();
		
		Room r = new Room(60, "201");
		
		Course c = new Course("3424", 4, "Desenv Sis", 60);
		
		StudentsClass sc = new StudentsClass("128",c);
		
		ClassTime ct1 = new ClassTime(DayOfWeek.MONDAY, Time.J);
		ClassTime ct2 = new ClassTime(DayOfWeek.MONDAY, Time.K);
		ClassTime ct3 = new ClassTime(DayOfWeek.MONDAY, Time.L);
		ClassTime ct4 = new ClassTime(DayOfWeek.MONDAY, Time.M);
		
		sc.addClassTime(ct1);
		sc.addClassTime(ct2);
		sc.addClassTime(ct3);
		sc.addClassTime(ct4);
		
		r.addClass(sc);
		
		bd.listCourse.add(c);
		bd.listRooms.add(r);
		bd.listStudentsClass.add(sc);
		
		Collection<Room> col = bd.listRooms;
		
		return col;
	}
	
	//apenas recebe o documento. Sera void
	@PUT
	@Path("bulk")
	@Consumes("application/json")
	@Produces("application/json")
	public String importFile(){
		return "sucesso";
	}
	
	@POST
	@Path("rooms")
	@Produces("application/json")
	public String distribute() {
		return "sucesso";
	}
	
}

