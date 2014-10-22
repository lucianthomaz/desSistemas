import java.util.HashSet;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "room")
public class Room {
	private int capacity;
	private String roomName;
	private HashSet<Class> classes;
	
	public Room (int capacity, String name){
		this.capacity = capacity;
		this.roomName = name;
		this.classes = new HashSet<Class>();
	}
	
	@XmlElement
	public String getRoomName(){
		return roomName;
	}
	
	public boolean addClass(Class c){
		return classes.add(c);
	}
	
	@XmlElement
	public int getCapacity(){
		return capacity;
	}
	
	
}
