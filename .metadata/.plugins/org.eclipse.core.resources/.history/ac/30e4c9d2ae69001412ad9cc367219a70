package com.clek.gef.operations;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.*;

import com.clek.gef.logic.Distributor;
import com.clek.gef.model.*;
import com.clek.gef.persistence.BD;
import com.clek.gef.persistence.DBException;
import com.clek.gef.persistence.PersistenceFacade;

@Path("/")
public class Services {
	/*@GET
	@Path("test")
	@Produces("application/json")
	public Collection<Room> test() throws Exception{
		Collection <Room> lstr = new ArrayList<>();
			PersistenceFacade pf = PersistenceFacade.getInstance();

			Room r = new Room(60, "324", "32");


			pf.persistRoom(r);


			lstr= pf.getAllRooms();
		return lstr;
	}*/
	
	@GET
	@Path("allocation")
	@Produces("application/json")
	public Collection<StudentsClass> distributedRooms(){
		BD bd = BD.getInstance();
		
		
		Collection<StudentsClass> col = bd.listStudentsClass;
		
		return col;
	}
	
	/*
	@GET
	@Path("all")
	@Produces("application/json")
	public BD getAllBD(){
		BD bd = BD.getInstance();
		
		if (bd.listRooms.isEmpty()){
			bd.populate();
		}
		Distributor d = Distributor.getInstance();
		d.distrubute();
		
		Collection<Room> col = bd.listRooms;
		
		return bd;
	}*/
	
	//apenas recebe o documento. Sera void
	@PUT
	@Path("bulk")
	@Consumes("application/json")
	public void importFile(BD bd){
		BD bede = BD.getInstance();
		bede.populate(bd);
	}
	
	@POST
	@Path("rooms")
	@Produces("application/json")
	public String distribute() {
		BD bd = BD.getInstance();
		
		if (bd.listRooms.isEmpty()){
			bd.populate();
		}
		Distributor d = Distributor.getInstance();
		d.distrubute();
		
		return "sucesso";
	}
	
}

