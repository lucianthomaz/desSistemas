import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.clek.gef.logic.Controller;
import com.clek.gef.model.Accesses;
import com.clek.gef.model.Bulk;
import com.clek.gef.model.ClassTime;
import com.clek.gef.model.Course;
import com.clek.gef.model.DayOfWeek;
import com.clek.gef.model.Room;
import com.clek.gef.model.StudentsClass;
import com.clek.gef.model.Time;
import com.clek.gef.persistence.DBException;


public class All {

	@Test
	public void testDistribution() throws SQLException, DBException {
		Room r = new Room(60, "LabCG", "P32");
		Course c = new Course("4546H", 4, "Dev Sis", 60);
		StudentsClass sc = new StudentsClass("128", c);
		ClassTime ct1 = new ClassTime(DayOfWeek.THURSDAY, Time.N);
		ClassTime ct2 = new ClassTime(DayOfWeek.THURSDAY, Time.P);
		ClassTime ct3 = new ClassTime(DayOfWeek.TUESDAY, Time.N);
		ClassTime ct4 = new ClassTime(DayOfWeek.TUESDAY, Time.P);
		sc.addClassTime(ct1);
		sc.addClassTime(ct2);
		sc.addClassTime(ct3);
		sc.addClassTime(ct4);
		
		Bulk b = new Bulk();
		b.getLstCourse().add(c);
		b.getLstRoom().add(r);
		b.getLstStudentsClass().add(sc);
		
		Controller.getInstance().resetDataBase(b);
		Controller.getInstance().distribute();
		Bulk b2 = Controller.getInstance().getAllData();
		
		for (ClassTime ct : b2.getLstStudentsClass().get(0).getClassTime()){
			assertNotNull(ct.gRoom());
		}
	}

	@Test
	public void testAccesses() throws SQLException, DBException{
		Accesses old = Controller.getInstance().getAccesses();
		Controller.getInstance().newAccess();
		Accesses newA = Controller.getInstance().getAccesses();
		
		assertEquals(old.dayAccesses + 1, newA.dayAccesses);
		assertEquals(old.monthAccesses + 1, newA.monthAccesses);
		assertEquals(old.yearAccesses + 1, newA.yearAccesses);
	}
}
