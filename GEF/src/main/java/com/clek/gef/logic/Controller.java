package com.clek.gef.logic;

import java.sql.SQLException;
import java.util.List;

import com.clek.gef.model.Bulk;
import com.clek.gef.model.ClassTime;
import com.clek.gef.model.Course;
import com.clek.gef.model.Room;
import com.clek.gef.model.StudentsClass;
import com.clek.gef.persistence.DBException;
import com.clek.gef.persistence.PersistenceFacade;

public class Controller {
	private static Controller instance;
	
	private PersistenceFacade persistence;
	private RoomController roomController;
	private StudentsClassController studentsClassController;
	private CourseController courseController;
	
	private Controller() throws DBException{
		persistence = PersistenceFacade.getInstance();
		roomController = RoomController.getInstance();
		studentsClassController = StudentsClassController.getInstance();
		courseController = CourseController.getInstance();
	}
	
	public static synchronized Controller getInstance() throws DBException{
		if (instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	public void addRoom(Room r) throws SQLException{
		roomController.addRoom(r);
	}
	
	public void resetDataBase(Bulk bulk) throws SQLException, DBException{
		persistence.cleanTables();
		persistence.recreateTables();
		
		roomController.addRoom(bulk.getLstRoom());
		courseController.addCourse(bulk.getLstCourse());
		studentsClassController.addStudentsClass(bulk.getLstStudentsClass());
	}
	
	public Bulk getAllData() throws SQLException, DBException{
		Bulk bulk = new Bulk();
		
		bulk.setLstCourse(courseController.getAllCourses());
		bulk.setLstRoom(roomController.getAllRooms());
		bulk.setLstStudentsClass(studentsClassController.getAllStudentsClasses());
		
		return bulk;
	}
	
	public void distribute() throws SQLException, DBException{
		Bulk b = getAllData();
		Distributor.getInstance().distrubute(b);
	}
	
	public List<Room> getFreeRooms(ClassTime ct) throws SQLException, DBException{
		return Distributor.getInstance().getFreeRoom(getAllData(), ct);
	}
	
	public void addOrUpdateStudentsClass(StudentsClass sc) throws SQLException, DBException{
		Course c = courseController.getCourse(sc.getCourseCode());
		if (c == null) return;
		StudentsClass scaux = studentsClassController.getStudentsClass(sc.getCode(), c);
		
		if (scaux == null){
			sc.sCourse(c);
			studentsClassController.addStudentsClass(sc);
		} else {
			studentsClassController.updateAllocation(scaux);
		}
	}
}
