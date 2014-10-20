import java.util.HashSet;


public class Discipline {
	private String code;
	private int credit;
	private String name;
	private int module;
	private HashSet<Class> classes;
	
	public Discipline(String code, int credit, String name, int module){
		this.code = code;
		this.credit = credit;
		this.name = name;
		this.module = module;
		this.classes = new HashSet<Class>();
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
	
	public boolean addClass(Class aClass){
		return classes.add(aClass);
	}
}
