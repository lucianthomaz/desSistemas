import java.util.HashSet;


public class Course {
	private String code;
	private int credit;
	private String name;
	private int module;
	private HashSet<StudentsClass> classes;
	
	public Course(String code, int credit, String name, int module){
		this.code = code;
		this.credit = credit;
		this.name = name;
		this.module = module;
		this.classes = new HashSet<StudentsClass>();
	}
	
	public String getCode(){
		return this.code;
	}
	
	public int getCredit(){
		return this.credit;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getModule(){
		return this.module;
	}
	
	public boolean addClass(StudentsClass c){
		return this.classes.add(c);
	}
	
	public HashSet<StudentsClass> getClasses(){
		return this.classes;
	}
}
