package objets;

public class Bateau {
	int temps_minute;
	String name;
	
	
	public Bateau(int temps_minute, String name) {
		super();
		this.temps_minute = temps_minute;
		this.name = name;
	}
	public Bateau() {
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean equal(Bateau b) {
		return this.temps_minute == b.getTemps_minute() && this.name == b.name;
	}
	
	
	public int getTemps_minute() {
		return temps_minute;
	}
	public void setTemps_minute(int temps_minute) {
		this.temps_minute = temps_minute;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
