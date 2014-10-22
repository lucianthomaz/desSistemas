package clek.logic;


public class Distributor {
	public static Distributor instance;
	
	private Distributor(){
		
	}
	
	public static synchronized Distributor getInstance(){
		if (instance != null){
			instance = new Distributor();
		}
		return instance;
	}
	
	private void reset(){
		
	}
}
