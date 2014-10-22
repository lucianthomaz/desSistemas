
public class BD {
	public ArrayList<Room> listRooms;
	public ArrayList<Class> listClass;
	public ArrayList<Discipline> listDiscipline;
	
	private static BD instance;
	
	private BD(){
		listRooms = new ArrayList<Rooms>();
		listClass = new ArrayList<Class>();
		listDiscipline = new ArrayList<Discipline>();
	}
	
	public static synchronized BD getInstance(){
		if (instance == null){
			instance = new BD();
		}
		return instance;
	}
}
